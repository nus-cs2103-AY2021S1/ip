/*input
list
bye
*/
package duke;

/**
 * The main driver of the program
 */
public class Duke {
    private final TaskList taskList;
    private final LogicController logicController;

    /**
     * Constructor for Duke
     * Use to prepare the program before start replying to users
     *
     * @param filename the name of the data file
     */
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
