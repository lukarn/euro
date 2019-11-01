package utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommonTestData {

    @JsonProperty("surname")
    private List<String> surname;

    @JsonProperty("name")
    private List<String> name;

    @JsonProperty("searchItem")
    private List<String> searchItem;

    public static CommonTestData get(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), CommonTestData.class);
    }

    public List<String> getSurname() {
        return surname;
    }

    public List<String> getName() {
        return name;
    }

    public List<String> getSearchItem() {
        return searchItem;
    }

}
