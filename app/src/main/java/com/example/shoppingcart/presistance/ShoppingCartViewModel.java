package com.example.shoppingcart.presistance;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingcart.repository.ShoppingRepository;
import com.example.shoppingcart.utils.Recipe;

import java.util.List;

public class ShoppingCartViewModel extends ViewModel {
    public static ShoppingRepository shoppingRepository;
    private Context context;

    public ShoppingCartViewModel() {
    }

    public ShoppingCartViewModel(Context context) {
        this.context = context;
        shoppingRepository = ShoppingRepository.getInstance(context);
    }

    //from service
    public void getRecipesListResponse() {
        shoppingRepository.getRecipesListResponseFromService(context);
    }

    //from Room

    public LiveData<List<Recipe>> getRecipesListInfo() {
       return shoppingRepository.getRecipeLiveDataResponseFromRoom();
    }
}
