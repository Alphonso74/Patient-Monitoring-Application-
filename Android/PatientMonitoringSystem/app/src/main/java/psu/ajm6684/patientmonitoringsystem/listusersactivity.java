/*
package psu.ajm6684.patientmonitoringsystem;

import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.androidprochatapplication.Adapter.ListUserAdapter;
import com.androidprochatapplication.Common.Common;
import com.androidprochatapplication.Holder.QBUsersHolder;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.QBSystemMessagesManager;
import com.quickblox.chat.listeners.QBSystemMessageListener;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.chat.utils.DialogUtils;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;
import org.jivesoftware.smack.SmackException;

import java.util.ArrayList;

    public class listusersactivity extends AppCompatActivity {
        ListView firstUser;
        Button btnCreateChat;
        int countChoice;
        ArrayList<Integer> occupantIdsList;
        QBChatDialog dialog;
        ProgressDialog mDialog;
        QBUser user;
        //QBSystemMessagesManager qbSystemMessagesManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_listusers);

            retriveAllUser();

            firstUser = (ListView)findViewById(R.id.firstUser);
            firstUser.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            btnCreateChat = (Button)findViewById(R.id.btn_create_chat);
            btnCreateChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int countChoice = firstUser.getCount();
                    if (firstUser.getCheckedItemPositions().size() == 1)
                        createPrivateChat(firstUser.getCheckedItemPositions());
                    else if (firstUser.getCheckedItemPositions().size() > 1)
                        createGroupChat(firstUser.getCheckedItemPositions());
                    else
                        Toast.makeText(listusersactivity.this, "Chon nguoi de Nhan tin", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void createGroupChat(SparseBooleanArray checkedItemPositions) {
            mDialog = new ProgressDialog(ListUsersActivity.this);
            mDialog.setMessage("Xin vui long cho ...");
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();

            countChoice = firstUser.getCount();
            occupantIdsList = new ArrayList<>();
            for (int i=0; i<countChoice; i++){
                if (checkedItemPositions.get(i)){
                    QBUser user = (QBUser)firstUser.getItemAtPosition(i);
                    occupantIdsList.add(user.getId());
                }
            }

            dialog = new QBChatDialog();
            dialog.setName(Common.createChatDialogName(occupantIdsList));
            dialog.setType(QBDialogType.GROUP);
            dialog.setOccupantsIds(occupantIdsList);

            QBRestChatService.createChatDialog(dialog).performAsync(new QBEntityCallback<QBChatDialog>() {
                @Override
                public void onSuccess(QBChatDialog qbChatDialog, Bundle bundle) {
                    mDialog.dismiss();
                    Toast.makeText(getBaseContext(), "Tao Chat Nhom Thanh Cong !", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(QBResponseException e) {
                    Log.e("ERROR", e.getMessage());
                }
            });
        }

        private void createPrivateChat(SparseBooleanArray checkedItemPositions) {
            mDialog = new ProgressDialog(ListUsersActivity.this);
            mDialog.setMessage("Xin vui long cho ...");
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();

            countChoice = firstUser.getCount();
            for (int i=0; i<countChoice; i++){
                if (checkedItemPositions.get(i)){
                    user = (QBUser)firstUser.getItemAtPosition(i);
                    dialog = DialogUtils.buildPrivateDialog(user.getId());

                    QBRestChatService.createChatDialog(dialog).performAsync(new QBEntityCallback<QBChatDialog>() {
                        @Override
                        public void onSuccess(QBChatDialog qbChatDialog, Bundle bundle) {
                            mDialog.dismiss();
                            Toast.makeText(getBaseContext(), "Tao Chat Rieng Tu Thanh Cong !", Toast.LENGTH_SHORT).show();

                            qbSystemMessagesManager = QBChatService.getInstance().getSystemMessagesManager();
                            QBChatMessage qbChatMessage = new QBChatMessage();
                            qbChatMessage.setBody(qbChatDialog.getDialogId());
                            for (int i=0;i<qbChatDialog.getOccupants().size();i++){
                                qbChatMessage.setRecipientId(qbChatDialog.getOccupants().get(i));
                                try {
                                    qbSystemMessagesManager.sendSystemMessage(qbChatMessage);
                                }catch (SmackException.NotConnectedException e){
                                    e.printStackTrace();
                                }
                            }
                            finish();
                        }

                        @Override
                        public void onError(QBResponseException e) {
                            Log.e("ERROR", e.getMessage());
                        }
                    });
                }
            }
        }

        private void retriveAllUser() {
            QBUsers.getUsers(null).performAsync(new QBEntityCallback<ArrayList<QBUser>>() {
                @Override
                public void onSuccess(ArrayList<QBUser> qbUsers, Bundle bundle) {
                    QBUsersHolder.getInstance().putUsers(qbUsers);

                    ArrayList<QBUser> qbUserWithourCurrent = new ArrayList<QBUser>();
                    for (QBUser user : qbUsers){
                        if (!user.getLogin().equals(QBChatService.getInstance().getUser().getLogin()))
                            qbUserWithourCurrent.add(user);
                    }
                    ListUserAdapter adapter = new ListUserAdapter(getBaseContext(), qbUserWithourCurrent);
                    firstUser.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(QBResponseException e) {
                    Log.e("ERROR", e.getMessage());
                }
            });
        }
    }
}
*/
