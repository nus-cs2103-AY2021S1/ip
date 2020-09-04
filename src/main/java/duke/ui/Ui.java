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

    /**
     * Prints welcome message.
     */
    public String printWelcomeMessage() {
        String emoji = Emoji.CHICKEN.toString();
        String welcomeMessage = "    ____________________________________________\n"
                + "    Hello! I'm ByteMe " + emoji + emoji + emoji + "\n"
                + "    What can I do for you? (Don't bite me!)\n"
                + "    ____________________________________________\n";

        //System.out.println(welcomeMessage);
        return welcomeMessage;
    }

    /**
     * Prints bye message.
     */
    public String sendBye() {
        String msgForBye = "    ____________________________________________\n"
                + "    Bye. Hope to see you again soon! \n"
                + "    ____________________________________________\n";
        //System.out.println(msgForBye);
        return msgForBye;
    }

    /**
     * Outputs the number of tasks.
     * @param i number of tasks.
     */
    public String sendCount(int i) {
        String countMsg = "    Now you have " + i + " tasks in the list.";
        return countMsg;
    }

    /**
     * Prints the newly added to-do item.
     * @param tl a list of tasks.
     * @param todo a to-do item.
     */
    public String printAddedToDo(TaskList tl, Todo todo) {
        String msgForToDo = "    ____________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + todo.toString() + "\n"
                + tl.countNum() + "\n"
                + "    ____________________________________________\n";
        //System.out.println(msgForToDo);
        return msgForToDo;
    }

    /**
     * Prints the newly added deadline item.
     * @param tl a list of tasks.
     * @param ddl a deadline item.
     */
    public String printAddedDdl(TaskList tl, Deadline ddl) {
        String msgForToDo = "    ____________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + ddl.toString() + "\n"
                + tl.countNum() + "\n"
                + "    ____________________________________________\n";
        //System.out.println(msgForToDo);
        return msgForToDo;
    }

    /**
     * Prints the newly added event item.
     * @param tl a list of tasks.
     * @param event a event item.
     */
    public String printAddedEvent(TaskList tl, Event event) {
        String msgForEvent = "    ____________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + event.toString() + "\n"
                + tl.countNum() + "\n"
                + "    ____________________________________________\n";
        //System.out.println(msgForEvent);
        return msgForEvent;
    }

    /**
     * Prints loading error.
     */
    public String showLoadingError() {
        String errorMsg = "Loading error!";
        return errorMsg;
        //System.out.println("Loading error!");
    }
}
