import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Duke {
    ArrayList<Task> tasklist;
    String divider = "\t_________________________________________\n";
    String filePath = "data/duke.txt";

    public Duke() {
        this.tasklist = new ArrayList<>();
    }

    public void checkIfDone(String s, Task task) {
        if (s.equals("1")) {
            task.markAsDone();
        }
    }

    public void addFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String[] data = s.nextLine().split(" , ");

            if (data[0].equals("T")) {
                Task t = new Todo(data[2]);
                checkIfDone(data[1], t);
                tasklist.add(t);
            }

            if (data[0].equals("D")) {
                Task d = new Deadline(data[2], data[3]);
                checkIfDone(data[1], d);
                tasklist.add(d);
            }

            if (data[0].equals("E")) {
                Task e = new Event(data[2], data[3]);
                checkIfDone(data[1], e);
                tasklist.add(e);
            }
        }
    }

    private void updateFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 1; i < tasklist.size() + 1; i++) {
            Task t = tasklist.get(i - 1);
            fw.write(processTaskToUpdateFile(t));
        }

        fw.close();
    }

    private String processTaskToUpdateFile(Task t) {
        String task = t.toString();
        char type = task.charAt(1);
        boolean isDone = task.charAt(4) == 'X';
        String indicateDone = isDone ? "1" : "0";

        String frontHalfToPrint = type + " , " + indicateDone + " , ";
        String rest = task.substring(7);
        String toPrint;

        switch (type) {
            case ('T'):
                toPrint = frontHalfToPrint + rest + "\n";
                break;
            case ('D'):
                int startOfBy = rest.indexOf("(by");
                String deadlineDescription = rest.substring(0, startOfBy - 1);
                String by = rest.substring(startOfBy + 5, rest.length() - 1);
                toPrint = frontHalfToPrint + deadlineDescription + " , " + by + "\n";
                break;
            case ('E'):
                int startOfAt = rest.indexOf("(at");
                String eventDescription = rest.substring(0, startOfAt - 1);
                String at = rest.substring(startOfAt + 5, rest.length() - 1);
                toPrint = frontHalfToPrint + eventDescription + " , " + at + "\n";
                break;
            default:
                toPrint = "something wrong";
                break;
        }
        return toPrint;
    }

    public void addToDo(String description) throws DukeException {
        if (description.length() <= 0) {
            throw new DukeException("Please provide a description!");
        }
        Task t = new Todo(description);
        this.tasklist.add(t);
        printOutputAddTask(t);
        try {
            updateFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void addDeadline(String details) throws DukeException {
        try {
            String[] temp = details.split("/by");
            String description = temp[0].trim();
            String by = temp[1].trim();

            if (description.length() <= 0 || by.length() <= 0) {
                throw new DukeException("Please provide both a description and date in format 'description /by date'!");
            }
            Task t = new Deadline(description, by);
            this.tasklist.add(t);
            printOutputAddTask(t);
            try {
                updateFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please provide both a description and date in format 'description /by date'!");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide date in format yyyy-mm-dd");
        }
    }

    public void addEvent(String details) throws DukeException {
        try {
            String[] temp2 = details.split("/at");
            String description = temp2[0].trim();
            String at = temp2[1].trim();

            if (description.length() <= 0 || at.length() <= 0) {
                throw new DukeException("Please provide both a description and date in format 'description /at date'!");
            }
            Task t = new Event(description, at);
            this.tasklist.add(t);
            printOutputAddTask(t);
            try {
                updateFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please provide both a description and date in format 'description /at date'!");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide date in format yyyy-mm-dd");
        }
    }

    public void deleteTask(int taskIndex) throws DukeException {
        try {
            Task t = tasklist.get(taskIndex - 1);
            tasklist.remove(taskIndex - 1);

            System.out.println(divider);
            System.out.println("\tMr Camel will delete this task:\n" + "\t" + t);
            System.out.println("\tNumber of tasks: " + tasklist.size());
            System.out.println(divider);

            try {
                updateFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    public void doneTask(int taskIndex) throws DukeException {
        try {
            Task t = tasklist.get(taskIndex - 1);
            t.markAsDone();

            System.out.println(divider);
            System.out.println("\tMr Camel will mark this task as done:\n" + "\t" + t);
            System.out.println(divider);

            try {
                updateFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    public void printOutputAddTask(Task t) {
        System.out.println(divider);
        System.out.println("\tMr Camel has added: " + t);
        System.out.println("\tNumber of tasks: " + tasklist.size());
        System.out.println(divider);
    }


    public void printList() {
        System.out.println(divider);
        System.out.println("\tTasks:");

        for (int i = 1; i < tasklist.size() + 1; i++) {
            System.out.println("\t" + i + ". " + tasklist.get(i-1));
        }
        System.out.println(divider);
    }

    public void initDuke() {
        String MrCamel =
                "                  ,,__\n"
                        + "        ..  ..   / o._)\n"
                        + "       /--'/--\\  \\-'|| \n"
                        + "      /        \\_/ / |\n"
                        + "    .'\\  \\__\\  __.'.'\n"
                        + "      )\\ |  )\\ |\n"
                        + "     // \\\\ // \\\\\n"
                        + "    ||_  \\\\|_  \\\\_\n"
                        + "    '--' '--'' '--'\n";
        System.out.println("Mr Camel says hello. What can Mr Camel do for you today?\n" + MrCamel);
    }

    public void runDuke() {
        Scanner sc = new Scanner(System.in);
        boolean hasNext = true;

        while (hasNext) {
            String command = sc.next();
            try {
                switch (command) {
                    case "bye":
                        System.out.println(divider);
                        System.out.println("\tBye. Mr Camel will miss you! :(");
                        System.out.println(divider);
                        hasNext = false;
                        break;
                    case "list":
                        printList();
                        break;
                    case "todo":
                        String todoDescription = sc.nextLine().trim();
                        addToDo(todoDescription);
                        break;
                    case "deadline":
                        String deadlineDetails = sc.nextLine();
                        addDeadline(deadlineDetails);
                        break;
                    case "event":
                        String eventDetails = sc.nextLine();
                        addEvent(eventDetails);
                        break;
                    case "done":
                        int doneTaskIndex = sc.nextInt();
                        doneTask(doneTaskIndex);
                        break;
                    case "delete":
                        int deleteTaskIndex = sc.nextInt();
                        deleteTask(deleteTaskIndex);
                        break;

                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(divider);
                System.out.println("\t" + e.getMessage());
                System.out.println(divider);
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();

        try {
            duke.addFileContents(duke.filePath);
            duke.initDuke();
            duke.runDuke();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
