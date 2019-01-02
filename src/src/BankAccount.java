public abstract class BankAccount 
{
	//fields
	private String name;
	private int accountNumber;
	private double balance;
	private static int nextAccNum;
	
	//constructors
	public BankAccount(String name, double balance)
	{
		this.name=name;
		this.balance=balance;
		accountNumber = nextAccNum;
		nextAccNum++;
	}
	public BankAccount(String name)
	{
		this(name, 0);
	}
	
	//methods
	/**
	 * increases balance by amount deposited
	 * @param amt	amount deposited
	 */
	public void deposit(double amt)
	{
		balance += amt;
	}
	/**
	 * decreases balance by amount withdrawn
	 * @param amt	amount withdrawn
	 */
	public void withdraw(double amt)
	{
		balance -=amt;
	}
	/**
	 * withdraws money from this account and deposits it into another account
	 * @param other		second account/target account
	 * @param amt		amount transferred
	 */
	public void transfer(BankAccount other, double amt)
	{
		withdraw(amt);
		other.deposit(amt);
	}
	/**
	 * returns name of the account
	 * @return	name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * returns the account number
	 * @return		account number
	 */
	public int getAccountNumber()
	{
		return accountNumber;
	}
	/**
	 * returns balance of the account
	 * @return	balance
	 */
	public double getBalance()
	{
		return balance;
	}
	/**
	 * returns the account in a string format
	 */
	public String toString()
	{
		return "Account Number: " + accountNumber + "\nName: " + name + "\nBalance: " + balance;
	}
	/**
	 * to be implemented in sub classes
	 */
	public abstract void endOfMonthUpdate();
}
