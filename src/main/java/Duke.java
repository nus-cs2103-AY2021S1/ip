import java.util.Scanner;

public class Duke {

    private Task[] tasks = new Task[100];
    private int currentIndex = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println(wrapWithLines(greet()));

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(wrapWithLines(exit()));
                System.exit(0);
            } else if (input.equals("list")) {
                System.out.println(wrapWithLines(list()));
            } else if (input.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                System.out.println(wrapWithLines(done(index)));
            } else {
                System.out.println(wrapWithLines(add(input)));
            }
        }
    }

    private String greet() {
        return "\t Hello! I'm Duke\n"
                + "\t What can I do for you?\n";
    }

    private String echo(String str) {
        return "\t " + str + '\n';
    }

    private String exit() {
        return "\t Bye. Hope to see you again soon!\n";
    }

    private String line() {
        StringBuilder str = new StringBuilder("\t");
        for (int i = 0; i < 50; i++) {
            str.append("-");
        }
        return str.toString();
    }

    private String wrapWithLines(String str) {
        return line() + "\n" + str + "\n" + line();
    }

    private String add(String str) {
        tasks[currentIndex] = new Task(str);
        currentIndex++;
        return "\t added: " + str + "\n";
    }

    private String list() {
        StringBuilder str = new StringBuilder("\t Here are the tasks in your list:\n");
        for (int i = 0; i < currentIndex; i++) {
            str.append("\t ").append(i + 1).append(".")
                    .append(tasks[i].toString()).append("\n");
        }
        return str.toString();
    }

    private String done(int input) {
        int index = input - 1;
        Task task = tasks[index];
        task.markAsDone();
        return "\t Nice! I've marked this task as done:\n\t\t"
                + task.toString();
    }

}
