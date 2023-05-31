public class CheckpointSystem {
    
    private List<String> authorizedUsers;
    private String securityLevel;
    private boolean isSecure;
    
    public CheckpointSystem(List<String> authorizedUsers, String securityLevel) {
        this.authorizedUsers = authorizedUsers;
        this.securityLevel = securityLevel;
        this.isSecure = true;
    }
    
    public void addAuthorizedUser(String user) {
        this.authorizedUsers.add(user);
    }
    
    public void removeAuthorizedUser(String user) {
        this.authorizedUsers.remove(user);
    }
    
    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }
    
    public boolean checkUser(String user) {
        return this.authorizedUsers.contains(user);
    }
    
    public boolean checkSecurityLevel(String securityLevel) {
        return this.securityLevel.equals(securityLevel);
    }
    
    public void toggleSecurity() {
        this.isSecure = !this.isSecure;
    }
    
    public boolean isSecure() {
        return this.isSecure;
    }
/////////////////   
    public class Bank {
    
        private CheckpointSystem checkpointSystem;
        private String name;
        private List<Account> accounts;
        
        public Bank(String name, List<Account> accounts) {
            this.name = name;
            this.accounts = accounts;
            
            // Initialize the checkpoint system with authorized users and security level
            List<String> authorizedUsers = new ArrayList<>();
            authorizedUsers.add("security_guard");
            authorizedUsers.add("manager");
            authorizedUsers.add("teller");
            this.checkpointSystem = new CheckpointSystem(authorizedUsers, "high");
        }
        
        public boolean authenticate(String user) {
            return checkpointSystem.checkUser(user) && checkpointSystem.isSecure();
        }
        
        public void toggleSecurity() {
            checkpointSystem.toggleSecurity();
        }
        
        public Account getAccount(int accountNumber) {
            for (Account account : accounts) {
                if (account.getAccountNumber() == accountNumber) {
                    return account;
                }
            }
            return null;
        }
        
        public List<Account> getAccounts() {
            return accounts;
        }
        
    }
    
}
