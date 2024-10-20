import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {

    public Main() {
        GymMemberChecker gmc = new GymMemberChecker();
        Path readPath = Path.of("src/gymmembers.txt");
        Path writePath = Path.of("src/gymvisits");
        List<GymMember> gymMemberList = gmc.readFileToList(readPath);

        System.out.println("Välkommen till Best Gym Ever!\n(q för att avsluta)");
        while (true) {
            System.out.println("Ange namn eller personnummer på besökare: ");
            String input = gmc.readUserInput(null);
            if (input.equals("q")) {
                System.exit(0);
            }
            try {
                GymMember visitor = gmc.findGymMember(input, gymMemberList);
                System.out.println(visitor.printMembershipDetails());
                if (visitor.hasActiveMembership()) {
                    gmc.logVisit(visitor, writePath);
                    System.out.println("Registrerar gymbesök");
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
