

/**
 * A chat bot reacting on tasks.
 */

public class Duke {

    Storage storage = new Storage();
    TaskList tasks = new TaskList();

    String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
