package maiz.me.toyapplication.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import maiz.me.toyapplication.R;
import maiz.me.toyapplication.activities.ContainerActivity;
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

    @OnClick(R.id.toBRVAHDemo)
    public void toBRVAHDemo(){
        startActivity(new Intent(this,BRVAHDemoActivity.class));
    }

    @OnClick(R.id.toHome)
    public void toHome(){
        startActivity(new Intent(this,ContainerActivity.class));
    }


}
