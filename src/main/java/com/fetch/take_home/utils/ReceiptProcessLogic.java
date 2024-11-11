package com.fetch.take_home.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fetch.take_home.data.Item;
import com.fetch.take_home.data.Receipt;
import com.fetch.take_home.data.RewardPostData;
import com.fetch.take_home.entities.RewardPointsEntity;

public class ReceiptProcessLogic {

    /*
     * Function uses regular expressions to count
     * the number of alphanumeric characters in retailer variable
     */
    public static Integer alphaNumericRule(Receipt receipt) {
        Integer points = 0;
        Matcher matcher = Pattern.compile("[a-z0-9]", Pattern.CASE_INSENSITIVE).matcher(receipt.getRetailer());

        while (matcher.find())
            points++;

        return points;
    }

    /*
     * Total is split after the decimal to check if it is a
     * round dollar amount
     */
    public static Integer roundDollarRule(Receipt receipt) {
        return (receipt.getTotal().split("\\.")[1].equals("00")) ? 50 : 0;
    }

    public static Integer multipleRule(Receipt receipt) {
        return (Double.valueOf(receipt.getTotal()) % 0.25 == 0) ? 25 : 0;
    }

    public static Integer twoItemsRule(Receipt receipt) {
        return (receipt.getItems().length / 2) * 5;
    }

    public static Integer trimmedLengthRule(Receipt receipt) {
        Integer points = 0;

        for (Item i : receipt.getItems())
            if (i.getShortDescription().trim().length() % 3 == 0)
                points += (int) Math.ceil(Double.valueOf(i.getPrice()) * 0.2); // Ceiling is used to round the points
                                                                               // amount upward

        return points;
    }

    /*
     * Split is used to divide date into 3 seperate strings after the delimiter: '-'
     * then we acces third element in the new array to get the day
     */
    public static Integer oddDateRule(Receipt receipt) {
        return (Integer.valueOf(receipt.getPurchaseDate().split("-")[2]) % 2 != 0) ? 6 : 0;
    }

    /*
     * Here we set the time using the date class and then just used the
     * before and after functions to check for time
     */
    public static Integer timeRule(Receipt receipt) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        Date time = dateFormat.parse(receipt.getPurchaseTime());
        Date afterTime = dateFormat.parse("14:00");
        Date beforeTime = dateFormat.parse("16:00");

        return (time.before(beforeTime) && time.after(afterTime)) ? 10 : 0;
    }

    public static Integer collectPoints(Receipt receipt) throws ParseException {
        Integer points = 0;

        points += alphaNumericRule(receipt);

        points += roundDollarRule(receipt);

        points += multipleRule(receipt);

        points += twoItemsRule(receipt);

        points += trimmedLengthRule(receipt);

        points += oddDateRule(receipt);

        points += timeRule(receipt);

        return points;
    }

    /*
     * Function creates a new string to overwrite the file with including the new
     * entity, then returns the response for the user as well as the new string to
     * write
     * to the file containg our local data
     */
    public static RewardPostData createRewardPost(Integer points) throws IOException {
        String post = "[";

        File file = new File("recieptsDataBase.json");
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rewardsList = objectMapper.readTree(file);

        /*
         * This function uses the same logic as the getPoints function in
         * ReceiptServiceImpl.java
         * for reading and writing to json files
         */

        for (JsonNode rewards : rewardsList) {
            post += rewards.toString() + ",";
        }

        RewardPointsEntity rewardPoints = new RewardPointsEntity(generateUniqueId(rewardsList), points);

        post += objectMapper.writeValueAsString(rewardPoints) + "]"; // Turns entity into json object to be written in
                                                                     // json file

        return new RewardPostData(rewardPoints, post);
    }

    /*
     * We turn the JsonNode into a String list
     * to easily asses if it contains the id already
     * if so a new one is given
     */
    public static String generateUniqueId(JsonNode node) {
        String id = UUID.randomUUID().toString();
        List<String> idList = new ArrayList<>();

        node.forEach(reward -> idList.add(((TextNode) (reward.get("id"))).asText("")));

        while (idList.contains(id)) {
            id = UUID.randomUUID().toString();
        }

        return id;
    }
}
