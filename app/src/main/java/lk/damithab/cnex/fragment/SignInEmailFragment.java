package lk.damithab.cnex.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lk.damithab.cnex.R;
import lk.damithab.cnex.activity.SignInActivity;

public class SignInEmailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_email, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button continueBtn = view.findViewById(R.id.emailContinueBtn);

        continueBtn.setOnClickListener(V->{
            if(getActivity() != null){
                ((SignInActivity) getActivity()).loadFragments(new SignInPasswordFragment());
            }
        });
    }
}