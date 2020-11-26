package com.example.gymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymapp.Modal.gym;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class home extends AppCompatActivity {
    TextView result ;
    FirebaseFirestore ProductsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        result =findViewById(R.id.result);
        ProductsRef = FirebaseFirestore.getInstance();

        display();
    }
    public void display(){

        ProductsRef.collection("Members")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            String resultstr="";
                            for(DocumentSnapshot document:task.getResult()){

                                gym product =document.toObject(gym.class);
                                resultstr +="Member name: "+ product.getUsername()+"\nMember height: " +product.getHeight()+
                                        "\nMember weight: " +product.getWeight()+"\n\n";
                            }
                            result.setText(resultstr);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getApplicationContext(),"loading fail",Toast.LENGTH_SHORT).show();
            }
        });

    }
}