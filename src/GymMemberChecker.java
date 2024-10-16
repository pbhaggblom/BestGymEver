import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GymMemberChecker {

    protected List<GymMember> gymMemberList = new ArrayList<>();
    protected Path readPath = Path.of("src/gymmembers.txt");
    protected boolean test = false;

    public GymMemberChecker() {

    }

    public List<String> readFileToList(Path filePath) {
        List<String> members = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String tempLine = "";
            while ((tempLine = br.readLine()) != null) {
                members.add(tempLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }

    public static void main(String[] args) {
        GymMemberChecker g = new GymMemberChecker();
    }
}