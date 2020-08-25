import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BotHandler {
    private final static ArrayList<Task> lst = new ArrayList<>();

    private static void displayList() throws DuckieException{
        if (lst.size() == 0) {
            throw new DuckieNoListException();
        }

        BotResponses.displayListReply(lst);
    }

    private static void addTask(String input, String type) throws IOException, DuckieException {
        Task t1 = null;
        try {
            if (is_word(input)) {
                throw new DuckieInsufficientInfoException();
            }

            if (type.equals("todo")) {
                String todo = input.split(" ", 2)[1];
                t1 = new Todo(todo);
            } else {
                String[] splitted = input.split("/");
                String time = splitted[1].split(" ", 2)[1];
                String description = splitted[0].split(" ", 2)[1];
                if (type.equals("deadline")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
                    LocalDate date = LocalDate.parse(time, formatter);
                    t1 = new Deadline(description, date);
                } else { //This will be Event task.
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
                    t1 = new Event(description, dateTime);
                }
            }
            lst.add(t1);
            writeToFile();
        } catch (IOException e) {
            throw e;
        }
        BotResponses.addTaskReply(t1, lst);
    }

    private static void checkTask(int ind) throws IOException {
        Task t1 = lst.get(ind - 1);
        t1.checked();
        writeToFile();
        BotResponses.checkTaskReply(t1);
    }

    public static void deleteTask(int ind) throws IOException {
        Task t1 = lst.get(ind - 1);
        lst.remove(ind - 1);
        writeToFile();
        BotResponses.deleteTaskReply(t1);
    }

    public static void deleteAllTasks() throws IOException {
        lst.clear();
        writeToFile();
        BotResponses.deleteAllReply();
    }

    public static void processData(Scanner sc) throws DuckieException {
        while(sc.hasNextLine()) {
            String task = sc.nextLine();
            String[] taskBreakdown = task.split("\\|");

            String type = taskBreakdown[0].strip();
            String isDone = taskBreakdown[1].strip();
            String description = taskBreakdown[2].strip();
            if (type.equals("T")) {
                Todo taskToDo = new Todo(description);
                if (isDone.equals("1")) {
                    taskToDo.checked();
                }
                lst.add(taskToDo);
            } else if (type.equals("D")) {
                String date = taskBreakdown[3].strip();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
                LocalDate d1 = LocalDate.parse(date, formatter);
                Deadline taskD = new Deadline(description, d1);
                if (isDone.equals("1")) {
                    taskD.checked();
                }
                lst.add(taskD);
            } else {
                String dateTime = taskBreakdown[3].strip();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
                LocalDateTime d1 = LocalDateTime.parse(dateTime, formatter);
                Event taskE = new Event(description, d1);
                if (isDone.equals("1")) {
                    taskE.checked();
                }
                lst.add(taskE);
            }
        }
        System.out.println("\t" + "Loading tasks...");
        displayList();
    }

    private static void writeToFile() throws IOException {
        String cwd = System.getProperty("user.dir");
        String dukeFile = cwd + "/data/duckie.txt";
        System.out.println(dukeFile);
        FileWriter fw = new FileWriter(dukeFile);
        String toWrite = "";
        for (Task t1: lst) {
            toWrite += (t1.getType() + (t1.isCompleted() ? " | 1 | " : " | 0 | ")
                        + t1.getDescription())
                        + (t1.getDate() != null ? "| " + t1.getDate() : "")
                        + System.lineSeparator();
        }
        fw.write(toWrite);
        fw.close();
    }//Check if a String only
    private static boolean is_word(String s) {
        return (s.length() > 0 && s.split("\\s+").length == 1);
    }

    public static void serve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String initialInput = sc.nextLine();
                String input = initialInput.strip();
                System.out.println(input);
                if (input.equalsIgnoreCase("bye")) {
                    System.out.print(BotResponses.getHorizL());
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    displayList();
                } else if (input.toLowerCase().indexOf("done") == 0) {
                    if (lst.size() == 0) {
                        throw new DuckieNoListException();
                    } else if (is_word(input)) {
                        throw new DuckieInsufficientInfoException();
                    }
                    int ind = Integer.parseInt(input.split(" ")[1]);

                    if (lst.size() < ind) {
                        throw new DuckieNoIndexException();
                    }

                    checkTask(ind);
                } else if (input.toLowerCase().indexOf("delete") == 0) {
                    if (lst.size() == 0) {
                        throw new DuckieNoListException();
                    } else if (is_word(input)) {
                        throw new DuckieInsufficientInfoException();
                    }

                    String description = input.split(" ")[1].strip();

                    if (description.toLowerCase().equals("all")) {
                        deleteAllTasks();
                    } else {
                        int ind = Integer.parseInt(description);
                        if (lst.size() < ind) {
                            throw new DuckieNoIndexException();
                        }
                        deleteTask(ind);
                    }
                } else if (input.toLowerCase().indexOf("todo") == 0) {
                    addTask(input, "todo");
                } else if (input.toLowerCase().indexOf("deadline") == 0) {
                    addTask(input, "deadline");
                } else if (input.toLowerCase().indexOf("event") == 0) {
                    addTask(input, "event");
                } else {
                    throw new DuckieInvalidCommandException();
                }
            } catch (DuckieException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
