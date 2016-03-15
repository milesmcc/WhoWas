package com.mdc.whowas;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Main on 3/14/16.
 */
public class Username {
    String name;
    long changedToAt;

    public Username(String name, long changedToAt){
        this.name = name;
        this.changedToAt = changedToAt;
    }

    public String getName(){
        return name;
    }

    public String getTime(){
        if(changedToAt == 0){
            return "original";
        }else{
            return new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss z").format(new Date(changedToAt));
        }
    }
}
