package hk.edu.polyu.comp.comp2021.assignment4;

/** A bank account object. */
public class BankAccount {

    private int balance;

	/** Instantiate an account with 'initialBalance'. */
    public BankAccount(int initialBalance){
        if(initialBalance < 0)
            throw new IllegalArgumentException();

        balance = initialBalance;
    }

	/** Balance of the account. The balance should never be negative. */
    public int getBalance(){
        return balance;
    }

	/** Deposit 'amount' into this account. 'amount' should always be positive. */
    synchronized void deposit(int amount){
    	System.out.println("Depositing..."); 
    	if(amount < 0){
    		System.out.println("Deposit amount has to be positive. Wating for changes...");
    		try{
    			wait();
    		} catch(Exception e){}  
    	}
    	System.out.print("Depositing " + amount);
    	balance += amount;
    	System.out.println(", new balance is " + balance); 
    	System.out.println("Deposit completed.");  
    	notify();  
    }

	/** Withdraw 'amount' from this account. 'amount' should always be positive. */
    synchronized void withdraw(int amount) {
    	System.out.println("Withdrawing...");  
    	if(getBalance() < amount){
    		System.out.println("Not able to withdraw, unsufficient money on your account. Wating for deposit...");
    		try{
    			wait();
    		} catch(Exception e){}  
    	}
    	
    	else if(amount < 0){
    		System.out.println("Withdrawal amount has to be positive. Wating for changes...");
    		try{
    			wait();
    		} catch(Exception e){}  
    	}
    	System.out.print("Withdrawing " + amount);
	    balance -= amount;
	    System.out.println(", new balance is " + balance);
	    System.out.println("Withdraw completed.");
	    notify();
    }
}
