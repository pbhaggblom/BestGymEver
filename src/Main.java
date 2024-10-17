import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {

    protected Path readPath = Path.of("src/gymmembers.txt");
    protected Path writePath = Path.of("src/gymvisits");

    public Main() {
        GymMemberChecker gmc = new GymMemberChecker();
        List<GymMember> gymMemberList = gmc.readFileToList(readPath);
        System.out.println("Välkommen till Best Gym Ever!");

        while (true) {
            try {
                System.out.println("Ange namn eller personnummer på besökare: ");
                String input = gmc.readUserInput(null);
                GymMember visitor = gmc.findGymMember(input, gymMemberList);
                System.out.println(visitor.printMembershipDetails());
                if (visitor.hasActiveMembership()) {
                    visitor.logVisit(writePath, null);
                }
            } catch (NoSuchElementException e) {
                System.out.println("Hittade ingen medlem med matchande namn eller personnummer");
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
