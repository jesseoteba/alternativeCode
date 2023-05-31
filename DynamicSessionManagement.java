import java.util.*;

class DynamicSessionManagement {
    private Map<String, Session> sessions;
    private String name;
    private List<Account> accounts;

    public DynamicSessionManagement(String name, List<Account> accounts) {
        this.sessions = new HashMap<>();
        this.name = name;
        this.accounts = accounts;
    }

    public boolean login(String username, String password) {
        User user = authenticate(username, password);
        if (user == null) {
            return false;
        }
        Session session = new Session(user);
        sessions.put(session.getId(), session);
        return true;
    }

    public boolean logout(String sessionId) {
        return sessions.remove(sessionId) != null;
    }

    public Account getAccount(String sessionId, int accountNumber) {
        Session session = sessions.get(sessionId);
        if (session == null) {
            return null;
        }
        if (!session.getUser().hasAccess(accountNumber)) {
            return null;
        }
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    private User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}

class Session {
    private String id;
    private User user;
    private Date created;

    public Session(User user) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.created = new Date();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}

class User {
    private String username;
    private String password;
    private List<Integer> accounts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public boolean hasAccess(int accountNumber) {
        return accounts.contains(accountNumber);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Account {
    private int accountNumber;
    private double balance;

    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
