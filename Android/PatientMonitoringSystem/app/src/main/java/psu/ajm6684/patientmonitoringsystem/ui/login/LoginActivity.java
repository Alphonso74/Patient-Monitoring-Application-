package psu.ajm6684.patientmonitoringsystem.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import psu.ajm6684.patientmonitoringsystem.R;
import psu.ajm6684.patientmonitoringsystem.createAccount.createAccount;
import psu.ajm6684.patientmonitoringsystem.patientFeed;
import psu.ajm6684.patientmonitoringsystem.neonatalPatientFeed;
import psu.ajm6684.patientmonitoringsystem.postOpPatientFeed;
//import psu.ajm6684.patientmonitoringsystem.patientListActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;


    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUiWithUser(currentUser);
    }

    String Uid;
    FirebaseFirestore firestore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        final EditText emailEditText = findViewById(R.id.email_login);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button createAccountButon = (Button) findViewById(R.id.createAccount);
        final ProgressBar loadingProgressBar = findViewById(R.id.progressBar2);

        loadingProgressBar.setVisibility(View.INVISIBLE);

        createAccountButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openCreateAccountActivity();
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        //Uid = firebaseAuth.getCurrentUser().getUid();
       // DocumentReference documentReference = firestore.collection("Users").document(Uid);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {


                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    final CollectionReference users = db.collection("Users");

                    String userid = user.getUid();

                    DocumentReference documentReference = db.collection("Users").document(userid);

                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot document = task.getResult();

                            Log.d("User", "DocumentSnapshot data: " + document.getData());

                            if(document.get("department").equals("General Care")){

                                Intent intent = new Intent(LoginActivity.this, patientFeed.class);
                                startActivity(intent);
                                finish();

                            }

                            if(document.get("department").equals("Neonatal")){

                                Intent intent = new Intent(LoginActivity.this, neonatalPatientFeed.class);
                                startActivity(intent);
                                finish();

                            }

                            if(document.get("department").equals("Post-Operation")){

                                Intent intent = new Intent(LoginActivity.this, postOpPatientFeed.class);
                                startActivity(intent);
                                finish();

                            }

                        }
                    });

//                    Intent intent = new Intent(LoginActivity.this, patientFeed.class);
//                    startActivity(intent);
//                    finish();
                }
            }
        };

        firebaseAuth.addAuthStateListener(authStateListener);


        //Sign in button stays unclickable until fields are changed
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    emailEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });


//
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(emailEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(emailEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail = emailEditText.getText().toString().trim();
                String pass = passwordEditText.getText().toString().trim();


                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(emailEditText.getText().toString(),
//                        passwordEditText.getText().toString());

                if(pass.length() < 6){

                    passwordEditText.setError("Password must be longer than 6 characters!");

                    loadingProgressBar.setVisibility(View.INVISIBLE);

                    return;
                }

                if(emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Missing Fields Need to Entered", Toast.LENGTH_LONG).show();

                    loadingProgressBar.setVisibility(View.INVISIBLE);
                }


                firebaseAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful()){

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUiWithUser(user);
                            Toast.makeText(getApplicationContext(), "Logged in user: " + mail, Toast.LENGTH_LONG).show();
                            //openPatientFeedActivity();

                        }
                        else{

                            Toast.makeText(getApplicationContext(), "Incorrect Credentials! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            updateUiWithUser(null);

                            loadingProgressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                });


            }
        });
    }

    public void openCreateAccountActivity(){
        Intent intent = new Intent(LoginActivity.this, createAccount.class);
        startActivity(intent);
    }

    public void openPatientFeedActivity(){
        Intent intent = new Intent(LoginActivity.this, patientFeed.class);
        startActivity(intent);
    }

    private void updateUiWithUser(FirebaseUser user) {
//        String welcome = getString(R.string.welcome) + model.getDisplayName();
//        // TODO : initiate successful logged in experience
//        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
