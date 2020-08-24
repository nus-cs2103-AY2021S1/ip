import java.util.Scanner;
import java.util.ArrayList;

enum actionType {
    QUIT,
    LIST,
    MARK_DONE,
    DELETE,
    ADD_TODO,
    ADD_DEADLINE,
    ADD_EVENT,
    WRONG_INPUT
}

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.getTasks());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    private actionType getAction(String input) {
        return input.equalsIgnoreCase("bye")
                ? actionType.QUIT
                : input.equalsIgnoreCase("list")
                ? actionType.LIST
                : input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("done")
                ? actionType.MARK_DONE
                : input.length() >= 6 && input.substring(0, 6).equalsIgnoreCase("delete")
                ? actionType.DELETE
                : input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("todo")
                ? actionType.ADD_TODO
                : input.length() >= 5 && input.substring(0, 5).equalsIgnoreCase("event")
                ? actionType.ADD_EVENT
                : input.length() >= 8 && input.substring(0, 8).equalsIgnoreCase("deadline")
                ? actionType.ADD_DEADLINE
                : actionType.WRONG_INPUT;
    }

    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            ui.showWelcome();
            String next;
            while (true) {
                next = sc.nextLine();
                actionType action = getAction(next);
                switch (action) {
                case QUIT:
                    ui.goodbye();
                    sc.close();
                    System.exit(0);
                    break;
                case LIST:
                    ui.printList(tasks);
                    break;
                case MARK_DONE:
                    int taskNo = Integer.parseInt(next.substring(5));
                    if (taskNo > tasks.getList().size()) {
                        throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
                    } else {
                        Task completedTask = tasks.getList().get(taskNo - 1);
                        completedTask.markAsDone();
                        storage.updateFile(tasks);
                        System.out.println("Task marked complete:");
                        System.out.println(completedTask.toString());
                    }
                    break;
                case DELETE:
                    int deleteNo = Integer.parseInt(next.substring(7));
                    if (deleteNo > tasks.getList().size()) {
                        throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
                    } else {
                        Task deletedTask = tasks.getList().remove(deleteNo - 1);
                        storage.updateFile(tasks);
                        System.out.println("Task deleted:");
                        System.out.println(deletedTask.toString());
                    }
                    break;
                case ADD_TODO:
                    if (next.length() < 6) {
                        throw new DukeException("Task cannot be empty _(´ཀ`」 ∠)_");
                    } else {
                        Task newTodo = new ToDo(next.substring(5), false);
                        tasks.getList().add(newTodo);
                        storage.updateFile(tasks);
                        System.out.println("Added: " + newTodo.toString());
                        System.out.println("Total tasks: " + tasks.getList().size());
                    }
                    break;
                case ADD_EVENT:
                    if (next.length() < 7) {
                        throw new DukeException("Event cannot be empty _(´ཀ`」 ∠)_");
                    } else {
                        Task newEvent = new Event(next.substring(6), false);
                        tasks.getList().add(newEvent);
                        storage.updateFile(tasks);
                        System.out.println("Added: " + newEvent.toString());
                        System.out.println("Total tasks: " + tasks.getList().size());
                    }
                    break;
                case ADD_DEADLINE:
                    if (next.length() < 10) {
                        throw new DukeException("Deadline cannot be empty _(´ཀ`」 ∠)_");
                    } else {
                        Task newDeadline = new Deadline(next.substring(9), false);
                        tasks.getList().add(newDeadline);
                        storage.updateFile(tasks);
                        System.out.println("Added: " + newDeadline.toString());
                        System.out.println("Total tasks: " + tasks.getList().size());
                    }
                    break;
                case WRONG_INPUT:
                    throw new DukeException("I have no idea what that means ¯\\_(ツ)_/¯");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/data.txt").run();
    }
}
