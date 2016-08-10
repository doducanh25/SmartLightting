package com.example.arduinosensors;

/**
 * Created by user on 10/08/2016.
 */
public class Status {

    private String id;
    private int valueDim;

    public Status(String id,int valueDim) {
        super();
        this.id = id;
        this.valueDim = valueDim;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValueDim() {
        return valueDim;
    }

    public void setValueDim(int valueDim) {
        this.valueDim = valueDim;
    }
}
