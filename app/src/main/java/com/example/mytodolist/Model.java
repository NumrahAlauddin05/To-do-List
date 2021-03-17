package com.example.mytodolist;

public class Model {
    private String text,description,id;
    int image;

    public Model() {
    }

    public Model(String text, String description, String id, int image) {
        this.text = text;
        this.description = description;
        this.id = id;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
