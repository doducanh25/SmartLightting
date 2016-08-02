package com.example.arduinosensors;

/**
 * Created by user on 21/07/2016.
 */
public class Light {
    private String mNameLight;
    private int id;
    public Light(String name,int id) {
        super();
        this.mNameLight = name;
        this.id = id;
    }

    public String getmNameLight() {
        return mNameLight;
    }

    public void setmNameLight(String mNameLight) {
        this.mNameLight = mNameLight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
