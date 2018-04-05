package lucian.example.com.projetcircuits;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    TextView signUpUser;
    Button loginBtn;
    private FirebaseAuth mAuth;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signUpUser = (TextView) this.findViewById(R.id.link_signup);
        loginBtn = (Button)this.findViewById(R.id.btn_login);
        username = (EditText)this.findViewById(R.id.input_email);
        password = (EditText)this.findViewById(R.id.input_password);
        mAuth = FirebaseAuth.getInstance();
        signUpUser.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    private void userLogin() {
        final String nomUtilisateur = username.getText().toString().trim();
        String motPasse = password.getText().toString().trim();
        if(nomUtilisateur.isEmpty()) {
            username.setError("Entrez le courriel");
            username.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(nomUtilisateur).matches()) {
            username.setError("Entrez une adresse de courriel valide!");
            username.requestFocus();
            return;
        }

        if(motPasse.isEmpty()) {
            password.setError("Entrez le mot de passe");
            password.requestFocus();
            return;
        }

        if(motPasse.length()<6) {
            password.setError("Longueur minimale de mot de passe est 6 caracteres");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(nomUtilisateur, motPasse)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            if(nomUtilisateur=="oglindalucian@gmail.com") {
                                Intent i = new Intent(SignIn.this, MainActivity.class);
                                i.putExtra("EXTRA_CONNECT", "admin");
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                            } else {
                                Intent i = new Intent(SignIn.this, MainActivity.class);
                                i.putExtra("EXTRA_CONNECT", "not_admin");
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                            }


                        } else {
                            Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.link_signup:
                startActivity(new Intent(this, SignUp.class));
                break;

            case R.id.btn_login:
                userLogin();
                break;
        }
    }
}
