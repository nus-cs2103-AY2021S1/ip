import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class FileUpdater {
    public static void updateFile() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");

            Iterator i = Command.tasks.iterator();

            while (i.hasNext()) {
                fw.write(i.next().toString() + System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
