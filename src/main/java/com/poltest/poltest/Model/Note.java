package com.poltest.poltest.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String content;
    private Date initial;
    private Date modified;
    private int versionNumber;
    private boolean visible;

    public Note(String title, String content)
    {
        this.title = title;
        this.content = content;
        versionNumber = 1;
        visible = true;
    }

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

    public Date getInitial() {
        return initial;
    }

    public void setInitial(Date initial) {
        this.initial = initial;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

}
