import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymMemberTest {

    GymMemberCheckerTest g = new GymMemberCheckerTest();

    @Test
    void testHasActiveMembership() {
        assert(g.testList.get(0).hasActiveMembership());
        assert(!g.testList.get(1).hasActiveMembership());
    }

    @Test
    void testPrintMembershipDetails() {
        String s1 = "Per Persson, aktiv medlem, medlemskapet utgår " + LocalDate.now().plusYears(1);
        String s2 = "Kajsa Karlsson, f.d. medlem, medlemskapet gick ut " + LocalDate.now().minusYears(1);
        assertEquals(s1, g.testList.get(0).printMembershipDetails());
        assertEquals(s2, g.testList.get(1).printMembershipDetails());
    }


}
