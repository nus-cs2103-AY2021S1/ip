import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private TaskList taskList;
    private Scanner scanner = new Scanner(System.in);

    private Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public Ui() {
        this.taskList = null;
    }

    private void reply() {
        Ui.sectionize();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + this.taskList.getList().get(this.taskList.size() - 1).toString());
        System.out.println("\tNow you have " + this.taskList.size() + " tasks in the list.");
        Ui.sectionize();
    }

    private void listItems() {
        Ui.sectionize();
        System.out.println("\tHere are the tasks in your list:");
        int counter = 1;
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("\t" + counter + "." + this.taskList.getItem(i).toString());
            counter++;
        }
        Ui.sectionize();
    }

    private void sayBye() {
        try {
            Storage.saveListToFile(taskList);
            Ui.sectionize();
            System.out.println("\tBye. Hope to see you again soon!");
            Ui.sectionize();
        } catch (IOException e) {
            System.out.println("Sorry! The file failed to save. Please try again.");
        }
    }

    private void markDone(int index) {
        this.taskList.setDone(index);
        Ui.sectionize();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + this.taskList.getItem(index).toString());
        Ui.sectionize();
    }

    private void remove(int index) {
        try {
            Task task = this.taskList.remove(index);
            Ui.sectionize();
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println("\t\t" + task.toString());
            System.out.println("\tNow you have " + this.taskList.size() + " tasks in the list.");
            Ui.sectionize();
        } catch (IndexOutOfBoundsException e) {
            Ui.sectionize();
            System.out.println("\t☹ OOPS!!! I'm sorry, this task does not exist in your list!");
            Ui.sectionize();
        }
    }

    public void showWelcome() {
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
        Ui ui = new Ui(new TaskList());
        ui.showWelcome();
        ui.listen();
    }

    public static void initialize(TaskList taskList) {
        Ui ui = new Ui(taskList);
        ui.showWelcome();
        ui.listen();
    }

    public void showLoadingError() {
        System.out.println("File failed to load. Initializing new File...");
        initialize();
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
                        taskList.addItem(new ToDos(task));
                        this.reply();
                        break;
                    case ("deadline"):
                        // date = 'by Sunday'
                        taskAndDateArr = Parser.splitTaskAndDate(task);
                        task = taskAndDateArr[0];
                        date = taskAndDateArr[1];
                        taskList.addItem(new Deadlines(task, date));
                        this.reply();
                        break;
                    case ("event"):
                        taskAndDateArr = Parser.splitTaskAndDate(task);
                        task = taskAndDateArr[0];
                        date = taskAndDateArr[1];
                        taskList.addItem(new Events(task, date));
                        this.reply();
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
