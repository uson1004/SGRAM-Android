package com.example.sgram.presentation.recycle;

public class RecyclerData {
    private int profileText;
    private String title;
    private String subText;

    public RecyclerData(int profileText, String title, String subText) {
        this.profileText = profileText;
        this.subText = subText;
        this.title = title;
    }

    public int getProfileText() {
        return profileText;
    }

    public void setProfileText(int profileText) {
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
