package mx.tec.proyectofinal.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mx.tec.proyectofinal.R;

public class UserDashboardActivity extends AppCompatActivity {

    private String email;
    private TextView helloTextView;
    private Button toJobsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");

        this.initVars(email);
        this.manageBtn();


    }

    public void initVars(String email)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fullNameReference = database.getReference();
        fullNameReference.child("JobsApp").child("User").child(email).child("FullName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                helloTextView = findViewById(R.id.customHelloTextView);
                helloTextView.setText("Hello, " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        this.toJobsBtn = findViewById(R.id.toJobsBtn);
    }

    public void manageBtn()
    {
        this.toJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboardActivity.this, ListJobsActivity.class);
                startActivity(intent);
            }
        });
    }
}
