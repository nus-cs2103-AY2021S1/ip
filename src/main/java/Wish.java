import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Wish {
    private static String HOME = System.getProperty("user.home");
    private static Path SAVED_FILE_PATH = Paths.get(HOME, "ip", "data", "wish.txt");
    private static Path DATABASE_DIRECTORY_PATH = Paths.get(HOME, "ip", "data");

    public static void main(String[] args) {
        try {
            greet();
            getUserInput();
        } catch (WishException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void greet() {
        System.out.println("Hello! I am Wish\n" + "What can I do for you?");
    }

    private static void getUserInput() throws WishException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> database = loadFromDatabase();
        String command = sc.next();

        while (true) {
            switch (command) {
                case "bye":
                    onByeCommand(database);
                    break;

                case "list":
                    list(database);
                    break;

                case "done":
                    onDoneCommand(database, sc);
                    break;

                case "delete":
                    onDeleteCommand(database, sc);
                    break;

                case "todo":
                    onToDoCommand(database, sc);
                    break;

                case "deadline":
                    onDeadlineCommand(database, sc);
                    break;

                case "event":
                    onEventCommand(database, sc);
                    break;

                default:
                    noSuchCommand();
                    break;
            }

            command = sc.next();
        }
    }

    private static void onByeCommand(ArrayList<Task> database) {
        try {
            FileWriter fw = new FileWriter(SAVED_FILE_PATH.toString());

            for (int i = 0; i < database.size(); i++) {
                Task currentTask = database.get(i);

                if (currentTask instanceof ToDo) {
                    fw.write("T | " + (currentTask.getDoneStatus() ? "1" : "0") + " | " +
                            currentTask.getDescription() + "\n" );
                } else if (currentTask instanceof Deadline) {
                    fw.write("D | " + (currentTask.getDoneStatus() ? "1" : "0") + " | " +
                            currentTask.getDescription() + " | " + ((Deadline)currentTask).getDeadline() + "\n" );
                } else {
                    fw.write("E | " + (currentTask.getDoneStatus() ? "1" : "0") + " | " +
                        currentTask.getDescription() + " | " + ((Event)currentTask).getStartDate() + "\n" );
                }
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Goodbye! All the best and see you again soon!");
        System.exit(0);
    }

    private static void add(ArrayList<Task> database, Task task) {
        database.add(task);
        System.out.println("Looking good! I have added this task:");
        System.out.println(task);
        System.out.println("Now you have " + database.size() + " tasks in your list");
    }

    /**
     * Prints all the tasks in the database.
     */
    private static void list(ArrayList<Task> database) {
        if (database.size() == 0) {
            System.out.println("There are no tasks in the database");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < database.size(); i++) {
                System.out.println((i + 1) + ". " + database.get(i));
            }
        }
    }

    private static void onDeleteCommand(ArrayList<Task> database, Scanner sc) {
        if (database.size() == 0) {
            System.out.println("There are no tasks in the database to delete");
        } else {
            try {
                int index = sc.nextInt();

                if (index > database.size()) {
                    throw new Exception("Index cannot be greater than size of database");
                }

                Task removedTask = database.remove(index - 1);
                System.out.println("Noted! I have removed this task:");
                System.out.println(removedTask);
                System.out.println("Now you have " + database.size() + " tasks in your list");
            } catch (InputMismatchException e) {
                System.out.println("Index must be a number");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        sc.nextLine();
    }

    private static void onDoneCommand(ArrayList<Task> database, Scanner sc) {
        if (database.size() != 0) {
            try {
                int index = sc.nextInt();

                if (index > database.size()) {
                    throw new Exception("Index cannot be greater than size of database");
                }

                database.get(index - 1).markAsDone();
                System.out.println("Nice! I have marked this task as done!");
            } catch (InputMismatchException e) {
                System.out.println("Index must be a number");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There are no tasks in the database to mark as done");
        }

        sc.nextLine();
    }

    private static void onToDoCommand(ArrayList<Task> database, Scanner sc) {
        String description = sc.nextLine();

        if (description.equals("")) {
            try {
                throw new WishException("The description of a todo cannot be empty");
            } catch (WishException e) {
                System.out.println(e.getMessage());
            }
        } else {
            ToDo toDo = new ToDo(description.trim());
            add(database, toDo);
        }
    }

    private static void onDeadlineCommand(ArrayList<Task> database, Scanner sc) {
        String toParse = sc.nextLine();

        if (!toParse.equals("")) {
            try {
                String[] parsed = toParse.split("/by ");
                if (parsed.length == 1) {
                    throw new Exception("Please input the deadline in a valid format");
                } else {
                    Deadline deadline = new Deadline(parsed[0].trim(), parsed[1].trim());
                    add(database, deadline);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Task description and deadline cannot be empty");
        }
    }

    private static void onEventCommand(ArrayList<Task> database, Scanner sc) {
        String toParse = sc.nextLine();

        if (!toParse.equals("")) {
            try {
                String[] parsed = toParse.split("/at ");
                if (parsed.length == 1) {
                    throw new Exception("Please input the start and end timing in a valid format");
                } else {
                    Event event = new Event(parsed[0].trim(), parsed[1].trim());
                    add(database, event);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Task description, start time and end time cannot be empty");
        }
    }

    private static void noSuchCommand() {
        try {
            throw new WishException("Oops! I am sorry but I don't understand what that means");
        } catch (WishException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<Task> loadFromDatabase() throws WishException {
        boolean directoryExists = Files.exists(DATABASE_DIRECTORY_PATH);
        ArrayList<Task> database = new ArrayList<>(100);

        if (!directoryExists) {
            System.out.println("Oops! Folder where data is saved does not exists");
            File newFolder = new File(DATABASE_DIRECTORY_PATH.toString());
            boolean createdNewFolder = newFolder.mkdir();

            if (createdNewFolder) {
                System.out.println("I have created a new folder to store saved data");
            } else {
                throw new WishException("Could not create new directory to store saved data");
            }
        } else {
            if (Files.exists(SAVED_FILE_PATH)) {
                try {
                    System.out.println("I have loaded data from the database");
                    File f = new File(SAVED_FILE_PATH.toString());
                    Scanner s = new Scanner(f);

                    while (s.hasNext()) {
                        String currentLine = s.nextLine();
                        String[] parsed = currentLine.split(" \\| ");

                        switch (parsed[0]) {
                        case "T":
                            database.add(new ToDo(parsed[2], parsed[1].equals("1")));
                            break;

                        case "D":
                            database.add(new Deadline(parsed[2], parsed[1].equals("1"), parsed[3]));
                            break;

                        default:
                            database.add(new Event(parsed[2], parsed[1].equals("1"), parsed[3]));
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }
            }
        }

        return database;
    }
}
