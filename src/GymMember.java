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

    public boolean hasActiveMembership() {
        LocalDate today = LocalDate.now();
        return today.isBefore(dateOfMembershipRenewal.plusYears(1));
    }

    public String printMembershipDetails() {
        String details = name;
        if (hasActiveMembership()) {
            details += ", aktiv medlem, medlemskapet utgår ";
        } else {
            details += ", f.d. medlem, medlemskapet gick ut ";
        }
        details += dateOfMembershipRenewal.plusYears(1);
        return details;
    }


}
