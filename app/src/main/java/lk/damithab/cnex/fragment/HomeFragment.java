package lk.damithab.cnex.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lk.damithab.cnex.R;
import lk.damithab.cnex.adapter.HomeCategoryAdapter;
import lk.damithab.cnex.api.CategoryAPI;
import lk.damithab.cnex.client.RetrofitClient;
import lk.damithab.cnex.dto.CategoryDTO;
import lk.damithab.cnex.dto.LoginRequestDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        loadAllCategories(view.getContext());
    }

    private void loadNewArrivals(Context context){
        Retrofit retrofit = RetrofitClient.getInstance(context);
        CategoryAPI categoryAPI =retrofit.create(CategoryAPI.class);
        Call<List<CategoryDTO>> listCall = categoryAPI.getAllCategories();
        listCall.enqueue(new Callback<List<CategoryDTO>>() {
            @Override
            public void onResponse(Call<List<CategoryDTO>> call, Response<List<CategoryDTO>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();
                    if(categoryList != null && !categoryList.isEmpty()){
                        categoryAdapter = new HomeCategoryAdapter(categoryList);
                        categoryRecyclerView.setAdapter(categoryAdapter);
                    }
                }else{
                    Log.w("HomeFragment", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<CategoryDTO>> call, Throwable t) {

            }
        });
    }

    private void loadAllCategories(Context context){
        Retrofit retrofit = RetrofitClient.getInstance(context);
        CategoryAPI categoryAPI =retrofit.create(CategoryAPI.class);
        Call<List<CategoryDTO>> listCall = categoryAPI.getAllCategories();
        listCall.enqueue(new Callback<List<CategoryDTO>>() {
            @Override
            public void onResponse(Call<List<CategoryDTO>> call, Response<List<CategoryDTO>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();
                    if(categoryList != null && !categoryList.isEmpty()){
                       categoryAdapter = new HomeCategoryAdapter(categoryList);
                       categoryRecyclerView.setAdapter(categoryAdapter);
                    }
                }else{
                    Log.w("HomeFragment", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<CategoryDTO>> call, Throwable t) {

            }
        });
    }
}