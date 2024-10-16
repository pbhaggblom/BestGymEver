import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GymMemberChecker {

    protected List<GymMember> gymMemberList = new ArrayList<>();
    protected Path readPath = Path.of("src/gymmembers.txt");
    protected boolean test = false;

    public GymMemberChecker() {

        gymMemberList = readFileToList(readPath);

    }

    public List<GymMember> readFileToList(Path filePath) {
        List<GymMember> members = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String firstLine = "";
            while ((firstLine = br.readLine()) != null ) {
                String secondLine = br.readLine();
                GymMember member = parseGymMember(firstLine, secondLine);
                members.add(member);
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
        LocalDate dateOfMembershipRenewal = LocalDate.parse(s2.trim());
        return new GymMember(socialSecurityNumber, name, dateOfMembershipRenewal);
    }

    public GymMember findGymMember(String input, List<GymMember> gymMemberList) throws NoSuchElementException {
        for (GymMember member : gymMemberList) {
            if (member.getSocialSecurityNumber().equals(input) || member.getName().equals(input)) {
                return member;
            }
        }
        throw new NoSuchElementException();
    }

    public static void main(String[] args) {
        GymMemberChecker g = new GymMemberChecker();
    }
}