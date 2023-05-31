public class DirectedSession{
    // Store the user and session IDs for the current session
    private int userId;
    private String sessionId;
    
    // Set the user and session IDs for the current session
    public void setSession(int userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }
    
    // Check whether the given user and session IDs match the current session
    public boolean isValidSession(int userId, String sessionId) {
        return this.userId == userId && this.sessionId.equals(sessionId);
    }
    
    // Perform some action only if the given user and session IDs match the current session
    public void doSecureAction(int userId, String sessionId, Runnable action) {
        if (isValidSession(userId, sessionId)) {
            action.run();
        } else {
            throw new SecurityException("Invalid session!");
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Assume the user has logged in and obtained their user ID and session ID
        int userId = 1234;
        String sessionId = "abc123";
        
        // Create an instance of DirectedSessionSecurity
        DirectedSession security = new DirectedSession();
        
        // Set the current session
        security.setSession(userId, sessionId);
        
        // Try to perform a secure action with the correct user and session IDs
        security.doSecureAction(userId, sessionId, () -> {
            System.out.println("Secure action performed!");
        });
        
        // Try to perform a secure action with an incorrect session ID
        String invalidSessionId = "xyz789";
        try {
            security.doSecureAction(userId, invalidSessionId, () -> {
                System.out.println("Secure action performed!");
            });
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }
    }
}