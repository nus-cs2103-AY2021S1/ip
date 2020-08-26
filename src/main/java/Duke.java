import java.io.File;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        File f = new File("data");
        if (!f.exists()) {
            f.mkdir();
        }

        try {
            File duke = new File("data/duke.txt");
            if (!duke.exists()) {
                duke.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        UpdateListFromFile.updateList("data/duke.txt");

        System.out.println(new GreetCommand().execute());
        Responder.responder();
    }
}
