package cinema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Token
{

    private String token;
    // No-argument constructor for Jackson
    public Token()
    {
    }

    // Constructor with @JsonCreator and @JsonProperty annotations for deserialization
    @JsonCreator
    public Token(@JsonProperty("token") String token)
    {
        this.token = token;
    }

    // Getters and setters
    public String getToken()
    {
        return token;
    }


}
