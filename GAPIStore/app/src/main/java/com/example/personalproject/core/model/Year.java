
package com.example.personalproject.core.model;

import java.util.List;

public class Year {

    private Integer from;
    private Integer to;
    private String filter;
    private Integer decade;
    private List<Year__1> years;
    private Boolean nofollow;
    private Integer count;

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getDecade() {
        return decade;
    }

    public void setDecade(Integer decade) {
        this.decade = decade;
    }

    public List<Year__1> getYears() {
        return years;
    }

    public void setYears(List<Year__1> years) {
        this.years = years;
    }

    public Boolean getNofollow() {
        return nofollow;
    }

    public void setNofollow(Boolean nofollow) {
        this.nofollow = nofollow;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
