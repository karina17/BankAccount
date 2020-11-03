import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** A bank account object. */
public class BankAccount {

    private int balance;
    
    final ReentrantLock lock = new ReentrantLock();
    final Condition positiveAmount  = lock.newCondition(); 
    final Condition enoughMoney = lock.newCondition(); 
    
	/** Instantiate an account with 'initialBalance'. */
    public BankAccount(int initialBalance){
        if(initialBalance < 0)
            throw new IllegalArgumentException();

        balance = initialBalance;
    }

	/** Balance of the account. The balance should never be negative. */
    public int getBalance() {
        return balance;
    }

    /** Deposit 'amount' into this account. 'amount' should always be positive. */
    public void deposit(int amount){
    	lock.lock();
    	System.out.println("Depositing..."); 
    	try{
    		positiveAmount.await();
    		if (amount > 0) {
    			positiveAmount.signalAll();
    		}
    		System.out.print("Depositing " + amount);
    		balance += amount;
            System.out.println(", new balance is " + balance); 
         } catch(Exception e){}  
    	finally {
    		lock.unlock();
    	}
    	System.out.println("Deposit completed.");  
    	notify();  
    }

	/** Withdraw 'amount' from this account. 'amount' should always be positive. 
	 * @throws InterruptedException */
    public void withdraw(int amount) throws InterruptedException {
    	System.out.println("Withdrawing...");  
    	lock.lock();
        try
        {
        	enoughMoney.await();
        	if (balance >= amount) {
        		enoughMoney.signalAll();
        	}
        	
        	positiveAmount.await();
        	if (amount > 0) {
    			positiveAmount.signalAll();
    		}
        	
        	System.out.print("Withdrawing " + amount);
        	balance -= amount;
        	System.out.println(", new balance is " + balance);
        }
        finally
        {
           lock.unlock();
        }
        
	    System.out.println("Withdrawal completed.");
	    notify();
    }
}
