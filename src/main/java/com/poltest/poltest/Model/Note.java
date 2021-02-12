package com.poltest.poltest.Model;

import javax.persistence.*;

@Entity
@Table(name = "Notes")
public class Note {

    private int id;
    private String title;
    private String content;
    private String date_initial;
    private String date_modified;
    private int versionNumber;
    private boolean visible;

    public Note(int id, String title, String content)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        versionNumber = 1;
        visible = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_initial() {
        return date_initial;
    }

    public void setDate_initial(String date_initial) {
        this.date_initial = date_initial;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

}
