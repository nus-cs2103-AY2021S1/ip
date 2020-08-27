package duke;

import duke.exception.DateException;
import duke.exception.DukeException;
import duke.exception.MissingInformationException;
import duke.task.Task;
import duke.format.DateFormat;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.List;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DateException e) {
            ui.printMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayWelcome();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            Parser parser = new Parser(line);
            String command = parser.getCommand();
            try {
                if (command.equals("bye")) {
                    ui.printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    ui.listTasks(tasks.getTasks());
                } else if (command.equals("done")) {
                    int index = parser.getIndex();
                    Task task = tasks.completeTask(index);
                    ui.completeSuccess(task);
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("delete")) {
                    int index = parser.getIndex();
                    Task task = tasks.deleteTask(index);
                    ui.deleteSuccess(task, tasks.getCount());
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("todo")) {
                    String name = parser.getDescription(command);
                    Task todo = tasks.addTodo(name);
                    ui.addSuccess(todo, tasks.getCount());
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("deadline")) {
                    String name = parser.getName(command);
                    Date by = parser.getDeadlineBy();
                    Task deadline = tasks.addDeadline(name, by);
                    ui.addSuccess(deadline, tasks.getCount());
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("event")) {
                    String name = parser.getName(command);
                    Date at = parser.getEventAt();
                    Task event = tasks.addEvent(name, at);
                    ui.addSuccess(event, tasks.getCount());
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("get")) {
                    String dateString = parser.getDate();
                    Date date = DateFormat.parseDate(dateString);
                    List<Task> tasksWithDate = tasks.getTasksWithDate(date);
                    ui.listTasksWithDate(tasksWithDate, dateString);
                } else {
                    throw new DukeException("I am sorry, I don't know what that means :(");
                }
            } catch (DukeException | MissingInformationException | DateException | IOException e) {
                ui.printMessage(e.getMessage());
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                ui.printMessage("Invalid task number!");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
