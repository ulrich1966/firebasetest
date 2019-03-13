package de.auli.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    private TextView statusTextView, detailtextView;
    private EditText emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        statusTextView = findViewById(R.id.status);
        detailtextView = findViewById(R.id.detail);
        emailField = findViewById(R.id.fieldEmail);
        passwordField = findViewById(R.id.fieldPassword);

        findViewById(R.id.emailSignInButton);
        findViewById(R.id.emailCreateAccountButton);
        findViewById(R.id.signOutButton);
        findViewById(R.id.verifyEmailButton);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.emailCreateAccountButton:
                createAccound(emailField.getText().toString(), passwordField.getText().toString());
                break;
            case R.id.emailSignInButton:
                signIn(emailField.getText().toString(), passwordField.getText().toString());
                break;
            case R.id.signOutButton:
                signOut();
                break;
            case R.id.verifyEmailButton:
                sendEmailVerificaton();
                break;
            default:
                return;
        }
    }

    private void signOut() {
    }

    private void signIn(String toString, String toString1) {
    }

    private void sendEmailVerificaton() {
    }

    private void createAccound(String toString, String toString1) {
    }
}
