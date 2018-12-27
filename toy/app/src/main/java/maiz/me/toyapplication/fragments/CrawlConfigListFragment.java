package maiz.me.toyapplication.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.activities.ContainerActivity;
import maiz.me.toyapplication.common.HomeFragment;
import maiz.me.toyapplication.fragments.adapters.ConfigListAdapter;
import maiz.me.toyapplication.integration.RetrofitHelper;
import maiz.me.toyapplication.integration.api.ConfigService;
import maiz.me.toyapplication.integration.api.CrawlService;
import maiz.me.toyapplication.integration.api.dto.CrawlConfig;
import maiz.me.toyapplication.integration.api.dto.PageResult;
import maiz.me.toyapplication.integration.api.dto.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrawlConfigListFragment extends FragmentBase implements HomeFragment {

    public static CrawlConfigListFragment newInstance() {

        Bundle args = new Bundle();

        CrawlConfigListFragment fragment = new CrawlConfigListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crawl_config_list, container, false);

        RecyclerView rv = v.findViewById(R.id.configList);

        ConfigListAdapter adapter = configAdapter(rv);

        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(_mActivity));

        return v;
    }

    @NonNull
    private ConfigListAdapter configAdapter(RecyclerView rv) {

        List<CrawlConfig> data = new ArrayList<>();


        ConfigListAdapter adapter = new ConfigListAdapter(R.layout.rv_item_config, data);

        adapter.setOnItemClickListener((baseQuickAdapter, view, position) -> {
            Toast.makeText(_mActivity, baseQuickAdapter.getItem(position) + "被点击", Toast.LENGTH_LONG).show();
        });

        adapter.setOnItemChildClickListener((ad, view, i) -> {
            CrawlConfig item = (CrawlConfig) ad.getItem(i);
            CrawlService crawlService = new RetrofitHelper().getService(CrawlService.class);
            Call<Result> crawlCall = crawlService.crawl(item.getConfigId());
            crawlCall.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {

                    if (response.isSuccessful()) {
                        if (response.body().isSuccess()) {
                            Toast.makeText(_mActivity, item.getConfigName() + "被触发,执行爬取，请稍后在结果页查看结果", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(_mActivity, "触发" + item.getConfigName() + "失败，请稍候再试", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(_mActivity, "网络出错，请检查您的网络设置", Toast.LENGTH_LONG).show();
                }
            });
        });

        ConfigService service = new RetrofitHelper().getService(ConfigService.class);
        fetchData(adapter, service, 0);

        //允许加载更多
        adapter.setEnableLoadMore(true);
        //加载更多
        adapter.setOnLoadMoreListener(() -> rv.postDelayed(() -> {
            int count = adapter.getItemCount();
            Log.i(TAG, "configAdapter: count" + count);
            int page = count / 5;
            fetchData(adapter, service, page);
        }, 100), rv);



        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);


        return adapter;
    }

    private void fetchData(ConfigListAdapter adapter, ConfigService service, int page) {
        Log.i(TAG, "发起请求 page:" + page);

        SharedPreferences sp = getActivity().getSharedPreferences("info",Context.MODE_PRIVATE);
        int userId = sp.getInt("userId",-1);
        Call<PageResult<CrawlConfig>> resultCall2 = service.queryConfig(userId, page, 5);
        resultCall2.enqueue(new Callback<PageResult<CrawlConfig>>() {

            @Override
            public void onResponse(Call<PageResult<CrawlConfig>> call, Response<PageResult<CrawlConfig>> response) {
                Log.i(TAG, response.raw().toString());
                if (response.isSuccessful()) {
                    PageResult<CrawlConfig> r = response.body();
                    if (r.isSuccess()) {
                        Log.i(TAG, "onResponse: "+r);
                        if (r.getTotalPages() <= page) {
                            adapter.loadMoreEnd();
                        } else {
                            List<CrawlConfig> data = r.getContent();
                            adapter.addData(data);
                            adapter.loadMoreComplete();
                        }
                    } else {
                        adapter.loadMoreFail();
                    }
                }
            }

            @Override
            public void onFailure(Call<PageResult<CrawlConfig>> call, Throwable t) {
                adapter.loadMoreFail();
            }
        });
    }
}
