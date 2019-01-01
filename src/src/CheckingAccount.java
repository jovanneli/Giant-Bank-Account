
public class CheckingAccount extends BankAccount
{
	//fields
	private double numTransactions;
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANSACTIONS;
	
	//constructors
	public CheckingAccount(String name, double balance, double odf, double tf, double ft)
	{
		super(name, balance);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANSACTIONS = ft;
		numTransactions = 0;
	}
	public CheckingAccount(String name, double odf, double tf, double ft)
	{
		this(name, 0, odf, tf, ft);
		numTransactions = 0;
	}
	
	//methods
	/**
	 * amount deposited must be greater than zero
	 * withdraws a transaction fee if transaction number is greater than number of free transactions
	 */
	public void deposit(double amt)
	{
		if(amt >= 0)
		{
			super.deposit(amt);
			if (numTransactions >= FREE_TRANSACTIONS)
				super.withdraw(TRANSACTION_FEE);
			numTransactions++;
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * amount withdrawn must be greater than zero
	 * balance before withdrawal cannot be negative
	 * withdraws a transaction fee if transaction number is greater than number of free transactions
	 * withdraws an overdraft fee if balance becomes negative
	 */
	public void withdraw(double amt)
	{
		if(amt >= 0 && getBalance() >= 0)
		{
			super.withdraw(amt);
			if(numTransactions >= FREE_TRANSACTIONS)
				super.withdraw(TRANSACTION_FEE);
			if(getBalance() < 0)
				super.withdraw(OVER_DRAFT_FEE);
			numTransactions++;
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * both accounts must be under the same name
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
	 * resets number of transactions to zero
	 */
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
}
