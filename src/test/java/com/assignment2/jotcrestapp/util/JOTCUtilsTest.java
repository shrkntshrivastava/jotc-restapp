package com.assignment2.jotcrestapp.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class JOTCUtilsTest {

    @Test
    public void processCloudsTest_successscenario() {
        // Given
        String cloudString = "0101010";
        // When
        int[] cloudArr = JOTCUtils.processClouds(cloudString);
        // Then
        assertThat(cloudArr.length).isEqualTo(7);
    }

    @Test
    public void processCloudsTest_invalidcloudstring() {
        // Given
        String cloudString = "0101ds010";
        // When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> JOTCUtils.processClouds(cloudString));
        // Then
        assertThat(ex.getMessage())
                .isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, request can contains only '0' and '1'\"");
    }

    @Test
    public void validateCloudStringTest_successscenario() {
        // Given
        String cloudString = "0101010";
        // When
        boolean isValid = JOTCUtils.validateCloudString(cloudString);
        // Then
        assertThat(isValid).isEqualTo(true);
    }
    
    

    @Test
    public void validateCloudArrTest_successscenario() {
        // Given
        int[] cloudArr = new int[] { 0, 1, 0, 0, 1, 0 };
        // When
        boolean isValid = JOTCUtils.validateCloudArr(cloudArr);
        // Then
        assertThat(isValid).isEqualTo(true);
    }

    @Test
    public void validateCloudArrTest_invalidsizelessthan2() {
     // Given
        int[] cloudArr = new int[] { 0};
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> JOTCUtils.validateCloudArr(cloudArr));
        //Then
        assertThat(ex.getMessage()).isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, request's size must be greater that 2 and less than 100\"");
    }
    
    @Test
    public void validateCloudArrTest_invalidsizegreatethanthan100() {
     // Given
        int[] cloudArr = new int[] { 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 };
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> JOTCUtils.validateCloudArr(cloudArr));
        //Then
        assertThat(ex.getMessage()).isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, request's size must be greater that 2 and less than 100\"");
    }
    
    @Test
    public void validateCloudArrTest_invalidrequestcloudarr() {
     // Given
        int[] cloudArr = new int[] { 1, 1, 0, 0, 1, 0 };
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> JOTCUtils.validateCloudArr(cloudArr));
        //Then
        assertThat(ex.getMessage()).isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, first and last element of request must be 0\"");
    }
    
    @Test
    public void validateCloudArrTest_invalidrequestcloudarrconsecutive1() {
     // Given
        int[] cloudArr = new int[] { 0, 1, 0, 1, 1, 0 };
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> JOTCUtils.validateCloudArr(cloudArr));
        //Then
        assertThat(ex.getMessage()).isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, two consecutive element can not be 1\"");
    }
    
    @Test
    public void jumpingOnCloudsTest_successscenario() {
        // Given
        int[] cloudArr = new int[] { 0, 1, 0, 0, 1, 0 };
        // When
        int jumps = JOTCUtils.jumpingOnClouds(cloudArr);
        // Then
        assertThat(jumps).isEqualTo(3);
    }
}
