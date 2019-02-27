package mx.tec.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class createJob extends AppCompatActivity {

    private EditText jobTitle, jobDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        jobTitle=findViewById(R.id.jobTitle);
        jobDescription=findViewById(R.id.jobDescription);
    }

    public void addJob(View v){
        /*
        añadir a base de datos usando
        jobTitle.getText().toString();
        jobDescription.getText().toString();
         */

        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
