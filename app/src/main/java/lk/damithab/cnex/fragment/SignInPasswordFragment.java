package lk.damithab.cnex.fragment;

import static lk.damithab.cnex.util.RegexUtil.isPasswordValid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import lk.damithab.cnex.R;
import lk.damithab.cnex.activity.SignInActivity;
import lk.damithab.cnex.model.SignInViewModel;

public class SignInPasswordFragment extends Fragment {

    private EditText passwordInput;

    private Button continueToHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.passwordInput = view.findViewById(R.id.passwordSignInInput);

        this.continueToHome = view.findViewById(R.id.passwordContinueBtn);


        continueToHome.setOnClickListener(v -> {
            String password = passwordInput.getText().toString().trim();
            validatePassword(password);
        });

    }

    private void validatePassword(String password) {
        if (password.isEmpty()) {
            passwordInput.setError("Password is required.");
        } else if (password.length() < 8) {
            passwordInput.setError("Password must be at least 8 characters long.");
        } else if (!isPasswordValid(password)) {
            passwordInput.setError("Password must include at least one uppercase letter, one number, and one special character.");
        }else{
            SignInViewModel viewModel = new ViewModelProvider(requireActivity()).get(SignInViewModel.class);
            String email = viewModel.getEmail();
            ((SignInActivity) getActivity()).login(email, password);
        }
    }
}