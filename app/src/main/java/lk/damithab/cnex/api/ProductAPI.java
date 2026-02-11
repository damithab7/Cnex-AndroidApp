package lk.damithab.cnex.api;

import java.util.List;

import lk.damithab.cnex.dto.ProductDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductAPI {
    @GET("/product/load-newarrivals")
    Call<List<ProductDTO>> getNewArrivals();
}
