import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

import java.lang.StringBuilder;

import java.time.LocalDate;
import java.time.DateTimeException;

public class Duke {
    private static final String LINE = "____________________________________________________________\n";
    private static final String BYE = "bye";
    private static final String FILEPATH = "./data/tasks.txt";
    private ArrayList<Task> inputList;
    private Storage storage;

    private Duke() {
        inputList = new ArrayList<>();
        storage = new Storage(FILEPATH);
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        try {
            inputList = storage.read();
        } catch (FileNotFoundException e) {
            System.out.println(template("No existing user data file found. Welcome to the bot club:)"));
        } catch (CorruptedFileException e) {
            System.out.println("Your file has been corrupted :( Unfortunately the data cannot be used");
        }
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals(BYE)) {
                System.out.println(template("Cya!!"));
                return;
            } else {
                try {
                    String reply = inputHandler(command);
                    System.out.println(template(reply));
                    storage.write(FILEPATH, inputList);
                } catch (IllegalArgumentException | InvalidArgumentException | InvalidCommandException e){
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("There is an issue writing to the storage file!!");
                }
            }
        }
    }

    private String inputHandler(String input) throws InvalidArgumentException, InvalidCommandException {
        String[] commands = input.split(" ", 2);
        Commands current;
        try {
            current = Commands.valueOf(commands[0].toUpperCase());
            switch(current) {
            case LIST:
                return display();
            case DONE:
                return updateTask(Integer.valueOf(commands[1]));
            case TODO:
                return addTodo(commands[1]);
            case EVENT:
                String[] arr = commands[1].split("/at");
                return addEvent(arr[0], arr[1].trim());
            case DEADLINE:
                String[] arr2 = commands[1].split("/by");
                return addDeadline(arr2[0], arr2[1].trim());
            case DELETE:
                return deleteTask(Integer.valueOf(commands[1]));
            default:
                throw new InvalidCommandException("");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new InvalidArgumentException("Sorry, your argument cannot be empty!");
        } catch (InvalidArgumentException exception) {
            throw new InvalidArgumentException(exception.getMessage());
        } catch (InvalidCommandException exception) {
            throw new InvalidCommandException("Sorry, your command is not recognised!");
        }
    }

    private String display() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < inputList.size(); i++) {
            str.append(String.valueOf(i+1) + "." + inputList.get(i).toString() + "\n");
        }
        return str.toString();
    }

    private String addTodo(String task) throws InvalidArgumentException {
        try {
            StringBuilder str = new StringBuilder();
            Todo current = new Todo(task);
            inputList.add(current);
            str.append("Got it bro, I've added this task:\n  ").append(current.toString() + "\n").append(
                    "Now you have ").append(inputList.size()).append(" tasks in the list.");
            return str.toString();
        } catch (Exception e) {
            throw new InvalidArgumentException("Sorry, ToDo does not accept this argument!");
        }
    }

    private String addDeadline(String task, String by) throws InvalidArgumentException {
        try {
            StringBuilder str = new StringBuilder();
            LocalDate temp = LocalDate.parse(by);
            Deadline current = new Deadline(task, temp);
            inputList.add(current);
            str.append("Got it bro, I've added this task:\n  ").append(current.toString() + "\n").append(
                    "Now you have ").append(inputList.size()).append(" tasks in the list.");
            return str.toString();
        } catch (DateTimeException e) {
            throw new InvalidArgumentException("Sorry, Invalid date format!");
        } catch (Exception e) {
            throw new InvalidArgumentException("Sorry, Deadline does not accept this argument!");
        }
    }

    private String addEvent(String task, String at) throws InvalidArgumentException {
        try {
            StringBuilder str = new StringBuilder();
            LocalDate temp = LocalDate.parse(at);
            Event current = new Event(task, temp);
            inputList.add(current);
            str.append("Got it bro, I've added this task:\n  ").append(current.toString() + "\n").append(
                    "Now you have ").append(inputList.size()).append(" tasks in the list.");
            return str.toString();
        } catch (DateTimeException e) {
            throw new InvalidArgumentException("Sorry, Invalid date format!");
        } catch (Exception e) {
            throw new InvalidArgumentException("Sorry, Event does not accept this argument!");
        }
    }

    private String updateTask(int index) throws InvalidArgumentException {
        try {
            inputList.get(index - 1).markAsDone();
            StringBuilder str = new StringBuilder();
            str.append("Solid bro!! I've marked this task as done:\n").append(
                    inputList.get(index - 1).toString() + "\n");
            return str.toString();
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Sorry! The task index you wanted to complete does not exist!");
        }
    }

    private String deleteTask(int index) throws InvalidArgumentException {
        try {
            StringBuilder str = new StringBuilder();
            str.append("Understood. I've removed this task:\n  ").append(
                    inputList.get(index - 1).toString()).append("Now you have ").append(
            inputList.size() - 1).append(" tasks in the list.");
            inputList.remove(index - 1);
            return str.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Sorry! The index to be removed does not exist!");
        }
    }

    private static void greet() {
        StringBuilder str = new StringBuilder();
        str.append("Yo I'm Dood!!\nAnything I can do for you?\n").append("The commands available are:\n")
                .append("list     | Shows the list of tasks on the bot.\n")
                .append("bye      | Exits the program\n")
                .append("done     | Marks the Task as done. Format is 'done {task index}.\n")
                .append("todo     | Creates a ToDo task. Format is 'Todo {description}.\n")
                .append("event    | Creates an Event task. Format is 'event {description} /at {date in YYYY-MM-DD}.\n")
                .append("deadline | Creates a DeadLine task. Format is 'deadline {description} /by {date in YY-MM-DD}.")
                .append("\ndelete   | Deletes a Task. Format is ' delete {task index}");
        System.out.println(template(str.toString()));
    }

    private static String template(String reply) {
        return LINE + reply + "\n" + LINE;
    }

    public static void main(String[] args) {
        greet();
        Duke bot = new Duke();
        bot.init();
    }
}
