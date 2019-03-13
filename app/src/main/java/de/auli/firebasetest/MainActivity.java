package de.auli.firebasetest;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtOut;
    private EditText txtMsg;
    private EditText txtUser;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private DatabaseReference childRef;
    private ObjectMapper mapper = new ObjectMapper();
    private Message msg;

    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtOut = findViewById(R.id.txt_out);
        this.txtMsg = findViewById(R.id.txt_msg);
        this.txtUser = findViewById(R.id.txt_user);
        this.db = FirebaseDatabase.getInstance();
        this.dbref = db.getReference("messages");

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Message value = dataSnapshot.getValue(Message.class);
//                if(value != null){
//                    String msg = String.format("%s : %s\n", value.getUser(), value.getMsg());
//                    txtOut.append(msg);
//                } else {
//                    Toast.makeText(MainActivity.this, "Keine Nachricht gefunden", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Fehler beim lesen", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void send(View view) {
        try {
            msg = new Message(txtUser.getText().toString(), txtMsg.getText().toString());
            childRef = dbref.push();
            childRef.setValue(msg);
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }
}
