package mx.tec.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class enterpDashboard extends AppCompatActivity {

    private static final int ENTER_DASHBOARD_CODE =0;

    private TextView helloMessage;
    private String emailAddress;
    private Integer countJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterp_dashboard);
        Intent intent = getIntent();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbReference = database.getReference();
        emailAddress= intent.getStringExtra("email");
        helloMessage=findViewById(R.id.textView10);

        String name = intent.getStringExtra("fullName");
        if (name == null){
            dbReference.child("JobsApp").child("Enterprise").child(emailAddress).child("EnterpriseName")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        helloMessage=findViewById(R.id.textView10);
                        helloMessage.setText("Hello, " + value);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
        }else{
            String text = ("Hello " + name + "!");
            helloMessage.setText(text);
        }

    }

    public void jobCreator(View v){
        Intent intent = new Intent(enterpDashboard.this, createJob.class);
        intent.putExtra("email", emailAddress);
        startActivityForResult(intent, ENTER_DASHBOARD_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode== ENTER_DASHBOARD_CODE) {
            if (resultCode == Activity.RESULT_OK)
                Toast.makeText(this, "Job Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

}
