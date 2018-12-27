package maiz.me.toyapplication.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.common.SwitchToFragementEvent;
import maiz.me.toyapplication.common.HomeFragment;
import maiz.me.toyapplication.fragments.CrawlConfigListFragment;
import maiz.me.toyapplication.fragments.CrawlConfigureFragment;
import maiz.me.toyapplication.fragments.CrawlResultListFragment;
import maiz.me.toyapplication.view.BottomBar;
import maiz.me.toyapplication.view.BottomBarTab;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class ContainerActivity extends SupportActivity {

    public static final String T = "ContainerActivity";
    // 持有多个Fragment的数组
    private SupportFragment[] mFragments = new SupportFragment[3];
    //持有底部导航bar
    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        //预先查找
        CrawlConfigListFragment clf = findFragment(CrawlConfigListFragment.class);
        //不存在则创建
        if(clf==null) {
            mFragments[0]=CrawlConfigListFragment.newInstance();
            mFragments[1]=CrawlConfigureFragment.newInstance();
            mFragments[2]=CrawlResultListFragment.newInstance();
            loadMultipleRootFragment(R.id.frgment_contaienr,0
                    ,mFragments[0]
                    ,mFragments[1]
                    ,mFragments[2]
                    );
        }else{
            mFragments[0]=clf;
            mFragments[1]=findFragment(CrawlConfigureFragment.class);
            mFragments[2]=findFragment(CrawlResultListFragment.class);
        }

        initViews();
        //注册eventbus
        EventBusActivityScope.getDefault(this).register(this);
    }

    private void initViews() {
        mBottomBar = (BottomBar) findViewById(R.id.bottombar);

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.configlist))
                .addItem(new BottomBarTab(this, R.drawable.config))
                .addItem(new BottomBarTab(this, R.drawable.ic_message_white_24dp));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override//当被选中
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override//当失去焦点
            public void onTabUnselected(int position) {}

            @Override//当再次选中（再次点击已选中的tab）
            public void onTabReselected(int position) {
                mBottomBar.setCurrentItem(position);
            }
        });

    }

    @Subscribe
    public void onBackToHomeFragement(SwitchToFragementEvent e){
        Log.i(T,"跳转事件被触发=>"+e.getIndex());
        mBottomBar.setCurrentItem(e.getIndex());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusActivityScope.getDefault(this).unregister(this);
    }
}