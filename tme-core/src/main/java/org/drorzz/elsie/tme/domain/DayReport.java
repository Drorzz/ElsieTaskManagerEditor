package org.drorzz.elsie.tme.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 06.12.13
 * Time: 12:41
 */
@Entity
@Table(name = "day_reports")
@AttributeOverride(name="id",column=@Column(name="dayr_id"))
public class DayReport extends PersistentObject{
    private User user;//dayr_crew_id
    private Date date;//dayr_date
    private String projectText;//dayr_proj_text
    private String text;//dayr_text
    private int fullDate;//dayr_fdate

    @ManyToOne(fetch = FetchType.LAZY)
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
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "dayr_proj_text", nullable = true)
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
}
