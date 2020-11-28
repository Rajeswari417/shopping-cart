package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart.presistance.ShoppingCartViewModel;
import com.example.shoppingcart.presistance.ShoppingCartViewModelFactory;
import com.example.shoppingcart.utils.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private Menu customMenu;
    private NotificationBadge badge;
    private ShoppingCartViewModelFactory shoppingCartViewModelFactory;
    private ShoppingCartViewModel shoppingCartViewModel;
    private List<Recipe> currentRecipesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeView = (SwipePlaceHolderView) findViewById(R.id.swipeView);
        mContext = getApplicationContext();
        shoppingCartViewModelFactory = new ShoppingCartViewModelFactory(getApplicationContext());
        shoppingCartViewModel = ViewModelProviders.of(this, shoppingCartViewModelFactory).get(ShoppingCartViewModel.class);
        shoppingCartViewModel.getRecipesListResponse();
        loadImages(currentRecipesList);
        shoppingCartViewModel.getRecipesListInfo().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                currentRecipesList.clear();
                currentRecipesList = recipes;
                loadImages(currentRecipesList);
            }
        });


        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.add_cart_swipe_view)
                        .setSwipeOutMsgLayoutId(R.layout.add_queue_swipe_view));

    }

    private void loadImages(List<Recipe> currentRecipesList) {
        if(!currentRecipesList.isEmpty()) {
            for (Recipe recipe : currentRecipesList) {
                mSwipeView.addView(new CardView(mContext, recipe, mSwipeView));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        View view = menu.findItem(R.id.action_cart).getActionView();
        return true;
    }

    private void updateCartCount() {
        if (badge == null) return;
        int i = 0;
        runOnUiThread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                if (i == 0) {
                    badge.setVisibility(View.INVISIBLE);
                } else {
                    badge.setVisibility(View.VISIBLE);
                    badge.setText(i + 1 + MainActivity.this.toString());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            updateCartCount();
            Intent intent = new Intent(this,CartActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


