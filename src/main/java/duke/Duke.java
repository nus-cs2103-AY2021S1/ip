/*input
list
bye
*/
package duke;

/**
 * The main driver of the program
 */
public class Duke {
    /**
     * The entry point to the program
     */
    private final TaskList taskList;
    private final LogicController logicController;

    public Duke(String filename) {
        if (filename.isEmpty()) {
            taskList = new TaskList();
        } else {
            taskList = new TaskList(filename);
        }
        Ui.init();
        Ui.greet();
        logicController = new LogicController(taskList);
    }

    public String getResponse(String input) {
        return logicController.run(input);
    }
}
