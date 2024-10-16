import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class GymMemberTest {

    GymMember per = new GymMember("5612245678", "Per Persson", LocalDate.of(2024, 10, 2));
    GymMember kajsa = new GymMember("6504224253", "Kajsa Karlsson", LocalDate.of(2023, 10, 10));

    @Test
    void testHasActiveMembership() {
        assert(per.hasActiveMembership());
        assert(!kajsa.hasActiveMembership());
    }
}
