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
			
			if(input.trim().toLowerCase().equals("add an account"))
			{
				System.out.println("Please enter your name"); String name = in.nextLine();
				boolean checkingorSavings = true;
				while(checkingorSavings)
				{
					System.out.println("Would you like to add a checking account or a savings account?");
					input = in.nextLine();
					if(input.trim().toLowerCase().equals("checking account"))
					{
						myAccounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
						checkingorSavings = false;
					}
					else if(input.trim().toLowerCase().equals("savings account"))
					{
						myAccounts.add(new SavingsAccount(name, INT_RATE, MIN_BALANCE, MIN_BALANCE_FEE));
						checkingorSavings = false;
					}
					else
						System.out.println("That is an invalid input. Please try again.");
				}
			}
			else if(input.trim().toLowerCase().equals("make a transaction"))
			{
				BankAccount account = null;
				while(account == null)
				{
					System.out.println("Please enter the account number that you would like to perform the transaction.");
					String number = in.nextLine();
					
					while(!isNumeric(number))
					{
						System.out.println("That is an invalid input. Please enter a valid numeric account number.");
						number = in.nextLine();
					}
					
					account = getAccount(number, myAccounts);
					if(account == null)
					{
						boolean invalidAccount = true;
						while(invalidAccount)
						{
							System.out.println("That is an invalid input. Would you like to reenter an account number or retrieve all accounts?");
							input = in.nextLine();
						
							if(input.trim().toLowerCase().equals("reenter an account number"))
								invalidAccount = false;
							else if(input.trim().toLowerCase().equals("retrieve all accounts"))
							{
								boolean retrieveAccounts = true;
								while(retrieveAccounts)
								{
									System.out.println("Please enter your name."); 
									input = in.nextLine();
								
									String allAccounts = "";
									for(int i = 0; i < myAccounts.size(); i++)
									{
										if(myAccounts.get(i).getName().equals(input))
										{
											allAccounts += myAccounts.get(i).toString();
											if(myAccounts.get(i) instanceof CheckingAccount)
												allAccounts += "\tChecking Account\n";
											else if(myAccounts.get(i) instanceof SavingsAccount)
												allAccounts += "\tSavings Account\n";
											System.out.println(allAccounts);
											retrieveAccounts = false;
											invalidAccount = false;
										}
										else
											System.out.println("That is not a valid name. Please try again.");
									}
								}
							}
							else
								System.out.println("That is an invalid input. Please try again.");
						}
					}
				}
				
				System.out.println("Would you like to deposit, withdraw, transfer, or retrieve all accounts?");
				input = in.nextLine();
				
				switch(input)
				{
					case "deposit":
						System.out.println("Please enter the amount of money you would like to deposit.");
						String amount = in.nextLine();
					while(!isNumeric(amount))
					{
						System.out.println("That is an invalid input. Please try again."); 
						amount = in.nextLine();
					}
					try
					{
						account.deposit(Double.parseDouble(amount));
					}
					catch(IllegalArgumentException a)
					{
						System.out.println("Transaction is not authorized.");
					}
					break;
					
					case "withdraw":
						System.out.println("Please enter the amount of money you would like to withdraw.");
						amount = in.nextLine();
					while(!isNumeric(amount))
					{
						System.out.println("That is an invalid input. Please try again."); 
						amount = in.nextLine();
					}
					try
					{
						account.withdraw(Double.parseDouble(amount));
					}
					catch(IllegalArgumentException a)
					{
						System.out.println("Transaction is not authorized.");
					}
					break;
					
					case "transfer":
						System.out.println("Please enter the amount of money you would like to transfer.");
						amount = in.nextLine();
					while(!isNumeric(amount))
					{
						System.out.println("That is an invalid input. Please try again."); amount = in.nextLine();
					}
						
					BankAccount account2 = null;
					while(account2 == null)
					{
						System.out.println("Please enter the account number that you would like to transfer the money to.");
						String number = in.nextLine();
						
						while(!isNumeric(number))
						{
							System.out.println("That is an invalid input. Please input a valid numeric account number.");
							number = in.nextLine();
						}
						
						account2 = getAccount(number, myAccounts);
						if(account2 == null || account2 == account)
						{
							System.out.println("That is an invalid input. Would you like to reenter an account number or retrieve all accounts?");
							input = in.nextLine();
							if(input.trim().toLowerCase().equals("retrieve all accounts"))
							{
								System.out.println("Please enter your name."); input = in.nextLine();
								String allAccounts = "";
								for(int i = 0; i < myAccounts.size(); i++)
								{
									if(myAccounts.get(i).getName().equals(input))
									{
										allAccounts += myAccounts.get(i).toString();
										if(myAccounts.get(i) instanceof CheckingAccount)
											allAccounts += "\tChecking Account\n";
										else if(myAccounts.get(i) instanceof SavingsAccount)
											allAccounts += "\tSavings Account\n";
									}
								}
								System.out.println(allAccounts);
							}
						}
					}
					
					try
					{
						account.transfer(account2, Double.parseDouble(amount));
					}
					catch(IllegalArgumentException a)
					{
						System.out.println("Transaction is not authorized.");
					}
					break;
					
					case "retrieve all accounts":
						System.out.println("Please enter your name."); input = in.nextLine();
						String allAccounts = "";
						for(int i = 0; i < myAccounts.size(); i++)
						{
							if(myAccounts.get(i).getName().equals(input))
							{
								allAccounts += myAccounts.get(i).toString();
								if(myAccounts.get(i) instanceof CheckingAccount)
									allAccounts += "\tChecking Account\n";
								else if(myAccounts.get(i) instanceof SavingsAccount)
									allAccounts += "\tSavings Account\n";
							}
						}
						System.out.println(allAccounts);
					break;
					
					default:
						System.out.println("That is an invalid input. Please try again.");
				}
			}
			else if(input.equals("terminate the program"))
				terminate = false;
			else
				System.out.println("That is an invalid input. Please try again.");
		}
		System.out.println("The program has been terminated.");
	}
	
	public static BankAccount getAccount(String num, ArrayList<BankAccount> myAccounts)
	{
		BankAccount account = null;
		for(int i = 0; i < myAccounts.size(); i++)
		{
			if(myAccounts.get(i).getAccountNumber() == Integer.parseInt(num))
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
