package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.resource.TaskList;
import duke.task.Task;
import duke.util.DukeException;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        var list = new ArrayList<Task>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            br.lines().forEach(str -> {
                try {
                    list.add(Task.parse(str));
                } catch (DukeException de){
                    System.out.println("    Error: "+ de.getMessage());
                }
            });
        } catch (FileNotFoundException e) {
            new File("./src/main/data").mkdirs();
            try {
                new File("./src/main/data/duke.txt").createNewFile();
            } catch (IOException ioe) {
                throw new DukeException(
                        "Sorry, the file couldn't be created!\n" +
                        "Please try again.");
            }
            throw new DukeException(
                    "    No save file found in [root]/src/main/data/duke.txt.\n" +
                    "    A file has been created by default.\n" +
                    "    If you'd like to import one, simply copy the file\n" +
                    "    over to the above location and rerun me!");
        }
        return list;
    }

    public void save(TaskList tasks) {
        String toWrite = tasks.stream()
                .map(Task::toSave)
                .reduce((x, y) -> x + "\n" + y)
                .orElse("");
        try {
            FileWriter fw = new FileWriter("./src/main/data/duke.txt");
            fw.write(toWrite);
            fw.close();
        } catch (IOException ignored) { }
    }

}
