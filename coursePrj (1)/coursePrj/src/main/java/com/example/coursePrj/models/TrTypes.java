package com.example.coursePrj.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TrTypes")
public class TrTypes {
    @Id
    private Long tr_type;
    private String tr_description;

    public TrTypes() {}

    public TrTypes(Long tr_type, String tr_description) {
        this.tr_type = tr_type;
        this.tr_description = tr_description;
    }

    public Long getTrType(){
        return this.tr_type;
    }

    public String getTrDescription(){
        return this.tr_description;
    }

    public void setTrType(Long tr_type){
        this.tr_type = tr_type;
    }

    public void setTrDescription(String tr_description){
        this.tr_description = tr_description;
    }
}
