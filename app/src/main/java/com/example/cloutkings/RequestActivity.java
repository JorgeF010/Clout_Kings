package com.example.cloutkings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class RequestActivity extends AppCompatActivity {

    // Submit Button
    private Button submit;
    // Input fields
    private EditText name;
    private EditText socialMedia;
    // String values of input fields
    private String nameText;
    private String usernameText;
    // Dataclass for inputs
    private HashMap<String, String> requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        this.submit = findViewById(R.id.button_submit);
        this.name = findViewById(R.id.name_input);
        this.socialMedia = findViewById(R.id.socialmedia_input);
        this.requests = new HashMap<>();
        this.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    public void submitForm() {
        this.nameText = this.name.getText().toString();
        this.usernameText = this.socialMedia.getText().toString();
        if(!(this.requests.containsKey(this.nameText))) {
            this.requests.put(this.nameText, this.usernameText);
        }
        this.name.getText().clear();
        this.socialMedia.getText().clear();
    }

}
