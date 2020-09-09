import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private Path path;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /**
     * Check if file exists, if it does then read it, else create a file.
     *
     * @return TaskList containing any tasks from file.
     */
    public TaskList readFile() {
        if (Files.exists(path)) {
            return Parser.reader(new File(path.toString()));
        } else {
            new File(path.toString());
        }
        return new TaskList();
    }

    /**
     * Overwrites and updates the file at the path for changes to the tasks.
     *
     * @param tasks TaskList containing all tasks.
     * @param path Path where file is located.
     */
    public static void updateFile(TaskList tasks, Path path) {
        try {
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
        } catch (IOException e) {
            assert Files.exists(path) : "Something went wrong with the fileUpdate function!";
            System.out.println("OOPS!! Something went wrong :(");
        }

    }

}
