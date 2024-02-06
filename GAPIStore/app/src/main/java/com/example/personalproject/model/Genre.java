
package com.example.personalproject.model;


public class Genre {

    private Integer id;
    private String name;
    private String slug;
    private Integer gamesCount;
    private String imageBackground;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getGamesCount() {
        return gamesCount;
    }

    public void setGamesCount(Integer gamesCount) {
        this.gamesCount = gamesCount;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }

}
