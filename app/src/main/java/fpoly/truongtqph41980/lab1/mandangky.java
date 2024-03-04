package fpoly.truongtqph41980.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class mandangky extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandangky);
        firebaseAuth = FirebaseAuth.getInstance();

        EditText edtEmail = findViewById(R.id.edtemaildk);
        EditText edtPass = findViewById(R.id.edtpassdk);
        Button btnDangKy = findViewById(R.id.btnDangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(mandangky.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(mandangky.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Toast.makeText(mandangky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(mandangky.this, mandangnhap.class));
                            } else {
                                Log.w("hehehe", task.getException());
                                Toast.makeText(mandangky.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}