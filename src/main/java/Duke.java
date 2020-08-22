import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Duke(String name) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(taskList, name);
    }

    public void run() {
        TaskList taskList = this.taskList;
        Scanner scan = new Scanner(System.in);
        Storage storage = this.storage;
        String border = Ui.getBorder();

        ui.startUp(taskList, storage);

        while (scan.hasNext()) {
            String test = scan.next();
            if (ui.checkBye(test)) {
                ui.exitLine();
                break;
            } else {
                String next = scan.nextLine().replaceFirst(" ", "");
                if (taskList.checkList(test)) {
                    taskList.displayList();
                } else if (taskList.checkDone(test)) {
                    try {
                        taskList.doneTask(next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(border + e.getMessage() + "\n" + border);
                    }
                } else if (taskList.checkDel(test)) {
                    try {
                        taskList.delTask(next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(border + e.getMessage() + "\n" + border);
                    }
                } else {
                    try {
                        taskList.addTask(test, next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(border + e.getMessage() + "\n" + border);
                    }
                }
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        new Duke("duke").run();
    }
}
