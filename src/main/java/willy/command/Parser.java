package willy.command;

import willy.exceptions.WillyException;
import willy.task.TaskList;
import willy.task.TaskSymbol;
import willy.task.TodoTask;
import willy.task.EventTask;
import willy.task.DeadlineTask;
import willy.ui.Greet;
import willy.ui.Willy;

/**
 * In charge of executing actions based on the user's command.
 */
public class Parser {
    private TaskList list;
    private final String MISSING_INFO_MESSAGE = "Hmmm are you missing description/deadline of the task? \n\tCheck and" +
            " try again?";
    private final String TODO_FORMAT = "\nFormat: todo [task] \n\te.g. 'todo do Homework'";
    private final String DEADLINE_FORMAT = "\nFormat: deadline [task] /by <date> <time> \n\te.g. 'deadline project /by " +
            "20/20/2020 18:00'";
    private final String EVENT_FORMAT = "\nFormat: event [task] /at <date> <time> \n\te.g. 'event project meeting /at " +
            "20/20/2020 18:00'";
    private final String NO_TASK_MESSAGE = "Please add in a task!";
    private final String NO_SENSE_MESSAGE = "Hmmm sorry I'm not sure what you are saying, try something else?:(";
    private final String EDIT_FORMAT = "\nFormat: edit [task number] > [task details] \n\te.g. 'edit 1 > todo sleep ";

    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Creates the various types of Tasks to be added to the list.
     * @param message Input by the user.
     * @return String version of the task being added to the list.
     */
    private String taskCreator(String message) {
        String response = "";
        if (message.contains("todo")) {
            try {
                String activity = message.substring(5);
                TodoTask newTask = new TodoTask(activity, TaskSymbol.TODO);
                response = list.addToList(newTask);
            } catch (Exception e) {
                WillyException error = new WillyException(NO_TASK_MESSAGE + TODO_FORMAT);
                response = error.toString();
            }
        }

        // Deadline input format: dd/MM/yyyy HHmm, output format: dd MMM yyyy HH:mm a
        if (message.contains("deadline")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(9, separatorIndex - 1);
                String deadline = message.substring(separatorIndex + 4);
                DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                response = list.addToList(newTask);

            } catch (Exception e) {
                WillyException error = new WillyException(MISSING_INFO_MESSAGE + DEADLINE_FORMAT);
                response = error.toString();
            }
        }

        // Deadline input format: dd/MM/yyyy HH:mm, output format: dd MMM yyyy HH:mm a
        if (message.contains("event")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(6, separatorIndex - 1);
                String duration = message.substring(separatorIndex + 4);
                EventTask newTask = new EventTask(duration, activity, TaskSymbol.EVENT);
                response = list.addToList(newTask);
            } catch (Exception e) {
                WillyException error = new WillyException(MISSING_INFO_MESSAGE + EVENT_FORMAT);
                response = error.toString();
            }

        }
        return response;
    }
    /**
     * Edit Tasks in the List by replacing it with a new version of the task.
     * @param taskNum Number representing the task in the list that is selected to be edited.
     * @param message Input by the user.
     * @return String version of the task that will replace the old task in the list.
     */
    private String taskEditor(int taskNum, String message) {
        String response = "";
        if (message.contains("todo")) {
            try {
                String activity = message.substring(5);
                TodoTask newTask = new TodoTask(activity, TaskSymbol.TODO);
                response = list.updateTask(taskNum, newTask);
            } catch (Exception e) {
                WillyException error = new WillyException(NO_TASK_MESSAGE + TODO_FORMAT);
                response = error.toString();
            }
        }

        // Deadline input format: dd/MM/yyyy HHmm, output format: dd MMM yyyy HH:mm a
        if (message.contains("deadline")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(9, separatorIndex - 1);
                String deadline = message.substring(separatorIndex + 4);
                DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                response = list.updateTask(taskNum, newTask);

            } catch (Exception e) {
                WillyException error = new WillyException(MISSING_INFO_MESSAGE + DEADLINE_FORMAT);
                response = error.toString();
            }
        }

        // Deadline input format: dd/MM/yyyy HH:mm, output format: dd MMM yyyy HH:mm a
        if (message.contains("event")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(6, separatorIndex - 1);
                String duration = message.substring(separatorIndex + 4);
                EventTask newTask = new EventTask(duration, activity, TaskSymbol.EVENT);
                response = list.updateTask(taskNum, newTask);
            } catch (Exception e) {
                WillyException error = new WillyException(MISSING_INFO_MESSAGE + EVENT_FORMAT);
                response = error.toString();
            }

        }
        return response;
    }


    /**
     * Interpret the message and carry out an action.
     *
     * @param message Instructions that the user wants the bot process and record.
     * @return
     */
    public String parse(String message, boolean isOnJavaFX) {
        String response = "";
        String keyword = "";

        if (message.length() > 4) {
            keyword = message.substring(0, 6);
        }
        if (isOnJavaFX) {
            if (message.equals(Willy.getLastGreeting())) {
                Greet endGreeting = new Greet(message);
                return endGreeting.toString();
            }
        }

        if (message.equals("list")) {
            response = list.readList();

        } else if (keyword.contains("done")) {
            int taskNum = Integer.parseInt(message.substring(5));
            response = list.setTaskDone(taskNum);

        } else if (keyword.contains("delete")) {
            int taskNum = Integer.parseInt(message.substring(7));
            response = list.removeTask(taskNum);

        } else if (keyword.contains("todo") || message.contains("deadline") || message.contains("event")) {
            response = taskCreator(message);

        } else if (keyword.contains("find")) {
            assert message.length() > 5 : "Please insert a keyword for us to find";
            String key = message.substring(5);
            response = list.findTask(key);

            // Format e.g : edit 1 > deadline go home /by 20/20/2020 18:00
            // Not working, need to be fixed
        } else if (keyword.contains("edit")) {
            assert message.length() > 5 : "Please insert a task for us to update the list";
            try {
                int taskNum = Integer.parseInt(message.substring(5, 6));
                int separatorIndex = message.indexOf(">");
                String taskMessage = message.substring(separatorIndex + 2);
                response = taskEditor(taskNum, taskMessage);
            } catch(Exception e) {
                WillyException error = new WillyException(EDIT_FORMAT);
                response = error.toString();
            }

        } else {
            WillyException error = new WillyException(NO_SENSE_MESSAGE);
            response = error.toString();
        }

        if (! isOnJavaFX) {
            System.out.println(response);
            return "";
        }
        return response;
    }
}
