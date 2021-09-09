package com.example.samachar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {
   public Context context;
   private ArrayList<categoryModal>categoryModals;
   private categoryOnClickInterface categonClickInterface;

    public categoryAdapter(Context context, ArrayList<categoryModal> categoryModals, categoryOnClickInterface categonClickInterface) {
        this.context = context;
        this.categoryModals = categoryModals;
        this.categonClickInterface = categonClickInterface;
    }

    @NonNull
    @Override
    public categoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        return new categoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.ViewHolder holder, int position) {
      categoryModal categorymodal = categoryModals.get(position);
      holder.categoryTextView.setText(categorymodal.getCategory());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              categonClickInterface.oncategoryclick(holder.getAdapterPosition());
          }
      });
    }

    @Override
    public int getItemCount() {
        return categoryModals.size();
    }
    public interface categoryOnClickInterface {
        void oncategoryclick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.idcategorysname);
        }
    }

}
