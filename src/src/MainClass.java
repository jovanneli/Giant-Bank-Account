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
		
		System.out.println("Would you like to add an account, make a transaction, or terminate the program?");
		String input = in.nextLine();
		
		boolean terminate = true;
		while(terminate)
		{
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
		}
	}
}
