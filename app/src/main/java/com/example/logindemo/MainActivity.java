package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    TextView viewText;
    Button mSaveBtn;
    EditText etTextDate,etTextNote;
    FirebaseFirestore fStore;
    ArrayList<Object> notlar = new ArrayList<>();








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fStore = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_main);

        viewText = findViewById(R.id.viewText);
        mSaveBtn = findViewById(R.id.saveBtn);
        etTextDate = findViewById(R.id.etTextDate);
        etTextNote = findViewById(R.id.etTextNote);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = etTextDate.getText().toString();
                String note = etTextNote.getText().toString();



                if (TextUtils.isEmpty(date)) {
                    etTextDate.setError("Date is required.");
                    return;
                }
                if (TextUtils.isEmpty(note)) {
                    etTextNote.setError("Note is required.");
                    return;
                }

                String userID = fAuth.getCurrentUser().getUid();

                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String,Object> notes = new HashMap<>();
                notes.put("date",date);
                notes.put("note",note);




                fStore.collection(userID).document().set(notes);




             /*   documentReference.set(notes).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: Saved.");
                    }
                });*/


            }
        });

        viewText.setOnClickListener(view -> {
         startActivity(new Intent(getApplicationContext(),MainActivity2.class));
         finish();
        });



    }
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void  logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();

    }







}