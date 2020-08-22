package com.jacob.Duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    /**
     * Basic Driver code to get the inputs
     * @param args Basic syntax requirement
     */
    public static void main(String[] args) {
        List<Task> toDoList = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Omo!! hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            // all encompassing driver code
            handleAllCommands(input, toDoList);

            // checks for the next input
            input = sc.nextLine();
            }

        //handle the bye command when encountered
        System.out.println(" -----------------");
        System.out.println(byeHandler());
        System.out.println(" -----------------");
        sc.close();
        }


    /**
     * Handles all commands and send them to the correct eventHandler
     * 1) take input and split by white space
     * 2) use case to check command type
     * @param inputCommand Command issued to duke chat bot
     * @param taskList List of all recorded tasks
     */
    static void handleAllCommands(String inputCommand, List<Task> taskList) {
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

        //set task as done
        theTask.setDone();
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
     * Handles the todo Command and return the task description
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
        Task theTodo = new Todo(inputCommand.substring(4));
        taskList.add(theTodo);
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
        int breakpoint = inputCommand.indexOf("/");
        if (inputCommand.length() <= "deadline ".length() ) {
            throw new DukeException("☹ OOPS!!! The description of a Deadline cannot be empty.\n");
        } else if (breakpoint == -1) {
            throw new DukeException("Hey a deadline cannot have no actual date!!\n");
        }
        String tempString = inputCommand.substring("deadline".length(), breakpoint);
        String tempString2 = "(by: " + inputCommand.substring(breakpoint + 3) + ")";
        String result = tempString + tempString2;
        Task theDeadline = new Deadline(result);
        taskList.add(theDeadline);
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
        int breakpoint = inputCommand.indexOf("/");
        if (inputCommand.length() <= "event ".length() ) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be incomplete.");
        } else if (breakpoint == -1) {
            throw new DukeException("Hey, a event cannot have no actual date and time!!");
        }
        String tempString = inputCommand.substring("event".length(), breakpoint);
        String tempString2 = "(at: " + inputCommand.substring(breakpoint + 3) + ")";
        String result = tempString + tempString2;
        Task theEvent = new Event(result);
        taskList.add(theEvent);
        return String.format(" Got it. I've added this task: \n" +
                "   %s\n" +
                " Now you have %d tasks in the list.", theEvent.getCurrentStatus(), taskList.size());
    }

    /**
     * Print the bye description
     * @return stored bye description
     */
    static String byeHandler() {
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
        return String.format(" Noted. I've removed this task:\n" +
                "   %s\n" +
                " Now you have %d tasks in the list.",theRemovedTask.getCurrentStatus(), taskList.size());
    }
}
