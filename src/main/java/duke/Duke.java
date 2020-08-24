package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.scanner.CommandScanner;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** Duke class */
public class Duke {
  private String fileString;
  private TaskList taskList;

  public Duke(String fileString) {
    this.fileString = fileString;
    this.taskList = new TaskList();
  }

  /** Runs the duke program */
  public void run() {
    Ui.showGreet();
    CommandScanner cmdScanner = new CommandScanner();

    try {
      Storage storage = Storage.create(this.fileString);

      while (true) {
        try {
          Command cmd = cmdScanner.nextCommand();
          cmd.execute(this.taskList);
          storage.write(this.taskList);
          if (cmd.isExit()) {
            break;
          }
        } catch (DukeException e) {
          Ui.showError(e.getMessage());
        }
      }
    } catch (DukeException e) {
      Ui.showError(e.getMessage());
    } catch (Exception e) {
      Ui.showUnexpectedError(e.getMessage());
    }
  }
}
