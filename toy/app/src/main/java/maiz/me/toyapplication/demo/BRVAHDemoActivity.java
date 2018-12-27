package maiz.me.toyapplication.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.demo.adapters.BRVAHDemoAdapter;
import maiz.me.toyapplication.demo.adapters.RecyclerViewDemoAdapter;

/**
 * 一个可以切换布局的recyclerview的demo
 * recycleview_demo页面布局
 * rv_item_recycleview_demo单个条目布局
 */
public class BRVAHDemoActivity extends AppCompatActivity {

    private int count=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //指定布局
        setContentView(R.layout.recyclerview_demo);
        //布局中加载recyclerview
        RecyclerView recyclerView = findViewById(R.id.rv);
        //指定默认布局管理器
        RecyclerView.LayoutManager ll = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(ll);

        //提供数据，并使用Adapter
        List<String> data = Arrays.asList("香蕉","苹果","梨子","苦瓜","腌萝卜","苹果醋");
        BRVAHDemoAdapter brvahDemoAdapter = new BRVAHDemoAdapter(R.layout.rv_item_recyclerview_demo, data);
        recyclerView.setAdapter(brvahDemoAdapter);

        //切换布局方向的按钮
        Button hb = findViewById(R.id.switchLayout);
        GridLayoutManager gl = new GridLayoutManager(this,2);
        hb.setOnClickListener(v->{
            recyclerView.setLayoutManager(count %2==0?gl:ll);
            count++;
        });

    }


}
