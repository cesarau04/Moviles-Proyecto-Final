package mx.tec.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class enterpDashboard extends AppCompatActivity {

    private static final int ENTER_DASHBOAR_CODE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterp_dashboard);

    }

    public void jobCreator(View v){
        Intent intent = new Intent(enterpDashboard.this, createJob.class);
        startActivityForResult(intent, ENTER_DASHBOAR_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode==ENTER_DASHBOAR_CODE) {
            if (resultCode == Activity.RESULT_OK)
                Toast.makeText(this, "Job Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
