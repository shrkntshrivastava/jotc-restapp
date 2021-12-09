package com.assignment2.jotcrestapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.assignment2.jotcrestapp.Repository.JOTCRepository;
import com.assignment2.jotcrestapp.Service.JOTCService;
import com.assignment2.jotcrestapp.entity.JOTCRequest;

@ExtendWith(MockitoExtension.class)
public class JOTCControllerTest {

    private JOTCController controller;
    @Mock
    private JOTCRepository repo;
    private JOTCRequest request;
    @BeforeEach
    public void setup() {
        controller = new JOTCController(new JOTCService(repo));
    }
    
    @Test
    public void processJOTCRequestTest_successscenario() {
        //Given
        request = new JOTCRequest("test", "0101010",0);
        Mockito.when(repo.save(request)).thenReturn( new JOTCRequest("test", "0101010",3));
        //When
        JOTCRequest reponse = null;
        try {
         reponse = controller.processJOTCRequest(request);}
        catch(Exception e) {
            e.printStackTrace();
        }
        //Then
        assertThat(reponse.getResult()).isEqualTo(3);
    }
    
    @Test
    public void processJOTCRequestTest_InvalidRequestString() {
        //Given
        request = new JOTCRequest("test", "01010a10",0);
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> controller.processJOTCRequest(request));
        //Then
        assertThat(ex.getMessage()).isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, request can contains only '0' and '1'\"");
    }
    
    @Test
    public void processJOTCRequestTest_InvalidSizelessThan2() {
        //Given
        request = new JOTCRequest("test", "0",0);
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> controller.processJOTCRequest(request));
        //Then
        assertThat(ex.getMessage()).isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, request's size must be greater that 2 and less than 100\"");
    }

    @Test
    public void processJOTCRequestTest_InvalidSizegreatethanThan100() {
        //Given
        request = new JOTCRequest("test", "01000001010100000101010000010101000001010100000101010000010101000001010100000101010000010101000001010",0);
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> controller.processJOTCRequest(request));
        //Then
        assertThat(ex.getMessage()).isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, request's size must be greater that 2 and less than 100\"");
    }
    
    @Test
    public void processJOTCRequestTest_InvalidRequestCloudArr() {
        //Given
        request = new JOTCRequest("test", "0101001",0);
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> controller.processJOTCRequest(request));
        //Then
        assertThat(ex.getMessage()).isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, first and last element of request must be 0\"");
    }
    
    @Test
    public void processJOTCRequestTest_InvalidRequestCloudArrConsecutive1() {
        //Given
        request = new JOTCRequest("test", "0101100",0);
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> controller.processJOTCRequest(request));
        //Then
        assertThat(ex.getMessage()).isEqualTo("400 BAD_REQUEST \"invalid input for JOTC, two consecutive element can not be 1\"");
    }
    
    @Test
    public void getRequestsTest_successscenario() {
        //Given
        request = new JOTCRequest("test", "0101010",3);
        List<JOTCRequest> list = new ArrayList<JOTCRequest>();
        list.add(request);
        Mockito.when(repo.findAll()).thenReturn(list);
        //When
        List<JOTCRequest> reponses = controller.getRequests();
        //Then
        assertThat(reponses.get(0).getResult()).isEqualTo(3);
    }
    @Test
    public void getRequestsByUserNameTest_successscenario() {
        //Given
        request = new JOTCRequest("test", "0101010",3);
        List<JOTCRequest> list = new ArrayList<JOTCRequest>();
        list.add(request);
        Mockito.when(repo.findByUserName("test")).thenReturn(list);
        //When
        List<JOTCRequest> reponses = controller.getRequestsByUserName("test");
        //Then
        assertThat(reponses.get(0).getUserName()).isEqualTo("test");
        assertThat(reponses.get(0).getResult()).isEqualTo(3);
    }
    
    @Test
    public void deleteRequestsByUserNameTest_successscenario() {
        //Given
        request = new JOTCRequest("test", "0101010",3);
        List<JOTCRequest> list = new ArrayList<JOTCRequest>();
        list.add(request);
        //When
        controller.deleteRequestsByUserName("test");
        //Then
        Mockito.verify(repo).deleteByUserName("test");
    }
}
