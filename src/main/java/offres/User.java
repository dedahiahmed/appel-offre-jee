package offres;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String id;
    
    @JsonProperty
    private String username;
    
    @JsonProperty
    private String password;

    // Default constructor
    public User() {
    }

  

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}