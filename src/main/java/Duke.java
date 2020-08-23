public class Duke {
  private String fileString;
  private TaskList taskList;

  public Duke(String fileString) {
    this.fileString = fileString;
    this.taskList = new TaskList();
  }

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

  public static void main(String[] args) {
    new Duke("tasks.txt").run();
  }
}
