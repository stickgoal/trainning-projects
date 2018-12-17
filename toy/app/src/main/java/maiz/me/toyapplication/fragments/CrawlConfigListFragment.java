package maiz.me.toyapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.common.HomeFragement;
import me.yokeyword.fragmentation.SupportFragment;

public class CrawlConfigListFragment extends FragementBase  implements HomeFragement {

    public static CrawlConfigListFragment newInstance() {

        Bundle args = new Bundle();

        CrawlConfigListFragment fragment = new CrawlConfigListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crawl_config_list,container,false);
    }
}
