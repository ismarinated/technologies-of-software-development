package com.example.coursePrj.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GenderTrain")
public class GenderTrain {
    @Id
    private Long customer_id;
    private int gender;

    public GenderTrain() {}

    public GenderTrain(Long customer_id, int gender) {
        this.customer_id = customer_id;
        this.gender = gender;
    }

    public Long getCustomerId(){
        return this.customer_id;
    }

    public int getGender(){
        return this.gender;
    }

    public void setCustomerId(Long customer_id){
        this.customer_id = customer_id;
    }

    public void setGender(int gender){
        this.gender = gender;
    }
}
