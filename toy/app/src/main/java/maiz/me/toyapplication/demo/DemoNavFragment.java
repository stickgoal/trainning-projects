package maiz.me.toyapplication.demo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import maiz.me.toyapplication.R;


public class DemoNavFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("navFragement","===>inflate  Nav Fragment");
        return inflater.inflate(R.layout.fragment_demo_nav, container, false);
    }

}
