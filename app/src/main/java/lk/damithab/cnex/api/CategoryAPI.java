package lk.damithab.cnex.api;

import java.util.List;

import lk.damithab.cnex.dto.CategoryDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPI {
    @GET("category/get-all")
    Call<List<CategoryDTO>> getAllCategories();
}
