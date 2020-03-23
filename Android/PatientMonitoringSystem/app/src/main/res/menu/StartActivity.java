package psu.ajm6684.patientmonitoringsystem.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.opencensus.stats.View;
import psu.ajm6684.patientmonitoringsystem.MainActivity;
import psu.ajm6684.patientmonitoringsystem.R;
import psu.ajm6684.patientmonitoringsystem.createAccount.createAccount;

public class StartActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {

            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        }

}

