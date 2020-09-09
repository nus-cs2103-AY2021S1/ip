package duke.ui;

import java.util.ArrayList;

import duke.model.Interval;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.tool.Emoji;
import duke.tool.TimeConverter;


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

    /**
     * Outputs the free slots in a day.
     * @param intervalArrayList free intervals in a day.
     * @return string representation of free slots.
     */
    public String showFreeSlots(ArrayList<Interval> intervalArrayList) {
        TimeConverter timeConverter = new TimeConverter();
        StringBuilder message = new StringBuilder(SEPARATION_LINE + "    Here is your free time in the day: \n");
        for (Interval interval : intervalArrayList) {
            message.append("    ").append(timeConverter.convertIntToTime(interval.getStart()).toString())
                    .append(" to ").append(timeConverter.convertIntToTime(interval.getEnd()).toString())
                    .append("\n");
        }
        message.append(SEPARATION_LINE);
        return message.toString();
    }
}
