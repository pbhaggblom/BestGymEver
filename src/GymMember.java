import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;

public class GymMember {

    String name;
    String socialSecurityNumber;
    LocalDate dateOfMembershipRenewal;

    public GymMember(String name, String socialSecurityNumber, LocalDate dateOfMembershipRenewal) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfMembershipRenewal = dateOfMembershipRenewal;
    }
}
