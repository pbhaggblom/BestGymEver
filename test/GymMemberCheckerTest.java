import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class GymMemberCheckerTest {

    GymMemberChecker g = new GymMemberChecker();
    GymMember per = new GymMember("5612245678", "Per Persson", LocalDate.of(2024, 10, 2));
    GymMember kajsa = new GymMember("6504224253", "Kajsa Karlsson", LocalDate.of(2023, 10, 10));

    String testString = "test";
    List<GymMember> testList = List.of(per, kajsa);

    @Test
    void testReadFileToList() throws IOException {
        Path temp = Files.createTempFile("readtest", ".txt");
        Files.write(temp, "5612245678, Per Persson\n2024-10-02".getBytes());

        assertEquals(testList.get(0).getName(), g.readFileToList(temp).get(0).getName());

        Files.deleteIfExists(temp);
    }

    @Test
    void testParseGymMember() {
        assertEquals(per.getName(), g.parseGymMember("5612245678, Per Persson", "2024-10-02").getName());
        assertEquals(per.getDateOfMembershipRenewal(), g.parseGymMember("5612245678, Per Persson", "2024-10-02").getDateOfMembershipRenewal());
    }

    @Test
    void testFindGymMember() {
        assertEquals(kajsa, g.findGymMember("6504224253", testList));
        assertEquals(per, g.findGymMember("Per Persson", testList));
        assertThrows(NoSuchElementException.class, () -> {
            g.findGymMember("Felaktig input", testList);
        });
    }

    @Test
    void testReadUserInput() {
        assertEquals("Per Persson", g.readUserInput("Per Persson"));
    }

    @Test
    void testLogVisit() throws IOException {
        Path temp = Files.createTempFile("writetest", "txt");
        LocalTime time = LocalTime.of(13, 45, 16);
        LocalDate date = LocalDate.of(2024, 10, 5);
        LocalDateTime testDateTime = LocalDateTime.of(date, time);

        g.logVisit(per, temp, testDateTime);
        g.logVisit(kajsa, temp, testDateTime);
        String excpected = "5612245678, Per Persson\n2024-10-05 13:45\n\n6504224253, Kajsa Karlsson\n2024-10-05 13:45";
        String gymVisits = Files.readString(temp);
        assertEquals(excpected, gymVisits.trim());

        Files.deleteIfExists(temp);
    }


}
