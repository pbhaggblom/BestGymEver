import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GymMemberTest {

    GymMember per = new GymMember("5612245678", "Per Persson", LocalDate.of(2024, 10, 2));
    GymMember kajsa = new GymMember("6504224253", "Kajsa Karlsson", LocalDate.of(2023, 10, 10));

    @Test
    void testHasActiveMembership() {
        assert(per.hasActiveMembership());
        assert(!kajsa.hasActiveMembership());
    }

    @Test
    void testPrintMembershipDetails() {
        String expectedOutput = "Per Persson har ett aktivt medlemskap som utgår 2025-10-02";
        assertEquals(expectedOutput, per.printMembershipDetails());
        assertNotEquals("Felaktiga uppgifter", per.printMembershipDetails());
    }

    @Test
    void testLogVisit() throws IOException {

        Path temp = Files.createTempFile("writetest", "txt");
        LocalTime time = LocalTime.of(13, 45, 16);
        LocalDate date = LocalDate.of(2024, 10, 5);
        LocalDateTime testDateTime = LocalDateTime.of(date, time);

        per.logVisit(temp, testDateTime);
        kajsa.logVisit(temp, testDateTime);
        String excpected = "5612245678, Per Persson\n2024-10-05 13:45\n\n6504224253, Kajsa Karlsson\n2024-10-05 13:45";
        String gymVisit = Files.readString(temp);
        assertEquals(excpected, gymVisit.trim());

        Files.deleteIfExists(temp);
    }
}
