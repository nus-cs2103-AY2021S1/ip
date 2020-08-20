import exception.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printDivider() {
        System.out.println("          ____________________________________________________________");
    }
    public static void printOutput(String output) {
        String[] splitted = output.split("\n");
        for(String line : splitted) {
            System.out.println("          " + line);
        }
    }

    public static void printWelcome() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        printOutput("Hi! I'm Duke \nWhat can I do for you?");
        printDivider();
    }

    public static void printGoodbye() {
        printDivider();
        printOutput("Bye. See you again next time!" );
        printDivider();
    }

    public static void printAddedTask(String task, int numberOfTask) {
        printDivider();
        printOutput("Got it. I've added this task: ");
        printOutput(task);
        printOutput("Now you have " + numberOfTask + " tasks in the list.");
        printDivider();
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        printDivider();
        printOutput("Here are the tasks in your list:");
        for(int index = 0; index < taskList.size(); index++) {
            printOutput((index + 1) + ": " + taskList.get(index));
        }
        printDivider();
    }

    public static void printCompleteTask(String task) {
        printDivider();
        printOutput("Nice! I've marked this task as done:");
        printOutput(task);
        printDivider();
    }

    public static void checkCommands (String command) throws InvalidCommandException {
        if (!command.equals("todo")
            && !command.equals("deadline")
            && !command.equals("event")
            && !command.equals("list")
            && !command.equals("done")) {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void checkDescription (String[] task, String type) throws EmptyDescriptionException {
        String usage = "Please input using the format: " +
            (type.equals("todo") ? "todo <todo_desc>" :
            type.equals("deadline") ? "deadline <deadline_desc> /by <time>" :
                "event <event_desc> /at <time>");
        if (task.length < 2 || task[1].trim().equals("")) {
            throw new EmptyDescriptionException("Please specify the task description\n" + usage);
        }
    }

    public static void checkTime (String[] desc, String type) throws EmptyTimeException {
        String usage = "Please input using the format: " +
            (type.equals("todo") ? "todo <todo_desc>" :
                type.equals("deadline") ? "deadline <deadline_desc> /by <time>" :
                    "event <event_desc> /at <time>");
        if (desc.length < 2 || desc[1].trim().equals("")) {
            throw new EmptyTimeException("Please specify the time for the task\n" + usage);
        }
    }

    public static void checkIndex (String[] inputs, int numberOfTask) throws InvalidIndexException {
        String usage = (numberOfTask > 1 ? "\nInput a number between 1 - " + numberOfTask : "");
        if(inputs.length < 2 ||
            inputs[1].trim().equals("") ||
            Integer.parseInt(inputs[1]) < 1 ||
            Integer.parseInt(inputs[1]) > numberOfTask) {
            throw new InvalidIndexException("Please input a valid index of task" + usage );
        }
    }

    public static void main(String[] args) {
        printWelcome();

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printGoodbye();
                break;
            } else if (input.equals("list")) {
                printTaskList(taskList);
            } else {
                String[] inputs = input.split("\\s+", 2);
                try {
                    checkCommands(inputs[0].trim());
                    if (inputs[0].trim().equals("done")) {
                        try {
                            checkIndex(inputs, taskList.size());
                            int index = Integer.parseInt(inputs[1]) - 1;
                            taskList.set(index, taskList.get(index).completeTask());
                            printCompleteTask(taskList.get(index).toString());
                        } catch (InvalidIndexException ex) {
                            printDivider();
                            printOutput(ex.getMessage());
                            printDivider();
                        }
                    } else {
                        if (inputs[0].trim().equals("todo")){
                            try {
                                checkDescription(inputs, inputs[0].trim());
                                taskList.add(new Todo(inputs[1]));
                                printAddedTask(taskList.get(taskList.size() - 1).toString(), taskList.size());
                            } catch (EmptyDescriptionException ex) {
                                printDivider();
                                printOutput(ex.getMessage());
                                printDivider();
                            }
                        } else if (inputs[0].trim().equals("deadline")) {
                            try {
                                checkDescription(inputs, inputs[0].trim());
                                String temp = " " + inputs[1];
                                String[] desc = temp.split("/by", 2);
                                inputs[1] = desc[0];
                                checkDescription(inputs, inputs[0].trim());
                                checkTime(desc, inputs[0].trim());
                                taskList.add(new Deadline(desc[0].trim(), desc[1].trim()));
                                printAddedTask(taskList.get(taskList.size() - 1).toString(), taskList.size());
                            } catch (EmptyDescriptionException ex) {
                                printDivider();
                                printOutput(ex.getMessage());
                                printDivider();
                            } catch (EmptyTimeException ex) {
                                printDivider();
                                printOutput(ex.getMessage());
                                printDivider();
                            }
                        } else {
                            try {
                                checkDescription(inputs, inputs[0].trim());
                                String temp = " " + inputs[1];
                                String[] desc = temp.split("/at", 2);
                                inputs[1] = desc[0];
                                checkDescription(inputs, inputs[0].trim());
                                checkTime(desc, inputs[0].trim());
                                taskList.add(new Event(desc[0].trim(), desc[1].trim()));
                                printAddedTask(taskList.get(taskList.size() - 1).toString(), taskList.size());
                            } catch (EmptyDescriptionException ex) {
                                printDivider();
                                printOutput(ex.getMessage());
                                printDivider();
                            } catch (EmptyTimeException ex) {
                                printDivider();
                                printOutput(ex.getMessage());
                                printDivider();
                            }
                        }
                    }
                } catch (InvalidCommandException ex) {
                    printDivider();
                    printOutput(ex.getMessage());
                    printDivider();
                }
            }
        }
    }
}
