package psu.ajm6684.patientmonitoringsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PatientAdapter extends FirestoreRecyclerAdapter<Note, PatientAdapter.patientHolder> {


    public PatientAdapter(@NonNull FirestoreRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull patientHolder patientHolder, int i, @NonNull Note note) {

        patientHolder.patientNameView.setText(note.getPatientName());
        patientHolder.patientDescriptionView.setText(note.getDescription());
        patientHolder.patientTriageTag.setText(note.getTriageTag());
        patientHolder.patientHeight.setText(note.getHeight());
        patientHolder.patientWeight.setText(String.valueOf(note.getWeight()));
        patientHolder.patientRHeartRate.setText(String.valueOf(note.getrHeartRate()));

    }

    @NonNull
    @Override
    public patientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item,parent,false);

        return new patientHolder(v);
    }

    class patientHolder extends RecyclerView.ViewHolder{
            TextView patientNameView;
            TextView patientDescriptionView;
            TextView patientHeight;
            TextView patientWeight;
            TextView patientRHeartRate;
            TextView patientTriageTag;



        public patientHolder(View itemView){
            super(itemView);
            patientNameView = itemView.findViewById(R.id.text_view_title);
            patientDescriptionView = itemView.findViewById(R.id.text_view_description);
            patientHeight = itemView.findViewById(R.id.text_view_height);
            patientWeight = itemView.findViewById(R.id.text_view_weight);
            patientRHeartRate = itemView.findViewById(R.id.text_view_heartrate);
            patientTriageTag = itemView.findViewById(R.id.text_view_triage);
        }
    }
}
