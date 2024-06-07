package tr.com.berkaykaanedikli.berkaykaanedikli769_07062024;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import tr.com.berkaykaanedikli.berkaykaanedikli769_07062024.databinding.ActivityKitapEkleBinding;

public class KitapEkleActivity extends AppCompatActivity {
    ActivityKitapEkleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKitapEkleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onClickKaydet(View view) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String textKitapAdi = binding.editTextKitapAdi.getText().toString().trim();
        String textYazarAdi = binding.editTextYazarAdi.getText().toString();
        String textSayfaSayisi = binding.editTextSayfaSayisi.getText().toString();
        String textYayinevi = binding.editTextYayinevi.getText().toString();

        if (!textKitapAdi.isEmpty() && !textYazarAdi.isEmpty() && !textSayfaSayisi.isEmpty() && !textYayinevi.isEmpty()) {

            int SayfaSayisi = Integer.parseInt(textSayfaSayisi);

            Map<String, Object> kitaplar = new HashMap<>();
            kitaplar.put("KitapAdi", Objects.requireNonNull(textKitapAdi));
            kitaplar.put("YazarAdi", Objects.requireNonNull(textYazarAdi));
            kitaplar.put("SayfaSayısı", Objects.requireNonNull(SayfaSayisi));
            kitaplar.put("Yayınevi", Objects.requireNonNull(textYayinevi));
            db.collection("kitaplar")
                    .add(kitaplar).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(KitapEkleActivity.this, "Kitap Başarıyla Eklendi", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            binding.textViewUyari.setTextColor(Color.parseColor("#ff0000"));
                            binding.textViewUyari.setText("Kitaplar eklenirken bir hata oluştu");

                        }
                    });
        } else {
            binding.textViewUyari.setTextColor(Color.parseColor("#ff1744"));
            binding.textViewUyari.setText("Lütfen Boş Alanları Doldurunuz");
        }

    }
}