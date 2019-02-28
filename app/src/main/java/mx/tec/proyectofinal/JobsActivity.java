package mx.tec.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobsActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, endpage;

    DatabaseReference reference;
    RecyclerView ourJobs;
    ArrayList<Jobs> list;
    JobsAdapter jobsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        endpage = findViewById(R.id.endpage);

        // working with data
        ourJobs = findViewById(R.id.ourJobs);
        ourJobs.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        // get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("JobsApp").child("Enterprise");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // set code to retrive data and replace layout
                Integer noJobs;
                Jobs jobToAdd;
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    // Each enterprise is here
                    noJobs = dataSnapshot1.child("CountJobs").getValue(Integer.class);
                    for (int i = 1; i <= noJobs; i++){
                        jobToAdd = new Jobs();
                        jobToAdd.setTitleJob(dataSnapshot1.child("Jobs").child("Job-"+ Integer.toString(i)).child("titleJob").getValue().toString());
                        jobToAdd.setDescJob(dataSnapshot1.child("Jobs").child("Job-"+Integer.toString(i)).child("descJob").getValue().toString());
                        jobToAdd.setDate(dataSnapshot1.child("Jobs").child("Job-"+Integer.toString(i)).child("date").getValue().toString());
                        list.add(jobToAdd);
                    }
                }
                jobsAdapter = new JobsAdapter(JobsActivity.this, list);
                ourJobs.setAdapter(jobsAdapter);
                jobsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // set code to show an error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
