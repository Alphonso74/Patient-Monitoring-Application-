package psu.ajm6684.patientmonitoringsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import psu.ajm6684.patientmonitoringsystem.Fragments.ChatFragment;
import psu.ajm6684.patientmonitoringsystem.Fragments.UsersFragment;

public class StartChat extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;
    FirebaseUser firebaseUser;
    ListView mListview;
    private PatientAdapter patientAdapter;
    RecyclerView recyclerView;
    DatabaseReference reference;

    ArrayList<String> arrayList;
    ImageButton l1;

    EditText e1;
    ArrayAdapter<String> adapter;
    String name;
    EditText ee;
    int chatmenuindexclicked = -1;
    boolean isEditMode = false;
    Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlayout);


/*

          Toolbar toolbar = findViewById(R.id.chattoolbar);
          setSupportActionBar(toolbar);
          getSupportActionBar().setTitle("");
*/

        profile_image = findViewById(R.id.profimage);
        username = findViewById(R.id.username);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    String name = dataSnapshot.child("username").getValue(String.class);
                    Log.d("TAG", String.valueOf(username));
                    arrayList.add(name);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter(StartChat.this, android.R.layout.simple_list_item_1, arrayList);

             /*   firebaseUser.getUid();
                user user = dataSnapshot.getValue(user.class);
                assert user != null;
              username.setText(user.getUsername());
              if (user.getImageURL().equals("default")) {
                    profile_image.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(StartChat.this).load(user.getImageURL()).into(profile_image);
                }*/
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        ViewPager viewpager = findViewById(R.id.view_pager);

        ViewPagerAdapter viewpageradapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewpageradapter.addFragment(new  ChatFragment(), "Chats");
        viewpageradapter.addFragment(new  UsersFragment(), "Users");


        viewpager.setAdapter(viewpageradapter);

        tabLayout.setupWithViewPager(viewpager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return false;
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }


    }
            }
