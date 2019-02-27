package mx.tec.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class preDashboard extends AppCompatActivity {

    private String email;
    private TextView helloTextView;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_dashboard);

        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");

        this.name = email.split("@")[0];

        this.helloTextView = findViewById(R.id.customHelloTextView);
        this.helloTextView.setText("Hello, " + this.name);

    }
}
