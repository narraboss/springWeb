package com.NarraBossWebsite.ItemFiles;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
  

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNo;
    private String customerId;
    private String balance;    
    private Date lastStatementDate;    
    private Date lastTransactionDate;
    private Date creationDate;


    // Accessors for the fields. JPA doesn't use these, but your application does.
    public Account(String accountNo,String customerId,String balance,
    		Date lastStatementDate,Date lastTransactionDate,
    		Date creationDate)  
    { 
        this.accountNo = accountNo;  
        this.customerId = customerId;  
        this.balance = balance;  
        this.lastTransactionDate=lastTransactionDate;
        this.lastStatementDate=lastStatementDate;
        this.creationDate=creationDate;
    }  

   
    public Long getId() {
		return id;
	}
	
    public String getAccountNo() {
        return accountNo;
    } 
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    } 

    public String getCustomerId() {
        return customerId;
    } 
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    } 

    public String getBalance() {
        return balance;
    } 
    public void setBalance(String balance) {
        this.balance = balance;
    } 
    
    public Date getLastStatementDate() {
        return lastStatementDate;
    } 
    public void setLastStatementDate(Date lastStatementDate) {
        this.lastStatementDate = lastStatementDate;
    } 
    public Date getLastTransactionDate() {
        return lastTransactionDate;
    } 
    public void setLastTransactionDate(Date lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }    
        
    public Date getCreationDate() {
        return creationDate;
    } 
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    } 
    
}


