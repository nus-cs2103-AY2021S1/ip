package duke.ui;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.tool.Emoji;


/**
 * Handles system output.
 */
public class Ui {

    public static final String SEPARATION_LINE = "    ____________________________________________\n";

    /**
     * Prints welcome message.
     */
    public String printWelcomeMessage() {
        String emoji = Emoji.CHICKEN.toString();
        return SEPARATION_LINE
                + "    Hello! I'm ByteMe " + emoji + emoji + emoji + "\n"
                + "    What can I do for you? (Don't bite me!)\n"
                + SEPARATION_LINE;
    }

    /**
     * Prints bye message.
     */
    public String sendBye() {
        return SEPARATION_LINE
                + "    Bye. Hope to see you again soon! \n"
                + SEPARATION_LINE;
    }

    /**
     * Outputs the number of tasks.
     *
     * @param i number of tasks.
     */
    public String sendCount(int i) {
        return "    Now you have " + i + " tasks in the list.";
    }

    /**
     * Prints the newly added to-do item.
     * @param tl a list of tasks.
     * @param todo a to-do item.
     */
    public String printAddedToDo(TaskList tl, Todo todo) {
        return SEPARATION_LINE
                + "    Got it. I 've added this task: \n"
                + "      " + todo.toString() + "\n"
                + tl.countNum() + "\n"
                + SEPARATION_LINE;
    }

    /**
     * Prints the newly added deadline item.
     *
     * @param tl a list of tasks.
     * @param ddl a deadline item.
     */
    public String printAddedDdl(TaskList tl, Deadline ddl) {
        return SEPARATION_LINE
                + "    Got it. I 've added this task: \n"
                + "      " + ddl.toString() + "\n"
                + tl.countNum() + "\n"
                + SEPARATION_LINE;
    }

    /**
     * Prints the newly added event item.
     *
     * @param tl a list of tasks.
     * @param event a event item.
     */
    public String printAddedEvent(TaskList tl, Event event) {
        return SEPARATION_LINE
                + "    Got it. I 've added this task: \n"
                + "      " + event.toString() + "\n"
                + tl.countNum() + "\n"
                + SEPARATION_LINE;
    }

    /**
     * Prints loading error.
     */
    public String showLoadingError() {
        return "Loading error!";
    }
}
