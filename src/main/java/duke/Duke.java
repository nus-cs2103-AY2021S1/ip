package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

import exception.*;

public class Duke {
    Scanner readSc;
    Scanner inputSc;
    ArrayList<Task> list;
    String input;

    public Duke() {
        inputSc = new Scanner(System.in);
        list = new ArrayList<>();
    }

    private void readFile(String filePath) throws FileNotFoundException, NullPointerException  {
        File f = new File(filePath);
        this.readSc = new Scanner(f);

        while (readSc.hasNextLine()) {
            String curr = readSc.nextLine();
            switch (curr.charAt(1)) {
                case 'T':
                    try {
                        if (curr.charAt(4) == '✗') {
                            addTodo(curr.split(" ", 2)[1], false, false);
                        } else {
                            addTodo(curr.split(" ", 2)[1], false, true);
                        }
                    } catch (InvalidTodoException e) {
                        System.out.println(e);
                    }
                    break;
                case 'D':
                    try {
                        if (curr.charAt(4) == '✗') {
                            addDeadline(curr.split(" ", 2)[1], false, false);
                        } else {
                            addDeadline(curr.split(" ", 2)[1], false, true);
                        }
                    } catch (InvalidDeadlineException e) {
                        System.out.println(e);
                    }
                    break;
                case 'E':
                    try {
                        if (curr.charAt(4) == '✗') {
                            addEvent(curr.split(" ", 2)[1], false, false);
                        } else {
                            addEvent(curr.split(" ", 2)[1], false, true);
                        }
                    } catch (InvalidEventException e) {
                        System.out.println( e);
                    }
                    break;
                default:
                    System.out.println("Unknown task");
            }
        }
    }

