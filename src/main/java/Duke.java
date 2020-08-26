/**
 * Driver class for Duke
 *
 * @author Biao Yi
 */
public class Duke {
  private TaskList tasks;
  private Parser parser;
  private Ui ui;

  public Duke() {
    tasks = new TaskList(Storage.load());
    parser = new Parser();
    ui = new Ui(parser, tasks);
  }

  public void run() {
    ui.run();
    Storage.save(ui.getUpdatedTasks().getList());
  }

  public static void main(String[] args) {
    Duke d = new Duke();
    d.run();
  }
}
