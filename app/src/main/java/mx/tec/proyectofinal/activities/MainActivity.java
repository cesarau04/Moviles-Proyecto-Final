package mx.tec.proyectofinal.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mx.tec.proyectofinal.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickLogIn(View v){
        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
        startActivity(intent);
    }

    public void clickSignUp(View v){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
