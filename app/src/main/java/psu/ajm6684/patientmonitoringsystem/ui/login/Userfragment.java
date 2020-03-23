package psu.ajm6684.patientmonitoringsystem.ui.login;

import psu.ajm6684.patientmonitoringsystem.R;

import android.annotation.SuppressLint;
import android.graphics.ColorSpace;
import android.graphics.ColorSpace.Model;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Userfragment<Private> extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapterUsers;
    List<LoginViewModel> userlist;

    FirebaseAuth firebaseAuth;

    public Userfragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        @SuppressLint("ResourceType") View view = inflater.inflate(R.id.fragmentuser, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView = view.findViewById(R.id.recycleview);

        userlist = new ArrayList<>();
        getAllUsers();

        return view;

    }


    void getAllUsers() {
        final FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userlist.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LoginViewModel model = ds.getValue(LoginViewModel.class);

                }
                ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

            @Override
            public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

                inflater.inflate(R.menu.chat_menu, menu);
                Userfragment.super.onCreateOptionsMenu(menu, inflater);
            }

            @Override
            public boolean onOptionsItemSelected (MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.logout) {
                    firebaseAuth.signOut();
                }
                return Userfragment.super.onOptionsItemSelected(item);
            }

}

