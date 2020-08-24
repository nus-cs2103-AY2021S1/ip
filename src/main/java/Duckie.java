import java.io.File;
import java.io.IOException;
import java.net.IDN;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class Duckie {
    private static ArrayList<Task> lst = new ArrayList<>();

    private static void displayList() throws DuckieException{
        if (lst.size() == 0) {
            throw new DuckieNoListException();
        }

        BotResponses.displayListReply(lst);
    }

    private static void addTask(String input, String type) throws DuckieException{
        Task t1;
        try {
            if (type.equals("todo")) {
                String todo = input.split(" ", 2)[1];
                t1 = new Todo(todo);
            } else {
                String[] splitted = input.split("/");
                String time = splitted[1].split(" ", 2)[1];
                String description = splitted[0].split(" ", 2)[1];
                if (type.equals("deadline")) {
                    t1 = new Deadline(description, time);
                } else { //This will be Event task.
                    t1 = new Event(description, time);
                }
            }
        } catch (Exception e) {
            throw new DuckieNoInfoException();
        }
        lst.add(t1);
        BotResponses.addTaskReply(t1, lst);
    }

    private static void checkTask(int ind) {
        Task t1 = lst.get(ind - 1);
        t1.checked();
        BotResponses.checkTaskReply(t1);
    }

    public static void deleteTask(int ind) {
        Task t1 = lst.get(ind - 1);
        lst.remove(ind - 1);
        BotResponses.deleteTaskReply(t1);
    }

    //Check if a String only
    private static boolean is_word(String s) {
        return (s.length() > 0 && s.split("\\s+").length == 1);
    }

    private static void serve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String initialInput = sc.nextLine();
                String input = initialInput.strip();
                System.out.println(input);
                if (input.equalsIgnoreCase("bye")) {
                    System.out.print(BotResponses.getHorizL());
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    Duckie.displayList();
                } else if (input.toLowerCase().indexOf("done") == 0
                        || input.toLowerCase().indexOf("delete") == 0) {
                    if (lst.size() == 0) {
                        throw new DuckieNoListException();
                    } else if (is_word(input)) {
                        throw new DuckieNoInfoException();
                    }
                    int ind = Integer.parseInt(input.split(" ")[1]);

                    if (lst.size() < ind) {
                        throw new DuckieNoIndexException();
                    }

                    if (input.toLowerCase().indexOf("done") == 0) {
                        Duckie.checkTask(ind);
                    } else {
                        Duckie.deleteTask(ind);
                    }
                } else if (input.toLowerCase().indexOf("todo") == 0) {
                    Duckie.addTask(input, "todo");
                } else if (input.toLowerCase().indexOf("deadline") == 0) {
                    Duckie.addTask(input, "deadline");
                } else if (input.toLowerCase().indexOf("event") == 0) {
                    Duckie.addTask(input, "event");
                } else {
                    throw new DuckieInvalidCommandException();
                }
            } catch (DuckieException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static File openFile() {
        String cwd = System.getProperty("user.dir");
        Path dirPath = Paths.get(cwd, "data");
        Path filePath = Paths.get(cwd, "data", "duke.txt");
        try {
            File file = new File(String.valueOf(filePath));
            if (!file.exists()) {
                File dir = new File(String.valueOf(dirPath));
                if (!dir.exists()) {
                    if (dir.mkdirs() && file.createNewFile()) {
                        System.out.println("Memory File created successfully!");
                    } else {
                        System.out.println("Quack! Unable to create file!");
                    }
                } else {
                    if (file.createNewFile()) {
                        System.out.println("Memory File created successfully!");
                    } else {
                        System.out.println("Quack! Unable to create file!");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Quack! Encounter problem while loading data!")
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        BotResponses.intro();
        Duckie.serve();
        BotResponses.ending();
    }
}
