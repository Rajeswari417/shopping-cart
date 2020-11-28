package com.example.shoppingcart.presistance;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppingcart.utils.Recipe;

import java.util.List;

@Dao
public interface ShoppingCartDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipeList(List<Recipe> recipesList);

    @Query("SELECT * FROM shopping_recipe_info")
    LiveData<List<Recipe>> getRecipeListInfo();

    @Query("DELETE FROM shopping_recipe_info")
    void deleteRecipeListDetails();

}
