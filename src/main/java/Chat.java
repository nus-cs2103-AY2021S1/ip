import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Chat {
    private boolean isRunning;
    private NumberedList<Task> list;
    private static final String DATA_DIR_PATHNAME = "data/";
    private static final String DATA_FILE_PATHNAME = DATA_DIR_PATHNAME + "botbot.txt";

    Chat() {
        isRunning = true;
        list = new NumberedList<>();
        loadData();
        greet();
    }
    
    void loadData() {
        File dataDir = new File(DATA_DIR_PATHNAME);
        File dataFile = new File(DATA_FILE_PATHNAME);
        if (!dataDir.isDirectory()) {
            dataDir.mkdir();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!dataFile.isFile()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            readData();
        }
    }
    
    void readData() {
        try {
            FileInputStream file = new FileInputStream(DATA_FILE_PATHNAME);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArr = data.split("\\|");
                char taskType = dataArr[0].charAt(0);
                boolean isDone = dataArr[1].equals("1");
                String description = dataArr[2];
                if (taskType == Todo.TYPE_CODE) {
                    list.add(new Todo(description, isDone));
                } else if (taskType == Deadline.TYPE_CODE) {
                    String by = dataArr[3];
                    list.add(new Deadline(description, isDone, by));
                } else if (taskType == Event.TYPE_CODE) {
                    String at = dataArr[3];
                    list.add(new Event(description, isDone, at));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("    oops! your data file is missing!");
        }
    }
    
    void saveData() {
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATHNAME);
            for (Task task : list) {
                List<String> temp = new LinkedList<>();
                temp.add(String.valueOf(task.getType()));
                temp.add(task.getStatus());
                temp.add(task.getDescription());
                if (task instanceof Deadline) {
                    temp.add(task.getBy());
                } else if (task instanceof Event) {
                    temp.add(task.getAt());
                }
                String data = String.join("|", temp);
                fw.write(data + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void greet() {
        String logo = "\n.-. .-')                .-') _  .-. .-')                .-') _\n"
                + "\\  ( OO )              (  OO) ) \\  ( OO )              (  OO) )\n"
                + " ;-----.\\  .-'),-----. /     '._ ;-----.\\  .-'),-----. /     '._\n"
                + " | .-.  | ( OO'  .-.  '|'--...__)| .-.  | ( OO'  .-.  '|'--...__)\n"
                + " | '-' /_)/   |  | |  |'--.  .--'| '-' /_)/   |  | |  |'--.  .--'\n"
                + " | .-. `. \\_) |  | |  |   |  |   | .-. `. \\_) |  | |  |   |  |\n"
                + " | |  \\  |  \\ |  | |  |   |  |   | |  \\  |  \\ |  | |  |   |  |\n"
                + " | '--'  /   `'  '-'  '   |  |   | '--'  /   `'  '-'  '   |  |\n"
                + " `------'      `-----'    `--'   `------'      `-----'    `--'\n";
        System.out.println("helluu! I'm\n" + logo + "\nwhat would you like me to do?\n");
    }

    boolean isRunning() {
        return isRunning;
    }

    void handleInput(String input) throws EmptyTaskException, EmptyTaskNumberException,
            InvalidFormatException, NoSuchCommandException {
        if (input.equals("bye")) {
            exit();
        } else if (input.equals("list")) {
            list();
        } else if (input.length() >= 8 && input.substring(0, 7).equals("delete ")) {
            delete(input);
        } else if (input.equals("delete") || (input.length() >= 7 && input.substring(0, 7).equals("delete "))) {
            throw new EmptyTaskNumberException();
        } else if (input.length() >= 6 && input.substring(0, 5).equals("done ")) {
            markAsDone(input);
        } else if (input.equals("done") || (input.length() >= 5 && input.substring(0, 5).equals("done "))) {
            throw new EmptyTaskNumberException();
        } else if (input.length() >= 6 && input.substring(0, 5).equals("todo ")) {
            addToList(new Todo(input));
        } else if (input.equals("todo") || (input.length() >= 5 && input.substring(0, 5).equals("todo "))) {
            throw new EmptyTaskException("a todo");
        } else if (input.length() >= 10 && input.substring(0, 9).equals("deadline ")) {
            if (!input.contains(" /by ")) {
                throw new InvalidFormatException(Deadline.FORMAT);
            }
            addToList(new Deadline(input));
        } else if (input.equals("deadline")
                || (input.length() >= 9 && input.substring(0, 9).equals("deadline "))) {
            throw new EmptyTaskException("a deadline");
        } else if (input.length() >= 7 && input.substring(0, 6).equals("event ")) {
            if (!input.contains(" /at ")) {
                throw new InvalidFormatException(Event.FORMAT);
            }
            addToList(new Event(input));
        } else if (input.equals("event")
                || (input.length() >= 6 && input.substring(0, 6).equals("event "))) {
            throw new EmptyTaskException("an event");
        } else {
            throw new NoSuchCommandException();
        }
    }

    void addToList(Task task) {
        list.add(task);
        saveData();
        int numOfTasks = list.size();
        System.out.println(String.format("    ok! I've added this task:\n      %s\n    you now have %d task"
                + (numOfTasks > 1 ? "s" : "") + " in your list\n", task, numOfTasks));
    }

    void delete(String input) {
        try {
            int id = Integer.parseInt(input.substring("delete ".length())) - 1;
            Task task = list.get(id);
            list.remove(id);
            saveData();
            int numOfTasks = list.size();
            System.out.println(String.format("    ok! I've removed this task:\n      %s\n    you now have "
                    + "%d task" + (numOfTasks > 1 ? "s" : "") + " in your list\n", task, numOfTasks));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("    oops! invalid task number!\n");
        }
    }

    void list() {
        if (list.size() <= 0) {
            System.out.println("    your list is empty!\n");
        } else {
            System.out.println("    here's your list:\n" + list);
        }
    }

    void markAsDone(String input) {
        try {
            int id = Integer.parseInt(input.substring("done ".length())) - 1;
            Task task = list.get(id);
            task.markAsDone();
            saveData();
            System.out.println("    nice! I've marked this task as done:\n      " + task + "\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("    oops! invalid task number!\n");
        }
    }

    void exit() {
        System.out.println("    bye! see you soon!");
        isRunning = false;
    }
}