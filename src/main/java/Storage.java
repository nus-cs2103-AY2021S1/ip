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

    /**
     * Returns a TaskList containing all the tasks loaded from the file.
     * @param file is the file to be read.
     * @return the TaskList.
     * @throws FileNotFoundException if file is not found.
     */
    public static TaskList reader(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        TaskList tasks = new TaskList();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String splitOn = "\\s*@\\s*";
            String[] words = line.split(splitOn);
            int done = Integer.parseInt(words[1]);
            if (words.length == 3) {
                ToDo toDo = new ToDo(words[2]);
                if (done == 1) {
                    toDo.setDone();
                }
                tasks.add(toDo);
            } else {
                if (words[0].equals("[E]")) {
                    Event event = new Event(words[2], words[3]);
                    if (done == 1) {
                        event.setDone();
                    }
                    tasks.add(event);
                } else {
                    Deadline deadline = new Deadline(words[2], words[3]);
                    if (done == 1) {
                        deadline.setDone();
                    }
                    tasks.add(deadline);
                }
            }
        }
        return tasks;
    }

    /**
     * Check if file exists, if it does then read it, else create a file.
     * @return TaskList containing any tasks from file.
     * @throws FileNotFoundException if the file cannot be found.
     */
    public TaskList readFile() throws FileNotFoundException {
        if (Files.exists(path)) {
            return reader(new File(path.toString()));
        } else {
            File f = new File(path.toString());
        }
        return new TaskList();
    }

    /**
     * Overwrites and updates the file at the path for changes to the tasks.
     * @param tasks TaskList containing all tasks.
     * @param path where file is located.
     * @throws IOException for any input/ output issues with FileWriter.
     */
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
