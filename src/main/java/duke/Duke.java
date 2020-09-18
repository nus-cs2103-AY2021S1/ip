/*input
list
bye
*/
package duke;

/**
 * The main driver of the program
 */
public class Duke {
    private final LogicController logicController;

    /**
     * Constructor for Duke
     * Use to prepare the program before start replying to users
     *
     * @param filename the name of the data file
     */
    public Duke(String filename) {
        TaskList taskList;
        if (filename.isEmpty()) {
            taskList = new TaskList();
        } else {
            taskList = new TaskList(filename);
        }
        Ui.init();
        logicController = new LogicController(taskList);
    }

    public static String getWelcomeMessage() {
        Ui.init();
        Ui.greet();
        return Ui.getResponse();
    }

    public String getResponse(String input) {
        return logicController.run(input);
    }
}
