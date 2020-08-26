import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Butler {

    private static void addTask(String input, ArrayList<Task> taskList, Boolean isNew) {
        String[] inputList = input.split(" ", 2);
        String reply = "";
        Task addedTask;
        String summary;
        Boolean isValidCommand = false;
        try {
            switch (inputList[0]) {
                case "todo":
                    try {
                        addedTask = new ToDoTask(inputList[1]);
                        taskList.add(addedTask);
                        reply += "\nI have added your ToDo task as follows:\n"
                                + "Added: " + addedTask + "\n";
                        isValidCommand = true;
                    } catch (Exception e) {
                        System.out.println("\nExcuse me, " +
                                "but I will need a description for the ToDo task given.\n");
                    }
                    break;

                case "deadline":
                    try {
                        summary = inputList[1].split(" /by ", 2)[0];
                        String deadline = inputList[1].split(" /by ", 2)[1];
                        addedTask = new DeadlineTask(summary, deadline);
                        taskList.add(addedTask);
                        reply += "\nI have added your Deadline task as follows:\n"
                                + "Added: " + addedTask + "\n";
                        isValidCommand = true;
                    } catch (Exception e) {
                        System.out.println("\nExcuse me, " +
                                "please provide a summary and deadline using the tag /by.\n");
                    }
                    break;

                case "event":
                    try {
                        summary = inputList[1].split(" /at ", 2)[0];
                        String time = inputList[1].split(" /at ", 2)[1];
                        addedTask = new EventTask(summary, time);
                        taskList.add(addedTask);
                        reply += "\nI have added your Event task as follows:\n"
                                + "Added: " + addedTask + "\n";
                        isValidCommand = true;
                    } catch (Exception e) {
                        System.out.println("\nExcuse me, " +
                                "please provide a summary and time of event using the tag /at.\n");
                    }
                    break;

                default:
                    reply += "\nI am sorry, but I do not understand this command.\n";

            }

            if (isNew && isValidCommand) {
                System.out.println(reply);
                writeToFile(input);
            }
        } catch (Exception e) {
            System.out.println("\nInvalid command given.\n");
        }
    }

    private static void deleteTask(String input, ArrayList<Task> taskList, Boolean isNew) {
        String reply;
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            taskList.remove(index-1);
            if (isNew) {
                reply = "\nTask " + index + " has been deleted.\n";
                System.out.println(reply);
                writeToFile(input);
            }
        } catch (Exception e) {
            System.out.println("\nPlease provide a valid index.\n");
        }
    }

    private static void completeTask(String input, ArrayList<Task> taskList, Boolean isNew) {
        String[] indexList = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);
        String reply = "\n";

        for (String index : indexList) {
            try {
                int i = Integer.parseInt(index);
                taskList.get(i - 1).markComplete();
                if (isNew) {
                    reply += "Task " + i + " has been marked as complete.\n";
                    System.out.println(reply);
                    writeToFile(input);
                }
            } catch (Exception e) {
                reply += "Please give a valid index. \""
                        + index + "\" is not a valid index.\n";
                System.out.println(reply);
            }
        }

        if (indexList.length == 0) {
            reply += "No index was given . Please provide a valid index.\n";
            System.out.println(reply);
        }
    }

    private static void printTaskList(ArrayList<Task> taskList) {
        String listString = "\nHere are your list of tasks, Master.\n"
                + "You have " + taskList.size()
                + (taskList.size() > 1 ? " tasks" : " task")
                + " in total.\n";
        int index = 0;
        for (Task task : taskList) {
            index++;
            listString += "\n" + index + ": " + task;
        }

        System.out.println(listString + "\n");
    }

    private static void readTaskList(String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String input = s.nextLine();
            if (input.split(" ")[0].equals("done")) {
                completeTask(input, taskList, false);

            } else if (input.split(" ")[0].equals("delete")) {
                deleteTask(input, taskList, false);

            } else {
                addTask(input, taskList, false);
            }
        }
    }

    private static void writeToFile(String textToAdd) throws IOException {
        String filePath = "./data/taskList.txt";
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    public static void main(String[] args) {

        // Greetings
        String greetings = "Welcome! I am your personal manager, Butler.\n"
                + "How may I help you today, Master?\n";
        System.out.println(greetings);

        ArrayList<Task> taskList = new ArrayList<>();
        String listLocation = "./data/taskList.txt";

        try {
            readTaskList(listLocation, taskList);
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectory(Paths.get("./data/"));
                Files.createFile(Paths.get(listLocation));
            } catch (IOException f) {
                System.out.println("Code should never reach here." + f.getMessage());
            }
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Reply Loop
        while(!input.equals("bye")) {

            if (input.equals("list")) {
                printTaskList(taskList);

            } else if (input.split(" ")[0].equals("done")) {
                completeTask(input, taskList, true);

            } else if (input.split(" ")[0].equals("delete")) {
                deleteTask(input, taskList, true);

            } else {
                addTask(input, taskList, true);
            }

            input = sc.nextLine();
        }

        sc.close();

        // Farewells
        String farewells = "\nIt is my honor to have served you.\n"
                + "Please come back again.";
        System.out.println(farewells);
    }
}
