import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        readInputs();
    }

    private static void greet() {
        String logo =
                " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print("Hello, I am\n" + logo);
        System.out.println("___________________________________________________" +
            "\nDuke: What can i do for you?");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ". " + task);
        }
        System.out.println("--------------------------" +
                "-------------------------");
    }

    private static void printTotalNumberOfTasks() {
        int numTasks = tasks.size();
        if (numTasks < 2) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
    }

    private static void setDoneTask(int index) {
        Task task = tasks.get(index - 1); // index - 1 to match the index in ArrayList
        task.markDone();
        System.out.println("Nice! I've marked this task as done:" +
                "\n\t" + task);
        System.out.println("---------------------------------------------------");
    }

    public static void deleteTask(int index) {
        Task task = tasks.get(index - 1); // index -1 to match the index in ArrayList
        tasks.remove(index - 1); // index - 1 to match the index in ArrayList
        System.out.println("Noted. I've deleted this task:" +
                "\n\t" + task);
        printTotalNumberOfTasks();
        System.out.println("---------------------------------------------------");
    }

    private static void handleTask(String input) throws DukeException {
        String taskType = input.split(" ")[0];

        if (taskType.equals("done")) {
            try {
                String taskIndex = input.split(" ")[1];
                if (taskIndex.isBlank()) {
                    throw new DukeException("Please include item number!");
                }
                setDoneTask(Integer.parseInt(taskIndex));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Missing or invalid item number!");
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format!");
            }
        } else if (taskType.equals("delete")) {
          try {
              String taskIndex = input.split(" ")[1];
              if (taskIndex.isBlank()) {
                  throw new DukeException("Please include item number!");
              }
              deleteTask(Integer.parseInt(taskIndex));
          } catch (IndexOutOfBoundsException e) {
              throw new DukeException("Missing or invalid item number!");
          } catch (NumberFormatException e) {
              throw new DukeException("Invalid format!");
          }
        } else {
            String taskName = "";
            if (taskType.equals("todo")) {
                try {
                    taskName = input.substring(5);
                    if (taskName.isBlank()) {
                        throw new DukeException("Description cannot be only empty spaces!");
                    }
                    Task taskCreated = new Todo(taskName);
                    tasks.add(taskCreated);
                    System.out.println("Got it. I've added this task:" +
                            "\n\t" + taskCreated);
                    printTotalNumberOfTasks();
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please include description!");
                }
            } else if (taskType.equals("deadline")) {
                try {
                    if (input.substring(9).isBlank()) {
                        throw new DukeException("Description cannot be only empty spaces!");
                    }
                    String[] task = input.substring(9).split(" /by ");
                    taskName = task[0];
                    String timeBy = task[1].replace(' ', 'T');
                    System.out.println("After formatting: " + timeBy);
                    Task taskCreated = new Deadline(taskName, LocalDateTime.parse(timeBy,
                            DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HHmm")));
                    System.out.println("Success");
                    tasks.add(taskCreated);
                    System.out.println("Got it. I've added this task:" +
                            "\n\t" + taskCreated);
                    printTotalNumberOfTasks();
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid description of a deadline item!");
                } catch (DateTimeParseException f) {
                    throw new DukeException("Please enter deadline in this format: dd/MM/yyyy timeIn24Hr"
                            + "\nE.g. 01/12/2020 2359");
                }
            } else if (taskType.equals("event")) {
                try {
                    if (input.substring(6).isBlank()) {
                        throw new DukeException("Description cannot be only empty spaces!");
                    }
                    String[] task = input.substring(6).split(" /at ");
                    taskName = task[0];
                    String timeAt = task[1].replace(' ', 'T');

                    Task taskCreated = new Event(taskName, LocalDateTime.parse(timeAt,
                            DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HHmm")));
                    tasks.add(taskCreated);
                    System.out.println("Got it. I've added this task:" +
                            "\n\t" + taskCreated);
                    printTotalNumberOfTasks();
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid description of an event!");
                } catch (DateTimeParseException f) {
                    throw new DukeException("Please enter event time in this format: dd/MM/yyyy timeIn24Hr"
                            + "\nE.g. 01/12/2020 2359");
                }
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means!");
            }
            System.out.println("___________________________________________________");
        }
    }

    private static void readInputs() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else {
               try {
                   handleTask(input);
               } catch (DukeException e) {
                   System.out.println(e.getMessage());
                   System.out.println("___________________________________________________");
               }
            }

        }
    }
}
