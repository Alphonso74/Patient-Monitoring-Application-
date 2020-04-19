package psu.ajm6684.patientmonitoringsystem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import psu.ajm6684.patientmonitoringsystem.R;

public class Chatroom extends AppCompatActivity {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference users = db.collection("Users");

    DatabaseReference reference;
    ArrayList<String> arrayList;
    FirebaseUser user;
    String uid;
    EditText e1;
    ListView l1;
    ArrayAdapter<String> adapter;
    String name;

    EditText ee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom);

        e1 = (EditText) findViewById(R.id.editText);
        l1 = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        l1.setAdapter(adapter);


        DocumentReference userDoc = users.document(uid);

        userDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                                    DocumentSnapshot document = task.getResult();

                                                    name = document.get("fullName").toString();

                                                    e1.setText(name);
                                                }
                                            });

                reference = FirebaseDatabase.getInstance().getReference().getRoot();
                get_username();

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Set<String> set = new HashSet<String>();


                        Iterator i = dataSnapshot.getChildren().iterator();
                        while (i.hasNext()) {
                            set.add(((DataSnapshot) i.next()).getKey());

                        }

                        arrayList.clear();
                        arrayList.addAll(set);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Chatroom.this, "No network connectivity", Toast.LENGTH_SHORT).show();
                    }
                });


                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        Intent intent = new Intent(Chatroom.this, ChatroomMessage.class);
                        intent.putExtra("room_name", ((TextView) view).getText().toString());
                        intent.putExtra("user_name", name);
                        startActivity(intent);

                    }
                });
            }

            public void get_username() {

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(name);
                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        get_username();


                    }
                });
                builder.show();

            }

            public void insert_data(View v) {

                Map<String, Object> map = new HashMap<>();
                map.put(e1.getText().toString(), "");
                reference.updateChildren(map);

            }

}

