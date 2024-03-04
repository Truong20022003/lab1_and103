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

public class mandangnhap extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandangnhap);
        firebaseAuth = FirebaseAuth.getInstance();
        EditText edtEmail = findViewById(R.id.edtemail);
        EditText edtPass = findViewById(R.id.edtpass);
        Button btnDangKy = findViewById(R.id.btnDangNhap);





                btnDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = edtEmail.getText().toString();
                        String pass = edtPass.getText().toString();

                        if (email.isEmpty() || pass.isEmpty()) {
                            Toast.makeText(mandangnhap.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(mandangnhap.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Toast.makeText(mandangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(mandangnhap.this, MainActivity.class));
                                    } else {
                                        Log.w("hehehe", task.getException());
                                        Toast.makeText(mandangnhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                });



    }
}