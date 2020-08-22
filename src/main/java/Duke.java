import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();


    private static void processEventOrDeadline(String input) throws EventException, DeadlineException {
        String[] temp = input.split("/");
        String[] commandTemp = temp[0].split(" ");
        String command = commandTemp[0];
        if (temp.length != 2) {
            if (command.equals("deadline")) {
                throw new DeadlineException("Both time description and description of a deadline must be filled!");
            } else if (command.equals("event")) {
                throw new EventException("Both time description and description of a event must be filled!");
            }
        }

        String[] timeTemp = temp[1].split(" ");
        String timeCommand = timeTemp[0];

        String description = "";
        for (int i = 1; i < commandTemp.length; i++) {
            description += commandTemp[i] + " ";
        }

        String timeDescription = "";
        for (int i = 1; i < timeTemp.length; i++) {
            if (i == timeTemp.length - 1) {
                timeDescription += timeTemp[i];
            } else {
                timeDescription += timeTemp[i] + " ";
            }
        }

        System.out.println("Got it. I've added this task:");

        if (command.equals("deadline")) {
            Task task = new Deadline(description, timeDescription);
            taskList.add(task);
            System.out.println(task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else if (command.equals("event")) {
            Task task = new Event(description, timeDescription);
            taskList.add(task);
            System.out.println(task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    private static void processToDo(String input) throws ToDoException {
        String[] temp = input.split(" ");

        String command = temp[0];
        String description = "";

        for(int i = 1; i < temp.length; i++) {
            description += temp[i] + " ";
        }

        if (description.equals("")) {
            throw new ToDoException("The description of a todo cannot be empty!");
        }

        Task task = new ToDo(description);
        taskList.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void processDelete(String input) throws DeletionException {
        String[] temp = input.split(" ");
        if (temp.length == 1) {
            throw new DeletionException("Please input index after delete! Example of input would be 'delete 1' which deletes 1st item from list");
        }
        if (temp.length > 2) {
            throw new DeletionException("Too many arguments! Example of input would be 'delete 1' which deletes 1st item from list");
        }

        String command = temp[0];
        int index = Integer.parseInt(temp[1]) - 1;

        if (index >= taskList.size() || index < 0) {
            throw new DeletionException("Item does not exist in list!");
        }

        Task task = taskList.get(index);
        taskList.remove(index);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void processDone(String input) throws DoneException {
        String[] temp = input.split(" ");
        if (temp.length == 1) {
            throw new DoneException("Please input number after done! Example of input would be 'done 2' checks item number 2 from list");
        }
        if (temp.length > 2) {
            throw new DoneException("Too many arguements! Example of input would be 'done 2' which checks item number 2 from list");
        }
        int listNumber = Integer.parseInt(temp[1]);
        if (listNumber > taskList.size()) {
            throw new DoneException("Item number " + listNumber + " does not exist in list!");
        }
        taskList.get(listNumber - 1).completeTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(listNumber - 1).toString());
    }

    private static void processList() {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : taskList) {
            System.out.println(index + ". " + task.toString());
            index++;
        }
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");

        DukeCSV dukeCSV = new DukeCSV();
        taskList = dukeCSV.read();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                // when user inputs bye
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                // when user wants to print list of task
                processList();
            } else if (input.startsWith("done")) {
                // when user completes task
                try {
                    processDone(input);
                } catch (DoneException e) {
                    System.out.println(e.getMessage());
                }
            } else if ((input.startsWith("deadline") || input.startsWith("event")) || input.startsWith("todo")) {
                // user trying to input to List
                if (input.startsWith("deadline") || input.startsWith("event")) {
                    // input starts with deadline or event
                    try {
                        processEventOrDeadline(input);
                    } catch (EventException | DeadlineException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.startsWith("todo")){
                    // input starts with todo
                    try {
                        processToDo(input);
                    } catch (ToDoException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if (input.startsWith("delete")) {
                // if input starts with delete
                try {
                    processDelete(input);
                } catch (DeletionException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                // invalid input
                try {
                    throw new InvalidInputException("I'm sorry, but I don't know what that means! :-(");
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // saves taskList to data/duke.csv
        try {
            dukeCSV.saveToCSV(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}