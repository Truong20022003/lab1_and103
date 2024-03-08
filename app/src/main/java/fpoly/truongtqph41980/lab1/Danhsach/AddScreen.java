package fpoly.truongtqph41980.lab1.Danhsach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import fpoly.truongtqph41980.lab1.R;
import fpoly.truongtqph41980.lab1.databinding.ActivityAddScreenBinding;

public class AddScreen extends AppCompatActivity {
    ActivityAddScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddScreen.this, ListScreen.class));
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCity();
            }
        });
    }

    private void addCity() {
        String name = binding.edtNameCity.getText().toString();
        String country = binding.edtCountry.getText().toString();
        String foundedYear = binding.edtfoundedYear.getText().toString();
        CityModel cityModel = new CityModel(name, country, foundedYear);

        FirebaseFirestore.getInstance().collection("City").add(cityModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Đã thêm thành công
                        Toast.makeText(AddScreen.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xảy ra lỗi khi thêm
                        Toast.makeText(AddScreen.this, "Lỗi khi thêm thành phố: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
