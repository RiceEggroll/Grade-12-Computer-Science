package inheritance;

import java.util.ArrayList;

abstract class BankAccount {
	
	static final int transitNum = 6969;
	
	protected int balance, accountNum, fee; 
	String name;
	double intRate;
	
    static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
    
    BankAccount(String name) {
    	this(name,0.00);
    }
    
    BankAccount(String name, double amount){
    	this.name = name;
    	this.balance = convert(amount);
    	this.accountNum = makeAcctNum();
    }
    
    int makeAcctNum() {
    	return transitNum + (int) (Math.random()*89999+10000);
    }
    
    static void listActNums() {
    	
    }
    
    static void listActs() {
    	
    }
    
    int getAccountNum() {
    	return accountNum;
    }
    
    void printBalance() {
    	System.out.println(convert(balance));
    }
    
    void deposit(double amount) {
    	balance += convert(amount);
        info("Deposit $"+amount);
    }
    
    void withdraw(double amount) {
    	if(convert(amount) < balance) balance -= convert(amount);
        else {
            System.out.println("Withdraws over the account balance are not permitted");
            return;
        } 
        info("Withdraw $"+amount);
    }

    void transfer(BankAccount account, double amount) {
    	if(this.equals(account)) {
            System.out.println("Transfers to the same account are not permitted");
            return;
        }
        if(convert(amount) > balance) {
            System.out.println("Transfers over the account balance are not permitted");
            return;
        }
        
        info("Transfer $"+ amount + "to account number "+ account.accountNum +" ("+name+")");
        balance =- convert(amount);
        account.balance += convert(amount);
    }
    
    void transfer(int accountNum, double amount) {
        for(BankAccount i : accounts) {
            if(i.accountNum == accountNum) {
                transfer(i, amount);
                return;
            }
        }

        System.out.println("Transfers must be directed towards a valid account");
    }

    static void endOfMonth() {
    	for (BankAccount i : accounts) {
    		i.balance += i.balance*i.intRate;
    	}
    	
    }
    
    BankAccount close() {
    	if (this.balance > 0) {
    		System.out.println("Thank you for your donation of $"+ convert(balance));
    	}
    	accounts.remove(this);
    	return null; 
    }
    
    void info(String variation) {
        System.out.println("Transaction info for "+name+" ("+accountNum+")");
        System.out.println(variation);
        System.out.println("Balance = $"+balance);
    }

    //converts dollars to cents and vice versa (yes I'm that lazy)
    static int convert(double in) {
        return (int)(in*100);
    }

    static double convert(int in) {
        return (int)in/100;
    }
}