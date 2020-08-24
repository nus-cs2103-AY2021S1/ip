package duke.storage;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
  private final BufferedWriter writer;
  public static final Path dataDir = Paths.get(System.getProperty("user.dir"),"data" );

  private Storage(BufferedWriter writer) {
    this.writer = writer;
  }

  /**
   * Creates file in the data directory
   *
   * @param fileName Name of the file
   * @return a new Storage object
   * @throws DukeException that there is an io error
   */
  public static Storage create(String fileName) throws DukeException {
    try {
      if (!Files.exists(Storage.dataDir)) {
        Files.createDirectory(Storage.dataDir);
      }
      Path filePath = Paths.get(Storage.dataDir.toString(), fileName);
      BufferedWriter writer = Files.newBufferedWriter(filePath);
      return new Storage(writer);
    } catch (IOException e) {
      throw Ui.ioException(e);
    }
  }

  public void write(TaskList taskList) throws DukeException {
    this.write(Ui.getListString(taskList));
  }

  public void write(String message) throws DukeException {
    try {
      this.writer.write(message);
    } catch (IOException e) {
      throw Ui.ioException(e);
    }
  }

  public void close() throws DukeException {
    try {
      this.writer.close();
    } catch (IOException e) {
      throw Ui.ioException(e);
    }
  }
}
