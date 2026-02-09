package lk.damithab.cnex.fragment;

import static lk.damithab.cnex.util.RegexUtil.isEmailValid;

import android.content.Intent;
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
import android.widget.TextView;

import lk.damithab.cnex.R;
import lk.damithab.cnex.activity.SignInActivity;
import lk.damithab.cnex.activity.SignUpActivity;
import lk.damithab.cnex.model.SignInViewModel;

public class SignInEmailFragment extends Fragment {
    private Button continueToPassword;
    private EditText emailInput;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_email, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView signUpLink = view.findViewById(R.id.sign_up_link_view);
        this.continueToPassword = view.findViewById(R.id.emailContinueBtn);
        this.emailInput = view.findViewById(R.id.emailSignInInput);

        signUpLink.setOnClickListener(v->{
            Intent intent = new Intent(view.getContext(), SignUpActivity.class);
            startActivity(intent);
        });

        continueToPassword.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            validateEmail(email);
        });

    }
    private void validateEmail(String email) {
        if (email.isEmpty()) {
            emailInput.setError("Email address is required.");
        } else if (!isEmailValid(email)) {
            emailInput.setError("Invalid email format. Please use the format: name@example.com.");
        } else {
            SignInViewModel viewModel = new ViewModelProvider(requireActivity()).get(SignInViewModel.class);
            viewModel.setEmail(email);
            if(getActivity() != null){
                ((SignInActivity) getActivity()).loadFragments(new SignInPasswordFragment(), true);
            }
        }
    }
}