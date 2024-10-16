import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymMemberCheckerTest {

    GymMemberChecker g = new GymMemberChecker();

    @Test
    void testReadFileToList() throws IOException {
        Path temp = Files.createTempFile("readtest", ".txt");
        Files.write(temp, "Första raden\nAndra raden".getBytes());
        List<String> outputList = new ArrayList<>();
        List<String> expectedList = List.of("Första raden", "Andra raden");

        assertEquals(expectedList, g.readFileToList(temp));

        Files.deleteIfExists(temp);
    }

}
