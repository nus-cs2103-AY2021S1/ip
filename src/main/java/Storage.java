import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private final String filePath;
    
    Storage(String filePath) {
        this.filePath = filePath;
    }
    
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(new File(filePath));
            while (fileReader.hasNextLine()) {
                String s = fileReader.nextLine();
                Task task = getTask(s);
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("☹ OOPS!!! There was an error while loading the file");
        }
    }

    private Task getTask(String s) throws DukeException {
        try {
            Task currentTask = null;
            if (s.startsWith("T")) {
                currentTask = new Todo(s.substring(8));
            } else {
                int index = s.lastIndexOf(" |");
                String dateTime = s.substring(index + 3);
                if (s.startsWith("D")) {
                    currentTask = new Deadline(s.substring(8, index), LocalDate.parse(dateTime));
                } else if (s.startsWith("E")) {
                    currentTask = new Event(s.substring(8, index), LocalDate.parse(dateTime));
                }
            }

            if (s.charAt(4) == '1' && currentTask != null) {
                currentTask.markAsDone();
            }

            return currentTask;
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException();
        }
    }
    
    void updateTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task t : taskList.getMyTaskList()) {
                fileWriter.write(t.saveAsString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Unable to update tasks.");
        }
    }
}
