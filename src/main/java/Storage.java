import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
  private final BufferedWriter writer;

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
    String projectDir = System.getProperty("user.dir");
    Path dataDir = Paths.get(projectDir, "data");
    try {
      if (!Files.exists(dataDir)) {
        Files.createDirectory(dataDir);
      }
      Path filePath = Paths.get(dataDir.toString(), fileName);
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
