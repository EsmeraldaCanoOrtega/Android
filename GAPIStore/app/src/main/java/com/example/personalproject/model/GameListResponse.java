
package com.example.personalproject.model;

import java.util.List;

public class GameListResponse {

    private Integer count;
    private String next;
    private Object previous;
    private List<Result> results;
    private String seoTitle;
    private String seoDescription;
    private String seoKeywords;
    private String seoH1;
    private Boolean noindex;
    private Boolean nofollow;
    private String description;
    private Filters filters;
    private List<String> nofollowCollections;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoH1() {
        return seoH1;
    }

    public void setSeoH1(String seoH1) {
        this.seoH1 = seoH1;
    }

    public Boolean getNoindex() {
        return noindex;
    }

    public void setNoindex(Boolean noindex) {
        this.noindex = noindex;
    }

    public Boolean getNofollow() {
        return nofollow;
    }

    public void setNofollow(Boolean nofollow) {
        this.nofollow = nofollow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public List<String> getNofollowCollections() {
        return nofollowCollections;
    }

    public void setNofollowCollections(List<String> nofollowCollections) {
        this.nofollowCollections = nofollowCollections;
    }

}
