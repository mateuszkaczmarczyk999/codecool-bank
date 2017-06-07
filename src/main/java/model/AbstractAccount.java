package model;


public abstract class AbstractAccount implements Account{

    @Override
    public void deposit(long amount) {

    }

    @Override
    public void withdraw(long amount) {

    }

    @Override
    public long getBalance() {
        return 0;
    }

    @Override
    public int getAccountId() {
        return 0;
    }
}
