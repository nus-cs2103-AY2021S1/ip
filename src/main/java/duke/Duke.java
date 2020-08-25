package duke;

import duke.command.Command;
import duke.io.Storage;
import duke.io.TaskList;
import duke.parser.Parser;
import java.io.File;

public class Duke {

  private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
  private final Storage storage;
  private TaskList taskList;
  private final Ui ui;

  public Duke(String filePath) {
    this.ui = new Ui();
    this.storage = new Storage(filePath);
    try {
      this.taskList = new TaskList(storage.load());
    } catch (DukeException e) {
      this.ui.showLoadingError();
      this.taskList = new TaskList();
    }
  }

  public void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        ui.showLine(); // show the divider line ("_______")
        Command c = Parser.parse(fullCommand);
        c.execute(taskList, ui, storage);
        isExit = c.isExit();
      } catch (DukeException | NoSuchMethodException e) {
        ui.showError(e.getMessage());
      } finally {
        ui.showLine();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    final String dataDir = CURRENT_DIRECTORY + File.separator + "data";
    final String dataFile = "duke.txt";
    new Duke(dataDir + File.separator + dataFile).run();
  }
}
