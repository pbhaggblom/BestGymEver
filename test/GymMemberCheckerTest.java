import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymMemberCheckerTest {

    GymMemberChecker g = new GymMemberChecker();
    GymMember per = new GymMember("5612245678", "Per Persson", LocalDate.of(2024, 10, 2));

    @Test
    void testReadFileToList() throws IOException {
        Path temp = Files.createTempFile("readtest", ".txt");
        Files.write(temp, "5612245678, Per Persson\n2024-10-02".getBytes());
        List<GymMember> outputList = new ArrayList<>();
        List<GymMember> expectedList = List.of(per);

        assertEquals(expectedList, g.readFileToList(temp));

        Files.deleteIfExists(temp);
    }

    @Test
    void testParseGymMember() {
        assertEquals(per.getName(), g.parseGymMember("5612245678, Per Persson", "2024-10-02").getName());
        assertEquals(per.getDateOfMembershipRenewal(), g.parseGymMember("5612245678, Per Persson", "2024-10-02").getDateOfMembershipRenewal());
    }

}
