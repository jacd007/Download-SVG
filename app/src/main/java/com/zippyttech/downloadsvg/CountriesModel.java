package com.zippyttech.downloadsvg;

public class CountriesModel {
    private int id;
    private String alpha2Code;
    private String code;
    private String icon;
    private String name;

    public CountriesModel() {
    }

    public CountriesModel(int id, String alpha2Code, String code, String icon, String name) {
        this.id = id;
        this.alpha2Code = alpha2Code;
        this.code = code;
        this.icon = icon;
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
