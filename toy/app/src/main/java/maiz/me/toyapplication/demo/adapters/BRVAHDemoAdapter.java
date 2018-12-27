package maiz.me.toyapplication.demo.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import maiz.me.toyapplication.R;

/**
 * adapter为视图提供数据
 */
public class BRVAHDemoAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    //构造器，指定条目布局的ID和数据
    public BRVAHDemoAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    //填充具体数据的方法
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.configListItem,item);
    }
}

