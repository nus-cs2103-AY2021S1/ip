import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    
    public static void inputList(String display) {
        for (int i = 0; i < taskList.size(); i++) {
            if (i == 0) {
                System.out.println("Here are the tasks in your list:\n");
            }
            Task task = taskList.get(i);
            System.out.println((i + 1) + ". " + task + "\n");
        }
        readUserInput();
    }

    public static void inputDone(String display) {
        int index = Integer.parseInt(display.substring(5));
        Task task = taskList.get(index - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + task + "\n");
        readUserInput();
    }

    public static void inputTodo(String display) {
        try {
            if (display.length() == 4 || display.length() == 5) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String description = display.substring(5);
            Todo todo = new Todo(description);
            taskList.add(todo);
            System.out.println("Got it. I've added this task:\n" + todo + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.\n");
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            readUserInput();
        }
    }

    public static void inputDeadline(String display) {
        try {
            if (display.length() == 8 || display.length() == 9) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (display.contains("/")) {
                int index = display.indexOf("/");
                if (display.substring(index + 1).equals("")
                        || display.substring(index + 3).equals("")
                        || display.substring(index + 4).equals("")) {
                    throw new DukeException("☹ OOPS!!! Please key in a valid date and time.");
                }
                String description = display.substring(9, index - 1);
                String deadlineDate = display.substring(index + 4);

                try {
                    LocalDate date = LocalDate.parse(deadlineDate);
                    Deadline deadline = new Deadline(description, date);
                    taskList.add(deadline);
                    System.out.println("Got it. I've added this task:\n" + deadline + "\n"
                            + "Now you have " + taskList.size() + " tasks in the list.\n");
                } catch (DateTimeParseException e) {
                    System.out.println("Please key in the date in the format YYYY-MM-DD");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The format is wrong. A dash is missing.");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            readUserInput();
        }
    }

    public static void inputEvent(String display) {
        try {
            if (display.length() == 5 || display.length() == 6) { // "event" or "event "
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (display.contains("/")) {
                int index = display.indexOf("/");
                if (display.substring(index + 1).equals("")
                        || display.substring(index + 3).equals("")
                        || display.substring(index + 4).equals("")) {
                    throw new DukeException("☹ OOPS!!! Please key in a valid date and time.");
                }
                String description = display.substring(6, index - 1);
                String eventDate = display.substring(index + 4);
                try {
                    LocalDate date = LocalDate.parse(eventDate);
                    Event event = new Event(description, date);
                    taskList.add(event);
                    System.out.println("Got it. I've added this task:\n" + event + "\n"
                            + "Now you have " + taskList.size() + " tasks in the list.\n");
                } catch (DateTimeParseException e) {
                    System.out.println("Please key in the date in the format YYYY-MM-DD");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The format is wrong. A dash is missing.");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            readUserInput();
        }
    }
    
    public static void delete(String display) {
        try {
            if (display.length() == 6 || display.length() == 7) {
                throw new DukeException("ERROR: Specify the task number which you want to delete.");
            }
            int index = Integer.parseInt(display.substring(7));
            if (index > taskList.size()) {
                throw new DukeException("ERROR: Task does not exist");
            }
            Task task = taskList.get(index - 1);
            taskList.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + task + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.\n");
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            readUserInput();
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        try {
            FileWriter fileWriter = new FileWriter("taskList.txt");
            for (Task task : taskList) {
                fileWriter.write(task.saveTask() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    public static void readUserInput() {
        
        String display = sc.nextLine();
        if (display.equals("bye")) {
            exit();
        } else if (display.equals("list")) {
            inputList(display);
        } else if (display.startsWith("done")) {
            inputDone(display);
        } else if (display.startsWith("todo")) {
            inputTodo(display);
        } else if (display.startsWith("deadline")) {
            inputDeadline(display);
        } else if (display.startsWith("event")) {
            inputEvent(display);
        } else if (display.startsWith("delete")) {
            delete(display);
        } else {
            try {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } finally {
                readUserInput();
            }
        }
    }

    public static void readFiles(String data) {
        if (data.startsWith("T")) {
            String description = data.substring(8);
            Todo todo = new Todo(description);
            todo.isDone = data.charAt(4) == '1';
            taskList.add(todo);

        } else if (data.startsWith("E")) {
            int index = data.lastIndexOf("|");
            String description = data.substring(8, index - 1);
            String eventDate = data.substring(index + 2);
            Event event = new Event(description, eventDate);
            event.isDone = data.charAt(4) == '1';
            taskList.add(event);
        } else if (data.startsWith("D")) {
            int index = data.lastIndexOf("|");
            String description = data.substring(8, index - 1);
            String deadlineDate = data.substring(index + 2);
            Deadline deadline = new Deadline(description, deadlineDate);
            deadline.isDone = data.charAt(4) == '1';
            taskList.add(deadline);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke \n" + "What can I do for you?";
        System.out.println("Hello from\n" + logo + "\n" + greeting);
        try {
            File file = new File("taskList.txt");
            if (!file.createNewFile()) { // file already exists
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String data = fileScanner.nextLine();
                    readFiles(data);
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("ERROR: file not found.");
        }
        readUserInput();
    }
}
