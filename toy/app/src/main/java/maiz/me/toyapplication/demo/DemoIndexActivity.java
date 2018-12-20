package maiz.me.toyapplication.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import maiz.me.toyapplication.R;
import maiz.me.toyapplication.demo.fragmentation.FragmentationDemoActivity;

public class DemoIndexActivity extends AppCompatActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_index_activity);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.toFragmentDemo)
    public void toFragmentDemo(){
        startActivity(new Intent(this,FragmentDemoActivity.class));
    }

    @OnClick(R.id.toFragmentationDemo)
    public void toFragmentationDemo(){
        startActivity(new Intent(this,FragmentationDemoActivity.class));
    }


    @OnClick(R.id.toRecyclerViewDemo)
    public void toRVDemo(){
        startActivity(new Intent(this,RecylerViewDemoActivity.class));
    }


}
