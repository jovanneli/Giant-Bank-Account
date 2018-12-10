
public abstract class BankAccount 
{
	//fields
	private static int nextAccNum;
	private String name;
	private int accNum;
	private double balance;
	
	//constructors
	public BankAccount(String name, double balance)
	{
		this.name=name;
		this.accNum=nextAccNum;
		this.balance=balance;
		nextAccNum++;
	}
	public BankAccount(String name)
	{
		this(name, 0);
	}
	
	//methods
	/**
	 * increases balance by amount deposited
	 * @param amt		amount of money that is deposited
	 */
	public void deposit(double amt)
	{
		balance += amt;
	}
	
	/**
	 * deceases balance by amount withdrawn
	 * @param amt		amount of money that is withdrawn
	 */
	public void withdraw(double amt)
	{
		balance -= amt;
	}
	
	/**
	 * returns the name on the account
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * returns the balance of the account
	 * @return balance
	 */
	public double getBalance()
	{
		return balance;
	}
	/**
	 * method to be implemented in subclasses
	 * @return
	 */
	public abstract double endOfMonthUpdate();
	
	/**
	 * withdraws money from this account and transfers it to another
	 * @param other		another bank account
	 * @param amt		amount of money transfered
	 */
	public void transfer(BankAccount other, double amt)
	{
		withdraw(amt);
		other.deposit(amt);
	}
	
	/**
	 * returns string representation of bank account
	 */
	public String toString()
	{
		return "\tAccount Number: " + accNum + "Name: " + name + "\tBalance: $" + balance;
	}
	
}
