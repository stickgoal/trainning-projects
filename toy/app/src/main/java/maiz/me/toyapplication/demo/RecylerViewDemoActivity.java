package maiz.me.toyapplication.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.demo.adapters.RecyclerViewDemoAdapter;

/**
 * 一个可以切换布局的recyclerview的demo
 * recycleview_demo页面布局
 * rv_item_recycleview_demo单个条目布局
 */
public class RecylerViewDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_demo);

        RecyclerView recyclerView = findViewById(R.id.configList);
        LinearLayoutManager ll = new LinearLayoutManager(this);
        ll.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(ll);

        List<String> data = new ArrayList<>();
        data.add("香蕉");
        data.add("苹果");
        data.add("梨子");
        data.add("苦瓜");
        data.add("腌萝卜");
        data.add("苹果醋");
        recyclerView.setAdapter(new RecyclerViewDemoAdapter(this,data));

        Button hb = findViewById(R.id.horizontal);
        hb.setOnClickListener(v->{
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        });

        Button vb = findViewById(R.id.vertical);
        vb.setOnClickListener(v->{
            recyclerView.setLayoutManager(ll);
        });
    }


}
