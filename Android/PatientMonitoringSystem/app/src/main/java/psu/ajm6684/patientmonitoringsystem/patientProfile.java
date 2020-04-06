package psu.ajm6684.patientmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class patientProfile extends AppCompatActivity {
    PatientAdapter patientAdapter;


    @Override
    protected void onStart() {
//        Intent intent = getIntent();
//
//        final String standingO = intent.getStringExtra("standingO");
//        TextView standingOrder = (TextView) findViewById(R.id.textView17);
//        standingOrder.setText(standingO);
//


        super.onStart();
    }

    @Override
    protected void onResume() {

//        Intent intent = getIntent();
//
//        final String standingO = intent.getStringExtra("standingO");
//        TextView standingOrder = (TextView) findViewById(R.id.textView17);
//        standingOrder.setText(standingO);



        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_profile);

        Button addNurse = (Button) findViewById(R.id.button5);
        final Button createChart = (Button) findViewById(R.id.button7);

        Button backButton = (Button) findViewById(R.id.back2feed);
        Button viewCharts = (Button) findViewById(R.id.viewCharts);

        Button standingOrderButton = (Button) findViewById(R.id.button6);

        FirebaseFirestore  firebaseFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        final String patientName = intent.getStringExtra("Patient Name");
        final String patientDescription = intent.getStringExtra("Patient Description");
        final String patientHeight = intent.getStringExtra("Patient Height");
        final String patientWeight = intent.getStringExtra("Patient Weight");
        final String patientRestingHeartRate = intent.getStringExtra("Patient Resting Heart Rate");
        final String patientID = intent.getStringExtra("Patient ID");
        final Integer position = intent.getIntExtra("position",0);
        final String bodyTemp = intent.getStringExtra("bodyTemp");
        final String activeNurse = intent.getStringExtra("nurse");
        final String medications = intent.getStringExtra("medications");
        final String sHistory = intent.getStringExtra("surgicalH");
        final String standingO = intent.getStringExtra("standingO");


        final DocumentReference patientItem = firebaseFirestore.collection("patients3").document(patientID);


        final TextView pnameView = (TextView) findViewById(R.id.textView10);
        TextView pdescriptionView = (TextView) findViewById(R.id.textView13);
        TextView pRestRate = (TextView) findViewById(R.id.textView22);
        TextView pHeight  = (TextView) findViewById(R.id.textView23);
        TextView pWeight = (TextView) findViewById(R.id.textView24);
        TextView bodyTemp1 = (TextView) findViewById(R.id.textView29);
        TextView meds = (TextView) findViewById(R.id.textView30);
        TextView sHistory1 = (TextView) findViewById(R.id.textView31);
        TextView aNurse = (TextView) findViewById(R.id.textView25);
        TextView standingOrder = (TextView) findViewById(R.id.textView17);



        bodyTemp1.setText(bodyTemp);
        meds.setText(medications);
        sHistory1.setText(sHistory);
        aNurse.setText(activeNurse);
        pnameView.setText(patientName);
        pRestRate.setText(patientRestingHeartRate);
        pHeight.setText(patientHeight);
        pWeight.setText(patientWeight);
        pdescriptionView.setText(patientDescription);
        standingOrder.setText(standingO);

        viewCharts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(patientProfile.this, patientName.toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(patientProfile.this,PatientChart.class);
                intent.putExtra("Patient Name",patientName.toString());
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(patientProfile.this,patientFeed.class);
//                startActivity(intent);
                finish();

            }
        });


        addNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final Dialog dialog = new Dialog(patientProfile.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.addnursedialog);

                final EditText et = dialog.findViewById(R.id.et);


                Button btnok = (Button) dialog.findViewById(R.id.btnok);
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String nurse = et.getText().toString();


                        if(nurse.isEmpty()){

                            Toast toast = Toast.makeText(getApplicationContext(),"Please Enter the name of the Nurse",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            patientItem.update("activeNurse", nurse);

                            Toast.makeText(patientProfile.this, " Nurse Added", Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(patientProfile.this,patientProfile.class);
                            intent.putExtra("Patient Name",patientName);
                            intent.putExtra("Patient Description",patientDescription);
                            intent.putExtra("Patient Height",patientHeight);
                            intent.putExtra("Patient Weight",patientWeight);
                            intent.putExtra("Patient Resting Heart Rate",patientRestingHeartRate);
                            intent.putExtra("Patient ID",patientID);
                            intent.putExtra("position",position);
                            intent.putExtra("bodyTemp",bodyTemp);
                            intent.putExtra("nurse",nurse);
                            intent.putExtra("medications",medications);
                            intent.putExtra("surgicalH",sHistory);
                            intent.putExtra("standingO",standingO);


                            startActivity(intent);



                            dialog.dismiss();
                        }
                    }
                });

                Button btncn = (Button) dialog.findViewById(R.id.btncn);
                btncn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();



            }
        });

        standingOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(patientProfile.this,standingOrder.class);
                intent.putExtra("Patient ID",patientID); //Changeeeeeeeeeeeee***
                intent.putExtra("Patient Name", patientName);
                startActivity(intent);
            }
        });

        createChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(patientProfile.this,chart.class);
                intent.putExtra("Patient Name",patientName);
                intent.putExtra("Patient Description",patientDescription);
                intent.putExtra("Patient Height",patientHeight);
                intent.putExtra("Patient Weight",patientWeight);
                intent.putExtra("Patient Resting Heart Rate",patientRestingHeartRate);
                intent.putExtra("Patient ID",patientID);
                intent.putExtra("position",position);
               intent.putExtra("bodyTemp",bodyTemp);
                intent.putExtra("nurse",activeNurse);
                intent.putExtra("medications",medications);
                intent.putExtra("surgicalH",sHistory);
                intent.putExtra("standingO",standingO);
                startActivity(intent);


            }
        });


    }



    private void updateNurse(View v, String Name){

        //patientAdapter.updateNurse(position,name);

    }
}
