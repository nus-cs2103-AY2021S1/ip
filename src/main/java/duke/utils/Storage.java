package duke.utils;

import duke.exception.ParserException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

  private static final int ID_POS = 0;
  private static final int DONE_POS = 1;
  private static final int TYPE_POS = 2;
  private static final int DESC_POS = 3;
  private static final int DATE_POS = 4;

  private File file;

  public Storage(String path) {
    this.file = new File(path);
  }

  private void init() throws IOException {
    if (!file.exists()) {
      File dir = new File(file.getParent());
      if (!dir.exists()) {
        dir.mkdirs();
      }
      file.createNewFile();
    }
  }

  public void store(List<Task> tasks) throws IOException {
    init();
    FileWriter fw = new FileWriter(file);
    for (Task task : tasks) {
      fw.write(task.toString() + '\n');
    }
    fw.close();
  }

  public ArrayList<Task> load() throws FileNotFoundException, ParserException {
    Scanner sc = new Scanner(file);
    ArrayList<Task> tasks = new ArrayList<>();
    while (sc.hasNextLine()) {
      String[] chunks = sc.nextLine().split(" \\| ");
      Task task;
      switch (chunks[TYPE_POS]) {
        case "TODO":
          task = new TodoTask(chunks[DESC_POS]);
          break;
        case "DEADLINE":
          task = new DeadlineTask(chunks[DESC_POS], chunks[DATE_POS]);
          break;
        case "EVENT":
          task = new EventTask(chunks[DESC_POS], chunks[DATE_POS]);
          break;
        default:
          throw new ParserException("Invalid task format");
      }
      tasks.add(task);
    }
    sc.close();
    return tasks;
  }
}
