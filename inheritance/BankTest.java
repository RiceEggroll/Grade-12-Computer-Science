package inheritance;

public class BankTest {
	public static void main(String[] args) {
		SavingsAccount acct1 = new SavingsAccount("John Smith");
		BankAccount acct2 = new SavingsAccount("Sandra Jones", 5200.00);
		ChequingAccount acct3 = new ChequingAccount("Amanda Peet", 511.32);
		
		BankAccount.listActNums();
		BankAccount.listActs();
		
		acct1.deposit(400.00);
		acct2.withdraw(344.33);

		System.out.println("\n*** Printing balance for account #3:");
		acct3.printBalance();

		acct2.transfer(acct1, 600.00);
		acct1.transfer(acct1, 150.00); //this will give an error message. You can't transfer money to yourself.

		int JSmith_number = BankAccount.getAccountNum("John Smith");
		acct2.transfer(JSmith_number, 50.00); //see if I can do a transfer based on bank account number rather than account object
		
		BankAccount.listActs();
		
		BankAccount.endOfMonth();

		acct3 = acct3.close();
		
		BankAccount.listActNums();
		BankAccount.listActs();
	
	}
}
