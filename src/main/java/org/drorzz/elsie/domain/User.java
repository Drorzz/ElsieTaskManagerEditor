package org.drorzz.elsie.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 06.12.13
 * Time: 11:40
 */
@Entity
@Table(name = "crew")
@AttributeOverride(name="id",column=@Column(name="crew_id"))
public class User extends PersistentObject{
	private String login;//crew_log;
	private String password;//crew_pass;
	private String email;//crew_email;
	private String firstName;//crew_fname;
	private String lastName;//crew_lname;
	private String position;//crew_position;
    private String fullName;

	private Department department;//crew_department;
	private User chief;//crew_depart_chief;
	private AccessLevel accessLevel;//crew_access_lvl;
	private boolean vacation;//crew_otpusk_maker;
	private boolean isActive;//crew_is_active;

    private List<User> usersList;
    private List<DayReport> dayReportsList;

	@NaturalId
	@Column(name="crew_log", unique = true, nullable = false)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name="crew_pass")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="crew_email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="crew_fname")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="crew_lname")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    @Formula("CONCAT(crew_lname,' ',crew_fname)")
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name="crew_position")
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="crew_department",referencedColumnName = "depart_id")
    @NotFound(action= NotFoundAction.IGNORE)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="crew_depart_chief", referencedColumnName = "crew_id")
    @NotFound(action= NotFoundAction.IGNORE)
	public User getChief() {
		return chief;
	}
	public void setChief(User chief) {
		this.chief = chief;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="crew_access_lvl")
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Column(name="crew_otpusk_maker")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean getVacation() {
		return vacation;
	}
	public void setVacation(boolean vacation) {
		this.vacation = vacation;
	}

	@Column(name="crew_is_active")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean getActive() {
		return isActive;
	}
	public void setActive(boolean active) {
		isActive = active;
	}

    @OneToMany(mappedBy = "chief", fetch = FetchType.LAZY)
    public List<User> getUsersList() {
        return usersList;
    }
    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<DayReport> getDayReportsList() {
        return dayReportsList;
    }
    public void setDayReportsList(List<DayReport> dayReportsList) {
        this.dayReportsList = dayReportsList;
    }
}
