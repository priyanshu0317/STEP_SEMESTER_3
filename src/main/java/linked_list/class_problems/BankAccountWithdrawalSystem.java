package linked_list.class_problems;

import java.util.HashMap;
import java.util.Map;

public class BankAccountWithdrawalSystem {

    public abstract static class BankAccount {
        private String id;
        private int balance;

        public BankAccount(String id, int initialBalance) {
            this.id = id;
            this.balance = initialBalance;
        }

        public String getId() {
            return id;
        }

        public int getBalance() {
            return balance;
        }

        protected void setBalance(int balance) {
            this.balance = balance;
        }

        public abstract boolean withdraw(int amount);
    }

    public static class SavingsAccount extends BankAccount {
        private static final int MIN_BALANCE = 1000;

        public SavingsAccount(String id, int initialBalance) {
            super(id, initialBalance);
        }

        @Override
        public boolean withdraw(int amount) {
            if (getBalance() - amount < MIN_BALANCE) {
                System.out.printf("%s rejected: minimum balance %d%n", getId(), MIN_BALANCE);
                return false;
            }
            setBalance(getBalance() - amount);
            System.out.printf("%s balance %d%n", getId(), getBalance());
            return true;
        }
    }

    public static class CurrentAccount extends BankAccount {
        private static final int OVERDRAFT_LIMIT = 5000;

        public CurrentAccount(String id, int initialBalance) {
            super(id, initialBalance);
        }

        @Override
        public boolean withdraw(int amount) {
            if (getBalance() - amount < -OVERDRAFT_LIMIT) {
                System.out.printf("%s rejected: overdraft limit %d%n", getId(), OVERDRAFT_LIMIT);
                return false;
            }
            setBalance(getBalance() - amount);
            System.out.printf("%s balance %d%n", getId(), getBalance());
            return true;
        }
    }

    public static class BankingService {
        private Map<String, BankAccount> accounts = new HashMap<>();

        public void createSavings(String id, int balance) {
            accounts.put(id, new SavingsAccount(id, balance));
        }

        public void createCurrent(String id, int balance) {
            accounts.put(id, new CurrentAccount(id, balance));
        }

        public void withdraw(String id, int amount) {
            BankAccount account = accounts.get(id);
            if (account == null) {
                System.out.println("Account not found");
                return;
            }
            account.withdraw(amount);
        }

        public void processCommands(String[] commands) {
            for (String cmd : commands) {
                String[] parts = cmd.trim().split("\\s+");
                if (parts.length == 0 || parts[0].isEmpty()) continue;
                String op = parts[0];
                if (op.equalsIgnoreCase("Savings")) {
                    createSavings(parts[1], Integer.parseInt(parts[2]));
                } else if (op.equalsIgnoreCase("Current")) {
                    createCurrent(parts[1], Integer.parseInt(parts[2]));
                } else if (op.equalsIgnoreCase("WITHDRAW")) {
                    withdraw(parts[1], Integer.parseInt(parts[2]));
                }
            }
        }
    }

    public static void main(String[] args) {
        BankingService service = new BankingService();
        String[] operations = {
            "Savings S1 5000",
            "WITHDRAW S1 4500",
            "Current C1 2000",
            "WITHDRAW C1 6000",
            "WITHDRAW S1 3000",
            "WITHDRAW C1 2000"
        };
        service.processCommands(operations);
    }
}
