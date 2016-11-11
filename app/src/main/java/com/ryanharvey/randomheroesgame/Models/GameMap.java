package com.ryanharvey.randomheroesgame.Models;

/**
 * Created by Ryan on 11/3/2016.
 */
public class GameMap {
    private String primaryName;
    private String imageURL;

    public GameMap(){}

    public GameMap(String primaryName, String imageURL){
        this.primaryName = primaryName;
        this.imageURL = "http://d1i1jxrdh2kvwy.cloudfront.net/Images/Maps/" + imageURL + ".png";
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }



}
