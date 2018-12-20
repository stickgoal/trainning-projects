package maiz.me.toyapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.fragments.adapters.ResultListAdapter;
import maiz.me.toyapplication.integration.CommonCallBack;
import maiz.me.toyapplication.integration.RetrofitHelper;
import maiz.me.toyapplication.integration.api.CrawlService;
import maiz.me.toyapplication.integration.api.dto.News;
import maiz.me.toyapplication.integration.api.dto.Result;
import retrofit2.Call;
import retrofit2.Response;

public class CrawlResultListFragment extends FragmentBase {

    public static CrawlResultListFragment newInstance() {

        Bundle args = new Bundle();

        CrawlResultListFragment fragment = new CrawlResultListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crawl_result_list, container, false);
/*
        RecyclerView rv = view.findViewById(R.id.resultList);
        rv.setLayoutManager(new LinearLayoutManager(_mActivity));

        rv.setAdapter(configAdapter(rv));*/

        return view;
    }

    private RecyclerView.Adapter configAdapter(RecyclerView rv) {
        ResultListAdapter adt = new ResultListAdapter(R.layout.rv_item_result);
        CrawlService crawlService = new RetrofitHelper().getService(CrawlService.class);
        fetchData(adt, crawlService,0);

        adt.setEnableLoadMore(true);
        adt.setOnLoadMoreListener(() -> {
            int count = adt.getItemCount();
            int page = count/10;
            fetchData(adt,crawlService,page);
            adt.loadMoreComplete();

        }, rv);
        return adt;
    }

    private void fetchData(ResultListAdapter adt, CrawlService crawlService,int pageIdx) {
        Call<Result<List<News>>> newsResult = crawlService.getNewsResult(1, pageIdx, 10);
        newsResult.enqueue(new CommonCallBack<Result<List<News>>>() {
            @Override
            public void onResponse(Call<Result<List<News>>> call, Response<Result<List<News>>> response) {
                if(response.isSuccessful()&&response.body().isSuccess()){
                    adt.addData(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<Result<List<News>>> call, Throwable t) {
                super.onFailure(call, t);
                adt.loadMoreFail();
            }
        });
    }
}
