import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    protected Path readPath = Path.of("src/gymmembers.txt");

    public Main() {
        GymMemberChecker gmc = new GymMemberChecker();
        List<GymMember> gymMemberList = gmc.readFileToList(readPath);
        System.out.println("Välkommen till Best Gym Ever!");
        System.out.println("Ange namn eller personnummer på besökare: ");
        while (true) {
            try {
                String input = gmc.readUserInput("test");
                GymMember visitor = gmc.findGymMember(input, gymMemberList);
                System.out.println(visitor.getName());
                //printMembershipDetails
//                if (visitor.hasActiveMembership()) {
//                    //logVisit
//                }
            } catch (NoSuchElementException e) {
                System.out.println("Hittade ingen medlem med matchande namn eller personnummer");
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
