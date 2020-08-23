import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;

public class Duke {
    public static void checkForInvalidInput(String input) throws DukeException {
        if (!input.contains("todo")) {
            if (!input.contains("deadline")) {
                if (!input.contains("event")) {
                    if (!input.contains("done")) {
                        if (!input.contains("bye")) {
                            if (!input.contains("list")) {
                                if (!input.contains("delete"))
                                throw new DukeException("I don't know what that means! Try again.");
                            }
                        }
                    }
                }
            }
        }
    }

    public static void checkForItem(String input, String task) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("The description of a " + task + " cannot be empty!");
        }
    }
    public static void main(String[] args) {
        try {
            Storage store = new Storage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String lines = "------------------------------------------------\n";
        System.out.println(lines + "Hello! I'm Duke!\n" + "What can I do for you?\n" + lines);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        ArrayList<Task> ls = new ArrayList<>();
        try {
            Storage.currentList(ls);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        while (!userInput.equals("bye")) {
            try {
                checkForInvalidInput(userInput);
                if (userInput.equals("list")) {
                    System.out.println(lines);
                    System.out.println("  Here are the tasks in your list:\n");
                    for (int i = 0; i < ls.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + ls.get(i).toString());
                    }
                    System.out.println(lines);
                    userInput = sc.nextLine();
                } else if (userInput.contains("done")) {
                    try {
                        int index = userInput.charAt(5) - '0';
                        if (index < 1 || index > ls.size()) {
                            System.out.println(lines + "Index out of range! Try Again.\n" + lines);
                            userInput = sc.nextLine();
                        } else {
                            ls.get(index - 1).markAsDone();
                            Storage.completeTask(index - 1, ls.size());
                            System.out.println(lines + "  Nice! I have marked this task as done:\n");
                            System.out.println("    " + ls.get(index - 1).toString()
                                    + "\n" + lines);
                            userInput = sc.nextLine();
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(lines + "Index out of range! Try again.\n" + lines);
                        userInput = sc.nextLine();
                    }
                } else if (userInput.contains("delete")) {
                    int index = userInput.charAt(7) - '0';
                    if (index < 1 || index > ls.size()) {
                        System.out.println(lines + "Index out of range! Try Again.\n" + lines);
                        userInput = sc.nextLine();
                    } else {
                        System.out.println(lines);
                        System.out.println("Noted. I have removed this task:");
                        System.out.println("  " + ls.get(index - 1).toString());
                        ls.remove(index - 1);
                        Storage.deleteTask(index - 1, ls.size());
                        System.out.println("Now you have " + ls.size() + " tasks in the list");
                        userInput = sc.nextLine();
                        System.out.println(lines);
                    }
                } else {
                    if (userInput.contains("todo")) {
                        try {
                            checkForItem(userInput.substring(4), "todo");
                            String s = userInput.substring(5);
                            System.out.println(lines);
                            System.out.println("Got it. I have added this task:");
                            Todo temp = new Todo((s));
                            Storage.addTask(temp.getStorageString("T"));
                            System.out.println("  " + temp.toString());
                            ls.add(temp);
                            System.out.println("Now you have " + ls.size() + " tasks in the list");
                            System.out.println(lines);
                            userInput = sc.nextLine();
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            userInput = sc.nextLine();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                            userInput = sc.nextLine();
                        }
                    }
                }
                if (userInput.contains("deadline")) {
                    try {
                        checkForItem(userInput.substring(8), "deadline");
                        int dateIndex = userInput.indexOf("/");
                        String s = userInput.substring(9, dateIndex);
                        String time = userInput.substring(dateIndex + 1);
                        System.out.println(lines);
                        System.out.println("Got it. I have added this task:");
                        Deadline temp = new Deadline(s, time);
                        System.out.println("  " + temp.toString());
                        ls.add(temp);
                        Storage.addTask(temp.getStorageString("D", time));
                        System.out.println("Now you have " + ls.size() + " tasks in the list");
                        System.out.println(lines);
                        userInput = sc.nextLine();
                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                        userInput = sc.nextLine();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        userInput = sc.nextLine();
                    }
                }
                if (userInput.contains("event")) {
                    try {
                        checkForItem(userInput.substring(5), "event");
                        int dateIndex = userInput.indexOf("/");
                        String s = userInput.substring(6, dateIndex);
                        String time = userInput.substring(dateIndex + 1);
                        System.out.println(lines);
                        System.out.println("Got it. I have added this task:");
                        Event temp = new Event(s, time);
                        System.out.println("  " + temp.toString());
                        Storage.addTask(temp.getStorageString("E", time));
                        ls.add(temp);
                        System.out.println("Now you have " + ls.size() + " tasks in the list");
                        System.out.println(lines);
                        userInput = sc.nextLine();
                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                        userInput = sc.nextLine();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        userInput = sc.nextLine();
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                userInput = sc.nextLine();
            }
        }

        System.out.println(lines + "    Bye! Hope to see you again soon." + "\n" + lines);

    }
}