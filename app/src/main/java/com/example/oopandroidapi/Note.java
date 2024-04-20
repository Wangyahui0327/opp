package com.example.oopandroidapi;

public class Note {

    private String title;
    private String text;
    private String id;
    private boolean star;

    public Note(String title, String rem, boolean star) {
        this.title = title;
        this.text = rem;
        this.star = star;
        id = "Note" + (int)(Math.random() * 90000 + 1000);
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean getStar() {
        return star;
    }
    public String getId() {
        return id;
    }

}
