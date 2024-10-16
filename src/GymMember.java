import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;

public class GymMember {

    private String socialSecurityNumber;
    private String name;
    private LocalDate dateOfMembershipRenewal;

    public GymMember(String socialSecurityNumber, String name, LocalDate dateOfMembershipRenewal) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.name = name;
        this.dateOfMembershipRenewal = dateOfMembershipRenewal;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfMembershipRenewal() {
        return dateOfMembershipRenewal;
    }
}
