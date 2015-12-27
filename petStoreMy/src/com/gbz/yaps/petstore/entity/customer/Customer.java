package com.gbz.yaps.petstore.entity.customer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.*;

import com.gbz.yaps.petstore.entity.Address;
import com.gbz.yaps.petstore.exception.ValidationException;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity
@Table(name = "t_customer", schema="DBUSER")
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6306045129826695224L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true, nullable = false, length = 8)
	private String login;
	@Column(nullable = false, length = 8)
	private String password;
	@Column(nullable = false, length = 30)
	private String firstname;
	@Column(nullable = false, length = 30)
	private String lastname;
	private String telephone;
	private String email;
	@Column(name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	@Transient
	private Integer age;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_fk", nullable = true)
	private Address homeAddress;

	@PrePersist
	@PreUpdate
	private void validateData() {
		if (firstname == null || "".equals(firstname))
			 throw new ValidationException("Invalid first name");
			//System.out.println("Invalid first name");
		if (lastname == null || "".equals(lastname))
			 throw new ValidationException("Invalid last name");
			//System.out.println("Invalid last name");
		if (login == null || "".equals(login))
			 throw new ValidationException("Invalid login");
			//System.out.println("Invalid login");
		if (password == null || "".equals(password))
			 throw new ValidationException("Invalid password");
			//System.out.println("Invalid password");
	}

	@PostLoad
	@PostPersist
	@PostUpdate
	public void calculateAge() {
		if (dateOfBirth == null) {
			age = null;
			return;
		}
		Calendar birth = new GregorianCalendar();
		birth.setTime(dateOfBirth);
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());
		int adjust = 0;
		if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
			adjust = -1;
		}
		age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust;
	}

	public void matchPassword(String pwd) {

		if (pwd == null || "".equals(pwd))
			// throw new ValidationException("Invalid password");
			System.out.println("invalid password");
		if (!pwd.equals(password))
			// throw new ValidationException("Passwords don't match");
			System.out.println("passwords don't match");
	}
	// constructeurs, accesseurs
	// méthodes hashcode, equals et toString

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
}
