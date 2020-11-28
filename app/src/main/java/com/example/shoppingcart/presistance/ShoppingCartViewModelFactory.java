package com.example.shoppingcart.presistance;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ShoppingCartViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return(T) new ShoppingCartViewModel(context);
    }

    public ShoppingCartViewModelFactory(Context context){
        this.context = context;
    }
}
