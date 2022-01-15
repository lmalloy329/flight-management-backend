package com.lauren.springboot.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




@Entity
@Table(name ="customers", uniqueConstraints= {@UniqueConstraint(columnNames="email")})
public class Customer {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(max = 50)
	@Email
	@Column(name ="username")
	private String username;
	
	@NotBlank
	@Size(max = 20)
	@Column(name ="first_name")
	private String firstName;
	
	@NotBlank
	@Size(max = 20)
	@Column(name ="middle_name")
	private String middleName;
	
	@NotBlank
	@Size(max = 20)@Column(name ="last_name")
	private String lastName;

	@NotBlank
	@Size(max = 11, min=10)
	@Column(name="phone_number")
	private String phoneNumber;
	
	@NotBlank
	@Size(max = 50)
	@Email
	@Column(name ="email")
	private String email;
	
	@NotBlank
	@Size(max = 50)
	@Column(name ="address")
	private String address;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@NotBlank
	@Size(max = 20)
	@Column(name ="city")
	private String city;
	
	@NotBlank
	@Size(max = 20)
	@Column(name ="state")
	private String state;
	
	@NotBlank
	@Size(max = 20)
	@Column(name ="country")
	private String country;
	
	@NotBlank
	@Size(max = 200)
	@Column(name="password")
	private String password;
	
	
	@OneToMany(mappedBy="customer")
	private Set<Reservation> reservations;
	


	
	public Customer(@NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String middleName,
			@NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 11, min = 10) String phoneNumber,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 50) String address, @NotBlank @Size(max = 20) String city, @NotBlank @Size(max = 20) String state,
			@NotBlank @Size(max = 20) String country, @NotBlank @Size(max = 200) String password) {
		super();
		this.username= email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer() {	
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

		public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		this.username=email;
	}
	public String getUsername() {
		return this.username;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	

}
