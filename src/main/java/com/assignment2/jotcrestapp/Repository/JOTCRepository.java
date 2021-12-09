package com.assignment2.jotcrestapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment2.jotcrestapp.entity.JOTCRequest;

@Repository
public interface JOTCRepository extends JpaRepository<JOTCRequest, Long> {
    List<JOTCRequest> findByUserName(String userName);
    void deleteByUserName(String userName);
}
