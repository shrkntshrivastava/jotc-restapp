package com.assignment2.jotcrestapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="JOTCRequest")
public class JOTCRequest {
    public JOTCRequest(String userName, String clouds, int result) {
        this.userName=userName;
        this.clouds=clouds;
        this.result=result;
    }
    @JsonIgnore
    @GeneratedValue
    @Id
    private long id;
    @Column
    private String userName;
    @Column
    private String clouds;  
    @Column
    @JsonProperty("JumnpsCount")
    private int result;
 }
