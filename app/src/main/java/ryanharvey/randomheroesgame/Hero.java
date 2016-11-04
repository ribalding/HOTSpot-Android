package ryanharvey.randomheroesgame;

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
//
//    public static Hero createHeroFromJSONObject(JSONObject jsonObject){
//        try {
//            String name = jsonObject.getString("PrimaryName");
//            String imageURL = jsonObject.getString("ImageURL");
//            String attributeName = jsonObject.getString("AttributeName");
//            String group = jsonObject.getString("Group");
//            String subGroup = jsonObject.getString("SubGroup");
//            Hero newHero = new Hero(name, imageURL, attributeName, group, subGroup);
//            return newHero;
//        } catch (Exception e){
//        }
//
//    }
}
