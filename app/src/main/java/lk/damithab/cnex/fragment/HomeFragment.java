package lk.damithab.cnex.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lk.damithab.cnex.R;
import lk.damithab.cnex.adapter.HomeCategoryAdapter;
import lk.damithab.cnex.dto.CategoryDTO;

public class HomeFragment extends Fragment {
    private RecyclerView categoryRecyclerView;

    private HomeCategoryAdapter categoryAdapter;

    private List<CategoryDTO> categoryList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.categoryRecyclerView = view.findViewById(R.id.home_categories_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        /// Dump categories for testing
        categoryList = new ArrayList<CategoryDTO>();
        categoryList.add(new CategoryDTO(1, "Dress"));
        categoryList.add(new CategoryDTO(2, "Glasses"));
        categoryList.add(new CategoryDTO(3, "Hats"));
        categoryList.add(new CategoryDTO(4, "Hoodie"));
        categoryList.add(new CategoryDTO(5, "Jeans"));
        this.categoryAdapter = new HomeCategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }
}