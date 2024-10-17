import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GymMemberTest {

    //GymMember per = new GymMember("5612245678", "Per Persson", LocalDate.of(2024, 10, 2));
    GymMember per = new GymMember("5612245678", "Per Persson", LocalDate.now());
    //GymMember kajsa = new GymMember("6504224253", "Kajsa Karlsson", LocalDate.of(2023, 10, 10));
    GymMember kajsa = new GymMember("6504224253", "Kajsa Karlsson", LocalDate.now().minusYears(2));

    @Test
    void testHasActiveMembership() {
        assert(per.hasActiveMembership());
        assert(!kajsa.hasActiveMembership());
    }

    @Test
    void testPrintMembershipDetails() {
        //String expectedOutput = "Per Persson, aktiv medlem, medlemskapet utgår 2025-10-02";
        String expectedOutput = "Per Persson, aktiv medlem, medlemskapet utgår " + LocalDate.now().plusYears(1);
        assertEquals(expectedOutput, per.printMembershipDetails());
        assertNotEquals("Felaktiga uppgifter", per.printMembershipDetails());
    }


}
