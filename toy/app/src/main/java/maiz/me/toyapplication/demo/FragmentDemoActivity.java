package maiz.me.toyapplication.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;

import maiz.me.toyapplication.R;

public class FragmentDemoActivity extends FragmentActivity  {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_demo_activity);
        Log.i("demo","demo");

        Display display = getWindowManager().getDefaultDisplay();
        if (display.getWidth() > display.getHeight()) {
            Log.i("demo","==============>横");
            DemoDetailFragment fragment1 = new DemoDetailFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.baseLayout, fragment1).commit();
        } else {
            Log.i("demo","==============>纵");

            DemoNavFragment fragment2 = new DemoNavFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.baseLayout, fragment2).commit();
        }

    }

}
