package it.unisannio.service;

import it.unisannio.model.Account;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BranchLocal {
	public double totalAmount();
	public int createAccount(String cf, double a) throws Exception;
	public Account getAccount(int num);
	public List<Account> getAccounts(String cf);
	public void deposit(int num, double a) throws Exception; // Substitute with a more specific exception type
	public void withdraw(int num, double a) throws Exception;
}
