package com.poltest.poltest.Model;

import javax.persistence.*;

@Entity
@Table(name = "Notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String date_initial;

    @Column
    private String date_modified;

    @Column
    private int version_number;

    @Column
    private boolean visibility;

    public Note(int id, String title, String content, String date_initial, String date_modified, int version_number)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date_initial = date_initial;
        this.date_modified = date_modified;
        this.version_number = version_number;
    }

    public Note(int id)
    {
        this.id = id;
    }

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

    public int getVersionNumber() {
        return version_number;
    }

    public void setVersionNumber(int version_number) {
        this.version_number = version_number;
    }

    public boolean isVisible() {
        return visibility;
    }

    public void setVisible(boolean visibility) {
        this.visibility = visibility;
    }

}
