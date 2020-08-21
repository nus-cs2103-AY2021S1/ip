import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static final List<Task> storage = new ArrayList<>();
    private static final String border = "____________________________________________________________\n";
    private static final String pathname = System.getProperty("user.dir")
            + File.separator + "data" + File.separator + "duke.txt";
    protected static File file = new File(pathname);

    public static boolean checkBye(String s) {
        return s.equals("bye");
    }

    public static void exitLine() {
        System.out.println(border + "Bye. Hope to see you again soon!\n" + border);
    }

    public static boolean checkList(String s) {
        return s.equals("list");
    }

    public static void displayList() {
        int listLen = storage.size();
        System.out.println(border.replace("\n", ""));
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= listLen; i++) {
            Task curr = storage.get(i - 1);
            System.out.println(i + "." + curr);
        }
        System.out.println(border);
    }

    public static void addTask(String s, String next) throws DukeException {
        Task toAdd = null;
        if (s.matches("todo|deadline|event|done|delete") && next.equals("")) {
            throw new DukeException("OOPS!!! The description of " + s + " cannot be empty.");
        }

        switch (s) {
            case "todo": {
                ToDo todo = new ToDo(next);
                storage.add(todo);
                toAdd = todo;
                break;
            }
            case "deadline": {
                if (next.contains("/by ")) {
                    String[] ls = next.split(" /by ");
                    Deadline deadline = new Deadline(ls[0], ls[1]);
                    storage.add(deadline);
                    toAdd = deadline;
                } else {
                    throw new DukeException("Sorry, please specify expected deadline after \"/by\".");
//                    System.out.println(
//                            border + "Sorry, please specify expected deadline after \"/by\"\n"
//                            + border
//                    );
                }
                break;
            }
            case "event": {
                if (next.contains("/at ")) {
                    String[] ls = next.split(" /at ");
                    Event event = new Event(ls[0], ls[1]);
                    storage.add(event);
                    toAdd = event;
                } else {
                    throw new DukeException("Sorry, please specify event date after \"/at\".");
//                    System.out.println(
//                            border + "Sorry, please specify event date after \"/at\"\n"
//                            + border
//                    );
                }
                break;
            }
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }

        System.out.println(
                border + "Got it. I've added this task:\n"
                        + "  " + toAdd + "\n"
                        + "Now you have " + storage.size() + " tasks in the list.\n"
                        + border
        );
    }

    public static boolean checkDone(String s) {
        return s.equals("done");
    }

    public static void doneTask(String s) throws DukeException {
        if (s.equals("")) {
            try {
                addTask("done", "");
            } catch (DukeException e) {
                System.out.println(border + e.getMessage() + "\n" + border);
            }
            return;
        }

        int i = Integer.parseInt(s);
        if (i < 1 || i > storage.size()) {
            throw new DukeException("You have entered an invalid number: " + i
                                    + ". Please try again.");
        } else {
            Task t = storage.get(i - 1);
            Task completed = t.setDone(true);
            storage.set(i - 1, completed);
            System.out.println(
                    border + "Nice! I've marked this task as done:\n" + "  "
                            + completed + "\n" + border
            );
        }
    }

    public static boolean checkDel(String s) {
        return s.equals("delete");
    }

    public static void delTask(String s) throws DukeException {
        if (s.equals("")) {
            try {
                addTask("delete", "");
            } catch (DukeException e) {
                System.out.println(border + e.getMessage() + "\n" + border);
            }
            return;
        }

        int i = Integer.parseInt(s);
        if (i < 1 || i > storage.size()) {
            throw new DukeException("You have entered an invalid number: " + i
                    + ". Please try again.");
        } else {
            Task t = storage.get(i - 1);
            storage.remove(i - 1);
            System.out.println(
                    border + "Noted. I've removed this task:\n" + "  "
                    + t + "\n"
                    + "Now you have " + storage.size() + " tasks in the list.\n" + border
            );
        }
    }

    public static void fileCheck() {
        try {
            File f = new File(pathname);
            f.getParentFile().mkdirs();

            if (!f.createNewFile()) {
                loadFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong! Backup could not be made.");
        }
}

    public static void saveFile() {
        try {
            FileWriter fw = new FileWriter(pathname);
            List<Task> storageCopy = storage;
            String split = "_";
            int index = 0;

            while (!storageCopy.isEmpty()) {
                Task curr = storageCopy.remove(index);
                String currString = curr.toString();
                String type = currString.substring(currString.indexOf("[") + 1, currString.indexOf("]"));
                boolean completed = curr.isDone();
                String name = curr.getName();

                fw.write(type + split);
                fw.write(completed + split);
                fw.write(name + split);

                switch(type) {
                    case "E": {
                        Event event = (Event) curr;
                        fw.write(event.getAt() + System.lineSeparator());
                        break;
                    }
                    case "D": {
                        Deadline deadline = (Deadline) curr;
                        fw.write(deadline.getBy() + System.lineSeparator());
                        break;
                    }
                    default: {
                        fw.write("mark" + System.lineSeparator());
                    }
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops. Something went wrong while saving data.");
        }

    }

    public static void loadFile() {
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String curr = scan.nextLine();
                String[] data = curr.split("_");
                String type = data[0];
                boolean done = Boolean.parseBoolean(data[1]);
                String name = data[2];
                String time = data[3];

                switch (type) {
                    case "E": {
                        storage.add(new Event(name, done, time));
                        break;
                    }
                    case "D": {
                        storage.add(new Deadline(name, done, time));
                        break;
                    }
                    default: {
                        storage.add(new ToDo(name, done));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateFile() {
        file.delete();
        saveFile();
        loadFile();
    }

    public static void startUp() {
        if (!file.exists() || file.length() == 0) {
            System.out.println(
                    border + "Hello! I'm Duke\n"
                    + "What can I do for you?\n" + border
            );
        } else {
            fileCheck();
            System.out.println(
                    border + "Well come back!\n" + "You still have "
                    + storage.size() + " tasks left to clear.\n" + border
            );
        }
    }

    public static void run() {
        Scanner scan = new Scanner(System.in);
        startUp();
        while (scan.hasNext()) {
            String test = scan.next();
            if (checkBye(test)) {
                exitLine();
                break;
            } else {
                String next = scan.nextLine().replaceFirst(" ", "");
                if (checkList(test)) {
                    displayList();
                } else if (checkDone(test)) {
                    try {
                        doneTask(next);
                        updateFile();
                    } catch (DukeException e) {
                        System.out.println(border + e.getMessage() + "\n" + border);
                    }
                } else if (checkDel(test)) {
                    try {
                        delTask(next);
                        updateFile();
                    } catch (DukeException e) {
                        System.out.println(border + e.getMessage() + "\n" + border);
                    }
                } else {
                    try {
                        addTask(test, next);
                        updateFile();
                    } catch (DukeException e) {
                        System.out.println(border + e.getMessage() + "\n" + border);
                    }
                }
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        run();
    }
}
