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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button signUpUser;
    TextView loginUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText)this.findViewById(R.id.input_email);
        password = (EditText)this.findViewById(R.id.input_password);
        signUpUser = (Button)this.findViewById(R.id.btn_signup);
        loginUser = (TextView)this.findViewById(R.id.link_signin);
        loginUser.setOnClickListener(this);
        signUpUser.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser() {
        String nomUtilisateur = username.getText().toString().trim();
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

        mAuth.createUserWithEmailAndPassword(nomUtilisateur, motPasse).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Inscription effectue avec success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUp.this, SignIn.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(SignUp.this, "Vous etes deja enregistre!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_signup:
                registerUser();
                break;

            case R.id.link_signin:
                startActivity(new Intent(this, SignIn.class));
                break;
        }
    }
}
