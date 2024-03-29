
package com.example.personalproject.model;

import java.util.List;

public class Result {

    private Integer id;
    private String slug;
    private String name;
    private String released;
    private Boolean tba;
    private String background_image;
    private Double rating;
    private Integer ratingTop;
    private List<Rating> ratings;
    private Integer ratings_count;
    private Integer reviews_text_count;
    private Integer added;
    private AddedByStatus added_by_status;
    private Integer metacritic;
    private Integer playtime;
    private Integer suggestionsCount;
    private String updated;
    private Object userGame;
    private Integer reviewsCount;
    private String saturatedColor;
    private String dominantColor;
    private List<Platform> platforms;
    private List<ParentPlatform> parentPlatforms;
    private List<Genre> genres;
    private List<Store> stores;
    private Object clip;
    private List<Tag> tags;
    private EsrbRating esrbRating;
    private List<ShortScreenshot> shortScreenshots;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Boolean getTba() {
        return tba;
    }

    public void setTba(Boolean tba) {
        this.tba = tba;
    }

    public String getBackgroundImage() {
        return background_image;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.background_image = backgroundImage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatingTop() {
        return ratingTop;
    }

    public void setRatingTop(Integer ratingTop) {
        this.ratingTop = ratingTop;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Integer getRatingsCount() {
        return ratings_count;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratings_count = ratingsCount;
    }

    public Integer getReviewsTextCount() {
        return reviews_text_count;
    }

    public void setReviewsTextCount(Integer reviewsTextCount) {
        this.reviews_text_count = reviewsTextCount;
    }

    public Integer getAdded() {
        return added;
    }

    public void setAdded(Integer added) {
        this.added = added;
    }

    public AddedByStatus getAddedByStatus() {
        return added_by_status;
    }

    public void setAddedByStatus(AddedByStatus addedByStatus) {
        this.added_by_status = addedByStatus;
    }

    public Integer getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(Integer metacritic) {
        this.metacritic = metacritic;
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    public Integer getSuggestionsCount() {
        return suggestionsCount;
    }

    public void setSuggestionsCount(Integer suggestionsCount) {
        this.suggestionsCount = suggestionsCount;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Object getUserGame() {
        return userGame;
    }

    public void setUserGame(Object userGame) {
        this.userGame = userGame;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getSaturatedColor() {
        return saturatedColor;
    }

    public void setSaturatedColor(String saturatedColor) {
        this.saturatedColor = saturatedColor;
    }

    public String getDominantColor() {
        return dominantColor;
    }

    public void setDominantColor(String dominantColor) {
        this.dominantColor = dominantColor;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<ParentPlatform> getParentPlatforms() {
        return parentPlatforms;
    }

    public void setParentPlatforms(List<ParentPlatform> parentPlatforms) {
        this.parentPlatforms = parentPlatforms;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public Object getClip() {
        return clip;
    }

    public void setClip(Object clip) {
        this.clip = clip;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public EsrbRating getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(EsrbRating esrbRating) {
        this.esrbRating = esrbRating;
    }

    public List<ShortScreenshot> getShortScreenshots() {
        return shortScreenshots;
    }

    public void setShortScreenshots(List<ShortScreenshot> shortScreenshots) {
        this.shortScreenshots = shortScreenshots;
    }

}
