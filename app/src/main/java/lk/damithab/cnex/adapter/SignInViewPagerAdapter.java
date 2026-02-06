package lk.damithab.cnex.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import lk.damithab.cnex.fragment.SignInEmailFragment;
import lk.damithab.cnex.fragment.SignInPasswordFragment;

public class SignInViewPagerAdapter extends FragmentStateAdapter {

    public SignInViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new SignInEmailFragment();
        }
        return new SignInPasswordFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

