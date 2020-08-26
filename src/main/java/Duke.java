import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Scanner;

public class Duke {

<<<<<<< HEAD
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public void run() {
        ui.greet();
=======
    public enum TaskType {
        TODOS,
        DEADLINE,
        EVENT
    }

    private static void greet() {
        String greeting = "Hello mah dud, itza handsome robo speakin\n" +
                "What duh hell du yu wan?";
        System.out.println(greeting);
    }

    private static void farewell() {
        String farewell = "Never come back,\n" +
                "dun wanna see yu ever agin";
        System.out.println(farewell);
    }

    private static void processDone(String s) throws InvalidIndexException {
>>>>>>> branch-Level-7
        try {
            boolean isExit = false;
            Scanner sc = new Scanner(System.in);
            while (!isExit) {
                ui.showLine();
                String nextLine = sc.nextLine();
                isExit = parser.parse(tasks, nextLine);
            }
<<<<<<< HEAD
            sc.close();
        } catch (Exception e) { // Change to DukeException
            ui.showError(e.getMessage());
=======
        } catch (NumberFormatException e) {
            System.out.println("Input is not number ley...");
        }
    }

    private static void processDelete(String s) {
        try {
            int num = Integer.parseInt(s) - 1;
            if (num < 0 || num > TaskList.getSize() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            } else {
                System.out.println("okcan done:");
                String i = TaskList.removeTask(Integer.parseInt(s) - 1).toString();
                System.out.println(i);
                System.out.println("Now you have " + TaskList.getSize() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input is not number ley...");
        }
    }

    private static void testNextLineSplit(String nextLine) throws EmptyDescriptionException, UnknownCommandException {
        String[] nextLineSplit = nextLine.split(" ", 2);
        switch (nextLineSplit[0]) {
            case "todo":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                System.out.println(TaskList.addTaskHelper(TaskType.TODOS, nextLineSplit[1]));
                break;
            case "deadline":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                System.out.println(TaskList.addTaskHelper(TaskType.DEADLINE, nextLineSplit[1]));
                break;
            case "event":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                System.out.println(TaskList.addTaskHelper(TaskType.EVENT, nextLineSplit[1]));
                break;
            case "done":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                try {
                    processDone(nextLineSplit[1]);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
                break;
            case "delete":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                try {
                    processDelete(nextLineSplit[1]);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
                break;
            default:
                throw new UnknownCommandException("Don't understand...");
        }
    }

    private static boolean testNextLine(String nextLine) {
        switch (nextLine) {
            case "bye":
                farewell();
                return true;
            case "list":
                System.out.println("Here yur tasks faggit: ");
                System.out.println(TaskList.toStr());
                return false;
            default:
                try {
                    testNextLineSplit(nextLine);
                } catch (UnknownCommandException | EmptyDescriptionException e) {
                    System.out.println(e.toString());
                }
                return false;
>>>>>>> branch-Level-7
        }
        ui.farewell();
    }

    public static void main(String[] args) {
<<<<<<< HEAD
        new Duke("data/tasks.txt").run();
=======
        ReadFile.readFile();
        Scanner sc = new Scanner(System.in);
        greet();
        boolean saidBye = false;
        while (!saidBye) {
            String nextLine = "";
            if (!saidBye) nextLine = sc.nextLine();
            saidBye = testNextLine(nextLine);
        }
        sc.close();
        TaskList.saveTasks();
>>>>>>> branch-Level-7
    }

}