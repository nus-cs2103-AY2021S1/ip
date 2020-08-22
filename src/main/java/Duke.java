import java.util.Scanner;

public class Duke {
    private static final String BORDER = "____________________________________________________________\n";

    public static boolean checkBye(String s) {
        return s.equals("bye");
    }

    public static void exitLine() {
        System.out.println(BORDER + "Bye. Hope to see you again soon!\n" + BORDER);
    }


    public static void startUp(TaskList taskList, Storage storage) {
        storage.fileCheck();
        if (!storage.getFile().exists() || storage.getFile().length() == 0) {
            System.out.println(
                    BORDER + "Hello! I'm Duke\n"
                    + "What can I do for you?\n" + BORDER
            );
        } else {
            System.out.println(
                    BORDER + "Well come back!\n" + "You still have "
                    + taskList.getList().size() + " tasks left to clear.\n" + BORDER
            );
        }
    }

    public static void run() {
        TaskList taskList = new TaskList();
        Scanner scan = new Scanner(System.in);
        Storage storage = new Storage(taskList, "duke");
        startUp(taskList, storage);
        while (scan.hasNext()) {
            String test = scan.next();
            if (checkBye(test)) {
                exitLine();
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
                        System.out.println(BORDER + e.getMessage() + "\n" + BORDER);
                    }
                } else if (TaskList.checkDel(test)) {
                    try {
                        TaskList.delTask(next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(BORDER + e.getMessage() + "\n" + BORDER);
                    }
                } else {
                    try {
                        TaskList.addTask(test, next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(BORDER + e.getMessage() + "\n" + BORDER);
                    }
                }
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        run();
    }
}
