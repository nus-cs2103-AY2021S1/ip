/**
 * Duke class.
 * Driver class for Duke.
 * Contains task list, storage, parser and ui.
 *
 * @author YanCheng
 */

public class Duke {

    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage(taskList);
    public static Parser parser = new Parser();
    public static Ui ui = new Ui(taskList, storage, parser);




    /**
     * Main method of Duke.
     * @param args Unused.
     */
    public static void main(String[] args) {

        ui.greet();

        ui.echo();

        ui.exit();
    }
}
