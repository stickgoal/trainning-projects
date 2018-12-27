package maiz.me.toyapplication.demo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import maiz.me.toyapplication.R;

/**
 * adapter为视图提供数据
 */
public class RecyclerViewDemoAdapter extends RecyclerView.Adapter<RecyclerViewDemoAdapter.VH> {

    //外部上下文的引用
    private Context context;
    //数据
    private List<String> data;

    //手动构造器，用于添加数据
    public RecyclerViewDemoAdapter(Context context, List<String> data){
        this.data=data;
        this.context=context;
    }

    //视图持有者，静态内部类，在adapter内部持有itemLayout，避免重复加载itemLayout,提升性能
    public static class VH extends RecyclerView.ViewHolder{
        private TextView textView;
        public VH(View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.configListItem);
        }
    }

    //回调方法，当创建VH的时候调用该方法，用于获取单条数据的layout
      @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("rv","创建ViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_recyclerview_demo,parent,false);
        return new VH(v);//使用viewHolder将数据包装
    }

    //回调方法，当绑定VH的时候调用该方法，用于绑定数据
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Log.i("rv","绑定ViewHolder");
        holder.textView.setText(data.get(position));
        holder.textView.setOnClickListener(v -> Toast.makeText(context,((TextView)v).getText(),Toast.LENGTH_LONG).show());
    }

    //得到数据量
    @Override
    public int getItemCount() {
        return data.size();
    }
}

