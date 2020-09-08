package willy.command;

import willy.exceptions.WillyException;
import willy.task.*;
import willy.ui.Greet;
import willy.ui.Willy;

/**
 * In charge of executing actions based on the user's command.
 */
public class Parser {
    private TaskList list;
    private final String MISSING_INFO_MESSAGE = "Hmmm are you missing description/deadline of the task? \n\tCheck and try again?";
    private final String NO_TASK_MESSAGE = "Please add in a task!";
    private final String NO_SENSE_MESSAGE = "Hmmm sorry I'm not sure what you are saying, try something else?:(";

    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Interpret the message and carry out an action.
     *
     * @param message Instructions that the user wants the bot process and record.
     * @return
     */
    public String parse(String message, boolean isOnJavaFX) {
        String response="";

        if (isOnJavaFX) {
            if (message.equals(Willy.getLastGreeting())) {
                Greet endGreeting = new Greet(message);
                return endGreeting.toString();
            }
        }

        if (message.equals("list")) {
            response = list.readList();
        }

        else if (message.contains("done")) {
            int taskNum = Integer.parseInt(message.substring(5));
            response = list.setTaskDone(taskNum);
        }

        else if (message.contains("delete")) {
            int taskNum = Integer.parseInt(message.substring(7));
            response = list.removeTask(taskNum);
        }

        else if (message.contains("todo")) {
            try {
                String activity = message.substring(5);
                if (activity.length() < 1) {
                    WillyException error = new WillyException(NO_TASK_MESSAGE);
                    response = error.toString();
                } else {
                    ToDoTask newTask = new ToDoTask(activity, TaskSymbol.TODO);
                    response = list.addToList(newTask);
                }
            } catch (Exception e) {
                WillyException error = new WillyException(NO_TASK_MESSAGE);
                response = error.toString();
            }

        }

        // Deadline input format: dd/MM/yyyy HHmm, output format: dd MMM yyyy HH:mm a
        else if (message.contains("deadline")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(9, separatorIndex - 1);
                String deadline = message.substring(separatorIndex + 4);
                DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                response = list.addToList(newTask);

            } catch (Exception e) {
                WillyException error = new WillyException(MISSING_INFO_MESSAGE);
                response = error.toString();
            }
        }

        // Deadline input format: dd/MM/yyyy HH:mm, output format: dd MMM yyyy HH:mm a
        else if (message.contains("event")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(6, separatorIndex - 1);
                String duration = message.substring(separatorIndex + 4);
                EventsTask newTask = new EventsTask(duration, activity, TaskSymbol.EVENT);
                response = list.addToList(newTask);
            } catch (Exception e) {
                WillyException error = new WillyException(MISSING_INFO_MESSAGE);
                response = error.toString();
            }

        } else if (message.contains("find")) {
            assert message.length() > 5 : "Please insert a keyword for us to find";
            String keyword = message.substring(5);
            response = list.findTask(keyword);
        }

        else {
            WillyException error = new WillyException(NO_SENSE_MESSAGE);
            response = error.toString();
        }

        if (!isOnJavaFX) {
            System.out.println(response);
            return "";
        }
        return response;
    }
}
