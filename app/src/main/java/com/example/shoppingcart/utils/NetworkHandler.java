package com.example.shoppingcart.utils;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkHandler {
    public static final String BASE_URL = "https://s3-ap-southeast-1.amazonaws.com/";
    public static NetworkHandler networkHandler;
    List<Recipe> recipeResponseList = new ArrayList<>();
    MutableLiveData<List<Recipe>> mutableRecipeLiveData = new MutableLiveData<>();


    public static NetworkHandler getInstance(Context context) {
        if (networkHandler == null) {
            networkHandler = new NetworkHandler();
        }
        return networkHandler;
    }

    public static RecipeApiService getRecipeApiService() {
        return RetrofitClient.getClient(BASE_URL).create(RecipeApiService.class);
    }


    public void getRecipeInfo(Context context) {
        Call<List<ResponseEntity>> call = getRecipeApiService().getRecipeList();
        call.enqueue(new Callback<List<ResponseEntity>>() {
            @Override
            public void onResponse(Call<List<ResponseEntity>> call, Response<List<ResponseEntity>> response) {
                List<ResponseEntity> responseEntityList = response.body();
                if (response.raw().code() == 200) {
                    for (ResponseEntity entity : responseEntityList) {
                        Recipe recipe = new Recipe(entity.getId(), entity.getName(), entity.getImage(), entity.getLabel(), entity.getPrice(), entity.getDescription(),0);
                        recipeResponseList.add(recipe);
                    }
                     mutableRecipeLiveData.postValue(recipeResponseList);
                }

            }

            @Override
            public void onFailure(Call<List<ResponseEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<List<Recipe>> getMutableCityHourlyWeatherLiveData() {
        return mutableRecipeLiveData;
    }

}
