package utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LoginData {
    @JsonProperty("loginUsername")
    private String loginUsername;

    @JsonProperty("loginPassword")
    private String loginPassword;

    public LoginData() {
    }

    public static LoginData get(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), LoginData.class);
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }
}
