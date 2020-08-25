package duke.io;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
  protected String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public ArrayList<Task> load() {
    ArrayList<Task> taskArrayList = new ArrayList<>();
    File file = new File(filePath);
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String fileLine;
      while ((fileLine = br.readLine()) != null) {
        String[] tempArr = fileLine.split(",");
        String command = tempArr[0];
        switch (command) {
          case "duke.task.Todo":
            taskArrayList.add(new Todo(tempArr[2]));
            break;
          case "duke.task.Deadline":
            Task tempDeadline = new Deadline(tempArr[2], LocalDateTime.parse(tempArr[3]));
            if (tempArr[1].equals("true")) {
              tempDeadline.markAsDone();
            }
            taskArrayList.add(tempDeadline);
            break;
          case "duke.task.Event":
            Task tempEvent =
                new Event(
                    tempArr[2], LocalDateTime.parse(tempArr[3]), LocalDateTime.parse(tempArr[4]));
            if (tempArr[1].equals("true")) {
              tempEvent.markAsDone();
            }
            taskArrayList.add(tempEvent);
            break;
          default:
            System.err.println("No event of this type");
        }
      }
    } catch (IOException fileNotFoundException) {
      System.err.println("Failed to find file: " + fileNotFoundException.getMessage());
    }
    return taskArrayList;
  }
}
