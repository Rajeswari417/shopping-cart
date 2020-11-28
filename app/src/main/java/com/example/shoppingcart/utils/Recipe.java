package com.example.shoppingcart.utils;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "shopping_recipe_info")
public class Recipe{
@PrimaryKey
        private int id;
        private String name;
        private String image;
        private String label;
        private String price;
        private String description;
        private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Recipe(int id, String name, String image, String label, String price, String description, int count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.label = label;
        this.price = price;
        this.description = description;
        this.count = count;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


