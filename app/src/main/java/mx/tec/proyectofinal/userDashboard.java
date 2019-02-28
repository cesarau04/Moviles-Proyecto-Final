package mx.tec.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userDashboard extends AppCompatActivity {

    private String email;
    private TextView helloTextView;
    private String name;
    private Button toJobsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");

        this.initVars();
        this.manageBtn();
    }

    public void initVars()
    {
        this.name = email.split("@")[0];

        this.helloTextView = findViewById(R.id.customHelloTextView);
        this.helloTextView.setText("Hello, " + this.name);

        this.toJobsBtn = findViewById(R.id.toJobsBtn);
    }

    public void manageBtn()
    {
        this.toJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userDashboard.this, JobsActivity.class);
                startActivity(intent);
            }
        });
    }
}
