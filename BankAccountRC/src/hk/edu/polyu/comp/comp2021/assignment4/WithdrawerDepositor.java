package hk.edu.polyu.comp.comp2021.assignment4;

import org.junit.Test;

public class WithdrawerDepositor {
    @Test
    public void test() {
        BankAccount ba = new BankAccount(0); // initialize the bank account to have balance 0
        Thread withdrawer = new Thread(new Withdrawer(ba, 3));
        Thread depositor = new Thread(new Depositor(ba, 5));
        withdrawer.start();
        depositor.start();
        try {
            withdrawer.join();
            depositor.join();
        }
        catch(InterruptedException e){}

        assert ba.getBalance() == 20;
    }
}
