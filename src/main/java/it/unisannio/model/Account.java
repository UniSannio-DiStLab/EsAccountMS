package it.unisannio.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity implementation class for Entity: Account
 *
 */
@Entity
@NamedQueries ({
	@NamedQuery(name="Account.findAllPositiveAmounts", query = "SELECT a from Account a WHERE a.balance >0"),
	@NamedQuery(name="Account.findAllCustomerAccounts", query = "SELECT a from Account a WHERE a.customer = :customer")
})		
public class Account implements Serializable {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id private int number;
	private double balance;
	private Date lastModified;
	private String customer;
	private static final long serialVersionUID = 1L;

	public Account() {
		super();
	}   
	public Account(double a) {
		balance = a;
		lastModified = new Date();
	}
	public Account(String c, double a) {
		this(a);
		customer = c;
	}
	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
		this.lastModified = new Date();
	}
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	public void deposit(double amount) {
		balance += amount;
		this.lastModified = new Date();
	}
	public void withdraw(double amount) {
		balance -= amount;
		this.lastModified = new Date();
	}
	public void setLastModified(Date t) {
		lastModified = t;
	}
	public Date getLastModified() {
		return lastModified;
	}
}