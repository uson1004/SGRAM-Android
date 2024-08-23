package com.example.sgram.data.recycle;

public class RecyclerData {
    private String profileText;
    private String title;
    private String subText;

    public RecyclerData(String profileText, String title, String subText) {
        this.profileText = profileText;
        this.subText = subText;
        this.title = title;
    }

    public String getProfileText() {
        return profileText;
    }

    public void setProfileText(String profileText) {
        this.profileText = profileText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }
}
