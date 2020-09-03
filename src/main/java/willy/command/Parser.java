package willy.command;

import willy.exceptions.WillyException;
import willy.task.*;
import willy.ui.Greet;

/**
 * In charge of executing actions based on the user's command.
 */
public class Parser {
    private TaskList list;
    private static String lastGreeting = "bye";

    public Parser(TaskList list) {
        this.list = list;
    }

    public TaskList getList() {
        return list;
    }

    /**
     * Interpret the message and carry out an action.
     *
     * @param message Instructions that the user wants the bot process and record.
     */
    public void parse(String message) {
        // check when to end the bot
        if (message.equals(lastGreeting)) {
            // prints out exit
            System.out.println(new Greet(message));
            return;
        }

        // take note of keyword "list" to display the lists
        if (message.equals("list")) {
            // reads list
            list.readList();
        }

        // take note of keyword "done" to update task
        else if (message.contains("done")) {
            int taskNum = Integer.parseInt(message.substring(5));
            list.setTaskDone(taskNum);
        }

        // take note of keyword "delete" to remove task from list
        else if (message.contains("delete")) {
            int taskNum = Integer.parseInt(message.substring(7));
            list.removeTask(taskNum);
        }

        // take note of keyword "to-do" to add normal task
        else if (message.contains("todo")) {
            try {
                String activity = message.substring(5);
                ToDoTask newTask = new ToDoTask(activity, TaskSymbol.TODO);
                list.addToList(newTask);
            } catch (Exception e) {
                WillyException error = new WillyException("Hmmm what would you like to do?");
                System.out.println(error);
            }

        }

        // deadline input format: dd/MM/yyyy HHmm, output format: dd MMM yyyy HH:mm a
        // take note of keyword "deadline" to add task with deadline
        else if (message.contains("deadline")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(9, separatorIndex - 1);
                String deadline = message.substring(separatorIndex + 4);
                DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                list.addToList(newTask);

            } catch (Exception e){
                WillyException error = new WillyException("Hmmm are you missing description/deadline of the task? \n\tCheck and try again?");
                System.out.println(error);
            }
        }

        // deadline input format: dd/MM/yyyy HH:mm, output format: dd MMM yyyy HH:mm a
        // take note of keyword "event" to add task with duration
        else if (message.contains("event")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(6, separatorIndex - 1);
                String duration = message.substring(separatorIndex + 4);
                EventsTask newTask = new EventsTask(duration, activity, TaskSymbol.EVENT);
                list.addToList(newTask);
            } catch (Exception e) {
                WillyException error = new WillyException("Hmmm are you missing the description/timing of event? \n\tCheck and try again?");
                System.out.println(error);
            }

        }

        else if (message.contains("find")) {
            String keyword = message.substring(5);
            list.findTask(keyword);
        }

        // else is nonsense which will produce error
        else {
            WillyException error = new WillyException("Hmmm sorry I'm not sure what you are saying, try something else?:(");
            System.out.println(error);
        }
    }

    /**
     * Interpret the message and carry out an action through GUI.
     *
     * @param message Instructions that the user wants the bot process and record.
     * @return
     */
    public String javaFXParse(String message) {
        String response="";
        // check when to end the bot
        if (message.equals(lastGreeting)) {
            // prints out exit
            response = new Greet(message).getExitGreeting();
        }

        // take note of keyword "list" to display the lists
        else if (message.equals("list")) {
            // reads list
            response = list.javaKFReadList();
        }

        // take note of keyword "done" to update task
        else if (message.contains("done")) {
            int taskNum = Integer.parseInt(message.substring(5));
            response = list.javaFXSetTaskDone(taskNum);
        }

        // take note of keyword "delete" to remove task from list
        else if (message.contains("delete")) {
            int taskNum = Integer.parseInt(message.substring(7));
            response = list.javaFXRemoveTask(taskNum);
        }
        
        // take note of keyword "to-do" to add normal task
        else if (message.contains("todo")) {
            try {
                String activity = message.substring(5);
                ToDoTask newTask = new ToDoTask(activity, TaskSymbol.TODO);
                response = list.javaFXAddToList(newTask);
            } catch (Exception e) {
                WillyException error = new WillyException("Hmmm what would you like to do?");
                response = error.getMessage();
            }

        }

        // deadline input format: dd/MM/yyyy HHmm, output format: dd MMM yyyy HH:mm a
        // take note of keyword "deadline" to add task with deadline
        else if (message.contains("deadline")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(9, separatorIndex - 1);
                String deadline = message.substring(separatorIndex + 4);
                DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                response = list.javaFXAddToList(newTask);

            } catch (Exception e) {
                WillyException error = new WillyException("Hmmm are you missing description/deadline of the task? \n\tCheck and try again?");
                response = error.getMessage();
            }
        }

        // deadline input format: dd/MM/yyyy HH:mm, output format: dd MMM yyyy HH:mm a
        // take note of keyword "event" to add task with duration
        else if (message.contains("event")) {
            try {
                int separatorIndex = message.indexOf("/");
                String activity = message.substring(6, separatorIndex - 1);
                String duration = message.substring(separatorIndex + 4);
                EventsTask newTask = new EventsTask(duration, activity, TaskSymbol.EVENT);
                response = list.javaFXAddToList(newTask);
            } catch (Exception e) {
                WillyException error = new WillyException("Hmmm are you missing the description/timing of event? \n\tCheck and try again?");
                response = error.getMessage();
            }

        } else if (message.contains("find")) {
            String keyword = message.substring(5);
            response = list.javaFXFindTask(keyword);
        }

        // else is nonsense which will produce error
        else {
            WillyException error = new WillyException("Hmmm sorry I'm not sure what you are saying, try something else?:(");
            response = error.getMessage();
        }
        return response;
    }
}
