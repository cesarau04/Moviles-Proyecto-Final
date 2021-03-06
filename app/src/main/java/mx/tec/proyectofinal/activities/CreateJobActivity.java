package mx.tec.proyectofinal.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import mx.tec.proyectofinal.beans.Job;
import mx.tec.proyectofinal.R;

public class CreateJobActivity extends AppCompatActivity {

    private EditText jobTitle, jobDescription;
    private Integer countJobs;
    private Intent intentPrevious;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        jobTitle=findViewById(R.id.jobTitle);
        jobDescription=findViewById(R.id.jobDescription);

        intentPrevious = getIntent();
        String email = intentPrevious.getStringExtra("email");
        dbReference.child("JobsApp").child("Enterprise").child(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.child("CountJobs").exists()){
                            countJobs = 0;
                        }else{
                            countJobs = dataSnapshot.child("CountJobs").getValue(Integer.class);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    public void addJob(View v){
        countJobs++;

        Job job = new Job();
        job.setTitleJob(jobTitle.getText().toString());
        job.setDescJob(jobDescription.getText().toString());
        job.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        dbReference.child("JobsApp")
                .child("Enterprise")
                .child(intentPrevious.getStringExtra("email"))
                .child("Job")
                .child("Job-" + countJobs.toString())
                .setValue(job);
        dbReference.child("JobsApp")
                .child("Enterprise")
                .child(intentPrevious.getStringExtra("email"))
                .child("CountJobs")
                .setValue(countJobs);
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
