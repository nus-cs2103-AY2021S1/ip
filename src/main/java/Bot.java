import java.util.List;
import java.util.Scanner;

public class Bot {
    private Store store;
    private Scanner scanner = new Scanner(System.in);

    private Bot (Store store) {
        this.store = store;
    }

    private void repeat() {
        Bot.sectionize();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + this.store.getList().get(this.store.size() - 1).toString());
        System.out.println("\tNow you have " + this.store.size() + " tasks in the list.");
        Bot.sectionize();
    }

    private void listItems() {
        Bot.sectionize();
        System.out.println("\tHere are the tasks in your list:");
        int counter = 1;
        for (int i = 0; i < this.store.size(); i++) {
            System.out.println("\t" + counter + "." + this.store.getItem(i).toString());
            counter++;
        }
        Bot.sectionize();
    }

    private void sayBye() {
        Bot.sectionize();
        System.out.println("\tBye. Hope to see you again soon!");
        Bot.sectionize();
    }

    private void markDone(int index) {
        this.store.setDone(index);
        Bot.sectionize();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + this.store.getItem(index).toString());
        Bot.sectionize();
    }

    private void remove(int index) {
        try {
            Task task = this.store.remove(index);
            Bot.sectionize();
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println("\t\t" + task.toString());
            System.out.println("\tNow you have " + this.store.size() + " tasks in the list.");
            Bot.sectionize();
        } catch (IndexOutOfBoundsException e) {
            Bot.sectionize();
            System.out.println("\t☹ OOPS!!! I'm sorry, this task does not exist in your list!");
            Bot.sectionize();
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
        return "\t____________________________________________________________\n" +
                "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "\t____________________________________________________________";
    }

    public static void initialize() {
        Bot bot = new Bot(new Store());
        bot.intro();
        bot.listen();
    }

    public static void initialize(List<Task> taskArr) {
        Bot bot = new Bot(new Store(taskArr));
        bot.intro();
        bot.listen();
    }

    private static void exit() {
        System.exit(0);
    }

    private void listen() {
        String input = scanner.nextLine();
        if (InputParser.isDone(input)) {
            int index = Integer.parseInt(input.substring(5, 6)) - 1;
            this.markDone(index);
        } else if(InputParser.isDelete(input)) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            this.remove(index);
        } else if (input.equals("bye")) {
            this.sayBye();
            exit();
        } else if (input.equals("list")) {
            this.listItems();
        } else {
            try {
                if (!InputParser.correctInputFormat(input)) {
                    throw new DukeException(Bot.errorMessage());
                }

                //pull type of task and the task
                String taskType = input.substring(0, input.indexOf(" "));
                String task = input.substring(input.indexOf(" ") + 1);
                //System.out.println(task);
                switch (taskType) {
                    case ("todo"):
                        store.addItem(new ToDos(task));
                        this.repeat();
                        break;
                    case ("deadline"):
                        // date = 'by Sunday'
                        store.addItem(new Deadlines(task));
                        this.repeat();
                        break;
                    case ("event"):
                        store.addItem(new Events(task));
                        this.repeat();
                        break;
                    default:
                        throw new DukeException(Bot.errorMessage());
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        this.listen();
    }
}
