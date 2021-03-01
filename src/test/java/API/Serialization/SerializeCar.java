package API.Serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SerializeCar {
    @Test
    public void serialize() throws IOException {
        Car car= new Car("Honda", "Accord", 2010, 10000);

        ObjectMapper objectMapper =new ObjectMapper();
        objectMapper.writeValue(new File("target/car.json"),car);
    }


}
