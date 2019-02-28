package mx.tec.proyectofinal;

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

public class createJob extends AppCompatActivity {

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
        dbReference.child("JobsApp")
                .child("Enterprise")
                .child(intentPrevious.getStringExtra("email"))
                .child("Jobs")
                .child("Job-" + countJobs.toString())
                .child("title")
                .setValue(jobTitle.getText().toString());
        dbReference.child("JobsApp")
                .child("Enterprise")
                .child(intentPrevious.getStringExtra("email"))
                .child("Jobs")
                .child("Job-" + countJobs.toString())
                .child("description").setValue(jobDescription.getText().toString());
        dbReference.child("JobsApp")
                .child("Enterprise")
                .child(intentPrevious.getStringExtra("email"))
                .child("CountJobs").setValue(countJobs);
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
