/**
 * Duke class.
 * Driver class for Duke.
 *
 * @author YanCheng
 */

public class Duke {

    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage(taskList);
    public static Parser parser = new Parser(taskList, storage);
    public static Ui ui = new Ui(taskList, storage, parser);


    /**
     * Greeting used by Duke.
     */
    public static void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    /**
     * Farewell used by Duke.
     */
    public static void exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Main method of Duke
     * @param args Unused.
     */
    public static void main(String[] args) {

        greet();

        storage.init();

        ui.echo();

        exit();
    }
}
