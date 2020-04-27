package psu.ajm6684.patientmonitoringsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
    TextView currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom);

        e1 = (EditText) findViewById(R.id.editText);
        l1 = (ListView) findViewById(R.id.listView);

        currentuser = (TextView) findViewById(R.id.currentuser);
        arrayList = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        adapter = new ArrayAdapter<String>(this, R.layout.custom_textview, arrayList);

        l1.setAdapter(adapter);

        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {

                final int current_item = position;

                new AlertDialog.Builder(Chatroom.this)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(current_item);
                                adapter.notifyDataSetChanged();
                                view.setSelected(true);

                                Toast.makeText(Chatroom.this, "Chat deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
        DocumentReference userDoc = users.document(uid);

        userDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot document = task.getResult();

                name = document.get("fullName").toString();

                currentuser.append("User:  " + name);


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
    public void back (View view) {
        startActivity(new Intent(this, patientFeed.class));
    }

            public void get_username() {
                setTitle(name);
                    }

}

