package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
//@NamedQuery(name = "User.getAll", query = "select login from Users")
public class UserModel {
	@Id
	@GeneratedValue
	private int id;
	private String login;
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	private String email;
//	private String regionID;
//	private String roleID;
//	private String customerTypeID;
	private String RegionRef;
	private String RoleRef;
	private String CustomerTypeRef;
	private String balance;

	public UserModel() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getRegionID() {
		return RegionRef;
	}

	public void setRegionID(final String regionID) {
		this.RegionRef = regionID;
	}

	public String getRoleID() {
		return RoleRef;
	}

	public void setRoleID(final String roleID) {
		this.RoleRef = roleID;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getCustomerTypeID() {
		return CustomerTypeRef;
	}

	public void setCustomerTypeID(final String customerTypeID) {
		this.CustomerTypeRef = customerTypeID;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(final String balance) {
		this.balance = balance;
	}
}
