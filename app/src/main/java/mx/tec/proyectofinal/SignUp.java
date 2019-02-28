package mx.tec.proyectofinal;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, passwordEditText, passwordConfEditText;
    private TextView status;
    private CheckBox enterpriseCheck;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullNameEditText = findViewById(R.id.editText_FullNameSignUp);
        emailEditText = findViewById(R.id.editText_emailSignUp);
        passwordEditText = findViewById(R.id.editText_passwordSignUp);
        passwordConfEditText = findViewById(R.id.editText_passwordConfirmationSignUp);
        enterpriseCheck = findViewById(R.id.enterpriseCheckBox);

        status = findViewById(R.id.textView_Status);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickConfirm(View v){
        createAccount(fullNameEditText.getText().toString(), emailEditText.getText().toString(), passwordEditText.getText().toString(),
                        enterpriseCheck.isChecked());
    }

    public void createAccount(final String fullName, final String email, final String password, final boolean enterprise){
        Log.d("EmailPassword", "createAccount:" + email);
        if (!validateForm()){
            return;
        }
        if (enterprise){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "User created!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, LogIn.class);
                                intent.putExtra("fullName", fullName);
                                intent.putExtra("email", email);
                                intent.putExtra("password", password);
                                intent.putExtra("enterprise", true);
                                startActivity(intent);
                                finish();
                            } else {
                                status.setText("Failed:" + task.getException());
                                status.setTextColor(Color.RED);
                                Log.w("EmailPassword", "createUserWithEmail:failure", task.getException());
                            }
                        }
                    });
        }else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "User created!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, LogIn.class);
                                intent.putExtra("fullName", fullName);
                                intent.putExtra("email", email);
                                intent.putExtra("password", password);
                                intent.putExtra("enterprise", false);
                                startActivity(intent);
                                finish();
                            } else {
                                status.setText("Failed:" + task.getException());
                                status.setTextColor(Color.RED);
                                Log.w("EmailPassword", "createUserWithEmail:failure", task.getException());
                            }
                        }
                    });
        }

    }

    private boolean validateForm(){
        String fullName = fullNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConf = passwordConfEditText.getText().toString();
        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordConf)){
            Toast.makeText(SignUp.this, "Fill all the fileds!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidEmail(email)){
            Toast.makeText(SignUp.this, "No valid email format", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!TextUtils.equals(password, passwordConf)) {
            Toast.makeText(SignUp.this, "Password not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}
