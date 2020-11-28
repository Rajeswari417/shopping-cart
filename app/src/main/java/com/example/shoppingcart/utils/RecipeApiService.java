package com.example.shoppingcart.utils;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApiService {

    @GET("he-public-data/reciped9d7b8c.json")
    Call<List<ResponseEntity>> getRecipeList();



}

