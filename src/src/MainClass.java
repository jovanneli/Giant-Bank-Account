import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author jovanne li
 *
 */
public class MainClass
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		ArrayList<BankAccount> myAccounts = new ArrayList<BankAccount>();
		final double OVER_DRAFT_FEE = 15;
		final double TRANSACTION_FEE = 1.5;
		final double FREE_TRANSACTIONS = 10;
		final double INT_RATE = 0.0025;
		final double MIN_BALANCE = 300;
		final double MIN_BALANCE_FEE = 10;
	
		boolean terminate = true;
		while(terminate)
		{
			System.out.println("Would you like to add an account, make a transaction, or terminate the program?");
			String input = in.nextLine();
			
			if(input.equals("add an account"))
			{
				System.out.println("Please enter your name"); String name = in.nextLine();
				boolean checkingorSavings = true;
				while(checkingorSavings)
				{
					System.out.println("Would you like to add a checking account or a savings account?");
					input = in.nextLine();
					if(input.equals("checking account"))
					{
						myAccounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
						checkingorSavings = false;
					}
					else if(input.equals("savings account"))
					{
						myAccounts.add(new SavingsAccount(name, INT_RATE, MIN_BALANCE, MIN_BALANCE_FEE));
						checkingorSavings = false;
					}
					else
						System.out.println("That is not a valid input. Please try again.");
				}
			}
			else if(input.equals("make a transaction"))
			{
				BankAccount account = null;
				while(account == null)
				{
					System.out.println("Please enter the account number that you would like to perform the transaction.");
					int number = in.nextInt(); in.nextLine();
					account = getAccount(number, myAccounts);
					if(account == null)
					{
						System.out.println("That is an invalid input. Would you like to reenter an account number or retrieve all account numbers?");
						input = in.nextLine();
						if(input.equals("retrieve all account numbers"))
						{
							String allAccounts = "";
							for(int i = 0; i < myAccounts.size(); i++)
							{
								allAccounts += myAccounts.get(i).toString();
								if(myAccounts.get(i) instanceof CheckingAccount)
									allAccounts += "\tChecking Account\n";
								else if(myAccounts.get(i) instanceof SavingsAccount)
									allAccounts += "\tSavings Account\n";
							}
							System.out.println(allAccounts);
						}
					}
				}
				
				System.out.println("Would you like to deposit, withdraw, transfer, or get account numbers?");
				input = in.nextLine();
				int transaction = 1;
				switch(transaction)
				{
					case 1:
					try
					{
						System.out.println("Please enter the amount of money you would like to deposit.");
						int amount = in.nextInt(); in.nextLine();
						account.deposit(amount);
					}
					catch(IllegalArgumentException a)
					{
						System.out.println("Transaction is not authorized.");
					}
					case 2:
					try
					{
						System.out.println("Please enter the amount of money you would like to withdraw.");
						int amount = in.nextInt(); in.nextLine();
						account.withdraw(amount);
					}
					catch(IllegalArgumentException a)
					{
						
					}
					case 3:
					try
					{
						System.out.println("Please enter the amount of money you would like to transfer.");
						int amount = in.nextInt(); in.nextLine();
						System.out.println("Please enter the account number that you would like to transfer the money to.");
						int number = in.nextInt(); in.nextLine();
					}
					catch(IllegalArgumentException a)
					{
						
					}
					case 4:
						
				}
			}
			else if(input.equals("terminate the program"))
				terminate = false;
			else
				System.out.println("That is an invalid input. Please try again.");
		}
		System.out.println("The program has been terminated.");
	}
	
	public static BankAccount getAccount(int num, ArrayList<BankAccount> myAccounts)
	{
		BankAccount account = null;
		for(int i = 0; i < myAccounts.size(); i++)
		{
			if(myAccounts.get(i).getAccountNumber() == num)
				account = myAccounts.get(i);
		}
		return account;
	}
	
	private static boolean isNumeric(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
		}
	}
}
