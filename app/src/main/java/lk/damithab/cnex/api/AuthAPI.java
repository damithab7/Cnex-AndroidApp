package lk.damithab.cnex.api;

import lk.damithab.cnex.dto.LoginRequestDTO;
import lk.damithab.cnex.dto.TokenDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPI {
    @POST("auth/refresh")
    Call<TokenDTO> refreshAccessToken(@Body TokenDTO tokenDTO);

    @POST("auth/login")
    Call<TokenDTO> userLogin(@Body LoginRequestDTO requestDTO);
}
