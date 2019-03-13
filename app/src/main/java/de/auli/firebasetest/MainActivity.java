package de.auli.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private TextView txtOut;
    private EditText txtEdit;
    private Button cmdSend;
    private FirebaseDatabase db;
    private DatabaseReference dbref;

    public MainActivity(){
        super();
        this.txtOut = findViewById(R.id.txt_out);
        this.txtEdit = findViewById(R.id.txt_edit);
        this.cmdSend = findViewById(R.id.cmd_send);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view) {
    }
}
