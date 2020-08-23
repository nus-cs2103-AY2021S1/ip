import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

import static java.lang.Integer.parseInt;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();
    private static final String BORDER = "__________________________________________________";

    private static void say(String s) {
        System.out.println(BORDER);
        System.out.println(s);
        System.out.println(BORDER);
    }

    private static void printList() throws DukeException {
        if (list.size() > 0) {
            System.out.println(BORDER);
            System.out.println("Here is your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + "." + list.get(i - 1));
            }
            System.out.println(BORDER);
        } else {
            throw(DukeException.emptyList());
        }
    }

    private static void printDone(String input) throws DukeException {
        try {
            String pattern = "(done\\s)(.+)";
            if (input.trim().matches(pattern)) {
                String number = input.substring(5);
                int index = parseInt(number) - 1;
                list.get(index).setDone();
                say("Marked this task as done:\n" + list.get(index));
            } else {
                throw(DukeException.emptyDesc("done"));
            }
        } catch (NumberFormatException e) {
            throw(DukeException.typeMismatch("done"));
        } catch (IndexOutOfBoundsException e) {
            throw(DukeException.outOfBounds());
        }
    }

    private static void handleToDo(String input) throws DukeException {
        String pattern = "(todo\\s)(.+)";
        if (input.trim().matches(pattern)) {
            String task = input.replaceAll(pattern, "$2");
            Task next = new ToDo(task);
            list.add(next);
            String text = "Added ToDo '" + task + "' to your list!";
            say(text);
        } else {
            throw(DukeException.emptyDesc("todo"));
        }
    }

    private static void handleDeadline(String input) throws DukeException {
        String basePattern = "(deadline\\s)(.+)";
        String completePattern = "(deadline\\s)(.+)\\s(/by\\s)(.+)";
        String missingTaskPattern = "(deadline\\s)(/by)((\\s(.*))*)";
        if (input.trim().matches(basePattern)) {
            if (input.trim().matches(completePattern)) {
                String task = input.replaceAll(completePattern, "$2");
                String time = input.replaceAll(completePattern, "$4");
                Task next = new Deadline(task, time);
                list.add(next);
                String text = "Added Deadline '" + task + "' to your list!";
                say(text);
            } else if (input.trim().matches(missingTaskPattern)) {
                throw(DukeException.missingTask());
            } else {
                throw(DukeException.missingTime("by"));
            }
        } else {
            throw(DukeException.emptyDesc("deadline"));
        }
    }

    private static void handleEvent(String input) throws DukeException {
        String basePattern = "(event\\s)(.+)";
        String completePattern = "(event\\s)(.+)\\s(/at\\s)(.+)";
        String missingTaskPattern = "(event\\s)(/at)((\\s(.*))*)";
        if (input.trim().matches(basePattern)) {
            if (input.trim().matches(completePattern)) {
                String task = input.replaceAll(completePattern, "$2");
                String time = input.replaceAll(completePattern, "$4");
                Task next = new Event(task, time);
                list.add(next);
                String text = "Added Event '" + task + "' to your list!";
                say(text);
            } else if (input.trim().matches(missingTaskPattern)) {
                throw(DukeException.missingTask());
            } else {
                throw(DukeException.missingTime("at"));
            }
        } else {
            throw(DukeException.emptyDesc("event"));
        }
    }

    private static void handleOthers() throws DukeException {
        throw(DukeException.unknownCommand());
    }

    private static void delete(String input) throws DukeException {
        try {
            String pattern = "(delete\\s)(.+)";
            if (input.trim().matches(pattern)) {
                String number = input.substring(7);
                int index = parseInt(number) - 1;
                Task task = list.get(index);
                list.remove(index);
                say("Deleted this task:\n" + task);
            } else {
                throw(DukeException.emptyDesc("delete"));
            }
        } catch (NumberFormatException e) {
            throw(DukeException.typeMismatch("delete"));
        } catch (IndexOutOfBoundsException e) {
            throw(DukeException.outOfBounds());
        }
    }

    public static void printHelp() {
        String help = "These are the available commands:\n" +
                "bye - exits the program\n" +
                "deadline <description> /by <due date and time> - " +
                "adds a deadline with the given description and due date to the task list\n" +
                "delete <task number> - deletes the task corresponding to the number from the task list\n" +
                "done <task number> - marks the task corresponding to the number as done\n" +
                "event <description> /at <due date and time> - " +
                "adds an event with the given description and due date to the task list\n" +
                "help - shows this list of commands\n" +
                "list - shows the contents of the task list\n" +
                "todo <description> - adds a todo task with the given description to the task list";
        say(help);
    }

    private static void createTask(String s) {
        Task next;
        if (s.startsWith("todo")) {
            next = new ToDo(s.substring(5));
        } else if (s.startsWith("deadline")) {
            String pattern = "(deadline\\s)(.+)\\s(/by\\s)(.+)";
            String task = s.replaceAll(pattern, "$2");
            String time = s.replaceAll(pattern, "$4");
            next = new Deadline(task, time);
        } else {
            String pattern = "(event\\s)(.+)\\s(/at\\s)(.+)";
            String task = s.replaceAll(pattern, "$2");
            String time = s.replaceAll(pattern, "$4");
            next = new Event(task, time);
        }
        list.add(next);
    }

    private static void loadList() {
        try {
            File f = new File("./data/list.txt");
            Scanner listScanner = new Scanner(f);
            int index = 0;
            while (listScanner.hasNext()) {
                String command = listScanner.nextLine();
                String taskDesc;
                if (command.startsWith("done")) {
                    createTask(command.substring(5));
                    list.get(index).setDone();
                } else {
                    createTask(command);
                }
                index++;
            }
            say("List has been loaded.");
        } catch (FileNotFoundException e) {
            say("No saved data found.");
        }

    }

    private static void saveList() {
        try {
            File saveFile = new File("./data/list.txt");
            File parent_directory = saveFile.getParentFile();

            if (null != parent_directory) {
                parent_directory.mkdirs();
            }

            FileWriter fw = new FileWriter(saveFile);
            int len = list.size();
            if (len > 0) {
                String text = list.get(0).toCommand();
                for (int i = 1; i < len; i++) {
                    Task t = list.get(i);
                    text = text + "\n" + t.toCommand();
                }
                fw.write(text);
            } else {
                fw.write("");
            }
            fw.close();
        } catch (IOException e) {
            say("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadList();
        String logo =
                "█████████████████████████████████████████████████████████████\n" +
                "█░░░░░░░░░░░░░░███░░░░░░░░░░░░░░░░░░░░░░░░░█░░░░░░░░░░░░░░███\n" +
                "█░░▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄░░█░░▄▀▄▀▄▀▄▀▄▀░░███\n" +
                "█░░▄▀░░░░░░▄▀░░███░░▄▀░░░░░░░░░░░░░░░░░▄▀░░█░░▄▀░░░░░░▄▀░░███\n" +
                "█░░▄▀░░██░░▄▀░░███░░▄▀░░█████████████░░▄▀░░█░░▄▀░░██░░▄▀░░███\n" +
                "█░░▄▀░░░░░░▄▀░░░░█░░▄▀░░█████████████░░▄▀░░█░░▄▀░░░░░░▄▀░░░░█\n" +
                "█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█░░█░███░█░░█░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█\n" +
                "█░░▄▀░░░░░░░░▄▀░░█░░▄▀░░█████░░░█████░░▄▀░░█░░▄▀░░░░░░░░▄▀░░█\n" +
                "█░░▄▀░░████░░▄▀░░█░░▄▀░░█████████████░░▄▀░░█░░▄▀░░████░░▄▀░░█\n" +
                "█░░▄▀░░░░░░░░▄▀░░█░░▄▀░░░░░░░░░░░░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░█\n" +
                "█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░█\n" +
                "█░░░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░█\n" +
                "█████████████████████████████████████████████████████████████";
        String welcome = "Hello I am\n" + logo +"\nPlease say something.";
        say(welcome);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.trim().equals("help")) {
                    printHelp();
                } else if (input.trim().equals("list")) {
                    printList();
                } else if (input.startsWith("done")) {
                    printDone(input);
                } else if (input.startsWith("todo")) {
                    handleToDo(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else if (input.startsWith("delete")) {
                    delete(input);
                } else {
                    handleOthers();
                }
            } catch (DukeException e) {
                say(e.getMessage());
            }
            input = sc.nextLine();
        }
        saveList();
        say("Goodbye!");
        sc.close();
    }
}
