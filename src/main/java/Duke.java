import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {

    public Storage storage;
    public TaskList taskList;
    public Ui ui;
    public static final String BYE = "bye";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(Path.of(filePath));
        try {
            taskList = new TaskList(storage.showTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            System.exit(0);
        }
    }

    public void run() throws DukeException {

        ui.welcome();

        ArrayList<Task> tasks = new ArrayList<>();
        String input;

        while (!(input = ui.readInput()).equals(BYE)) {

            String parsedInput = Parser.parse(input);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            String trimmed = input.trim();
            String first = trimmed.split(" ")[0].trim(); // checking the first word
            String last = input.substring(first.length()).trim(); // get rid of the first word

            switch (first) {
                case "":
                    continue;
                case "done":
                    int id = Integer.parseInt(last) - 1;

                    ui.doneTask();
                    String changed = tasks.get(id).getDescription();
                    String type = tasks.get(id).getType();
                    System.out.println("[" + type + "][" + "\u2713" + "]" + changed);

                    tasks.get(id).markAsDone();
                    storage.saveTasks(tasks);

                    break;
                case "todo":

                    try {
                        Todo todo = Todo.makeToDo(last, false);
                        tasks.add(todo);
                        ui.addTask();
                        ui.printMessage(todo.toString());
                        ui.showNumberOfTasks(tasks);
                        storage.saveTasks(tasks);
                    } catch (DukeException e) {
                        System.err.println(e.getMessage());
                    }

                    break;
                case "deadline": {

                    String job = last.split("/by")[0].trim();
                    String time = last.split("/by")[1].trim();
                    LocalDate date = LocalDate.parse(time);
                    Deadline work = new Deadline(job + " (by: " + formatter.format(date) + ")", false, date);
                    tasks.add(work);
                    storage.saveTasks(tasks);
                    ui.addTask();
                    ui.printMessage(work.toString());
                    ui.showNumberOfTasks(tasks);

                    break;
                }
                case "event": {

                    String job = last.split("/at")[0].trim();
                    String time = last.split("/at")[1].trim();
                    LocalDate date = LocalDate.parse(time);
                    Event work = new Event(job + " (at: " + formatter.format(date) + ")", false, date);
                    tasks.add(work);
                    storage.saveTasks(tasks);
                    ui.addTask();
                    ui.printMessage(work.toString());
                    ui.showNumberOfTasks(tasks);

                    break;
                }
                case "delete": {
                    int index = Integer.parseInt(last) - 1;
                    ui.removeTask();
                    String deleted = tasks.get(index).getDescription();
                    String deletedType = tasks.get(index).getType();
                    String status = tasks.get(index).getStatusIcon();
                    System.out.println("[" + deletedType + "][" + status + "] " + deleted);
                    tasks.remove(index);
                    storage.saveTasks(tasks);
                    ui.showNumberOfTasks(tasks);

                    break;

                }
                case "list":
                    Iterator<Task> iter = tasks.iterator();
                    int index = 1;
                    ui.showList();
                    while (iter.hasNext()) {
                        Task currentTask = iter.next();
                        String next = currentTask.getDescription();
                        System.out.println(index + "." + "[" + currentTask.getType() + "][" + currentTask.getStatusIcon() + "] " + next);
                        index++;
                    }

                    break;
                default:

                    ui.invalidInput();

                    break;
            }
        }

        ui.exit();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("duke.txt").run();
    }
}
