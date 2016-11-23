package com.ryanharvey.randomheroesgame.Models;

import com.ryanharvey.randomheroesgame.Constants.Constants;

/**
 * Created by Ryan on 11/3/2016.
 */
public class Hero {
    private String primaryName;
    private String attributeName;
    private String imageURL;
    private String group;
    private String subGroup;
    private boolean isSet;
    private String hotsLogsURI;

    public Hero(){}

    public Hero(String name, String imageURL, String attributeName, String group, String subGroup){
        this.primaryName = name;
        this.attributeName = attributeName;
        this.imageURL = "http://d1i1jxrdh2kvwy.cloudfront.net/Images/Heroes/Portraits/" + imageURL + ".png";
        this.group = group;
        this.subGroup = subGroup;
        this.hotsLogsURI = Constants.HOTSLOGS_HERO_BASE_URL + name;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public String getHotsLogsURI() {
        return hotsLogsURI;
    }

}
