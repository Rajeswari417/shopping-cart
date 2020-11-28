package com.example.shoppingcart.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.shoppingcart.presistance.ShoppingCartDao;

import com.example.shoppingcart.presistance.ShoppingCartRoomDB;
import com.example.shoppingcart.utils.NetworkHandler;
import com.example.shoppingcart.utils.Recipe;

import java.util.List;

public class ShoppingRepository {

    public static ShoppingRepository shoppingRepository;
    private NetworkHandler networkHandler;
    private ShoppingCartDao shoppingCartDao;
    private ShoppingCartRoomDB shoppingRoomDB;

    public static ShoppingRepository getInstance(Context context) {
        if (shoppingRepository == null) {
            return shoppingRepository = new ShoppingRepository(context);
        } else {
            return shoppingRepository;
        }
    }

    public ShoppingRepository(Context context) {
        shoppingRoomDB = ShoppingCartRoomDB.getInstance(context);
        shoppingCartDao = shoppingRoomDB.shoppingCartDao();
        networkHandler = NetworkHandler.getInstance(context);

      //  inserting country details into room database in county table
        networkHandler.getMutableCityHourlyWeatherLiveData().observeForever(recipeResponce ->{
            shoppingRoomDB.databaseWriteExecutor.execute(()->{
                shoppingCartDao.deleteRecipeListDetails();
                shoppingCartDao.insertRecipeList(recipeResponce);
            });
        });
    }

   // Response from serive
    public void getRecipesListResponseFromService(Context context) {
        networkHandler.getRecipeInfo(context);
    }

    // resposnse from Room
    public LiveData<List<Recipe>> getRecipeLiveDataResponseFromRoom() {
        return shoppingCartDao.getRecipeListInfo();
    }

}
