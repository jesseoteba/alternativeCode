
import java.util.*;

public class AuthorizationEnforcer {
    
    private AuthorizationPolicy policy;
    
    public AuthorizationEnforcer(AuthorizationPolicy policy) {
        this.policy = policy;
    }
    
    public boolean checkAuthorization(User user, Action action, Resource resource) {
        // First, check if the policy allows the action on the resource
        if (!policy.isActionAllowed(action, resource)) {
            return false;
        }
        
        // If the policy allows the action, check if the user has permission
        return user.hasPermission(action, resource);
    }
    
    //The User class represents a user in the system and has a set of Permissions that allow the user to perform actions on resources.
    public static class User {
        private String username;
        private Set<Permission> permissions;
        
        public User(String username) {
            this.username = username;
            this.permissions = new HashSet<>();
        }
        
        public void addPermission(Permission permission) {
            this.permissions.add(permission);
        }
        
        public boolean hasPermission(Action action, Resource resource) {
            // Check if the user has a permission that allows the action on the resource
            return permissions.stream()
                    .anyMatch(permission -> permission.getAction().equals(action)
                            && permission.getResource().equals(resource));
        }
    }
    
    public static class Permission {
        private Action action;
        private Resource resource;
        
        public Permission(Action action, Resource resource) {
            this.action = action;
            this.resource = resource;
        }
        
        public Action getAction() {
            return action;
        }
        
        public Resource getResource() {
            return resource;
        }
    }
    
    public static class Resource {
        private String name;
        
        public Resource(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
    
    public static enum Action {
        READ,
        WRITE,
        EXECUTE
    }
    
    public static interface AuthorizationPolicy {
        boolean isActionAllowed(Action action, Resource resource);
    }
    
    public static class DefaultAuthorizationPolicy implements AuthorizationPolicy {
        private Map<Action, Set<String>> allowedResources;
        
        public DefaultAuthorizationPolicy() {
            this.allowedResources = new HashMap<>();
        }
        
        public void allowActionOnResource(Action action, String resourceName) {
            if (!allowedResources.containsKey(action)) {
                allowedResources.put(action, new HashSet<>());
            }
            allowedResources.get(action).add(resourceName);
        }
        
        @Override
        public boolean isActionAllowed(Action action, Resource resource) {
            return allowedResources.containsKey(action)
                    && allowedResources.get(action).contains(resource.getName());
        }
    }
}