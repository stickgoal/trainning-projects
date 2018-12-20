package maiz.me.toyapplication.demo.fragmentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;

import maiz.me.toyapplication.R;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class FragmentationDemoActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_demo_activity);
        //加载一个fragment
        loadRootFragment(R.id.baseLayout,new DemoDetailFragment2());
        Display display = getWindowManager().getDefaultDisplay();
        Log.i("demo","==============>纵向");
        if (display.getWidth() > display.getHeight()) {
            //替换为另一个fragment
            Log.i("demo","==============>转为横向");
              replaceFragment(new DemoNavFragment2(),false);
        }
    }
}
