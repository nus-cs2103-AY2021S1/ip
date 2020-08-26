import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    List<Task> list = new ArrayList<>();
    Storage storage;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! I'm Duke your friendly neighbourhood chat bot");
        System.out.println("What can i do for you?");
        Duke duke = new Duke();
        duke.run();

    }

    public void run() {
        storage = new Storage();
        try {
            list = storage.readFile();
        } catch (IOException e) {
            System.out.println("You have no save tasks");
        }
        //System.out.println("todo-list size: " + list.size());
        //System.out.println("first task: " + list.get(0));
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String checker;
            if (input.length() > 5) {
                checker = input.substring(0, 4);
            } else {
                checker = "nothing";
            }
            if (input.equals("list")) {
                showList();
            } else if (input.equals("bye")) {
                System.out.println("        Bye have a good day!");
                break;
            } else if(checker.equals("done")) {
                int num = Character.getNumericValue(input.charAt(5));
                done(num);
            } else if (checker.equals("dele")) {
                int num = Character.getNumericValue(input.charAt(7));
                delete(num);
            } else {
                add(input);
            }
        }
    }

    public void done(int number) {
        try {
            if (number <= list.size()) {
                Task current = list.get(number - 1);
                current.markAsDone();
                System.out.println("        I have marked this as done:");
                System.out.println("        [" + current.isDone + "] " + current.description);
                storage.editFile(number);
            } else {
                throw new DukeException("☹ OOPS!!! there is no such task");
            }
        } catch (DukeException | IOException e) {
            System.out.println("------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("------------------------------------------------------");
        }
    }

    public void add(String input) {
        try {
            String[] type = input.split(" ", 2);
            Task task;
            if (type[0].equals("todo")) {
                if(type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new Todo(type[1]);
                Storage storage = new Storage(task);
                storage.addToFile("T | 0 | " + task.description);
            } else if (type[0].equals("deadline")) {
                if(type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String second = type[1];
                String[] secondSplit = second.split(" /by ", 2);
                task = new Deadline(secondSplit[0], secondSplit[1]);
                Storage storage = new Storage(task);
                storage.addToFile("D | 0 | " + task.description + " | " + secondSplit[1]);
            } else if (type[0].equals("event")) {
                if(type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String second = type[1];
                String[] secondSplit = second.split(" /at ", 2);
                task = new Event(secondSplit[0], secondSplit[1]);
                Storage storage = new Storage(task);
                storage.addToFile("E | 0 | " + task.description + " | " + secondSplit[1]);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            this.list.add(task);
            System.out.println("        Got it I have added this task:");
            System.out.println("        " + task.toString());
            System.out.println("        you now have " + list.size() + " tasks on the list");
        } catch (DukeException e) {
            System.out.println("------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("------------------------------------------------------");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public void showList() {
        System.out.println("        Here are the tasks in your list:");
        int counter = 1;
        for (Task t : list) {
            System.out.println("        " + counter +  "." + t.toString());
            counter++;
        }
    }

    public void delete(int num) {
        try {
            if (num < 0 || num > list.size()) {
                throw new DukeException("☹ OOPS!!! there is no such task");
            } else {
                Task deleted = list.remove(num - 1);
                System.out.println("        Noted I've removed this task");
                System.out.println("        " + deleted);
                System.out.println("        you now have " + list.size() + " tasks on the list");
                storage.deleteTask(num);
            }
        } catch (DukeException | IOException e) {
            System.out.println("------------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("------------------------------------------------------");
        }
    }

}
