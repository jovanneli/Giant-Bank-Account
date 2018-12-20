
public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BALANCE;
	private final double MIN_BALANCE_FEE;
	
	public SavingsAccount(String name, double balance, double intRate, double mb, double mbf)
	{
		super(name, balance);
		this.intRate = intRate;
		MIN_BALANCE = mb;
		MIN_BALANCE_FEE = mbf;
	}
	public SavingsAccount(String name, double intRate, double mb, double mbf)
	{
		this(name, 0, intRate, mb, mbf);
	}
	
	public void deposit(double amt)
	{
		if(amt>=0)
		{
			super.deposit(amt);
		}
		else
			throw new IllegalArgumentException();
	}
	
	public void withdraw(double amt)
	{
		if(amt >= 0 && getBalance() >= amt)
		{
			if(getBalance() - amt >= MIN_BALANCE)
				super.withdraw(amt);
			else
			{
				super.withdraw(amt);
				super.withdraw(MIN_BALANCE_FEE);
			}
		}
		else
			throw new IllegalArgumentException();
	}
	
	public void transfer(BankAccount other, double amt)
	{
		if(getName().equals(other.getName()))
		{
			if(amt >= 0 && getBalance() > amt)
			{
				super.transfer(other, amt);
			}
			else
				throw new IllegalArgumentException();
		}
		else
			throw new IllegalArgumentException();
	}
	
	public void addInterest()
	{
		super.deposit(getBalance()*intRate);
	}
	
	public void endOfMonthUpdate()
	{
		addInterest();
	}
}
