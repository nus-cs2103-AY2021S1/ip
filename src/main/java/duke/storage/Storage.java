package duke.storage;

import duke.task.Task;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.TaskList;

import duke.dukeException.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** This class handles the saving and loading of the file that contains the TaskList. */
public class Storage {
  protected String filePath;

  /**
   * Constructor for storage object.
   *
   * @param filePath the file path of the save file.
   */
  public Storage(String filePath) {
    this.filePath = filePath;
  }

  /**
   * A method that loads and reads the save file and output the Tasks into an ArrayList.
   *
   * @return ArrayList of Tasks.
   * @throws DukeException If the save file is blank or syntax error exists.
   */
  public ArrayList<Task> loadFile() throws DukeException {
    ArrayList<Task> list = new ArrayList<>();
    try {
      File file = new File(filePath);
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        String[] line = sc.nextLine().split(" \\| ");
        Task t;

        switch (line[0]) {
          case "T":
            t = new Todo(line[2]);
            break;
          case "D":
            t = new Deadline(line[2], line[3]);
            break;
          case "E":
            t = new Event(line[2], line[3]);
            break;
          default:
            throw new DukeException("Failed to load tasks, check file for syntax errors");
        }

        if (line[1].equals("1")) {
          t.setDone();
        }
        list.add(t);
      }
      sc.close();
      return list;
    } catch (FileNotFoundException e) {
      throw new DukeException("File Not Found.");
    }
  }

  /**
   * A method that saves the TaskList into the save file specified.
   *
   * @param list TaskList object.
   * @throws DukeException If IOException occurs.
   */
  public void saveFile(TaskList list) throws DukeException {
    try {
      FileWriter fw = new FileWriter(filePath);
      for (Task t : list.getList()) {
        fw.write(t.toFile());
        fw.write(System.lineSeparator());
      }
      fw.close();
    } catch (IOException e) {
      throw new DukeException("Save to File Failed.");
    }
  }
}
