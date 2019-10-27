package com.example.myapplication.Data;

public class FeedModel {
    public String title;
    public String link;
    public String pubDate;
    public String img;
    public String description;

    public FeedModel(String title, String link, String pubDate, String img, String description) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.img = img;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
