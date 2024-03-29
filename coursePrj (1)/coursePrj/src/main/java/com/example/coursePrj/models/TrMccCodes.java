package com.example.coursePrj.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TrMccCodes")
public class TrMccCodes {
    @Id
    private Long mcc_code;
    private String mcc_description;

    public TrMccCodes() {}

    public TrMccCodes(Long mcc_code, String mcc_description) {
        this.mcc_code = mcc_code;
        this.mcc_description = mcc_description;
    }

    public Long getMccCode(){
        return this.mcc_code;
    }

    public String getMccDescription(){
        return this.mcc_description;
    }

    public void setMccCode(Long mcc_code){
        this.mcc_code = mcc_code;
    }

    public void setMccDescription(String mcc_description){
        this.mcc_description = mcc_description;
    }
}
