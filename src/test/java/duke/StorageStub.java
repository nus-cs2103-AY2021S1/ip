package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import duke.task.Task;

public class StorageStub {

    public static void save(List<Task> listOfTask, File file) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);

            for (Task t : listOfTask) {
                fileWriter.write(t.toString());
                fileWriter.write(System.getProperty("line.separator"));
            }

            fileWriter.close();

        } catch (IOException e) {
            String s = " Unable to access file... *woof*\n";
            throw new DukeException(s);
        }
    }
}
