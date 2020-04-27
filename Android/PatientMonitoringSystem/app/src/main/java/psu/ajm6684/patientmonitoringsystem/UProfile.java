package psu.ajm6684.patientmonitoringsystem;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import psu.ajm6684.patientmonitoringsystem.ui.login.LoginActivity;

public class UProfile extends Activity {

    FirebaseAuth firebaseAuth;


    TextView userName;
    TextView userEmail;
    TextView userDepartment;
    TextView userPosition;
    TextView userHospital;

    String name;
    String email;
    String department;
    String position;
    String hospital;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference users = db.collection("Users");

    DocumentReference userDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        String Uid = currentUser.getUid().toString();




        userName = (TextView) findViewById(R.id.userNameView);
        userEmail = (TextView) findViewById(R.id.userEmailView);
        userDepartment = (TextView) findViewById(R.id.userDepartmentView);
        userPosition = (TextView) findViewById(R.id.userPositionView);
        userHospital = (TextView) findViewById(R.id.userHospitalView) ;




//        userDoc = users.document(Uid);

        DocumentReference userDoc = users.document(Uid);

        userDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                DocumentSnapshot document = task.getResult();

                name = document.get("fullName").toString();
                email = document.get("email").toString();
                department = document.get("department").toString();
                position = document.get("position").toString();
                hospital = document.get("hospital").toString();



                userName.setText(name);
                userEmail.setText(email);
                userDepartment.setText(department);
                userPosition.setText(position);
                userHospital.setText(hospital);



            }
        });
        //







    }



    public void logout(View view) {


        finish();
    }
}
