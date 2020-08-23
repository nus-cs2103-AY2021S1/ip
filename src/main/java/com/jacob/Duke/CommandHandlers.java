package com.jacob.Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

public class CommandHandlers {
    public void handleFileCommands(String inputCommand, List<Task> taskList,int count) {

        //parse input command (in format :  type, done, description, datetime) into task
        String[] inputs = inputCommand.split(",");
        String type = inputs[0];
        int isDone = Integer.parseInt(inputs[1]);
        String description = inputs[2];

        switch (type) {
            case "T":
                Task theTodo = new Todo(description);
                if (isDone == 1) {
                    theTodo.setDone();
                }
                taskList.add(count, theTodo);
                break;
            case "E":
                String dateTime = inputs[3];
                Task theEvent = new Event(description, dateTime);
                if (isDone == 1) {
                    theEvent.setDone();
                }
                taskList.add(count, theEvent);
                break;
            case "D":
                dateTime = inputs[3];
                Task theDeadline = new Deadline(description, dateTime);
                if (isDone == 1) {
                    theDeadline.setDone();
                }
                taskList.add(count, theDeadline);
                break;
        }
    }

    /**
     * Handles all commands and send them to the correct eventHandler
     * 1) take input and split by white space
     * 2) use case to check command type
     * @param inputCommand Command issued to duke chat bot
     * @param taskList List of all recorded tasks
     */
    public void handleConsoleCommands(String inputCommand, List<Task> taskList) {
        String[] splitStrings = inputCommand.split(" ");
        String firstInput = splitStrings[0];
        try {
            System.out.println(" -----------------");
            switch (firstInput) {
                case "todo":
                    //prints the task description and updates the relevant task into taskList
                    System.out.println(toDoHandler(inputCommand, taskList));
                    break;
                case "deadline":
                    //create the deadline and update the relevant deadline into taskList
                    System.out.println(deadlineHandler(inputCommand, taskList));
                    break;
                case "event":
                    //create the event and update the relevant event into taskList
                    System.out.println(eventHandler(inputCommand, taskList));
                    break;
                case "delete":
                    //delete the task from taskList
                    System.out.println(deleteHandler(inputCommand, taskList));
                    break;
                case "list":
                    //iterate through the taskList and print all active members
                    printListStatus(taskList);
                    break;
                case "list-due":
                    //iterate through the taskList and print all active members
                    filterPrintTasks(inputCommand,taskList);
                    break;
                case "done":
                    //doneEventHandler();
                    System.out.println(doneHandler(inputCommand,taskList));
                    break;
                default:
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        } finally { //always end event with the line even if exception is caught
            System.out.println(" -----------------");
        }
    }

    /**
     * Handles the UI and logic of the done command
     * @param inputCommand Command issued to duke chat bot
     * @param taskList List of all recorded tasks
     * @return Done Handler UI description
     */
    static String doneHandler(String inputCommand, List<Task> taskList) {
        System.out.println(" Nice! I've marked this task as done:");

        //get the integer from the string command and convert to integer
        Task theTask = taskList.get(Integer.parseInt(inputCommand.substring(5))-1);
        String lineToEdit = theTask.convertToFile();

        //set task as done
        theTask.setDone();
        String replacementText = theTask.convertToFile();

        //replace line
        FileEditor.replacement(lineToEdit,replacementText);
        return theTask.getCurrentStatus();
    }

    /**
     * Handles the printing of all existing tasks in a list
     * @param taskList List of all recorded tasks
     */
    static void printListStatus(List<Task> taskList) {
        int count = 1;
        System.out.println(" Here are the tasks in your list:");
        for (Task t: taskList) {
            System.out.println("  "+ count + ". " + t.getCurrentStatus());
            count++;
        }
    }

    /**
     * Handles the to-do Command and return the task description
     * @param inputCommand Command issued to Duke Chat bot
     * @param taskList List of stored tasks
     * @return Event Handler UI description
     * @throws DukeException To check if there are any statuses
     */
    //handle to-do UI and logic
    static String toDoHandler(String inputCommand, List<Task> taskList) throws DukeException{
        String s = inputCommand.substring(4);
        if (s.equals("")) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task theTodo = new Todo(inputCommand.substring(4+1));
        taskList.add(theTodo);

        //append text
        FileEditor.appendText(theTodo.convertToFile());

        return String.format(" Got it. I've added this task: \n" +
                "   %s\n" +
                " Now you have %d tasks in the list.\n", theTodo.getCurrentStatus(), taskList.size());
    }

    /**
     * handle deadline UI and logic
     * @param inputCommand Command Issued to Duke Chat Bot
     * @param taskList List of Tasks recorded
     * @return String description of added task
     * @throws DukeException In case there is no description
     */
    static String deadlineHandler(String inputCommand, List<Task> taskList) throws DukeException {
        int breakpoint = inputCommand.indexOf("/")-1;
        if (inputCommand.length() <= "deadline ".length() ) {
            throw new DukeException("☹ OOPS!!! The description of a Deadline cannot be empty.\n");
        } else if (breakpoint == -1) {
            throw new DukeException("Hey a deadline cannot have no actual date!!\n");
        }
        String description = inputCommand.substring("deadline".length()+1, breakpoint);
        String dateTime = inputCommand.substring(breakpoint + 5);
        Task theDeadline = new Deadline(description, dateTime);
        taskList.add(theDeadline);

        //append text
        FileEditor.appendText(theDeadline.convertToFile());
        return String.format(" Got it. I've added this task: \n" +
                "   %s\n" +
                " Now you have %d tasks in the list.", theDeadline.getCurrentStatus(), taskList.size());
    }

    /**
     * handle Event UI and logic
     * @param inputCommand Command Issued to Duke Chat Bot
     * @param taskList List of Tasks recorded
     * @return String description of added event
     * @throws DukeException in case there is no description
     */
    static String eventHandler(String inputCommand, List<Task> taskList) throws DukeException {
        int breakpoint = inputCommand.indexOf("/")-1;
        if (inputCommand.length() <= "event ".length() ) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be incomplete.");
        } else if (breakpoint == -1) {
            throw new DukeException("Hey, a event cannot have no actual date and time!!");
        }
        String description = inputCommand.substring("event".length()+1, breakpoint);
        String dateTime = inputCommand.substring(breakpoint + 5);
        Task theEvent = new Event(description, dateTime);
        taskList.add(theEvent);

        //append text
        FileEditor.appendText(theEvent.convertToFile());
        return String.format(" Got it. I've added this task: \n" +
                "   %s\n" +
                " Now you have %d tasks in the list.", theEvent.getCurrentStatus(), taskList.size());
    }

