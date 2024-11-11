package com.fetch.take_home.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fetch.take_home.data.PointsResponse;
import com.fetch.take_home.data.Receipt;
import com.fetch.take_home.data.ReceiptResponse;
import com.fetch.take_home.data.RewardPostData;
import com.fetch.take_home.utils.ReceiptProcessLogic;

@Service
public class ReceiptServiceImpl {

    public ReceiptResponse processReciept(Receipt receipt) throws IOException, ParseException {
        Integer points = 0;

        points += ReceiptProcessLogic.collectPoints(receipt);

        RewardPostData awards = ReceiptProcessLogic.createRewardPost(points);

        // This "Post" the data to local storage
        FileWriter writer = new FileWriter("recieptsDataBase.json");
        writer.write(awards.getPost());
        writer.close();

        return new ReceiptResponse(awards.getRewardPoints().getId());
    }

    /*
     * This function uses an object mapper to assist with the reading
     * of JSON objects from the local storage file
     */
    public PointsResponse getPoints(String id) throws IOException {
        File file = new File("recieptsDataBase.json");
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rewardsList = objectMapper.readTree(file);

        for (JsonNode rewards : rewardsList) {
            // TextNode needs to be cast back into a string using asText function
            if (((TextNode) (rewards.get("id"))).asText("").equals(id))
                return new PointsResponse(rewards.get("points").asInt());
        }

        return new PointsResponse(0); // In the case of a non-exsistent id we return 0
    }
}
