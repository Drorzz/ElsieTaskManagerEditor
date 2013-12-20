package org.drorzz.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 06.12.13
 * Time: 12:41
 */
@Entity
@Table(name = "day_reports")
public class DayReport {
    private Integer id;//dayr_id
    private User user;//dayr_crew_id
    private Calendar date;//dayr_date
    private String projectText;//dayr_proj_text
    private String text;//dayr_text
    private int fullDate;//dayr_fdate

    @Id
    @Column(name = "dayr_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne()
    @JoinColumn(name = "dayr_crew_id")
    @NotFound(action = NotFoundAction.IGNORE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "dayr_date")
    @Temporal(value=TemporalType.DATE)
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Column(name = "dayr_proj_text")
    @Type(type="text")
    public String getProjectText() {
        return projectText;
    }

    public void setProjectText(String projectText) {
        this.projectText = projectText;
    }

    @Column(name = "dayr_text")
    @Type(type="text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "dayr_fdate")
    public int getFullDate() {
        return fullDate;
    }

    public void setFullDate(int fullDate) {
        this.fullDate = fullDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DayReport)){return false;}
        return ((DayReport)obj).getId()==this.getId();
    }
}
