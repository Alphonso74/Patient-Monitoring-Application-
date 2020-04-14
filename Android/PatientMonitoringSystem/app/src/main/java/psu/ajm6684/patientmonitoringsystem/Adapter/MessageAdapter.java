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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import psu.ajm6684.patientmonitoringsystem.MessageActivity;
import psu.ajm6684.patientmonitoringsystem.R;
import psu.ajm6684.patientmonitoringsystem.chat;
import psu.ajm6684.patientmonitoringsystem.user;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>

{
    public static final int MSG_type_left = 0;
    public static final int MSG_type_right = 1;

    private Context mContext;
    private List<chat> mChat;
    private String imageurl;

    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<chat> mChat, String imageurl) {
        this.mChat = mChat;
        this.mContext = mContext;
        this.imageurl = imageurl;

    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_type_right) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        chat chat = mChat.get(position);

        holder.show_message.setText(chat.getMessage());

        if (imageurl.equals("default")) {
            holder.profimage.setImageResource(R.mipmap.ic_launcher);
        }else {
            Glide.with(mContext).load(imageurl).into(holder.profimage);
        }
        }


    @Override
    public int getItemCount () {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView show_message;
        public ImageView profimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profimage = itemView.findViewById(R.id.profimage);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(fuser.getUid()))
        {
            return MSG_type_right;
        } else {
            return MSG_type_left;
        }
    }
}
