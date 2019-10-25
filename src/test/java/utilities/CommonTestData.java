package utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommonTestData {

    @JsonProperty("peselMen")
    private List<String> peselMen;

    @JsonProperty("peselWomen")
    private List<String> peselWomen;

    @JsonProperty("nazwisko")
    private List<String> nazwisko;

    @JsonProperty("miejscowosc")
    private List<String> miejscowosc;

    @JsonProperty("imieMen")
    private String imieMen;

    @JsonProperty("imieWomen")
    private String imieWomen;

    @JsonProperty("noHouse")
    private String noHouse;

    public static CommonTestData get(String filename) throws IOException {
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
