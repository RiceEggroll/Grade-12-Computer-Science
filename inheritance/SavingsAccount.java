package inheritance;

class SavingsAccount extends BankAccount {
	
	SavingsAccount (String name) {
		super(name);
	}
	
	SavingsAccount(String name, double amount) {
		super(name, amount);
	}
	
	void savingsSetup() {
		intRate = 0.04;
		fee = 0;
		
	}
	
	SavingsAccount close() {
		super.close();
		return null;
	}
 }
