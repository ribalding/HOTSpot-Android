package ryanharvey.randomheroesgame.Models;

import org.json.JSONObject;

/**
 * Created by Ryan on 11/3/2016.
 */
public class Hero {
    private String primaryName;
    private String attributeName;
    private String imageURL;
    private String group;
    private String subGroup;

    public Hero(String name, String imageURL, String attributeName, String group, String subGroup){
        this.primaryName = name;
        this.attributeName = attributeName;
        this.imageURL = imageURL;
        this.group = group;
        this.subGroup = subGroup;
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
}
