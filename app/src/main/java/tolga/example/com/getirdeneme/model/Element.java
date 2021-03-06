package tolga.example.com.getirdeneme.model;


import android.graphics.Color;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Element {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("xPosition")
    @Expose
    private Integer xPosition;
    @SerializedName("yPosition")
    @Expose
    private Integer yPosition;
    @SerializedName("r")
    @Expose
    private Integer radius;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("color")
    @Expose
    private String color;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getXPosition() {
        return xPosition;
    }

    public void setXPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getYPosition() {
        return yPosition;
    }

    public void setYPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String colorString) {
        this.color = colorString;
    }


}