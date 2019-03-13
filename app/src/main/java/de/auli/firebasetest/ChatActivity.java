package de.auli.firebasetest;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = ChatActivity.class.getSimpleName();
    private TextView txtOut;
    private EditText txtMsg;
    private EditText txtUser;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private DatabaseReference childRef;
    private DatabaseReference dbId;
    private ObjectMapper mapper = new ObjectMapper();
    private Message msg;

    public ChatActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.txtOut = findViewById(R.id.txt_out);
        this.txtMsg = findViewById(R.id.txt_msg);
        this.txtUser = findViewById(R.id.txt_user);
        this.db = FirebaseDatabase.getInstance();
        this.dbref = db.getReference("messages");
        this.dbId = db.getReference("ids");

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message value = dataSnapshot.getValue(Message.class);
                if(value != null){
                    String msg = String.format("%s : %s\n", value.getUser(), value.getMsg());
                    txtOut.append(msg);
                } else {
                    Toast.makeText(ChatActivity.this, "Keine Nachricht gefunden", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ChatActivity.this, "Fehler beim lesen", Toast.LENGTH_SHORT).show();
            }
        });

        dbId.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Integer value = dataSnapshot.getValue(Integer.class);
                if(value == null){
                    value = 0;
                } else {
                    value++;
                    dbId.setValue(value);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ChatActivity.this, "Fehler beim lesen", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void send(View view) {
        try {
            dbId = dbref.push();
            msg = new Message(txtUser.getText().toString(), txtMsg.getText().toString());
            childRef = dbref.push();
            childRef.setValue(msg);
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }
}
