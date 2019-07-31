package utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommonTestData {

    @JsonProperty("peselMen")
    List<String> peselMen;

    @JsonProperty("peselWomen")
    List<String> peselWomen;

    @JsonProperty("nazwisko")
    List<String> nazwisko;

    @JsonProperty("miejscowosc")
    List<String> miejscowosc;

    @JsonProperty("imieMen")
    String imieMen;

    @JsonProperty("imieWomen")
    String imieWomen;

    @JsonProperty("noHouse")
    String noHouse;

    public static CommonTestData get(String filename) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), CommonTestData.class);
    }

    public List<String> getPeselMen() {
        return peselMen;
    }

    public List<String> getPeselWomen() {
        return peselWomen;
    }

    public List<String> getNazwisko() {
        return nazwisko;
    }

    public List<String> getMiejscowosc() {
        return miejscowosc;
    }


    public String getImieMen() {
        return imieMen;
    }

    public String getImieWomen() {
        return imieWomen;
    }

    public String getNoHouse() {
        return noHouse;
    }


}
