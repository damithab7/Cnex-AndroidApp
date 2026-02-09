package lk.damithab.cnex.activity;

import static lk.damithab.cnex.util.RegexUtil.isEmailValid;
import static lk.damithab.cnex.util.RegexUtil.isPasswordValid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Objects;

import lk.damithab.cnex.R;
import lk.damithab.cnex.adapter.SignInViewPagerAdapter;
import lk.damithab.cnex.api.AuthAPI;
import lk.damithab.cnex.client.RetrofitClient;
import lk.damithab.cnex.dto.LoginRequestDTO;
import lk.damithab.cnex.dto.TokenDTO;
import lk.damithab.cnex.fragment.SignInEmailFragment;
import lk.damithab.cnex.fragment.SignInPasswordFragment;
import lk.damithab.cnex.manager.TokenManager;
import lk.damithab.cnex.util.RegexUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {
    SharedPreferences mPrefs;
    private static final String welcomeScreenShownPref = "welcomeScreenShown",
            PREFERENCE_NAME = "welcome_screen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        /// Load fragments by fragment transactions
        loadFragments(new SignInEmailFragment(), false);


    }

    public void login(String email, String password) {

        Retrofit instance = RetrofitClient.getInstance(this);
        AuthAPI authAPI = instance.create(AuthAPI.class);

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(email, password);
        Call<TokenDTO> tokenDTOCall = authAPI.userLogin(loginRequestDTO);
        tokenDTOCall.enqueue(new Callback<TokenDTO>() {
            @Override
            public void onResponse(Call<TokenDTO> call, Response<TokenDTO> response) {
                if (response.isSuccessful()) {
                    TokenDTO tokenDTO = response.body();
                    if (tokenDTO != null) {
                        TokenManager.saveTokens(SignInActivity.this, tokenDTO);
                        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("userEmail", email);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(SignInActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenDTO> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void loadFragments(Fragment fragment, boolean addToStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                        R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment);

        if (addToStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        /// Welcome Screen Custom Dialog
        mPrefs = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

        boolean welcomeScreenShown = mPrefs.getBoolean(welcomeScreenShownPref, false);

        if (!welcomeScreenShown) {
            WelcomeDialog welcomeDialog = new WelcomeDialog();
            welcomeDialog.show(getSupportFragmentManager(), "welcome_dialog");

            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putBoolean(welcomeScreenShownPref, true);
            editor.commit();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}