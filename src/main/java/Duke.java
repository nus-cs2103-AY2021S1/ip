import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Creates a new instance of Duke which stores information at the given path.
     * @param filePath the path where task information is stored.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.read());
    }
    public Duke() {}

    /**
     * Generates response to user input.
     * @param in the user input string.
     * @return response to user input.
     */
    String getResponse(String in) {
        if (in.equals("bye")) {
            return Ui.print("bye bye!");
        }

        Command cmd = Parser.parse(in);
        if (cmd == Command.blank) {
        } else if (cmd == Command.clear) {
            storage.clear();
            tasks = new TaskList(new ArrayList<Task>());
        } else if (cmd == Command.list) {
            assert tasks != null : "Tasks is null";
            return Ui.print(tasks.toString());
        } else if (cmd == Command.find) {
            assert in.length() >= 5 : "input is too short";
            String keyword = in.substring(5);
            if (keyword.length() == 0) {
                return Ui.errorMsg("you haven't entered a search keyword!");
            } else {
                return Ui.print("here's the list of tasks that contain the keyword!") + Ui.print(tasks.findTasks(keyword).toString());
            }
        } else if (cmd == Command.done) {
            int current;
            try {
                assert in.length() >= 5 : "input is too short";
                current = Integer.parseInt(in.substring(5));
            } catch (Exception e){
                return Ui.errorMsg("you haven't entered a task number to complete!");
            }
            current--;
            Task task;
            if (current < 0 || current >= tasks.size()) {
                return Ui.errorMsg("that is not the number of a task in the list!");
            } else {
                task = tasks.get(current);
                assert task != null : "task is null";
                if (task.done) {
                    return Ui.errorMsg("you have already completed " + task.task + "!");
                }
                task.complete();
                return Ui.print("congrats on finishing your task :) it's marked as done:\n\t" + task);

            }
        } else if (cmd == Command.delete){
            int current;
            try {
                assert in.length() >= 7 : "input is too short";
                current = Integer.parseInt(in.substring(7));
            } catch (Exception e){
                return Ui.errorMsg("you haven't entered a task number to delete!");
            }
            current--;
            Task task;
            if (current < 0 || current >= tasks.size()) {
                return Ui.errorMsg("that is not the number of a task in the list!");
            } else {
                task = tasks.get(current);
                assert task != null : "task is null";
                tasks.delete(current);
                return Ui.print("i've removed the following task from the list:\n\t" + task + "\nnow you have " + tasks.size() + " items in your tasklist.");

            }
        } else if (cmd == Command.add){
            Task temp = Parser.getTask(in);
            if (temp != null) {
                tasks.add(temp);
                return Ui.print("i've added this task for you: \n\t" + temp + "\nnow you have " + tasks.size() + " items in your tasklist.");
            } else {
                assert Parser.msg != null : "parser error message is null";
                return Parser.msg;
            }
        } else {
            return Ui.errorMsg("i don't know what that means :(");
        }
        storage.save(tasks.getTaskList());
        return "";
    }
}
