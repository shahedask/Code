package com.shaheda.assignment.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IApi {

    @GET("api/breeds/image/random")
    public Call<ApiResponseObj> getImage();

}
