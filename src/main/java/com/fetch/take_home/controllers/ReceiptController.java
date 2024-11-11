package com.fetch.take_home.controllers;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fetch.take_home.data.PointsResponse;
import com.fetch.take_home.data.Receipt;
import com.fetch.take_home.data.ReceiptResponse;
import com.fetch.take_home.services.ReceiptServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptServiceImpl receiptService;
    private static final Logger logger = Logger.getLogger(ReceiptController.class.getName());

    public ReceiptController(ReceiptServiceImpl receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<ReceiptResponse> processReciept(@RequestBody Receipt receipt) {
        try {
            logger.info("Executing /receipts/process");
            ReceiptResponse response = receiptService.processReciept(receipt);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Failed executing /receipts/process, Error: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<PointsResponse> getMethodName(@PathVariable String id) {
        try {
            logger.info("Executing /receipts/{id}/points");
            PointsResponse response = receiptService.getPoints(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Failed executing /receipts/{id}/points, Error: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}