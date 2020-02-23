package psu.ajm6684.patientmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import psu.ajm6684.patientmonitoringsystem.ui.login.LoginActivity;

public class patientFeed extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference patients = db.collection("patients");

    private PatientAdapter patientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_patient);
        Button addPatient = (Button) findViewById(R.id.addPatient);


        setUpView();
    }
        private void setUpView() {
            Query query = patients;

            FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();

            patientAdapter = new PatientAdapter(options);

            RecyclerView recyclerView = findViewById(R.id.RecycleView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(patientAdapter);
        }

         @Override
         protected void onStart(){
            super.onStart();
            patientAdapter.startListening();
        }

        @Override
        protected void onStop(){
        super.onStop();
        patientAdapter.stopListening();
        }




    public void logout(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }


}
