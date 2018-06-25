package com.example.android.on_lineschool;

/**
 * Created by a90ma on 1/10/2017.
 */

public class users {
    private String display_name;
    private String pic;
    private String type;


    public users() {
    }

    public users(String pic, String display_name, String type) {
        this.display_name = display_name;
        this.pic = pic;
        this.type = type;
    }



    public String getPic(){ return pic;}

    public void setPic (String pic){this.pic = pic;}


    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
