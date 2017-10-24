package com.example.mohamed.simpleconverter.Models;

/**
 * Created by Mohamed on 01/02/2017.
 */

public class Categories {
    private int Image;
    private int Category;

    public Categories(int Image, int Category) {
        this.Image = Image;
        this.Category = Category;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int Category) {
        this.Category = Category;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int Image) {
        this.Image = Image;
    }
}
