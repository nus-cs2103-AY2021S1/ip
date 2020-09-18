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

    private String obtainKeyword(String message) {
        String[] messageArray = message.split(" ", 2);
        String keyword = messageArray[0];
        return keyword;
    }

    private int getTaskNumber(String message) {
        String[] messageArray = message.split(" ");
        int taskNum = Integer.valueOf(messageArray[1]);
        return taskNum;
    }

    private String provideHelp() {
        String header = "LIST OF COMMANDS: " + "\n";
        String commands = Format.TODO + "\n" + Format.DEADLINE + "\n" + Format.EVENT
                + "\n" + Format.DONE + "\n" + Format.DELETE + "\n" + Format.EDIT
                + "\n" + Format.FIND + "\n" + Format.LIST;
        return header + commands;
    }

    private String createTodoTask(String message) {
        String response = "";
        if (message.length() < 6) {
            WillyException error = new WillyException(Response.INCOMPLETE_INFO.toString() + Format.TODO.toString());
            response = error.toString();
        } else {
            String activity = message.substring(5);
            TodoTask newTask = new TodoTask(activity, TaskSymbol.TODO);
            response = list.addToList(newTask);
        }
        return response;
    }

    private String createDeadlineTask(String message) {
        String response = "";
        try {
            int separatorIndex = message.indexOf("/");
            String activity = message.substring(9, separatorIndex - 1);
            String deadline = message.substring(separatorIndex + 4);
            DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
            response = list.addToList(newTask);

        } catch (Exception e) {
            WillyException error = new WillyException(Response.INCOMPLETE_INFO.toString() + Format.DEADLINE);
            response = error.toString();
        }

        return response;
    }

    private String createEventTask(String message) {
        String response = "";
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
        return response;
    }

    private String updateTodoTask(int taskNum, String message) {
        String response = "";
        try {
            String activity = message.substring(5);
            TodoTask newTask = new TodoTask(activity, TaskSymbol.TODO);
            response = list.updateTask(taskNum, newTask);
        } catch (Exception e) {
            WillyException error = new WillyException(Response.NO_TASK.toString() + Format.TODO);
            response = error.toString();
        }
        return response;
    }

    private String updateDeadlineTask(int taskNum, String message) {
        String response = "";
        try {
            int separatorIndex = message.indexOf("/");
            String activity = message.substring(9, separatorIndex - 1);
            String deadline = message.substring(separatorIndex + 4);
            DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
            response = list.updateTask(taskNum, newTask);

        } catch (Exception e) {
            WillyException error = new WillyException(Response.INCOMPLETE_INFO.toString() + Format.DEADLINE);
            response = error.toString();
        }
        return response;
    }

    private String updateEventTask(int taskNum, String message) {
        String response = "";
        try {
            int separatorIndex = message.indexOf("/");
            String activity = message.substring(6, separatorIndex - 1);
            String duration = message.substring(separatorIndex + 4);
            EventTask newTask = new EventTask(duration, activity, TaskSymbol.EVENT);
            response = list.updateTask(taskNum, newTask);
        } catch (Exception e) {
            WillyException error = new WillyException(Response.INCOMPLETE_INFO.toString() + Format.EVENT);
            response = error.toString();
        }
        return response;
    }

    // Edit Tasks in the List by replacing it with a new version of the task.
    private String editTask(String message) {
        String response = "";
        try {
            int taskNum = getTaskNumber(message);
            int sign = message.indexOf(">");
            String taskMessage = message.substring(sign + 2);

            String keyword = obtainKeyword(taskMessage);

            if (keyword.equals("todo")) {
                response = updateTodoTask(taskNum, taskMessage);
            }

            if (keyword.equals("deadline")) {
                response = updateDeadlineTask(taskNum, taskMessage);
            }

            if (keyword.equals("event")) {
                response = updateEventTask(taskNum, taskMessage);
            }
        } catch (Exception e) {
            WillyException error = new WillyException(Response.INCOMPLETE_INFO + Format.EDIT.toString());
            response = error.toString();
        }
        return response;
    }

    /**
     * Interpret the message and carry out an action.
     *
     * @param message Instructions that the user wants the bot to process and record.
     * @return Bot's response in String form.
     */
    public String parseCommand(String message, boolean isOnJavaFX) {
        String response = "";
        String keyword = obtainKeyword(message);

        if (isOnJavaFX) {
            if (message.equals(Willy.getLastGreeting())) {
                Greet endGreeting = new Greet(message);
                return endGreeting.toString();
            }
        }

        if (keyword.equals("list")) {
            response = list.readList();

        } else if (keyword.equals("done")) {
            assert message.length() > 5 : "Please insert a task number for me to update";
            int taskNum = getTaskNumber(message);
            response = list.setTaskDone(taskNum);

        } else if (keyword.equals("delete")) {
            assert message.length() > 7 : "Please insert a task number for me to delete";
            int taskNum = getTaskNumber(message);
            response = list.removeTask(taskNum);

        } else if (keyword.equals("todo")) {
            assert message.length() > 5 : "Please insert a task for me to add";
            response = createTodoTask(message);

        } else if (keyword.equals("deadline")) {
            assert message.length() > 9 : "Please insert a task for me to add";
            response = createDeadlineTask(message);

        } else if (keyword.equals("event")) {
            assert message.length() > 6 : "Please insert a task for me to add";
            response = createEventTask(message);

        } else if (keyword.equals("find")) {
            assert message.length() > 5 : "Please insert a keyword for me to find";
            String key = message.substring(5);
            response = list.findTask(key);

            // Format e.g : edit 1 > deadline go home /by 20/20/2020 18:00
            // Not working, need to be fixed
        } else if (keyword.equals("edit")) {
            assert message.length() > 5 : "Please insert a task for me to update the list";
            response = editTask(message);

        } else if (keyword.equals("help")) {
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
