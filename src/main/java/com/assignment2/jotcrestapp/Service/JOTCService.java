package com.assignment2.jotcrestapp.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.assignment2.jotcrestapp.Repository.JOTCRepository;
import com.assignment2.jotcrestapp.entity.JOTCRequest;
import com.assignment2.jotcrestapp.util.JOTCUtils;

@Service
public class JOTCService {

    private JOTCRepository repo;

    public JOTCService(JOTCRepository repo) {
        this.repo = repo;
    }
    @Transactional()
    public JOTCRequest processJOTCRequest(JOTCRequest request) {
        int[] cloudArr =  JOTCUtils.processClouds(request.getClouds());
        int jumps = JOTCUtils.jumpingOnClouds(cloudArr);
        request.setResult(jumps);
        return repo.save(request);
    }
    public List<JOTCRequest> getRequests() {
        return repo.findAll();
    }
    public List<JOTCRequest> getRequestsByUserName(String userName) {
        return repo.findByUserName(userName);
    }
    @Transactional()
    public void deleteRequestsByUserName(String userName) {
        repo.deleteByUserName(userName);
    }
    
    
}
