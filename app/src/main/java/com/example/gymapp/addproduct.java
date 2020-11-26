package com.example.gymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymapp.Modal.gym;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class addproduct extends AppCompatActivity {

    private FirebaseFirestore ProductsRef;
    EditText uname,weight,height;
    Button AddNewProductButton;

    String  gym_uname ;
    String  gym_weight ;
    String gym_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        ProductsRef = FirebaseFirestore.getInstance();


        AddNewProductButton = (Button) findViewById(R.id.add_new_product);
        uname = (EditText) findViewById(R.id.gym_uname);
        weight = (EditText) findViewById(R.id.gym_uname_weight);
        height = (EditText) findViewById(R.id.gym_uname_height);

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SavegymToDatabase();
            }
        });

    }

    private void SavegymToDatabase()
    {

        gym_uname = uname.getText().toString().trim();
        gym_weight = weight.getText().toString().trim();
        gym_height = height.getText().toString().trim();
        gym gym = new gym(gym_uname, gym_weight, gym_height);


        ProductsRef.collection("Members")
                .add(gym)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Member added", Toast.LENGTH_LONG).show();
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}