import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GymMemberChecker {

    public static boolean test = false;
    protected Scanner scan;

    public List<GymMember> readFileToList(Path filePath) {
        List<GymMember> members = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String firstLine;
            String secondLine;
            while ((firstLine = br.readLine()) != null && (secondLine = br.readLine()) != null) {
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

    public String readUserInput(String testString) {
        if (test) {
            scan = new Scanner(testString);
        } else {
            scan = new Scanner(System.in);
        }
        return scan.nextLine();
    }
}