    private void createFile(String filePath) throws IOException {
        File dataFolder = new File("data");
        if (!dataFolder.isDirectory()) {
            dataFolder.mkdir();
        }

        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void start() {
        Duke.introduce();
        interact();
        Duke.bye();
    }

    public static void buildChatSeparator() {
        System.out.println("____________________________________________________________");
    }

    public void interact() {
        try {
            readFile("data/duke.txt");
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("Folder data not found! " + e);
        }

        while (inputSc.hasNextLine()) {
            input = inputSc.nextLine();
            String[] splitted = input.split(" ", 2);
            Commands command = null;
            try {
                command = Commands.valueOf(splitted[0].toUpperCase());
                this.processInput(command);
            } catch (IllegalArgumentException | UnknownCommandException ex) {
                System.out.println(ex);
                continue;
            }
            
            if (command.equals(Commands.BYE)) {
                try {
                    StringBuilder replacementText = new StringBuilder();
                    createFile("data/duke.txt");
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof Todo) {
                            replacementText.append(list.get(i).toString() + "\n");
                        } else {
                            replacementText.append(list.get(i).toString() + " on " + list.get(i).getDate() + "\n");
                        }
                    }
                    appendToFile("data/duke.txt", replacementText.toString());
                } catch(IOException e) {
                    System.out.println("Folder data not found! " + e);
                }

                break;
            } else if (command.equals(Commands.LIST)) {
                buildChatSeparator();
                System.out.println(" Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(" " + i + ". "
                            + list.get(i - 1).toString()
                            + list.get(i - 1).getDateDescription());
                }
                buildChatSeparator();
            } else if (command.equals(Commands.DONE)){
                try {
                    this.makeDone(Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (command.equals(Commands.DELETE)){
                try {
                    this.deleteTask(Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (command.equals(Commands.DEADLINE)) {
                try {
                    this.addDeadline(splitted[1], true, false);
                } catch (ArrayIndexOutOfBoundsException | InvalidDeadlineException ex) {
                    System.out.println(ex + ". ☹ Task deadline must be specified.");
                }
            } else if (command.equals(Commands.TODO)) {
                try {
                    this.addTodo(splitted[1], true, false);
                } catch (ArrayIndexOutOfBoundsException | InvalidTodoException ex) {
                    System.out.println(ex + ". ☹ The description of a todo cannot be empty.");
                }
            } else if (command.equals(Commands.EVENT)) {
                try {
                    this.addEvent(splitted[1], true, false);
                } catch (ArrayIndexOutOfBoundsException | InvalidEventException ex) {
                    System.out.println(ex + ". ☹ The description of an event cannot be empty.");
                }
            } else {
                buildChatSeparator();
                System.out.println("added: " + input);
                buildChatSeparator();
                list.add(new Task(input, false));
            }
        }
    }

    private void deleteTask(int index) throws DukeErrorException {
        if (index >= list.size() || index < 0) {
            throw new DukeErrorException("Operation: delete " + (index + 1) + " fails ☹.");
        }
        Task deleted = list.remove(index);
        buildChatSeparator();
        System.out.println(" Noted. I've removed this task: ");
        System.out.println(" " + deleted);
        System.out.println(" Now you have " + (list.size() <= 1
                ? list.size() + " task"
                : list.size() + " tasks")
                + " in the list.");
        buildChatSeparator();
    }

    public void processInput(Commands cmd) throws UnknownCommandException {
        if (!cmd.equals(Commands.BYE) &&
                !cmd.equals(Commands.EVENT) &&
                !cmd.equals(Commands.DEADLINE) &&
                !cmd.equals(Commands.DONE) &&
                !cmd.equals(Commands.LIST) &&
                !cmd.equals(Commands.TODO) &&
                !cmd.equals(Commands.DELETE)) {
            throw new UnknownCommandException();
        }
    }

    public void makeDone(int index) throws DukeErrorException {
        if (index >= list.size() || index < 0) {
            throw new DukeErrorException("Operation: done " + (index + 1) + " fails ☹.");
        }
        list.set(index, list.get(index).completeTask());
        buildChatSeparator();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + list.get(index));
        buildChatSeparator();
    }

    private void addEvent(String str, boolean isNew, boolean condition) throws InvalidEventException {
        String[] event;
        if (isNew) {
            event = str.split("/at");
        } else {
            event = str.split("on");
        }
        if (event.length > 2) {
            throw new InvalidEventException("☹ Event time must be written after `/at`.");
        }
        if (event[0].equals("")) {
            throw new InvalidEventException("☹ Event description must be specified.");
        }
        if (event[1].equals("")) {
            throw new InvalidEventException("☹ Event time must be specified.");
        }
        String description = event[0].trim();
        try {
            Event curr = new Event(description, condition, LocalDate.parse(event[1].trim()));
            list.add(curr);
            if (isNew) {
                this.describeTask(curr);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date format! Must be (yyyy-mm-dd).");
        }

    }

    private void addTodo(String str, boolean isNew, boolean condition) throws InvalidTodoException {
        String description = str.trim();
        if (description.equals("")) {
            throw new InvalidTodoException("☹ Todo description must be specified.");
        }
        Todo curr = new Todo(description, condition);
        list.add(curr);
        if (isNew) {
            this.describeTask(curr);
        }
    }

    private void addDeadline(String str, boolean isNew, boolean condition) throws InvalidDeadlineException {
        String[] deadline;
        if (isNew) {
            deadline = str.split("/by");
        } else {
            deadline = str.split("on");
        }
        if (deadline.length > 2) {
            throw new InvalidDeadlineException("☹ Task deadline must be written after `/by`.");
        }
        if (deadline[0].equals("")) {
            throw new InvalidDeadlineException("☹ Task description must be specified.");
        }
        if (deadline[1].equals("")) {
            throw new InvalidDeadlineException("☹ Task deadline must be specified.");
        }
        String description = deadline[0].trim();
        try {
            Deadline curr = new Deadline(description, false, LocalDate.parse(deadline[1].trim()));
            list.add(curr);
            if (isNew) {
                this.describeTask(curr);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date format! Must be (yyyy-mm-dd).");
        }
    }

    public void describeTask(Task curr) {
        buildChatSeparator();
        System.out.println(" Got it. I've added this task: ");
        System.out.println(" " + curr.toString() + curr.getDateDescription());
        System.out.println(" Now you have " + (list.size() == 1
                ? list.size() + " task"
                : list.size() + " tasks")
                + " in the list.");
        buildChatSeparator();
    }

    public static void introduce() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introduction = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(introduction);
    }

    public static void bye() {
        buildChatSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        buildChatSeparator();
    }
}
