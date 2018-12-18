package maiz.me.toyapplication.fragments.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.integration.api.dto.CrawlConfig;

public class ConfigListAdapter extends BaseQuickAdapter<CrawlConfig,BaseViewHolder> {

    public ConfigListAdapter(int layoutResId, @Nullable List<CrawlConfig> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CrawlConfig item) {
        helper.setText(R.id.configListItem,item.getConfigName());
        helper.addOnClickListener(R.id.startCrawling);
    }
}



