package lk.damithab.cnex.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Objects;

import lk.damithab.cnex.R;
import lk.damithab.cnex.adapter.SignInViewPagerAdapter;
import lk.damithab.cnex.fragment.SignInEmailFragment;

public class SignInActivity extends AppCompatActivity {
    SharedPreferences mPrefs;
    private static final String welcomeScreenShownPref = "welcomeScreenShown",
            PREFERENCE_NAME = "welcome_screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        /// Load fragments by fragment transactions
       loadFragments(new SignInEmailFragment());

    }

    public void loadFragments(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                        R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
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