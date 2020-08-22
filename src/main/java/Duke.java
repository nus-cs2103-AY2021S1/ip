import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    public Duke(String name) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(taskList, name);
    }
    public void run() {
        TaskList taskList = this.taskList;
        Scanner scan = new Scanner(System.in);
        Storage storage = this.storage;
        String border = ui.getBorder();
        ui.startUp(taskList, storage);
        while (scan.hasNext()) {
            String test = scan.next();
            if (ui.checkBye(test)) {
                ui.exitLine();
                break;
            } else {
                String next = scan.nextLine().replaceFirst(" ", "");
                if (TaskList.checkList(test)) {
                    TaskList.displayList();
                } else if (TaskList.checkDone(test)) {
                    try {
                        TaskList.doneTask(next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(border + e.getMessage() + "\n" + border);
                    }
                } else if (TaskList.checkDel(test)) {
                    try {
                        TaskList.delTask(next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(border + e.getMessage() + "\n" + border);
                    }
                } else {
                    try {
                        TaskList.addTask(test, next);
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
