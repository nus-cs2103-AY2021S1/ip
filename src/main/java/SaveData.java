import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SaveData {
    public static void save(String pathname, ArrayList<Task> data) throws IOException {
        // If no save file, create one
        if (!Files.exists(Paths.get(pathname))) {
            Files.createFile(Paths.get(pathname));
        }

        // Write to save file
        FileWriter fs = new FileWriter(pathname);
        for (Task i : data) {
            fs.write(i.toString() + System.lineSeparator());
        }
        fs.close();
    }
}
