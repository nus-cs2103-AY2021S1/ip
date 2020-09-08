import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Creates a new instance of Duke which stores information at the given path.
     *
     * @param filePath the path where task information is stored.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.read());
    }
    public Duke() {}

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String in) {
        String result = "";
        if (in.equals("bye")) {
            return Ui.print("bye bye!");
        }

        Command cmd = Parser.parse(in);
        if (cmd == Command.BLANK) {
            result = Ui.errorMsg("you haven't entered any command!");
        } else if (cmd == Command.CLEAR) {
            storage.clear();
            tasks = new TaskList(new ArrayList<Task>());
        } else if (cmd == Command.LIST) {
            result = Ui.print(tasks.toString());
        } else if (cmd == Command.FIND) {
            String keyword = in.substring(Parser.FIND_LENGTH);
            if (keyword.length() == 0) {
                result = Ui.errorMsg("you haven't entered a search keyword!");
            } else {
                result = Ui.print("here's the list of tasks that contain the keyword!") + Ui.print(tasks.findTasks(keyword).toString());
            }
        } else if (cmd == Command.DONE) {
            int current;
            try {
                current = Integer.parseInt(in.substring(Parser.DONE_LENGTH));
                current--;
                Task task;
                if (current < 0 || current >= tasks.size()) {
                    result = Ui.errorMsg("that is not the number of a task in the list!");
                } else {
                    task = tasks.get(current);
                    if (task.isDone) {
                        result = Ui.errorMsg("you have already completed " + task.task + "!");
                    } else {
                        task.complete();
                        result = Ui.print("congrats on finishing your task :) it's marked as done:\n\t" + task);
                    }

                }
            } catch (Exception e){
                result = Ui.errorMsg("you haven't entered a task number to complete!");
            }

        } else if (cmd == Command.DELETE){
            int current;
            try {
                current = Integer.parseInt(in.substring(Parser.DELETE_LENGTH));
                current--;
                Task task;
                if (current < 0 || current >= tasks.size()) {
                    result = Ui.errorMsg("that is not the number of a task in the list!");
                } else {
                    task = tasks.get(current);
                    tasks.delete(current);
                    result = Ui.print("i've removed the following task from the list:\n\t" + task + "\nnow you have " + tasks.size() + " items in your tasklist.");

                }
            } catch (Exception e){
                result = Ui.errorMsg("you haven't entered a task number to delete!");
            }
        } else if (cmd == Command.ADD){
            Task temp = Parser.getTask(in);
            if (temp != null) {
                tasks.add(temp);
                result = Ui.print("i've added this task for you: \n\t" + temp + "\nnow you have " + tasks.size() + " items in your tasklist.");
            } else {
                result = Parser.msg;
            }
        } else {
            result = Ui.errorMsg("i don't know what that means :(");
        }
        storage.save(tasks.getTaskList());
        return result;
    }
}
