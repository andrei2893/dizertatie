package com.andrei.backend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Announcement {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Date date;
    @ManyToOne
    private User user;
    @ManyToMany(cascade=CascadeType.ALL)
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
