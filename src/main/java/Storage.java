import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Object to enable permanent storing of task list onto hard drive.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Stores task list provided onto a specified text file.
     * @param toDoList given task list
     */
    public void save(List<Task> toDoList) {
        String path = System.getProperty("user.dir") + filePath;
        if(!Files.exists(Paths.get(path))) {
            try {
                Files.createFile(Paths.get(path));
            }
            catch (IOException e) {
                System.out.println("error creating file");
            }
        }
        try {
            new FileWriter(path, false).close();
        }
        catch (IOException e) {
            System.out.println("error deleting file");
        }

        int i = 1;
        try {
            FileWriter myWriter = new FileWriter("save.txt");
            BufferedWriter out = new BufferedWriter(myWriter);
            for (Task s : toDoList) {
                String write = i + ". " + " [" + s.getType() + "] "
                        + s.toString() + " [" + s.getTaskStatusIcon() + "]";
                i += 1;
                out.write(write);
                out.newLine();

            }
            out.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }
}
