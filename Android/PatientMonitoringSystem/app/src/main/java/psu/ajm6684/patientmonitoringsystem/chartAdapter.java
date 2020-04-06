package psu.ajm6684.patientmonitoringsystem;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Random;

import com.google.firebase.firestore.DocumentSnapshot;

public class chartAdapter extends FirestoreRecyclerAdapter<chartNote, chartAdapter.chartHolder> {

    private chartAdapter.onItemClickListener listener;






    public chartAdapter(@NonNull FirestoreRecyclerOptions<chartNote> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull chartAdapter.chartHolder chartHolder, int i, @NonNull chartNote chart) {



        chartHolder.patientName.setText(String.valueOf(chart.getPatientName()));
        chartHolder.dateChart.setText(String.valueOf(chart.getDate()));
        chartHolder.timeChart.setText(String.valueOf(chart.getTime()));
        chartHolder.textView.setText(String.valueOf(chart.getUserText()));







    }

    @NonNull
    @Override
    public chartAdapter.chartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_chart, parent, false);

        return new chartAdapter.chartHolder(v);
    }

    public void deletePatient(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();


    }

    public void updateNurse(int position, String newNurse) {

        getSnapshots().getSnapshot(position).getReference().update("activeNurse", newNurse);
    }


    class chartHolder extends RecyclerView.ViewHolder {
//        TextView patientNameView;
//        TextView patientDescriptionView;
//        TextView patientHeight;
//        TextView patientWeight;
//        TextView patientRHeartRate;
//        TextView patientTriageTag;

        TextView patientName;
        TextView dateChart;
        TextView timeChart;
        TextView textView;


        public chartHolder(final View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.text_view_title);
            dateChart = itemView.findViewById(R.id.dateChart);
            timeChart = itemView.findViewById(R.id.timeChart);
            textView = itemView.findViewById(R.id.textChart);






            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int position = getAdapterPosition();

                    listener.onItemLongClick(getSnapshots().getSnapshot(position), position);
                    return true;
                }
            });
//
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION && listener != null) {

                        listener.onItemClick(getSnapshots().getSnapshot(position), position);


                    }
                    // getSnapshots().getSnapshot(position).getReference()
                }
            });

        }
    }

    public interface onItemClickListener {

        void onItemClick(DocumentSnapshot documentSnapshot, int position);

        void onItemLongClick(DocumentSnapshot documentSnapshot, int position);

    }

    public void setOnItemClickListener(chartAdapter.onItemClickListener listener) {

        this.listener = listener;

    }
}


//public class PatientAdapter extends FirestoreRecyclerAdapter<Note, PatientAdapter.patientHolder> {
//
//    private onItemClickListener listener;
//
//
//    //ocumentReference documentReference =
//
//
//    patientFeed pfeed = new patientFeed();
//
//
//    public PatientAdapter(@NonNull FirestoreRecyclerOptions<Note> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull patientHolder patientHolder, int i, @NonNull Note note) {
//
//        patientHolder.patientNameView.setText(note.getPatientName());
//        patientHolder.patientDescriptionView.setText(note.getDescription());
//        patientHolder.patientTriageTag.setText(note.getTriageTag());
//        patientHolder.patientHeight.setText(note.getHeight());
//        patientHolder.patientWeight.setText(String.valueOf(note.getWeight()));
//
////        patientHolder.patientWeight.setText(note.getWeight());
//
//        patientHolder.patientRHeartRate.setText(String.valueOf(note.getrHeartRate()));
//
//        Integer rate = note.getrHeartRate();
//
//
//    }
//
//    @NonNull
//    @Override
//    public patientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent, false);
//
//        return new patientHolder(v);
//    }
//
//    public void deletePatient(int position) {
//        getSnapshots().getSnapshot(position).getReference().delete();
//
//
//    }
//
//    public void updateNurse(int position, String newNurse) {
//
//        getSnapshots().getSnapshot(position).getReference().update("activeNurse", newNurse);
//    }
//
//
//    class patientHolder extends RecyclerView.ViewHolder {
//        TextView patientNameView;
//        TextView patientDescriptionView;
//        TextView patientHeight;
//        TextView patientWeight;
//        TextView patientRHeartRate;
//        TextView patientTriageTag;
//
//
//        public patientHolder(final View itemView) {
//            super(itemView);
//            patientNameView = itemView.findViewById(R.id.text_view_title);
//            patientDescriptionView = itemView.findViewById(R.id.text_view_description);
//            patientHeight = itemView.findViewById(R.id.text_view_height);
//            patientWeight = itemView.findViewById(R.id.text_view_weight);
//            patientRHeartRate = itemView.findViewById(R.id.text_view_heartrate);
//            patientTriageTag = itemView.findViewById(R.id.text_view_triage);
//
//
//            ImageView face = itemView.findViewById(R.id.facey);
//
//
//            face.setVisibility(View.VISIBLE);
//
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//
//                    int position = getAdapterPosition();
//
//                    listener.onItemLongClick(getSnapshots().getSnapshot(position), position);
//                    return true;
//                }
//            });
////
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//
//                    if (position != RecyclerView.NO_POSITION && listener != null) {
//
//                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
//
//
//                    }
//                    // getSnapshots().getSnapshot(position).getReference()
//                }
//            });
//
//        }
//    }
//
//    public interface onItemClickListener {
//
//        void onItemClick(DocumentSnapshot documentSnapshot, int position);
//
//        void onItemLongClick(DocumentSnapshot documentSnapshot, int position);
//
//    }
//
//    public void setOnItemClickListener(onItemClickListener listener) {
//
//        this.listener = listener;
//
//    }
//
//
//}

