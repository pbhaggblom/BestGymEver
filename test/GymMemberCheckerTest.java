import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class GymMemberCheckerTest {

    GymMemberChecker g = new GymMemberChecker();
    GymMember member1 = new GymMember("5612245678", "Per Persson", LocalDate.now());
    GymMember member2 = new GymMember("6504224253", "Kajsa Karlsson", LocalDate.now().minusYears(2));
    String testString1 = member1.getSocialSecurityNumber() + ", " + member1.getName();
    String testString2 = member2.getSocialSecurityNumber() + ", " + member2.getName();
    List<GymMember> testList = List.of(member1, member2);

    @Test
    void testReadFileToList() throws IOException {
        Path temp = Files.createTempFile("readtest", ".txt");
        String member1RenewalDate = LocalDate.now().toString();
        String member2RenewalDate = LocalDate.now().minusYears(2).toString();
        Files.write(temp, (testString1 + "\n" + member1RenewalDate + "\n" + testString2 + "\n" + member2RenewalDate).getBytes());
        List<GymMember> listFromFile = g.readFileToList(temp);

        assertEquals(testList.get(0).getSocialSecurityNumber(), listFromFile.get(0).getSocialSecurityNumber());
        assertEquals(testList.get(1).getName(), listFromFile.get(1).getName());
        assertEquals(testList.get(0).getDateOfMembershipRenewal(), listFromFile.get(0).getDateOfMembershipRenewal());

        Files.deleteIfExists(temp);
    }

    @Test
    void testProcessGymMember() {
        String renewalDate = LocalDate.now().toString();
        GymMember processedMember = g.processGymMember(testString1, renewalDate);
        assertEquals(member1.getSocialSecurityNumber(), processedMember.getSocialSecurityNumber());
        assertEquals(member1.getName(), processedMember.getName());
        assertEquals(member1.getDateOfMembershipRenewal(), processedMember.getDateOfMembershipRenewal());
    }

    @Test
    void testFindGymMember() {
        assertEquals(member2, g.findGymMember("6504224253", testList));
        assertEquals(member1, g.findGymMember("per persson", testList));
        assertThrows(NoSuchElementException.class, () -> {
            g.findGymMember("Kalle Anka", testList);
        });
    }

    @Test
    void testReadUserInput() {
        assertEquals("test", g.readUserInput(" test "));
    }

    @Test
    void testLogVisit() throws IOException {
        Path temp = Files.createTempFile("writetest", "txt");
        String dateTime = g.dtf.format(LocalDateTime.now());

        g.logVisit(member1, temp);
        g.logVisit(member2, temp);
        String expected = testString1 + "\n" + dateTime + "\n\n" + testString2 + "\n" + dateTime;
        String testRead = Files.readString(temp);
        assertEquals(expected, testRead.trim());

        Files.deleteIfExists(temp);
    }


}
