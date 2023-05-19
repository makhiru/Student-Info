package com.example.studentinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Student_adapter extends RecyclerView.Adapter<Student_adapter.ViewHolder> {

    Context context;
    ArrayList<student> arrstudent;
    click_lisner onclick;

    Student_adapter(Context context, ArrayList<student> arrstudent,click_lisner onclick) {
        this.context = context;
        this.arrstudent = arrstudent;
       this.onclick = onclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.student_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Student_adapter.ViewHolder holder, int position) {

        holder.imgstudent.setImageResource(R.drawable.student);
        holder.txtname.setText(arrstudent.get(position).name);
        holder.txtfname.setText(arrstudent.get(position).fname);
        holder.txtrollno.setText(arrstudent.get(position).rollno);
        holder.txtstandard.setText(arrstudent.get(position).standard);

      holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
          @SuppressLint("NotifyDataSetChanged")
          @Override

          public boolean onLongClick(View v) {

              onclick.onlongclick(arrstudent.get(holder.getAdapterPosition()));
              return true;
          }
      });

      holder.cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              onclick.onclick(arrstudent.get(holder.getAdapterPosition()));
          }
      });
    }

    @Override
    public int getItemCount() {
        return arrstudent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgstudent;
        TextView txtname, txtfname, txtrollno, txtstandard;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgstudent = itemView.findViewById(R.id.imgstudent);
            txtname = itemView.findViewById(R.id.txtname);
            txtfname = itemView.findViewById(R.id.txtfname);
            txtrollno = itemView.findViewById(R.id.txtrollno);
            txtstandard = itemView.findViewById(R.id.txtstandard);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
