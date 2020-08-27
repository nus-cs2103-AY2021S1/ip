package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File dukeFile;
    String filePath;
    private final static DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
        dukeFile = new File(filePath);
        if (!dukeFile.exists()) {
            try {
                File dukeParent = new File(dukeFile.getParent());
                if (!dukeParent.exists()) {
                    dukeParent.mkdirs();
                }
                dukeFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }

    public ArrayList<Task> load(Ui ui) {
        ArrayList<Task> listOfTasks = new ArrayList<Task>();
        try {
            Scanner sc1 = new Scanner(dukeFile);
            while(sc1.hasNext()) {
                String loadedInput = sc1.nextLine();
                if (loadedInput.substring(0, 3).equals("[T]")) {
                    Todo newTodo = new Todo(loadedInput.substring(7));
                    if (loadedInput.substring(4, 7).equals("[✓]")) {
                        newTodo.completeTask();
                    }
                    listOfTasks.add(newTodo);
                } else if (loadedInput.substring(0, 3).equals("[D]")) {
                    int byPosition = loadedInput.indexOf("by:");
                    Deadline newDeadline = new Deadline(
                            loadedInput.substring(7, byPosition));
                    if (loadedInput.substring(4, 7).equals("[✓]")) {
                        newDeadline.completeTask();
                    }
                    LocalDateTime date = LocalDateTime.parse(loadedInput.substring(byPosition + 3), SAVE_READ_DATETIME_FORMAT);
                    newDeadline.setTime(date);
                    listOfTasks.add(newDeadline);
                } else if (loadedInput.substring(0, 3).equals("[E]")) {
                    int atPosition = loadedInput.indexOf("at:");
                    Event newEvent = new Event(
                            loadedInput.substring(7, atPosition));
                    if (loadedInput.substring(4, 7).equals("[✓]")) {
                        newEvent.completeTask();
                    }
                    LocalDateTime date = LocalDateTime.parse(loadedInput.substring(atPosition + 3), SAVE_READ_DATETIME_FORMAT);
                    newEvent.setTime(date);
                    listOfTasks.add(newEvent);
                }
            }
        } catch (FileNotFoundException e) {
            ui.showError(e.toString());
        }
        return listOfTasks;
    }

    public void updateStorage(TaskList taskList) {
        try {
            FileWriter dukeFileWriter = new FileWriter(filePath, false);
            for (int i = 0; i < taskList.getTaskList().size(); i++) {
                dukeFileWriter.write(taskList.getTaskList().get(i).saveFormat() + "\n");
            }
            dukeFileWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
