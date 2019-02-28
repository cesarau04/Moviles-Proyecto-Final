package mx.tec.proyectofinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.editText_emailLogIn);
        password = findViewById(R.id.editText_passwordLogIn);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();

        // here we can check against mAuth.getCurrentUser() if different from null
        if (intent.getStringExtra("email") != null && intent.getStringExtra("password") != null){
            //we came from sign in activity
            Toast.makeText(LogIn.this, "SIGN IN login", Toast.LENGTH_SHORT).show();
            logIn(intent.getStringExtra("email"), intent.getStringExtra("password"));
        }else {
            //normal login
            Toast.makeText(LogIn.this, "Normal login", Toast.LENGTH_SHORT).show();
        }

    }

    public void clickLogIn(View v){
        logIn(email.getText().toString(), password.getText().toString());
    }

    private void logIn(final String email, String password) {
        if (!validateForm(email, password)){
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LogIn.this, "Succesfull login", Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //here move to another intent
                            Intent intent = new Intent(LogIn.this, userDashboard.class);
                            //put some extras like the user? Or save that in preferences
                            intent.putExtra("email", email);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LogIn.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                        }

                        if (!task.isSuccessful()){
                            Toast.makeText(LogIn.this, "Failed to logIn", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean validateForm(String email, String password){
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(LogIn.this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidEmail(email)){
            Toast.makeText(LogIn.this, "No valid email format", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
