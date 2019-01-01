
public class SavingsAccount extends BankAccount
{
	//fields
	private double intRate;
	private final double MIN_BALANCE;
	private final double MIN_BALANCE_FEE;
	
	//constructors
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
	
	//methods
	/**
	 * amount deposited must be greater than zero
	 */
	public void deposit(double amt)
	{
		if(amt>=0)
		{
			super.deposit(amt);
		}
		else
			throw new IllegalArgumentException();
	}
	/**
	 * amount withdrawn must be greater than zero
	 * balance cannot go negative
	 * withdraws a minimum balance fee if the balance goes below the minimum balance
	 */
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
	/**
	 * bank accounts must be under the same name
	 * balance cannot go negative
	 */
	public void transfer(BankAccount other, double amt)
	{
		if(getName().equals(other.getName()) && getBalance() >= amt)
		{
			super.transfer(other, amt);
		}
		else
			throw new IllegalArgumentException();
	}
	/**
	 * calculates and adds interest to the account
	 */
	public void addInterest()
	{
		super.deposit(getBalance()*intRate);
	}
	/**
	 * calls the addInterest method
	 */
	public void endOfMonthUpdate()
	{
		addInterest();
	}
}
