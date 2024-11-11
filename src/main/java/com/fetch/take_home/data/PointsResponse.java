package com.fetch.take_home.data;

public class PointsResponse {
    Integer points;

    public PointsResponse(Integer points) {
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "PointsResponse [points=" + points + "]";
    }
}
