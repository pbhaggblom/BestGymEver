import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GymMemberChecker {

    private boolean test = true;

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
            if (member.getSocialSecurityNumber().equalsIgnoreCase(input) || member.getName().equalsIgnoreCase(input)) {
                return member;
            }
        }
        throw new NoSuchElementException();
    }

    public String readUserInput(String testString) {
        Scanner scan;
        if (test) {
            scan = new Scanner(testString);
        } else {
            scan = new Scanner(System.in);
        }
        return scan.nextLine();
    }

    public void logVisit(GymMember visitor, Path filePath, LocalDateTime testDate) {
        LocalDateTime dateTime;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (test) {
            dateTime = LocalDateTime.of(testDate.toLocalDate(), testDate.toLocalTime());
        } else {
            dateTime = LocalDateTime.now();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toString(), true))) {
            bw.append(visitor.getSocialSecurityNumber()).append(", ").append(visitor.getName()).append("\n").append(dtf.format(dateTime)).append("\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}