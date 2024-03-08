package fpoly.truongtqph41980.lab1.Danhsach;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import fpoly.truongtqph41980.lab1.R;

public class ListScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<CityModel> cityList = new ArrayList<>();
    private AdapterList adapter;


    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.rcv_qlList);
        FloatingActionButton fltAdd = findViewById(R.id.fladd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterList(this, cityList);
        recyclerView.setAdapter(adapter);



        loadDataFromFirestore();

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListScreen.this, AddScreen.class));
            }
        });
    }

    private void loadDataFromFirestore() {
        db.collection("City")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                // bai tap: hien thi du lieu len giao dien RecycleView / ListView
                                CityModel cityModel = document.toObject(CityModel.class);
                                cityList.add(cityModel);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


}