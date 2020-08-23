import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String LINE = "    ____________________________________________________________";
    static String INDENT = "    ";
    static Scanner sc = new Scanner(System.in);
    private static String DATA_PATH =  "./data/data.txt";
    private static String DATA_FILE = "./data.txt";

    static ArrayList<Task> arr =  new ArrayList<Task>();

    private static void markAsDone(String input) throws IOException {
        String index = input.substring(5, input.length());
        int number = Integer.parseInt(index) - 1;
        arr.get(number).setDone();
        System.out.println(LINE);
        System.out.println(INDENT + "Task marked as done: ");
        System.out.println(String.format(INDENT + "%s", arr.get(number).getOutput()));
        System.out.println(LINE);
        saveFile();
    }

    private static void saveFile() throws IOException {
        File file = new File(DATA_PATH);

        try {
            FileWriter writer = new FileWriter(file);
            writer.close();

            for (Task t : arr) {
                FileWriter fileWriter = new FileWriter(file, true);
                fileWriter.write(t.writeToFile());
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printList() {
        System.out.println(LINE);
        System.out.println(INDENT + "Here are the tasks in your list:");
        for(int i = 0; i < arr.size(); i++) {
            System.out.println(String.format("%s%d. %s",INDENT,  i + 1, arr.get(i).getOutput()));
        }
        System.out.println(LINE);
    }

    static void numTask() {
        System.out.println(String.format("%sNow you have %d tasks in the list.", INDENT, arr.size()));
    }

    static void addTodo(String input) {
        try {
            ToDo temp = new ToDo(input.substring(5, input.length()));
            arr.add(temp);
            System.out.println(LINE);
            System.out.println(INDENT + "Todo added: ");
            System.out.println(INDENT + temp.getOutput());
            numTask();
            System.out.println(LINE);
            System.out.println();
        } catch (Exception e) {
            System.out.println(LINE);
            System.out.println(INDENT + " The description of a todo cannot be empty. Try again.");
            System.out.println(LINE);
        }
    }

    static void addDeadline(String input) {
        try {
            int dash = input.indexOf('/');
            Deadline temp = new Deadline(input.substring(9, dash),
                    input.substring(dash, input.length()));
            arr.add(temp);
            System.out.println(LINE);
            System.out.println(INDENT + "Deadline added:  ");
            System.out.println(INDENT + temp.getOutput());
            numTask();
            System.out.println(LINE);
            System.out.println();
        } catch(Exception e) {
            System.out.println(LINE);
            System.out.println(INDENT + "The description of a deadline cannot be empty. Try again.");
            System.out.println(LINE);
        }
    }

    static void addEvent(String input) {
        try {
            int dash = input.indexOf('/');
            Event temp = new Event(input.substring(6, dash),
                    input.substring(dash, input.length()));
            arr.add(temp);
            System.out.println(LINE);
            System.out.println(INDENT + "Event added:  ");
            System.out.println(INDENT + temp.getOutput());
            numTask();
            System.out.println(LINE);
            System.out.println();
        } catch(Exception e) {
            System.out.println(LINE);
            System.out.println(INDENT + "The description of a event cannot be empty. Try again.");
            System.out.println(LINE);
        }
    }

    private static void addNewTask(String input) throws IOException {
        if(input.indexOf("todo") == 0) {
            addTodo(input);
        } else if (input.indexOf("deadline") == 0) {
            addDeadline(input);
        } else {
            addEvent(input);
        }
        saveFile();
    }

    private static void removeEntry(String input) throws IOException {
        int temp = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
        System.out.println(temp);
        Task removed = arr.remove(temp);
        System.out.println(LINE);
        System.out.println(INDENT + "Tasked removed: ");
        System.out.println(INDENT + removed.getOutput());
         numTask();
        System.out.println(LINE);
        saveFile();
    }
    private static File getFile() {
        File route = new File("./data");
        if(!route.exists()) {
            route.mkdirs();
        }

        File file = new File(DATA_PATH);

        try {
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return file;
    }
    private static void loadSave() {
        try {
            Path path = Paths.get(DATA_PATH);
            Scanner sc = new Scanner(path);
            while(sc.hasNextLine()) {
                String[] parts = sc.nextLine().split("//");
                switch (parts[0]) {

                    case "T":
                        arr.add(new ToDo(parts[2], parts[1].equals("1")));
                        break;
                    case "E":
                        arr.add(new Event(parts[2], parts[1].equals("1"), parts[3]));
                        break;
                    case "D":
                        arr.add(new Deadline(parts[2], parts[1].equals("1"), parts[3]));
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadSave();
        boolean on = true;
        System.out.println(LINE);
        System.out.println(INDENT + "Hello! What can I do for you?");
        System.out.println(LINE);
        while(on) {
            try {
                String input = sc.nextLine();
                if (input.compareTo("bye") == 0) {
                    on = false;
                    System.out.println(LINE);
                    System.out.println(INDENT + "Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                } else if (input.indexOf("done") == 0) {
                    markAsDone(input);
                } else if (input.compareTo("list") == 0) {
                    printList();
                } else if(input.indexOf("delete") == 0) {
                    removeEntry(input);
                } else {
                    addNewTask(input);
                }
            } catch (Exception e) {
                System.out.println(LINE);
                System.out.println(INDENT + "I'm sorry, but I don't know what that means");
                System.out.println(LINE);
            }
        }
    }
}
