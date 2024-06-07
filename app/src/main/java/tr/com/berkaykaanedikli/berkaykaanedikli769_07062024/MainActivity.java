package tr.com.berkaykaanedikli.berkaykaanedikli769_07062024;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import tr.com.berkaykaanedikli.berkaykaanedikli769_07062024.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onClickGirisYap(View view) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String textEposta = binding.editTextEmail.getText().toString().trim();
        String textParola = binding.editTextPassword.getText().toString();
        if (!textEposta.isEmpty()) {
            if (!textParola.isEmpty()) {

                firebaseAuth
                        .signInWithEmailAndPassword(textEposta, textParola)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                Toast.makeText(MainActivity.this, "Başarı ile giriş yaptınız.", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(MainActivity.this, KitapEkleActivity.class));

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                binding.textViewWarning.setText("Giriş başarısız\n" + e.getLocalizedMessage());
                            }
                        });


            } else binding.textViewWarning.setText("Şifre boş olamaz");
        } else binding.textViewWarning.setText("E-posta boş olamaz");
    }
}