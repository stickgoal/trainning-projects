package maiz.me.toyapplication.fragments;

import maiz.me.toyapplication.common.SwitchToFragementEvent;
import maiz.me.toyapplication.common.HomeFragment;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;

public class FragmentBase extends SupportFragment {
    protected String TAG = getClass().getName();

    //对fragment的返回键支持
    @Override
    public boolean onBackPressedSupport() {

        if(getChildFragmentManager().getBackStackEntryCount()>1){
            popChild();
        }else{
            if(this instanceof HomeFragment){
                _mActivity.finish();
            }else{
                EventBusActivityScope.getDefault(_mActivity).post(new SwitchToFragementEvent(0));
            }
        }

        return false;
    }

}
