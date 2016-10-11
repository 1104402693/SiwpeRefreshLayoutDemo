package com.gh.siwperefreshlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 95224 on 2016/9/12.
 */
public class MyAdatpter extends RecyclerView.Adapter<MyAdatpter.ViewHolder> {
    List<String> mdatas;
    private LayoutInflater minflater;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyAdatpter(List<String> datas) {
        this.mdatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        minflater = LayoutInflater.from(parent.getContext());
        View view = minflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mdatas.get(position));

    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public void addData(int position, String city) {
        mdatas.add(position, city);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mdatas.remove(position);
        notifyItemRemoved(position);//局部刷新
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.id_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(v, getLayoutPosition(), mdatas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    interface OnItemClickListener {
        void onClick(View v, int position, String city);
    }
}
