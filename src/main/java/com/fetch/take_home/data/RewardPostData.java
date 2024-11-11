package com.fetch.take_home.data;

import com.fetch.take_home.entities.RewardPointsEntity;

public class RewardPostData {

    RewardPointsEntity rewardPoints;

    String post;

    public RewardPostData(RewardPointsEntity rewardPoints, String post) {
        this.rewardPoints = rewardPoints;
        this.post = post;
    }

    public RewardPointsEntity getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(RewardPointsEntity rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "RewardPostData [rewardPoints=" + rewardPoints + ", post=" + post + "]";
    }
}
