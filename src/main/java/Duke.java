import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    class Task {
        private final String task;
        private final boolean isDone;

        private Task(String task, boolean isDone) {
            this.task = task;
            this.isDone = isDone;
        }

        public Task(String task){
            this.task = task;
            this.isDone = false;
        }

        public Task markDone() {
            return new Task(this.task, true);
        }

        @Override
        public String toString() {
            String symbol = isDone ? "[✓] " : "[✗] ";
            return symbol + task;
        }
    }

    private static String line = "____________________________________________________________";

    private String name = "Bolot";
    private String end = "bye";
    private ArrayList<Task> list = new ArrayList<>();

    public void greet() {
        System.out.println(line);
        System.out.println(String.format("Hello! I'm %s, your personal chat-bot companion.", name));
        System.out.println("How may I help you?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye! Thank you for chatting with me!");
        System.out.println("Hope to see you again soon!");
        System.out.println(line);
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);

        String type = sc.nextLine();

        while (!type.equalsIgnoreCase(end)) {
            System.out.println(line);
            System.out.println(type);
            System.out.println(line);

            type = sc.nextLine();
        }

        bye();
    }

    public void addList() {
        Scanner sc = new Scanner(System.in);

        String type = sc.nextLine();

        while (!type.equalsIgnoreCase(end)) {

            System.out.println(line);

            if (type.equalsIgnoreCase("list")) {
                int i = 1;
                for (Task todo: list) {
                    System.out.println(String.format("%d. %s", i, todo.toString()));
                    i++;
                }
            } else if (type.toLowerCase().contains("done")) {
                int taskNo = Integer.parseInt(type.substring(5)) - 1;
                list.set(taskNo, list.get(taskNo).markDone());

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(taskNo));
            } else {
                list.add(new Task(type));
                System.out.println(String.format("added: %s", type));
            }

            System.out.println(line);

            type = sc.nextLine();
        }

        bye();
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//
//        System.out.println("Hello from\n" + logo);

        Duke bot = new Duke();
        bot.greet();
//        bot.echo();
        bot.addList();
    }

}
