import java.awt.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);

        try {
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        greet();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            try {
                handleCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String command) {
        System.out.println("" + command);
    }

    private static void add(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void done(Task task) {
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    private static void delete(int index) {
        System.out.println("Noted. I've removed this task:\n" + tasks.get(index-1).toString());
        tasks.remove(index-1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * this method handles two types of error: invalid input (does not contain todo/deadline/event)
     * and empty task (if the input starts with todo/deadline/event and the content is not empty,
     * it is assumed that the input has correct structure)
     */
    private static void handleCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            exit();
        } else if (command.equals("list")) {
            printList();
        } else if (command.split(" ")[0].equals("done")) {
            int index = Integer.parseInt(command.split(" ")[1]);
            done(tasks.get(index - 1));
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("Oops! " + e.getMessage());
            }
        } else if (command.split(" ")[0].equals("delete")) {
            delete(Integer.parseInt(command.split(" ")[1]));
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("Oops! " + e.getMessage());
            }
        } else if (command.split(" ")[0].equals("todo")) {
            String taskcommand = command.replace("todo", "");
            if (!taskcommand.equals("")) {
                add(new ToDo(taskcommand));
            } else {
                throw new DukeException("EmptyToDo");
            }
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("Oops! " + e.getMessage());
            }
        } else if (command.split(" ")[0].equals("deadline")) {
            String taskcommand = command.split("/")[0].replace("deadline", "");
            if (!taskcommand.equals("")) {
                String time = command.split("/")[1].replace("by ", "");
                add(new Deadline(taskcommand, time));
            } else {
                throw new DukeException("EmptyDeadline");
            }
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("Oops! " + e.getMessage());
            }
        } else if (command.split(" ")[0].equals("event")) {
            String taskcommand = command.split("/")[0].replace("event", "");
            if (!taskcommand.equals("")) {
                String time = command.split("/")[1].replace("at ", "");
                add(new Event(taskcommand, time));
            } else {
                throw new DukeException("EmptyEvent");
            }
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("Oops! " + e.getMessage());
            }
        } else {
            throw new DukeException("invalid");
        }
    }

    private static void readFile() throws FileNotFoundException {
        File file = new File("C:\\Users\\e0316059\\Desktop\\cs2103 ip\\src\\main\\java\\data\\duke.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(" \\| ");
            if (data[0].equals("T")) {
                ToDo todo = new ToDo(data[2]);
                add(todo);
                if (data[1].equals("1")) {
                    todo.setDone();
                }
            } else if (data[0].equals("D")) {
                Deadline deadline = new Deadline(data[2], data[3]);
                add(deadline);
                if (data[1].equals("1")) {
                    deadline.setDone();
                }
            } else if (data[0].equals("E")) {
                Event event = new Event(data[2], data[3]);
                add(event);
                if (data[1].equals("1")) {
                    event.setDone();
                }
            }
        }
    }

    private static void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\e0316059\\Desktop\\cs2103 ip\\src\\main\\java\\data\\duke.txt");
        for (int i = 0; i < tasks.size(); i++)  {
            Task task = tasks.get(i);
            if (task instanceof ToDo) {
                String text = "T | " + (task.getStatus() ? "1" : "0") + " | " + task.getCommand();
                fileWriter.write(text + System.lineSeparator());
            } else if (task instanceof Deadline) {
                String text = "D | " + (task.getStatus() ? "1" : "0") + " | " + task.getCommand()
                        + " | " + ((Deadline) task).getTime();
                fileWriter.write(text + System.lineSeparator());
            } else if (task instanceof Event) {
                String text = "E | " + (task.getStatus() ? "1" : "0") + " | " + task.getCommand()
                        + " | " + ((Event) task).getTime();
                fileWriter.write(text + System.lineSeparator());
            }
        }
        fileWriter.close();
    }
}
