package com.example.shoppingcart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingcart.R;
import com.example.shoppingcart.utils.Recipe;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{
    private List<Recipe> selectedRecipeList = new ArrayList<>();
    private  Context mContext;

    public RecipeAdapter(List<Recipe> recipeList,Context context){
        this.selectedRecipeList = recipeList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.recipe_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = layoutInflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeViewHolder holder, int position) {
        String itemName = selectedRecipeList.get(position).getName();
        String itemPrice = selectedRecipeList.get(position).getPrice();
        String itemImage = selectedRecipeList.get(position).getImage();
        int value = selectedRecipeList.get(position).getCount();
        holder.mItemName.setText(itemName);
        holder.mItemPrce.setText(itemPrice);
        Glide.with(mContext).load(itemImage).into(holder.mItemImage);
    }



    @Override
    public int getItemCount() {
        if (null == selectedRecipeList) return 0;
        return selectedRecipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView mItemName;
        TextView mItemPrce;
        ImageView mItemImage;
        TextView mCount;
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemImage = itemView.findViewById(R.id.item_icon);
            mItemPrce = itemView.findViewById(R.id.item_price);
            mItemName = itemView.findViewById(R.id.item_name);
            mCount = itemView.findViewById(R.id.item_count);

        }
    }

    public void setRecipeList(List<Recipe> seletedRecipes) {
        selectedRecipeList = seletedRecipes;
        notifyDataSetChanged();
    }

}
