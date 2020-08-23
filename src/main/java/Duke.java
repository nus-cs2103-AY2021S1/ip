import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " .d8888b.  888               888    888                  888888b.            888    \n" +
                "d88P  Y88b 888               888    888                  888  \"88b           888    \n" +
                "888    888 888               888    888                  888  .88P           888    \n" +
                "888        88888b.   8888b.  888888 888888 888  888      8888888K.   .d88b.  888888 \n" +
                "888        888 \"88b     \"88b 888    888    888  888      888  \"Y88b d88\"\"88b 888    \n" +
                "888    888 888  888 .d888888 888    888    888  888      888    888 888  888 888    \n" +
                "Y88b  d88P 888  888 888  888 Y88b.  Y88b.  Y88b 888      888   d88P Y88..88P Y88b.  \n" +
                " \"Y8888P\"  888  888 \"Y888888  \"Y888  \"Y888  \"Y88888      8888888P\"   \"Y88P\"   \"Y888 \n" +
                "                                                888                                 \n" +
                "                                           Y8b d88P                                 \n" +
                "                                            \"Y88P\"                                  ";
        System.out.println("Hello from\n" + logo);
        String lines = "    ____________________________________________________________";
        String defaultGreeting = lines + "\n" + "     Hello! I'm Chatty Bot \n" + "     What can I do for you?\n" + lines + "\n";
        System.out.println(defaultGreeting);
        Scanner sc = new Scanner(System.in);
        List<Task> currentList = new ArrayList<>();
        String input = sc.nextLine();
        while (!input.equals("bye")){
            try{
                if (input.contains("done")) {
                    String[] doneInputs = input.split(" ");
                    if (doneInputs.length == 1) {
                        throw new invalidCommand("OOPS!!! Please enter at least 1 task number");
                    } else if (doneInputs.length > 2) {
                        throw new invalidCommand("OOPS!!! Please enter 1 task number only");
                    } else if (doneInputs.length == 2) {
                        try{
                            Integer.parseInt(doneInputs[1]);
                            int taskToDo = Integer.parseInt(doneInputs[1]);
                            currentList.get(taskToDo - 1).markDone();
                            System.out.println(lines);
                            System.out.println("     Nice! I've marked this task as done:");
                            System.out.println("       " + currentList.get(taskToDo - 1));
                            System.out.println(lines);
                        } catch(NumberFormatException ex) {
                            throw new invalidCommand("Please enter the task number instead of task name.");
                        } catch (IndexOutOfBoundsException ex) {
                            throw new invalidCommand("Please enter a valid task number.");
                        }
                    }
                } else if (input.contains("todo")) {
                    //process todo task
                    try {
                        String taskToDo = input.substring(5);
                        if (taskToDo.length() == 0) {
                            throw new invalidCommand("OOPS!!! Please specify your task.");
                        }
                        ToDo newToDo = new ToDo(taskToDo);
                        currentList.add(newToDo);
                        System.out.println(lines + "\n" + "     Got it. I've added this task:");
                        System.out.println("       " + newToDo);
                        System.out.println("     Now you have " + String.valueOf(currentList.size()) + " task(s) in the list.");
                        System.out.println(lines);
                    } catch(StringIndexOutOfBoundsException ex) {
                        throw new invalidCommand("OOPS!!! Please specify your task.");
                    }
                } else if (input.contains("deadline")) {
                    try {
                        String deadlineInput = input.substring(9);
                        int indexSeparator = deadlineInput.indexOf("/by");
                        if (indexSeparator == -1) {
                            throw new invalidCommand("Please include your deadline using /by !");
                        }
                        String deadlineTaskName = deadlineInput.substring(0, indexSeparator - 1);
                        String deadlineTime = deadlineInput.substring(indexSeparator + 4);
                        LocalDate deadlineDate = LocalDate.parse(deadlineTime);
                        Deadline newDeadline = new Deadline(deadlineTaskName, deadlineDate);
                        currentList.add(newDeadline);
                        System.out.println(lines + "\n" + "     Got it. I've added this task:");
                        System.out.println("       " + newDeadline);
                        System.out.println("     Now you have " + String.valueOf(currentList.size()) + " task(s) in the list.");
                        System.out.println(lines);
                    } catch (StringIndexOutOfBoundsException ex) {
                        throw new invalidCommand("OOPS!!! Please specify your task.");
                    }
                } else if (input.contains("event")) {
                    try {
                        String eventInput = input.substring(6);
                        int indexSeparator = eventInput.indexOf("/at");
                        if (indexSeparator == -1) {
                            throw new invalidCommand("Please include your event date using /at !");
                        }
                        String eventTaskName = eventInput.substring(0, indexSeparator - 1);
                        String eventTime = eventInput.substring(indexSeparator + 4);
                        LocalDate eventDate = LocalDate.parse(eventTime);
                        Event newEvent = new Event(eventTaskName, eventDate);
                        currentList.add(newEvent);
                        System.out.println(lines + "\n" + "     Got it. I've added this task:");
                        System.out.println("       " + newEvent);
                        System.out.println("     Now you have " + String.valueOf(currentList.size()) + " task(s) in the list.");
                        System.out.println(lines);
                    } catch (StringIndexOutOfBoundsException ex) {
                        throw new invalidCommand("OOPS!!! Please specify your task.");
                    }
                } else if (input.contains("list")){
                    if (input.split(" ").length > 1) {
                        throw new invalidCommand("You have to view your entire to-do list!");
                    }
                    //list out items in to-do list
                    System.out.println(lines);
                    System.out.println("     Here are the tasks in your list:");
                    for(int i = 0; i < currentList.size(); i++) {
                        String currentLine = "      "+ (i + 1) + ". " + currentList.get(i);
                        System.out.println(currentLine);
                    }
                    System.out.println(lines);
                } else if (input.contains("delete")) {
                    try {
                        String[] deleteInput = input.split(" ");
                        if (deleteInput.length > 2) {
                            throw new invalidCommand("Please only input 1 task number!");
                        }
                        int deleteIndex = Integer.parseInt(deleteInput[1]);
                        Task removedTask = currentList.remove(deleteIndex - 1);
                        System.out.println(lines);
                        System.out.println("     Alright, the following task has been removed");
                        System.out.println("     " + removedTask);
                        System.out.println("     Now you have " + String.valueOf(currentList.size()) + " task(s) in the list.");
                        System.out.println(lines);
                    } catch(IndexOutOfBoundsException ex) {
                        throw new invalidCommand("Please enter a valid task number.");
                    } catch (NumberFormatException ex) {
                        throw new invalidCommand("Please enter a valid task number");
                    }
                } else {
                    throw new invalidCommand("OOPS!!! I don't know what that means :-(");
                }
            } catch(invalidCommand ex) {
                System.out.println(lines);
                System.out.println("     "  + ex);
                System.out.println(lines);
            }
            input = sc.nextLine();
        }
        //greet user goodbye
        String end_Greeting = lines + "\n" + "     Bye. Hope to see you again soon!\n" + lines;
        System.out.println(end_Greeting);

    }
}
