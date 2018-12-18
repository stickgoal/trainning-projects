package maiz.me.toyapplication.fragments.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.integration.api.dto.News;

public class ResultListAdapter extends BaseQuickAdapter<News,BaseViewHolder> {
    public ResultListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        helper.setText(R.id.resultTitle,item.getTitle());
        helper.setText(R.id.resultIntro,item.getIntro());
    }
}
