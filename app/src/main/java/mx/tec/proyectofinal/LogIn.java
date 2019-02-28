package mx.tec.proyectofinal;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password;
    private CheckBox isEnterprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.editText_emailLogIn);
        password = findViewById(R.id.editText_passwordLogIn);
        isEnterprise = findViewById(R.id.checkBox_isEnterprise);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();


        String fullNameSignUp = intent.getStringExtra("fullName");
        String emailSignUp = intent.getStringExtra("email");
        String emailSignUpReplaced = null;
        String passwordSignUp = intent.getStringExtra("password");
        Boolean isEnterprise = intent.getBooleanExtra("enterprise", false);
        if (emailSignUp != null){
            emailSignUpReplaced = intent.getStringExtra("email").replace(".", ",");
        }
        if (fullNameSignUp != null && emailSignUp != null && emailSignUpReplaced != null && passwordSignUp != null){
            Toast.makeText(LogIn.this, "Loggin in ...", Toast.LENGTH_SHORT).show();
            if (isEnterprise){
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference userDataReference = database.getReference();
                userDataReference.child("JobsApp").child("Enterprise").child(emailSignUpReplaced).child("EnterpriseName").setValue(fullNameSignUp);
                logIn(fullNameSignUp, emailSignUp, passwordSignUp, true);
            }else {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference userDataReference = database.getReference();
                userDataReference.child("JobsApp").child("User").child(emailSignUpReplaced).child("FullName").setValue(fullNameSignUp);
                logIn(fullNameSignUp, emailSignUp, passwordSignUp, false);
            }
        }
        }

    public void clickLogIn(View v){
        logIn(null, email.getText().toString(), password.getText().toString(), isEnterprise.isChecked());
    }

    private void logIn(final String fullName, final String email, String password, boolean isEnterprise) {
        if (!validateForm(email, password)){
            return;
        }

        if (isEnterprise){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LogIn.this, "Succesfull login", Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                //here move to another intent
                                Intent intent = new Intent(LogIn.this, enterpDashboard.class);
                                //put some extras like the user? Or save that in preferences
                                intent.putExtra("fullName", fullName);
                                intent.putExtra("email", email.replace(".",","));
                                startActivity(intent);
                            }else{
                                Toast.makeText(LogIn.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                            }

                            if (!task.isSuccessful()){
                                Toast.makeText(LogIn.this, "Failed to logIn", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else{
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
                                intent.putExtra("email", email.replace(".",","));
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
