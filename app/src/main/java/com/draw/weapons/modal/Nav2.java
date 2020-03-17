package com.draw.weapons.modal;

public class Nav2 extends Nav {
    boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Nav2(String name, String image) {
        super(name, image);
    }
}
