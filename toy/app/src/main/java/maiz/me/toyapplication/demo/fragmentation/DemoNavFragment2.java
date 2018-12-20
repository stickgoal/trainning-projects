package maiz.me.toyapplication.demo.fragmentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import maiz.me.toyapplication.R;
import me.yokeyword.fragmentation.SupportFragment;


public class DemoNavFragment2 extends SupportFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("navFragement2","===>inflate  Nav Fragment");
        return inflater.inflate(R.layout.fragment_demo_nav, container, false);
    }

}
