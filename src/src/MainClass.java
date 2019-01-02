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
				System.out.println("Would you like to add a checking account or a savings account?");
				input = in.nextLine();		
				if(input.equals("checking account"))
					myAccounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
				else if(input.equals("savings account"))
					myAccounts.add(new SavingsAccount(name, INT_RATE, MIN_BALANCE, MIN_BALANCE_FEE));
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
					}
					catch(IllegalArgumentException a)
					{
						
					}
					case 2:
						
					case 3:
						
					case 4:
						
				}
		}
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
	
}
