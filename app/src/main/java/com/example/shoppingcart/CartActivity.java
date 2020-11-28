package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shoppingcart.adapter.RecipeAdapter;
import com.example.shoppingcart.utils.Recipe;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recipeRecyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> seletedRecipes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        seletedRecipes = CardView.seletedRecipesInfo;
        recipeRecyclerView = findViewById(R.id.recipe_list_recyclerview);
        recipeAdapter = new RecipeAdapter(seletedRecipes,getApplicationContext());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recipeRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplication(), layoutManager.VERTICAL);
        recipeRecyclerView.addItemDecoration(itemDecor);
        recipeRecyclerView.setHasFixedSize(true);
        recipeRecyclerView.setAdapter(recipeAdapter);
        bindAdapter(seletedRecipes);

    }

    public void bindAdapter(List<Recipe> recipeList) {
        recipeAdapter.setRecipeList(recipeList);

    }
}
