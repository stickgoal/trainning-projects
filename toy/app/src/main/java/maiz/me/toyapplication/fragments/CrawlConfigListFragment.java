package maiz.me.toyapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.common.HomeFragment;
import maiz.me.toyapplication.fragments.adapters.ConfigListAdapter;
import maiz.me.toyapplication.integration.RetrofitHelper;
import maiz.me.toyapplication.integration.api.ConfigService;
import maiz.me.toyapplication.integration.api.CrawlService;
import maiz.me.toyapplication.integration.api.dto.CrawlConfig;
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

        View v = inflater.inflate(R.layout.fragment_crawl_config_list,container,false);
/*

        RecyclerView rv = v.findViewById(R.id.configList);

        ConfigListAdapter adapter = configRecyclerViewAdapter(rv);


        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(_mActivity));
*/

        return v;
    }

    @NonNull
    private ConfigListAdapter configRecyclerViewAdapter(RecyclerView rv) {

        List<CrawlConfig> data = new ArrayList<>();


        ConfigListAdapter adapter = new ConfigListAdapter(R.layout.rv_item_config, data);

        adapter.setOnItemClickListener((baseQuickAdapter,view,position)->{
            Toast.makeText(_mActivity,baseQuickAdapter.getItem(position)+"被点击",Toast.LENGTH_LONG).show();
        });

        adapter.setOnItemChildClickListener((ad,view,i)->{
            CrawlConfig item = (CrawlConfig) ad.getItem(i);

            CrawlService crawlService = new RetrofitHelper().getService(CrawlService.class);
            Call<Result> crawlCall = crawlService.crawl(item.getConfigId());
            crawlCall.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {

                    if(response.isSuccessful()){
                        if(response.body().isSuccess()){
                            Toast.makeText(_mActivity,item.getConfigName()+"被触发,执行爬取，请稍后在结果页查看结果",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(_mActivity, "触发"+item.getConfigName()+"失败，请稍候再试", Toast.LENGTH_LONG).show();
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
        adapter.setOnLoadMoreListener(()-> rv.postDelayed(()->{
          int count = adapter.getItemCount();
          int page = count/5;
            fetchData(adapter, service, page);
        },100),rv);

        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        //添加空布局,默认出现了头部就不会显示Empty，和尾部，配置以下方法也支持同时显示
        adapter.setEmptyView(new SeekBar(_mActivity));

        return adapter;
    }

    private void fetchData(ConfigListAdapter adapter, ConfigService service, int page) {
        Log.i(TAG,"发起请求 page:"+page);
        Call<Result<List<CrawlConfig>>> resultCall2 = service.queryConfig(1, page, 5);
        resultCall2.enqueue(new Callback<Result<List<CrawlConfig>>>() {

            @Override
            public void onResponse(Call<Result<List<CrawlConfig>>> call, Response<Result<List<CrawlConfig>>> response) {
                Log.i(TAG,response.raw().toString());
                if(response.isSuccessful()){
                    Result<List<CrawlConfig>> r = response.body();
                    if(r.isSuccess()){
                        List<CrawlConfig> data =  r.getData();
                        adapter.addData(data);
                        adapter.loadMoreComplete();
                    }else{
                        adapter.loadMoreFail();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<List<CrawlConfig>>> call, Throwable t) {
                 adapter.loadMoreFail();
            }
        });
    }
}
