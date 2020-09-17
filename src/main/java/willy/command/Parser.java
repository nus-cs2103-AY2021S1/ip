package willy.command;

import willy.exceptions.WillyException;
import willy.task.TaskList;
import willy.task.TaskSymbol;
import willy.task.TodoTask;
import willy.task.EventTask;
import willy.task.DeadlineTask;
import willy.ui.Format;
import willy.ui.Greet;
import willy.ui.Response;
import willy.ui.Willy;

/**
 * In charge of executing actions based on the user's command.
 */
public class Parser {
    private TaskList list;

    public Parser(TaskList list) {
        this.list = list;
    }

    private String provideHelp() {
        String commands = Format.TODO + "\n" + Format.DEADLINE + "\n" + Format.EVENT
                + "\n" + Format.DONE + "\n" + Format.DELETE + "\n" + Format.EDIT
                + "\n" + Format.FIND + "\n" + Format.LIST;
        return commands;
    }

    /**
     * Creates the various types of Tasks to be added to the list.
     * @param message Input by the user.
     * @return String version of the task being added to the list.
     */
    private String createTask(String message) {
        String response = "";
        if (message.contains("todo")) {
            try {
                String activity = message.substring(5);
                TodoTask newTask = new TodoTask(activity, TaskSymbol.TODO);
                response = list.addToList(newTask);
            } catch (Exception e) {
                WillyException error = new WillyException(Response.NO_TASK.toString() + Format.TODO.toString());
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
                WillyException error = new WillyException(Response.NO_TASK.toString() + Format.DEADLINE);
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
                WillyException error = new WillyException(Response.INCOMPLETE_INFO.toString() + Format.EVENT);
                response = error.toString();
            }

        }
        return response;
    }

    /**
     * Edit Tasks in the List by replacing it with a new version of the task.
     * @param taskNum Number representing the task in the list that is selected to be edited.
     * @param truncatedMessage Input by the user.
     * @return String version of the task that will replace the old task in the list.
     */
    private String editTask(int taskNum, String truncatedMessage) {
        String response = "";
        if (truncatedMessage.contains("todo")) {
            try {
                String activity = truncatedMessage.substring(5);
                TodoTask newTask = new TodoTask(activity, TaskSymbol.TODO);
                response = list.updateTask(taskNum, newTask);
            } catch (Exception e) {
                WillyException error = new WillyException(Response.NO_TASK.toString() + Format.TODO);
                response = error.toString();
            }
        }

        // Deadline input format: dd/MM/yyyy HHmm, output format: dd MMM yyyy HH:mm a
        if (truncatedMessage.contains("deadline")) {
            try {
                int separatorIndex = truncatedMessage.indexOf("/");
                String activity = truncatedMessage.substring(9, separatorIndex - 1);
                String deadline = truncatedMessage.substring(separatorIndex + 4);
                DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                response = list.updateTask(taskNum, newTask);

            } catch (Exception e) {
                WillyException error = new WillyException(Response.INCOMPLETE_INFO.toString() + Format.DEADLINE);
                response = error.toString();
            }
        }

        // Deadline input format: dd/MM/yyyy HH:mm, output format: dd MMM yyyy HH:mm a
        if (truncatedMessage.contains("event")) {
            try {
                int separatorIndex = truncatedMessage.indexOf("/");
                String activity = truncatedMessage.substring(6, separatorIndex - 1);
                String duration = truncatedMessage.substring(separatorIndex + 4);
                EventTask newTask = new EventTask(duration, activity, TaskSymbol.EVENT);
                response = list.updateTask(taskNum, newTask);
            } catch (Exception e) {
                WillyException error = new WillyException(Response.INCOMPLETE_INFO.toString() + Format.EVENT);
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
            response = createTask(message);

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
                response = editTask(taskNum, taskMessage);
            } catch (Exception e) {
                WillyException error = new WillyException(Format.EDIT.toString());
                response = error.toString();
            }
        } else if (message.equals("help")) {
            response = provideHelp();
        } else {
            WillyException error = new WillyException(Response.NO_SENSE.toString());
            response = error.toString();
        }

        if (! isOnJavaFX) {
            System.out.println(response);
            return "";
        }
        return response;
    }
}
