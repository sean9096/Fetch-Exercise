package com.fetch.take_home.entities;

public class RewardPointsEntity {
    String id;
    Integer points;

    public RewardPointsEntity(String id, Integer points) {
        this.id = id;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "RewardPoints [id=" + id + ", points=" + points + "]";
    }

}
