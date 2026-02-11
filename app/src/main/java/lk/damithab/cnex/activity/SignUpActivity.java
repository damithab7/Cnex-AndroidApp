package lk.damithab.cnex.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import lk.damithab.cnex.R;

public class SignUpActivity extends AppCompatActivity {
    private Button sign_up_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        this.sign_up_back = findViewById(R.id.sign_up_back_btn);
        sign_up_back.setOnClickListener(v->{
            finish();
        });
    }
}