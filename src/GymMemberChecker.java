import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GymMemberChecker {

    protected List<GymMember> gymMemberList = new ArrayList<>();
    protected Path readPath = Path.of("src/gymmembers.txt");
    protected boolean test = false;

    public GymMemberChecker() {

    }

    public List<GymMember> readFileToList(Path filePath) {
        List<GymMember> members = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String firstLine = "";

            while ((firstLine = br.readLine()) != null ) {
                String secondLine = br.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }

    public GymMember parseGymMember(String s1, String s2) {
        String[] data = s1.split(",");
        String socialSecurityNumber = data[0].trim();
        String name = data[1].trim();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfMembershipRenewal = LocalDate.parse(s2);
        return new GymMember(socialSecurityNumber, name, dateOfMembershipRenewal);
    }

    public static void main(String[] args) {
        GymMemberChecker g = new GymMemberChecker();
    }
}