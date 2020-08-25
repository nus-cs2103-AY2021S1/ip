import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private Path path;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    public TaskList readFile() throws FileNotFoundException {
        if (Files.exists(path)) {
            return Parser.reader(new File(path.toString()));
        } else {
            File f = new File(path.toString());
        }
        return new TaskList();
    }

    public static void fileUpdate(TaskList tasks, Path path) throws IOException {
        FileWriter fw = new FileWriter(path.toString());
        for (Task t : tasks.getList()) {
            int done = t.isDone ? 1 : 0;
            if (t instanceof Event) {
                fw.write("[E]@" + done + "@" + t.desc + "@" +
                        ((Event) t).at + System.getProperty("line.separator"));
            } else if (t instanceof Deadline) {
                fw.write("[D]@" + done + "@" + t.desc + "@" +
                        ((Deadline) t).by + System.getProperty("line.separator"));
            } else {
                fw.write("[T]@" + done + "@" + t.desc +
                        System.getProperty("line.separator"));
            }
        }
        fw.close();
    }


}
