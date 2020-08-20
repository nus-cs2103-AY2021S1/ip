import main.java.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class Duke {
    private enum Speaking {
        GREET("My name? You don't need to know that. \nStop bothering me already... What do you want??"),
        BYE("Finally... don't come back if you can possibly help it, please."),
        LIST("I wouldn't bother doing them if I were you."),
        DONE("Oh goody... You actually accomplished something!!\n  "),
        ADD("You're making me feel tired... But if you insist:\n  "),
        DELETE("Oh, getting lazy are we? I approve. I've removed this:\n  "),
        EXCEPTION("Jesus, what are you doing?"),
        INVALIDINPUT("Invalid input, dummy."),
        INVALIDCOMMAND("I don't understand what you mean.");

        private final String line;

        private Speaking(String line) {
            this.line = line;
        }
    }

    public static String getFirstWord(String command) {
        return command.contains(" ") ? command.split(" ")[0] : command;
    }

    public static void readCommands(ArrayList<Task> tasks) {
        try {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            command = command.trim();

            // allow multiple termination commands
            Set<String> terminationCommands = new HashSet<String>();
            terminationCommands.add("bye");
            terminationCommands.add("toodles");
            terminationCommands.add("farewell");
            terminationCommands.add("sayonara");

            while (!terminationCommands.contains(command)) {
                if (command.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                    System.out.println(Speaking.LIST.line);
                } else if (getFirstWord(command).equals("done")) {
                    try {
                        int taskNumber = parseInt(command.split(" ")[1]) - 1;
                        tasks.get(taskNumber).markAsDone();
                        System.out.println(Speaking.DONE.line + tasks.get(taskNumber));
                    } catch (Exception e) {
                        throw new DukeException(Speaking.INVALIDINPUT.line + " Specify the task number correctly.");
                    }
                } else if (getFirstWord(command).equals("todo")) {
                    try {
                        String toAdd = command.split(" ", 2)[1];
                        tasks.add(new Todo(toAdd));
                        int length = tasks.size();
                        System.out.println(Speaking.ADD.line + tasks.get(length-1) +
                                "\nYou now have " + length + (length == 1 ? " thing" : " things") + " in your list");
                    } catch (Exception e) {
                        throw new DukeException(Speaking.INVALIDINPUT.line + " Did you put your task after a space?");
                    }
                } else if (getFirstWord(command).equals("deadline")) {
                    try {
                        String wholeTask = command.split(" ",2)[1];
                        String[] descAndDeadline = wholeTask.split(" /by ");
                        tasks.add(new Deadline(descAndDeadline[0], descAndDeadline[1]));
                        int length = tasks.size();
                        System.out.println(Speaking.ADD.line + tasks.get(length-1) +
                                "\nYou now have " + length + (length == 1 ? " thing" : " things") + " in your list");
                    } catch (Exception e) {
                        throw new DukeException(Speaking.INVALIDINPUT.line + " Did you put a task before and deadline after ' /by '?");
                    }
                } else if (getFirstWord(command).equals("event")) {
                    try {
                        String wholeTask = command.split(" ",2)[1];
                        String[] descAndTime = wholeTask.split(" /at ");
                        tasks.add(new Event(descAndTime[0], descAndTime[1]));
                        int length = tasks.size();
                        System.out.println(Speaking.ADD.line + tasks.get(length-1) +
                                "\nYou now have " + length + (length == 1 ? " thing" : " things") + " in your list");
                    } catch (Exception e) {
                        throw new DukeException(Speaking.INVALIDINPUT.line + " Did you put a task before and time after ' /at '?");
                    }
                } else if (getFirstWord(command).equals("delete")) {
                    try {
                        int taskNumber = parseInt(command.split(" ")[1]) - 1;
                        Task deletedTask = tasks.get(taskNumber);
                        tasks.remove(taskNumber);
                        int length = tasks.size();
                        System.out.println(Speaking.DELETE.line + deletedTask +
                                "\nYou now have " + length + (length == 1 ? " thing" : " things") + " in your list");
                    } catch (Exception e) {
                        throw new DukeException(Speaking.INVALIDINPUT.line + " Specify the task number correctly.");
                    }
                } else {
                    throw new DukeException(Speaking.INVALIDCOMMAND.line);
                }
                command = sc.nextLine();
                command = command.trim();
            }
            System.out.println(Speaking.BYE.line);
        } catch (DukeException e) {
            System.out.println(Speaking.EXCEPTION.line + " " + e.getMessage());
            readCommands(tasks);
        }
    }

    public static void main(String[] args) {
        System.out.println(Speaking.GREET.line);
        readCommands(new ArrayList<Task>());
    }
}
