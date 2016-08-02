package com.example.arduinosensors;

/**
 * Created by user on 19/07/2016.
 */
public class Scripts {
    private String mNameScript;
    private int id;

    private boolean isSelected = false;
    public Scripts(String name,int id) {
        super();
        this.mNameScript = name;
        this.id = id;
    }

    public String getmNameScript() {
        return mNameScript;
    }

    public void setmNameScript(String mNameScript) {
        this.mNameScript = mNameScript;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public void toggleSelected () {
        this.isSelected = !isSelected;
    }

}
