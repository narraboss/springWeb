
package com.NarraBossWebsite.ItemFiles;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customerId;
	private String accountNo;
	private Date dateOfTransaction;
	private String amount;
	private String toAccount;
	private String balance;
	private String toAccBalance;
	private String description;

	public Transaction(String customerId, String accountNo, Date dateOfTransaction, String toAccount, String amount,
			String balance, String toAccBalance, String description) {
		this.customerId = customerId;
		this.accountNo = accountNo;
		this.dateOfTransaction = dateOfTransaction;

		this.toAccount = toAccount;
		this.amount = amount;
		this.balance = balance;
		this.toAccBalance = toAccBalance;
		this.description = description;

	}

	public Long getId() {
		return id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getToAccBalance() {
		return toAccBalance;
	}

	public void setToAccBalance(String toAccBalance) {
		this.toAccBalance = toAccBalance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
