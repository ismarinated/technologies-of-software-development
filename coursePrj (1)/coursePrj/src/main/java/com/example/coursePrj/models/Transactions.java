package com.example.coursePrj.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    private Long customer_id;
    private String tr_datetime;
    private int mcc_code;
    private int tr_type;
    private int amount;
    private int term_id;

    public Transactions() {}

    public Transactions(Long customerId, String trDatetime, int mccCode, int trType, int amount, int termId){
        this.customer_id = customerId;
        this.tr_datetime = trDatetime;
        this.mcc_code = mccCode;
        this.tr_type = trType;
        this.amount = amount;
        this.term_id = termId;
    }

    public Long getCustomerId(){
        return this.customer_id;
    }

    public String getTrDatetime(){
        return this.tr_datetime;
    }

    public int getMccCode(){
        return this.mcc_code;
    }

    public int getTrType(){
        return this.tr_type;
    }

    public int getAmount(){
        return this.amount;
    }

    public int getTermId(){
        return this.term_id;
    }

    public void setCustomerId(Long customerId){
        this.customer_id = customerId;
    }

    public void setTrDatetime(String trDatetime){
        this.tr_datetime = trDatetime;
    }

    public void setMccCode(int mccCode){
        this.mcc_code = mccCode;
    }

    public void setTrType(int trType){
        this.tr_type = trType;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void setTermId(int termId){
        this.term_id = termId;
    }
}