    /**
     * Print the bye description
     * @return stored bye description
     */
    public String byeHandler() {
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Handle deletion of tasks from the task list
     * @param inputCommand Input Command of delete to the chat bot
     * @param taskList Task list of all recorded tasks
     * @return String description of the current status of task list and delete tasks
     */
    static String deleteHandler(String inputCommand, List<Task> taskList) {
        Task theRemovedTask = taskList.remove(Integer.parseInt(inputCommand.substring(7))-1);

        //TODO find a way to remove text, current method doesnt work
        FileEditor.removeText(theRemovedTask.convertToFile());
        return String.format(" Noted. I've removed this task:\n" +
                "   %s\n" +
                " Now you have %d tasks in the list.",theRemovedTask.getCurrentStatus(), taskList.size());
    }
    //add ability to filter for date time using predicate
    static void filterPrintTasks(String inputCommand,List<Task> taskList) {
        //get the date time string from the initial string
        String dateTime = inputCommand.substring("list-due ".length());

        //get the date time object for comparison
        LocalDateTime filterDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));

        //check that the date and time is the same before printing
        Predicate<LocalDateTime> dateTimePredicate = x -> x.equals(filterDateTime);

        //print out the filtered items
        int count = 1;
        System.out.println(" Here are the tasks in your filtered list:");
        for (Task t: taskList) {
            if (t.dueDateTime != null && dateTimePredicate.test(t.dueDateTime)) {
                System.out.println("  "+ count + ". "+ t.getCurrentStatus());
                count++;
            }
        }
    }
}
