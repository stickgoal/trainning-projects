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

    //静态内部类视图持有者，解耦外部和内部视图
    public static class VH extends RecyclerView.ViewHolder{

        private TextView textView;


        public VH(View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.configListItem);
        }
    }
    //外部上下文的引用
    private Context context;
    //数据
    private List<String> data;

    //手动构造器，用于添加数据
    public RecyclerViewDemoAdapter(Context context, List<String> data){
        this.data=data;
        this.context=context;
    }

    //回调方法，当创建VH的时候调用该方法，用于获取layout

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("===>","添加条目");
        //得到每个条目的视图
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_recyclerview_demo,parent,false);
        return new VH(v);
    }

    //回调方法，当绑定VH的时候调用该方法，用于绑定数据
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.textView.setText(data.get(position));
        Log.i("===>","绑定条目："+data.get(position));

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,((TextView)v).getText(),Toast.LENGTH_LONG).show();
            }
        });
    }

    //得到数据量
    @Override
    public int getItemCount() {
        return data.size();
    }
}

