import jdk.jshell.spi.ExecutionControl;

import java.nio.channels.NonWritableChannelException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {
    private static final String line = "____________________________________________________________\n";
    private static final String bye = "bye";
    private static final String list = "list";
    private static final String done = "done";
    private static final String todo = "todo";
    private static final String deadline = "deadline";
    private static final String event = "event";
    private static final String delete = "delete";
    private ArrayList<Task> inputList;

    private Duke() {
        inputList = new ArrayList<>();
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals(bye)) {
                System.out.println(template("Cya!!"));
                return;
            } else {
                try {
                    String reply = inputHandler(command);
                    System.out.println(template(reply));
                } catch (InvalidArgumentException | InvalidCommandException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private String template(String reply) {
        return line + reply + "\n" + line;
    }

    private String inputHandler(String input) throws InvalidArgumentException, InvalidCommandException {
        String[] commands = input.split(" ", 2);
        try {
            if (commands[0].equals(list)) {
                return display();
            } else if (commands[0].equals(done) && commands.length == 2) {
                return updateTask(Integer.valueOf(commands[1]));
            } else if (commands[0].equals(todo)) {
                return addTodo(commands[1]);
            } else if (commands[0].equals(event)) {
                String[] arr = commands[1].split("/at");
                return addEvent(arr[0], arr[1]);
            } else if (commands[0].equals(deadline)) {
                String[] arr = commands[1].split("/by");
                return addDeadline(arr[0], arr[1]);
            } else if (commands[0].equals(delete)) {
                return deleteTask(Integer.valueOf(commands[1]));
            } else {
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
            Deadline current = new Deadline(task, by);
            inputList.add(current);
            str.append("Got it bro, I've added this task:\n  ").append(current.toString() + "\n").append(
                    "Now you have ").append(inputList.size()).append(" tasks in the list.");
            return str.toString();
        } catch (Exception e) {
            throw new InvalidArgumentException("Sorry, Deadline does not accept this argument!");
        }
    }

    private String addEvent(String task, String at) throws InvalidArgumentException {
        try {
            StringBuilder str = new StringBuilder();
            Event current = new Event(task, at);
            inputList.add(current);
            str.append("Got it bro, I've added this task:\n  ").append(current.toString() + "\n").append(
                    "Now you have ").append(inputList.size()).append(" tasks in the list.");
            return str.toString();
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

    public static void main(String[] args) {
        String welcome = "Yo I'm Dood!!\nAnything I can do for you?\n";
        System.out.println(line + welcome + line);
        Duke bot = new Duke();
        bot.init();
    }
}
