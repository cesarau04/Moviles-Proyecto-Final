package mx.tec.proyectofinal.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.tec.proyectofinal.R;
import mx.tec.proyectofinal.beans.Job;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder>{

    Context context;
    ArrayList<Job> myJob;

    public JobAdapter(Context c, ArrayList<Job> p) {
        context = c;
        myJob = p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_jobs,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.titlejob.setText(myJob.get(i).getTitleJob());
        myViewHolder.descjob.setText(myJob.get(i).getDescJob());
        //myViewHolder.findout.setText(myJob.get(i).getFindout());
    }

    @Override
    public int getItemCount() {
        return myJob.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titlejob, descjob, findout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titlejob = (TextView) itemView.findViewById(R.id.titleJob);
            descjob = (TextView) itemView.findViewById(R.id.descJob);
            findout = (TextView) itemView.findViewById(R.id.findout);
        }
    }

}
