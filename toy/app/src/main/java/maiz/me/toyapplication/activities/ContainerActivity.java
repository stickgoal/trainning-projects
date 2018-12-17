package maiz.me.toyapplication.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.common.SwitchToFragementEvent;
import maiz.me.toyapplication.common.HomeFragement;
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

    private SupportFragment[] mFragments = new SupportFragment[4];

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        CrawlConfigureFragment ccf = findFragment(CrawlConfigureFragment.class);
        //不存在则创建
        if(ccf==null) {
            mFragments[0]=CrawlConfigureFragment.newInstance();
            mFragments[1]=CrawlConfigListFragment.newInstance();
            mFragments[2]=CrawlResultListFragment.newInstance();
            loadMultipleRootFragment(R.id.frgment_contaienr,0
                    ,mFragments[0]
                    ,mFragments[1]
                    ,mFragments[2]
                    );
        }else{
            mFragments[0]=ccf;
            mFragments[1]=findFragment(CrawlConfigListFragment.class);
            mFragments[2]=findFragment(CrawlResultListFragment.class);
        }

        initViews();
        //注册eventbus
        EventBusActivityScope.getDefault(this).register(this);
    }

    private void initViews() {
        mBottomBar = (BottomBar) findViewById(R.id.bottombar);

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_home_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_discover_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_message_white_24dp));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                final SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof HomeFragement) {
                        currentFragment.popToChild(CrawlConfigureFragment.class, false);
                    } else if (currentFragment instanceof CrawlConfigListFragment) {
                        currentFragment.popToChild(CrawlConfigureFragment.class, false);
                    } else if (currentFragment instanceof CrawlResultListFragment) {
                        currentFragment.popToChild(CrawlConfigListFragment.class, false);
                    }
                    return;
                }


//                // 这里推荐使用EventBus来实现 -> 解耦
//                if (count == 1) {
//                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
//                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
//                    EventBusActivityScope.getDefault(ContainerActivity.this).post(new TabSelectedEvent(position));
//                }
            }
        });

    }

    //对于Activity的返回键的支持
    @Override
    public void onBackPressedSupport() {
        Log.i("#onBackPressedSupport",">>>>>>>返回事件被触发");

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }

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