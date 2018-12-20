
public abstract class BankAccount 
{
	private String name;
	private int accountNumber;
	private double balance;
	private static int nextAccNum;
	
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
	
	public void deposit(double amt)
	{
		balance += amt;
	}
	
	public void withdraw(double amt)
	{
		balance -=amt;
	}
	
	public void transfer(BankAccount other, double amt)
	{
		withdraw(amt);
		other.deposit(amt);
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public String toString()
	{
		return "Account Number: " + accountNumber + "\nName: " + name + "\nBalance: " + balance;
	}
	
	public abstract void endOfMonthUpdate();
}
