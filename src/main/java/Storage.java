import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class. Used to save/load the TaskList to a predetermined directory saved file stored in
 * ./data/duke.txt
 *
 * @author Biao Yi
 */
public class Storage {

  public static final String FILENAME = "./data/duke.txt";
  public static final String DIRECTORY_NAME = "./data/";

  /**
   * Static method to save a TaskList to the hard drive
   *
   * @param list A TaskList object
   */
  public static void save(ArrayList<Task> list) {
    try {
      FileWriter writer = new FileWriter(FILENAME);
      for (Task task : list) {
        String extraInfo = "";
        if (task.getType().equals("E")) {
          Event task1 = (Event) task;
          extraInfo = task1.getAt();
        } else if (task.getType().equals("D")) {
          Deadline task1 = (Deadline) task;
          extraInfo = task1.getBy();
        }
        String output =
            task.getType()
                + " | "
                + task.getStatusIcon().charAt(1)
                + " | "
                + task.getDescription()
                + " | "
                + extraInfo;
        writer.write(output + System.lineSeparator());
      }
      writer.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  /**
   * Static method to load a TaskList from hard drive Creates an empty directory and file
   * "./data/duke.txt" if the file does not exist
   */
  public static ArrayList<Task> load() {
    List<String> tasksList = new ArrayList<>();
    ArrayList<Task> tasks = new ArrayList<>();

    File directory = new File(DIRECTORY_NAME);
    // Checks if directory exists, else directory will be created
    if (!directory.exists()) {
      directory.mkdir();
    }
    // Checks if file exists, else create empty file
    File f = new File(FILENAME);
    if (!f.exists()) {
      try {
        f.createNewFile();
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    try {
      tasksList = Files.readAllLines(Paths.get(FILENAME));
    } catch (Exception e) {
      System.out.println(e);
    }
    for (String line : tasksList) {
      String[] tokens = line.split(" \\| ");
      String type = tokens[0];
      String status = tokens[1];
      String description = tokens[2];
      Task t = null;

      switch (type) {
        case "T":
          t = new ToDo(description);
          break;
        case "E":
          t = new Event(description, tokens[3]);
          break;
        case "D":
          t = new Deadline(description, tokens[3]);
          break;
      }
      if (status.equals("\u2713")) {
        t.markDone();
      }
      tasks.add(t);
    }
    return tasks;
  }
}
