import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SaveFile {
    public static void save(ArrayList<Task> tasks) throws DukeException {
        String src = System.getProperty("user.dir");
        Path path = Paths.get(src, "data", "duke.txt");
        boolean dirExists = Files.exists(path);

        if (dirExists) {
            try {
                FileWriter fw = new FileWriter(path.toString());
                String newText = "";

                for (Task t : tasks) {
                    char type = t.getType().toUpperCase().charAt(0);
                    if (type == 'T' || type == 'D' || type == 'E') {
                        int doneStatus = t.getDoneStatus() ? 1 : 0;
                        String description = t.getDescription();
                        String timing = t.getTiming();
                        newText += String.format("%c|%d|%s", type, doneStatus, description);

                        if (timing.equals("")) {
                            newText += "\n";
                        } else {
                            newText += String.format("|%s%n", timing);
                        }
                    }
                }

                fw.write(newText);
                fw.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        } else {
            throw new DukeException("duke.txt not found");
        }
    }
}
