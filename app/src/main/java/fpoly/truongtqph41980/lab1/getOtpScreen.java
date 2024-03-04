package fpoly.truongtqph41980.lab1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class getOtpScreen extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String mVertficationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_otp_screen);
        firebaseAuth = FirebaseAuth.getInstance();
        EditText edtPhone = findViewById(R.id.edtPhone);
        Button btnGetOTP = findViewById(R.id.btnGetOtp);

        EditText edtOTP = findViewById(R.id.edtOtp);
        Button btnLogin = findViewById(R.id.btnLogin);




        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                edtOTP.setText(phoneAuthCredential.getSmsCode());
                mVertficationId = phoneAuthCredential.getSmsCode();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(getOtpScreen.this, "Xác thực OTP thất bại", Toast.LENGTH_SHORT).show();
            }

        };


        btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = edtPhone.getText().toString();
                getOTP(phoneNumber);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = edtOTP.getText().toString();
                if (!otp.isEmpty()) {
                    verifyOTP(otp);
                } else {
                    Toast.makeText(getOtpScreen.this, "Vui lòng nhập mã OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getOTP(String phoneNumber){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber("+84"+phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void verifyOTP(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVertficationId,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getOtpScreen.this, "đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(getOtpScreen.this, MainActivity.class));
                        }else {
                            Log.w(TAG,"signInWithCredential:failure",task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(getOtpScreen.this, "Mã OTP không hợp lệ", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }
}