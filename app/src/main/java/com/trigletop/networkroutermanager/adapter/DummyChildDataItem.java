package com.trigletop.networkroutermanager.adapter;

import java.io.Serializable;

/**
 * <b></b>
 * <p>This class is used to </p>
 * Created by Rohit.
 */
public class DummyChildDataItem implements Serializable {
    private String childName;

    public DummyChildDataItem(String childName) {
        this.childName = childName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}
