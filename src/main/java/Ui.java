import java.util.List;
import java.util.Scanner;

public class Ui {
    private Storage storage;
    private Scanner scanner = new Scanner(System.in);

    private Ui(Storage storage) {
        this.storage = storage;
    }

    private void repeat() {
        Ui.sectionize();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + this.storage.getList().get(this.storage.size() - 1).toString());
        System.out.println("\tNow you have " + this.storage.size() + " tasks in the list.");
        Ui.sectionize();
    }

    private void listItems() {
        Ui.sectionize();
        System.out.println("\tHere are the tasks in your list:");
        int counter = 1;
        for (int i = 0; i < this.storage.size(); i++) {
            System.out.println("\t" + counter + "." + this.storage.getItem(i).toString());
            counter++;
        }
        Ui.sectionize();
    }

    private void sayBye() {
        Ui.sectionize();
        System.out.println("\tBye. Hope to see you again soon!");
        Ui.sectionize();
    }

    private void markDone(int index) {
        this.storage.setDone(index);
        Ui.sectionize();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + this.storage.getItem(index).toString());
        Ui.sectionize();
    }

    private void remove(int index) {
        try {
            Task task = this.storage.remove(index);
            Ui.sectionize();
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println("\t\t" + task.toString());
            System.out.println("\tNow you have " + this.storage.size() + " tasks in the list.");
            Ui.sectionize();
        } catch (IndexOutOfBoundsException e) {
            Ui.sectionize();
            System.out.println("\t☹ OOPS!!! I'm sorry, this task does not exist in your list!");
            Ui.sectionize();
        }
    }

    private void intro() {
        System.out.println("Hello! I'm DukeBot");
        System.out.println("What can I do for you?");
    }
    private static void sectionize() {
        System.out.println("\t____________________________________________________________");
    }

    private static String errorMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public static void initialize() {
        Ui ui = new Ui(new Storage());
        ui.intro();
        ui.listen();
    }

    public static void initialize(List<Task> taskArr) {
        Ui ui = new Ui(new Storage(taskArr));
        ui.intro();
        ui.listen();
    }

    private static void exit() {
        System.exit(0);
    }

    private void listen() {
        String input = scanner.nextLine();
        if (Parser.isDone(input)) {
            int index = Integer.parseInt(input.substring(5, 6)) - 1;
            this.markDone(index);
        } else if(Parser.isDelete(input)) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            this.remove(index);
        } else if (input.equals("bye")) {
            this.sayBye();
            exit();
        } else if (input.equals("list")) {
            this.listItems();
        } else {
            try {
                if (!Parser.correctInputFormat(input)) {
                    throw new DukeException(Ui.errorMessage());
                }

                //pull type of task and the task
                String taskType = input.substring(0, input.indexOf(" "));
                String task = input.substring(input.indexOf(" ") + 1);
                String[] taskAndDateArr;
                String date;
                //System.out.println(task);
                switch (taskType) {
                    case ("todo"):
                        storage.addItem(new ToDos(task));
                        this.repeat();
                        break;
                    case ("deadline"):
                        // date = 'by Sunday'
                        taskAndDateArr = Parser.splitTaskAndDate(task);
                        task = taskAndDateArr[0];
                        date = taskAndDateArr[1];
                        storage.addItem(new Deadlines(task, date));
                        this.repeat();
                        break;
                    case ("event"):
                        taskAndDateArr = Parser.splitTaskAndDate(task);
                        task = taskAndDateArr[0];
                        date = taskAndDateArr[1];
                        storage.addItem(new Events(task, date));
                        this.repeat();
                        break;
                    default:
                        throw new DukeException(Ui.errorMessage());
                }
            } catch (DukeException e) {
                Ui.sectionize();
                System.out.println("\t\t" + e.getMessage());
                Ui.sectionize();
            }
        }
        this.listen();
    }
}
