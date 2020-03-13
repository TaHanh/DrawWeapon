package com.draw.weapons.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Weapon implements Serializable {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("images")
    @Expose
    private List<String> images = null;

    public Weapon(String code, String name, String avatar, ArrayList<String> images){
        this.code = code;
        this.name = name;
        this.avatar = avatar;
        this.images = images;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", images=" + images.size() +
                '}';
    }
}
