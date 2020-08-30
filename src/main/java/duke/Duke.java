/*input
list
bye
*/
package duke;

public class Duke {

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui.init();
        Ui.greet();
        Parser parser = new Parser(taskList);
        parser.run();
    }
}
