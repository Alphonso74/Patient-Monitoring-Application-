package psu.ajm6684.patientmonitoringsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import psu.ajm6684.patientmonitoringsystem.MessageActivity;
import psu.ajm6684.patientmonitoringsystem.R;
import psu.ajm6684.patientmonitoringsystem.user;

public class Useradapter extends RecyclerView.Adapter<Useradapter.ViewHolder>

{

    private Context mContext;
    private List<user> mUsers;

    public Useradapter(Context mContext, List<user> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;

    }

    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new Useradapter.ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final user user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        if (user.getImageURL().equals("default")) {
            holder.profimage.setImageResource(R.mipmap.ic_launcher);
        } else {

            Glide.with(mContext).load(user.getImageURL());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }

        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView username;
            public ImageView profimage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                username = itemView.findViewById(R.id.username);
                profimage = itemView.findViewById(R.id.profimage);
            }
        }
        @Override
        public int getItemCount () {
        return 0;
}
    }
