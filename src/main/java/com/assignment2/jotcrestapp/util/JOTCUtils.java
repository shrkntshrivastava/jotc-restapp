package com.assignment2.jotcrestapp.util;

import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JOTCUtils {

    public static final String REGEX = "[0-1]+";

    public static final int[] processClouds(String clouds) {
        if (validateCloudString(clouds)) {
            int[] cloudArr = Stream.of(clouds.split("")).mapToInt(Integer::parseInt).toArray();
            if (validateCloudArr(cloudArr)) {
                return cloudArr;
            }
        }
        return null;
    }

    public static final boolean validateCloudString(String clouds) {
        if (!clouds.matches(REGEX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "invalid input for JOTC, request can contains only '0' and '1'");
        }
        return true;
    }

    public static final boolean validateCloudArr(int[] cloudArr) {
        int size = cloudArr.length;
        if (size < 2 || size > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "invalid input for JOTC, request's size must be greater that 2 and less than 100");
        }
        if (cloudArr[0] == 1 || cloudArr[size - 1] == 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "invalid input for JOTC, first and last element of request must be 0");
        }
        int lastEle = 0;
        for (int current : cloudArr) {
            if (current == 1 && lastEle == 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "invalid input for JOTC, two consecutive element can not be 1");
            }
            lastEle = current;
        }
        return true;
    }

    public static final int jumpingOnClouds(int[] cloudArr) {
        int size = cloudArr.length;
        int jumpCount = 0;
        for (int i = 0; i < size - 1; i++, jumpCount++) {
            if (i + 2 < size && cloudArr[i + 2] == 0) {
                i++;
            }
        }
        return jumpCount;
    }
}
