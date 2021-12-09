/**
 * 
 */
package com.assignment2.jotcrestapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment2.jotcrestapp.Service.JOTCService;
import com.assignment2.jotcrestapp.entity.JOTCRequest;

/**
 * Controller of jotc-restapp to expose API endpoints.
 *
 */
@RestController
@RequestMapping("jotc-app/v1")
public class JOTCController {
    
    private JOTCService service;
    public JOTCController(JOTCService service){
        this.service = service;
    }
    
    @PostMapping("/")
    public JOTCRequest processJOTCRequest(@RequestBody JOTCRequest request) {
        return service.processJOTCRequest(request);
    }
    
    @GetMapping("/requests")
    public List<JOTCRequest> getRequests() {
        return service.getRequests();
    }
    
    @GetMapping("/requests/{userName}")
    public List<JOTCRequest> getRequestsByUserName(@PathVariable String userName) {
        return service.getRequestsByUserName(userName);
    }
    
    @DeleteMapping("/requests/{userName}")
    public Object deleteRequestsByUserName(@PathVariable String userName) {
        service.deleteRequestsByUserName(userName);
        return ResponseEntity.accepted().build();
    }
}
