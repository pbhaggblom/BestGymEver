import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            details += " har ett aktivt medlemskap som utgår ";
        } else {
            details += "s medlemskap gick ut ";
        }
        details += dateOfMembershipRenewal.plusYears(1);
        return details;
    }

    public void logVisit(Path filePath, LocalDateTime testDate) {
        LocalDateTime dateTime;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (GymMemberChecker.test) {
            dateTime = LocalDateTime.of(testDate.toLocalDate(), testDate.toLocalTime());
        } else {
            dateTime = LocalDateTime.now();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toString(), true))) {
            bw.append(socialSecurityNumber).append(", ").append(name).append("\n").append(dtf.format(dateTime)).append("\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
