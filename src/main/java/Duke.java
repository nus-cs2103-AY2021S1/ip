import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final static ArrayList<Task> list = new ArrayList<>();

    public static void loadDataFile() throws IOException {
        File file = new File("data/duke.txt");
        boolean hasFolder = file.getParentFile().mkdirs();
        boolean hasFile = file.createNewFile();
        if (!hasFile && !hasFolder) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] details = task.split(" \\| ");
                if (details[0].equals("T")) {
                    list.add(new ToDo(details[2]));
                } else if (details[0].equals("D")) {
                    list.add(new Deadline(details[2], details[3]));
                } else {
                    list.add(new Event(details[2], details[3]));
                }
                if (details[1].equals("1")) {
                    list.get(list.size() - 1).done();
                }
            }
            sc.close();
        }
    }

    public static void updateDataFile() throws IOException {
        File directory = new File("");
        FileWriter writer = new FileWriter(directory.getAbsolutePath() + "/data/duke.txt");
        PrintWriter print_line = new PrintWriter(writer);
        for (Task task : list) {
            String[] details = new String[4];
            details[2] = task.name;
            if (task instanceof ToDo) {
                details[0] = "T";
            } else if (task instanceof Deadline) {
                details[0] = "D";
                details[3] = ((Deadline) task).by;
            } else {
                details[0] = "E";
                details[3] = ((Event) task).duration;
            }
            if (task.isDone) {
                details[1] = "1";
            } else {
                details[1] = "0";
            }
            String textLine = details[0] + " | " + details[1] + " | " + details[2]
                    + " | " + details[3];
            print_line.printf("%s" + "%n", textLine);
        }
        print_line.close();
    }

    public static void start() {
        try {
            loadDataFile();
            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?");
        } catch (java.io.IOException error) {
            error.printStackTrace();
            System.exit(-1);
        }
    }

    public static void reply(String input) {
        if (input.trim().equals("list")) {
            if (list.size() == 0) {
                System.out.println("There are no tasks in your list");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < list.size() + 1; i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            }
        } else if (input.startsWith("done ") || input.equals("done")) {
            try {
                markAsDone(input);
                updateDataFile();
            } catch (DukeException | IOException error) {
                System.out.println(error.getMessage());
            } catch (NumberFormatException error) {
                System.out.println("Please provide a valid task number to mark as done");
            } catch (IndexOutOfBoundsException error) {
                System.out.println("This task is not in your list");
            }
        } else if (input.startsWith("delete ") || input.equals("delete")) {
            try {
                deleteTask(input);
                updateDataFile();
            } catch (DukeException | IOException error) {
                System.out.println(error.getMessage());
            } catch (NumberFormatException error) {
                System.out.println("Please provide a valid task number to delete");
            } catch (IndexOutOfBoundsException error) {
                System.out.println("This task is not in your list");
            }
        } else {
            try {
                addTask(input);
                updateDataFile();
            } catch (DukeException | IOException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    public static void addTask(String input) throws DukeException {
        Task task;
        if (input.startsWith("todo ") || input.equals("todo")) {
            if (input.length() < 6 || input.substring(5).trim().isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String name = input.substring(5).trim();
            task = new ToDo(name);
        } else if (input.startsWith("deadline ") || input.equals("deadline")) {
            if (input.length() < 10 || input.substring(9).trim().isEmpty()) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String details = input.substring(9).trim();
            String[] split = details.split(" /by ");
            if (split.length != 2) {
                throw new DukeException("Please use the format: deadline (name) /by (when)");
            }
            String name = split[0];
            String by = split[1];
            task = new Deadline(name, by);
        } else if (input.startsWith("event ") || input.equals("event")) {
            if (input.length() < 7 || input.substring(6).trim().isEmpty()) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String details = input.substring(6).trim();
            String[] split = details.split(" /at ");
            if (split.length != 2) {
                throw new DukeException("Please use the format: event (name) /at (what time)");
            }
            String name = split[0];
            String duration = split[1];
            task = new Event(name, duration);
        } else {
            System.out.println("I'm sorry, but I don't know what that means.");
            return;
        }
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (list.size() > 1) {
            System.out.println("Now you have " + list.size() + " tasks in your list");
        } else {
            System.out.println("Now you have " + list.size() + " task in your list");
        }
    }

    public static void markAsDone(String input) throws DukeException {
        if (input.length() < 6 || input.substring(5).trim().isEmpty()) {
            throw new DukeException("Which task do you want to mark as done?");
        }
        String item = input.substring(5).trim();
        int index = Integer.parseInt(item) - 1;
        list.get(index).done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index));
    }

    public static void deleteTask(String input) throws DukeException {
        if (input.length() < 8 || input.substring(7).trim().isEmpty()) {
            throw new DukeException("Which task do you want to delete?");
        }
        String item = input.substring(7).trim();
        int index = Integer.parseInt(item) - 1;
        Task deletedTask = list.get(index);
        list.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("You now have " + list.size() + " tasks in your list");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            reply(input);
            input = sc.nextLine();
        }
        sc.close();
        exit();
    }
}