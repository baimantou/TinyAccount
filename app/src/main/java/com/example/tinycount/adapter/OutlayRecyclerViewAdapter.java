package com.example.tinycount.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinycount.AccountApplication;
import com.example.tinycount.R;
import com.example.tinycount.db.AccountDao;
import com.example.tinycount.entity.AccountItem;

import java.util.List;

public   class OutlayRecyclerViewAdapter extends RecyclerView.Adapter<OutlayRecyclerViewAdapter.OutlayItemViewHolder> {
    //适配器的配置工作

    private List<AccountItem> mItems;
    private final LayoutInflater mLayoutInflater;
    private AlertDialog.Builder mDialogBuilder;

    public OutlayRecyclerViewAdapter(Activity context, List<AccountItem> mItems) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mItems = mItems;
        mDialogBuilder = new AlertDialog.Builder(context);
    }


    @NonNull
    @Override
    public OutlayItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OutlayItemViewHolder h=new OutlayItemViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_item,parent,false));

        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull OutlayItemViewHolder holder, int position) {
        //视图上显示数据
        AccountItem item=this.mItems.get(position);
        holder.tvCategory.setText(item.getCategory());
        holder.tvRemark.setText(item.getRemark());
        holder.tvMoney.setText(String.valueOf(item.getMoney()));
        holder.tvDate.setText(item.getDate());
        int icon=R.drawable.book_icon;
        if(icon>0)
            holder.imageView.setImageResource(icon);
        holder.imageViewDelete.setTag(item.getId()); //根据ID属性进行删除
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = (int) view.getTag();
                mDialogBuilder.setTitle("提示");
                mDialogBuilder.setMessage("确认要删除选择的数据嘛？");
                mDialogBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                   //     AccountDao dbManager = new AccountDao(OutlayRecyclerViewAdapter.this.getClass());
                        deleteRecord(id);

                    }
                });
                mDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();//对话框消失
                    }
                });
                mDialogBuilder.show();
            }
        });
    }

    //删除列表
    private void deleteRecord(int id){
        for(int i=mItems.size()-1;i>=0;i--){
            if(mItems.get(i).getId() == id){

                mItems.remove(id);
            }
        }
        this.notifyDataSetChanged();//调用方法 刷新列表
    }

    @Override
    public int getItemCount() {
        return mItems ==null?0:mItems.size();
    }

    public class OutlayItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvRemark;
        TextView tvMoney;
        TextView tvDate;
        ImageView imageView;
        ImageView imageViewDelete;
        public OutlayItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory= (TextView) itemView.findViewById(R.id.textViewCategory);
            tvRemark = (TextView)itemView.findViewById(R.id.textViewRemark);
            tvMoney = (TextView)itemView.findViewById(R.id.textViewMoney);
            tvDate = (TextView)itemView.findViewById(R.id.textViewDate);
            imageView = (ImageView)itemView.findViewById(R.id.imageViewIcon);
            imageViewDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
