package mx.tec.proyectofinal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.MyViewHolder>{

    Context context;
    ArrayList<Jobs> myJob;

    public JobsAdapter(Context c, ArrayList<Jobs> p) {
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
        /*
        final String getTitleJob = myJob.get(i).getTitleJob();
        final String getDescJob = myJob.get(i).getDescJob();
        final String getFindout = myJob.get(i).getFindout();
        //final String getKeyDoes = myJob.get(i).getKeydoes();


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context, EditTaskDesk.class);
                aa.putExtra("titlejob", getTitleJob);
                aa.putExtra("descjob", getDescJob);
                aa.putExtra("findout", getFindout);
                //aa.putExtra("keydoes", getKeyDoes);
                context.startActivity(aa);
            }
        });
        */
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
