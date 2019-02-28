package mx.tec.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class enterpDashboard extends AppCompatActivity {

    private static final int ENTER_DASHBOARD_CODE =0;

    private TextView helloMessage;

    private String emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterp_dashboard);
        Intent intent = getIntent();
        /*Toast.makeText(enterpDashboard.this,
                intent.getStringExtra("fullName") + intent.getStringExtra("email"),
                Toast.LENGTH_SHORT).show();*/

        emailAddress= intent.getStringExtra("email");

        helloMessage=findViewById(R.id.textView10);

        String text = ("Hello " + intent.getStringExtra("fullName")+ "!");

        helloMessage.setText(text);
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
