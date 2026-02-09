package lk.damithab.cnex.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lk.damithab.cnex.R;
import lk.damithab.cnex.adapter.HomeCategoryAdapter;
import lk.damithab.cnex.dto.CategoryDTO;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;

    private HomeCategoryAdapter categoryAdapter;

    private List<CategoryDTO> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /// Adding categories to home activity
        this.categoryRecyclerView = findViewById(R.id.home_categories_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
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

        /// Adding newArrivals product for home activity

    }
}