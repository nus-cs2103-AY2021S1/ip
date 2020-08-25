import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.read());
    }

    public void run() {
        Ui.print("hi! im conundrum boy :)");
        Scanner input = new Scanner(System.in);
        String in = "";
        while (!in.equals("bye")) {
            in = input.nextLine();
            if (in.equals("bye")) {
                Ui.print("bye bye!");
                continue;
            }

            Command cmd = Parser.parse(in);
            if (cmd == Command.blank) {
                continue;
            } else if (cmd == Command.clear) {
                storage.clear();
                tasks = new TaskList(new ArrayList<Task>());
                continue;
            } else if (cmd == Command.list) {
                Ui.print(tasks.toString());
            } else if (cmd == Command.find) {
                String keyword = in.substring(5);
                if (keyword.length() == 0) {
                    Ui.errorMsg("you haven't entered a search keyword!");
                } else {
                    Ui.print("here's the list of tasks that contain the keyword!");
                    Ui.print(tasks.findTasks(keyword).toString());
                }
            } else if (cmd == Command.done) {
                int current;
                try {
                    current = Integer.parseInt(in.substring(5));
                } catch (Exception e){
                    Ui.errorMsg("you haven't entered a task number to complete!");
                    continue;
                }
                current--;
                Task task;
                if (current < 0 || current >= tasks.size()) {
                    Ui.errorMsg("that is not the number of a task in the list!");
                } else {
                    task = tasks.get(current);
                    if (task.done) {
                        Ui.errorMsg("you have already completed " + task.task + "!");
                        continue;
                    }
                    task.complete();
                    Ui.print("congrats on finishing your task :) it's marked as done:\n\t" + task);

                }
            } else if (cmd == Command.delete){
                int current;
                try {
                    current = Integer.parseInt(in.substring(7));
                } catch (Exception e){
                    Ui.errorMsg("you haven't entered a task number to delete!");
                    continue;
                }
                current--;
                Task task;
                if (current < 0 || current >= tasks.size()) {
                    Ui.errorMsg("that is not the number of a task in the list!");
                } else {
                    task = tasks.get(current);
                    tasks.delete(current);
                    Ui.print("i've removed the following task from the list:\n\t" + task + "\nnow you have " + tasks.size() + " items in your tasklist.");

                }
            } else if (cmd == Command.add){
                Task temp = Parser.getTask(in);
                if (temp != null) {
                    tasks.add(temp);
                    Ui.print("i've added this task for you: \n\t" + temp + "\nnow you have " + tasks.size() + " items in your tasklist.");
                }
            } else {
                Ui.errorMsg("i don't know what that means :(");
            }
            storage.save(tasks.getTaskList());

        }

    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
