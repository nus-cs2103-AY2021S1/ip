package duke;

/**
 * DukeMain class.
 * Driver class for Duke but without GUI.
 * It is for use with CLI.
 * Contains task list, storage, parser and UI.
 *
 * @author YanCheng
 */

public class DukeMain {

    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage(taskList);
    private static Parser parser = new Parser();
    private static Ui ui = new Ui(taskList, storage, parser);



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
