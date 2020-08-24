import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private static Integer Intger;

    public enum Types {}

    public static void main(String[] args) throws IOException {
        ArrayList<Task> storage  = checkRetrieveSave();
        File saveFile  = new File("data/duke.txt");

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine();
        while(!in.equals("bye")) {
            if (in.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + storage.get(i));
                }
            } else if (in.contains("done ")) {
                int task = Intger.parseInt(in.replace("done ", "")) - 1;
                storage.get(task).done = true;
                storage.get(task).updateRep();
                System.out.println("\tNice! I've marked this task as done:\n\t" + storage.get(task));
                saveTasks(storage);
            } else if (in.contains("delete ")) {
                int task = Intger.parseInt(in.replace("delete ", "")) - 1;
                System.out.println("\tNoted. I've removed this task:\n\t" + storage.get(task));
                storage.remove(task);
                System.out.println("\tNow you have " + storage.size() + " tasks in the list.");
                saveTasks(storage);
            } else {
                try {
                    Task task;
                    if (in.contains("todo")) {
                        String actTask = in.replace("todo", "").trim();
                        if (actTask.equals("")) {
                            throw new EmptyTodo();
                        }
                        task = new Todo(actTask);
                    } else if (in.contains("deadline")) {
                        String[] split = in.replace("deadline", "").trim().split(" /by ");
                        task = new Deadline(split[0], split[1]);
                    } else if (in.contains("event")){
                        String[] split = in.replace("event", "").trim().split(" /at ");
                        task = new Event(split[0], split[1]);
                    } else {
                        throw new InvalidInput();
                    }
                    storage.add(task);
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + task);
                    System.out.println("\tNow you have " + storage.size() + " tasks in the list.");
                } catch (DukeException e) {
                    System.out.println("\t" + e.getMessage());
                }
                saveTasks(storage);
            }
            in = scan.nextLine();
        }
        System.out.println("\tBye. Hope to see you again soon!");

    }

    private static ArrayList<Task> checkRetrieveSave() throws IOException {
        ArrayList<Task> loadTask = new ArrayList<>();
        Path saveFolder = Paths.get("data");
        if (Files.exists(saveFolder)) {
            File saveFile = new File("data/duke.txt");
            if (!saveFile.createNewFile()) {
                //read file that already exists
                BufferedReader br = new BufferedReader(new FileReader(saveFile));
                String line = br.readLine();
                while (line != null) {
                    String[] split = line.split("%d%");
                    if (split.length > 3) {
                        loadTask.add(Task.createTask(split[0], split[1], split[2], split[3]));
                    } else {
                        loadTask.add(Task.createTask(split[0], split[1], split[2], ""));
                    }
                    line = br.readLine();
                }
            }
        } else {
            Files.createDirectory(saveFolder);
            Files.createFile(Paths.get("data/duke.txt"));
        }
        return loadTask;
    }

    private static void saveTasks(ArrayList<Task> tasks) throws IOException {
        File out = new File("data/duke.txt");
        FileOutputStream fos = new FileOutputStream(out);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (Task t : tasks) {
            String line = t.saveRep;
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }
}

class Task {
    String task;
    String saveRep;
    boolean done;
    Task(String task) {
        this.task = task;
        this.done = false;
        this.updateRep();
    }
    static Task createTask(String type, String done, String name, String time) {
        boolean doneBool = done.equals("true");
        Task returnTask;
        if (type.equals("T")) {
            returnTask = new Todo(name);
        } else if (type.equals("D")) {
            returnTask = new Deadline(name, time);
        } else {
            returnTask = new Event(name, time);
        }
        returnTask.done = doneBool;
        return returnTask;
    }

    @Override
    public String toString() {
        return done ? ("[✓] " + task) : ("[✗] " + task);
    }

    public void updateRep() {
        this.saveRep = this.done + "%d%" + this.task;
    }
}

class Todo extends Task {
    Todo(String task) {

        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void updateRep() {
        super.updateRep();
        this.saveRep = "T%d%" + this.done + "%d%" + this.task;
    }
}

class Deadline extends Task {
    LocalDate date;

    Deadline(String task, String deadline) {
        super(task);
        this.date = LocalDate.parse(deadline);
        updateRep();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public void updateRep() {
        super.updateRep();
        this.saveRep = "D%d%" + this.done + "%d%" + this.task + "%d%" + this.date;
    }
}

class Event extends Task {
    LocalDate date;

    Event(String task, String duration) {
        super(task);
        this.date = LocalDate.parse(duration);
        updateRep();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public void updateRep() {
        super.updateRep();
        this.saveRep = "E%d%" + this.done + "%d%" + this.task + "%d%" + this.date;
    }
}

class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
class EmptyTodo extends DukeException {
    public EmptyTodo() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
class InvalidInput extends DukeException {
    public InvalidInput() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
