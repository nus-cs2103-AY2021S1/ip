package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    public static final String line = "//";
    private String directoryName;
    private String fileName;
    private File databaseFile;
    private FileWriter fw;

    public Storage(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;

        //Create directory if it does not exist
        File directory = new File(directoryName);
        boolean directoryIsCreated = directory.mkdirs();

        //Create path to file
        databaseFile = new File(directoryName + fileName);
    }

    public List<Task> generateTaskList() throws DukeException, IOException {
        List<Task> tempTaskList = new ArrayList<>();

        //Create file if it does not yet exist
        boolean fileIsCreated = databaseFile.createNewFile();

        //Read contents of file and transfer into list
        Scanner s = new Scanner(databaseFile);
        while (s.hasNext()) {
            String nextstr = s.nextLine();
            Task next = generateTaskFromFile(nextstr);
            tempTaskList.add(next);
        }
        s.close();
        return tempTaskList;
    }

    public void saveTaskList(TaskList tl) throws IOException {
        fw = new FileWriter(directoryName + fileName);
        fw.write(tl.formatForSave());
        fw.flush();
    }

    public void finalise() throws IOException {
        fw.close();
    }

    private Task generateTaskFromFile(String nextLine) throws DukeException {
        String[] taskCharacteristics = nextLine.split(line);
        boolean isDone = taskCharacteristics[1].equals("1");
        String type = taskCharacteristics[0];
        String desc = taskCharacteristics[2];
        switch (type) {
        case ("T"):
            return new Todo(desc);
        case ("D"):
            return new Deadline(desc, LocalDate.parse(taskCharacteristics[3]));
        case ("E"):
            return new Event(desc, LocalDate.parse(taskCharacteristics[3]));
        default:
            throw new DukeException(type,
                    DukeException.ExceptionType.UNEXPECTED_INPUT_IN_FILE);
        }
    }
}
