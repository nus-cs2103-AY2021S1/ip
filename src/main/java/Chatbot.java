import java.util.Scanner;

public class Chatbot {
    private Task[] tasksList;
    private int totalTasks;
    private Scanner scanner;

    public Chatbot(Scanner scanner) {
        this.tasksList = new Task[100];
        this.totalTasks = 0;
        this.scanner = scanner;
    }

    private void add(Task task) {
        tasksList[totalTasks] = task;
        totalTasks++;
    }

    private void listTasks() {
        for (int i = 1; tasksList[i - 1] != null; i++) {
            System.out.println(i + "." + tasksList[i - 1]);
        }
    }

    public void start() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        String line = this.scanner.nextLine();
        while(!line.equals("bye")) {
            if (line.contains("done")) {
                Scanner s2 = new Scanner(line);
                s2.skip("done");
                int taskNumber = s2.nextInt();
                tasksList[taskNumber - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        tasksList[taskNumber - 1]);
            } else if (line.equals("list")) {
                this.listTasks();
            } else {
                Task currTask;
                if (line.contains("todo")) {
                    Scanner s2 = new Scanner(line);
                    s2.skip("todo");
                    currTask = new Todo(s2.nextLine());
                } else if (line.contains("deadline")) {
                    Scanner s2 = new Scanner(line);
                    s2.skip("deadline");
                    s2.useDelimiter("/by");
                    currTask = new Deadline(s2.next(), s2.next());
                } else {
                    Scanner s2 = new Scanner(line);
                    s2.skip("event");
                    s2.useDelimiter("/at");
                    currTask = new Event(s2.next(), s2.next());
                }
                this.add(currTask);
                System.out.println("Got it. I've added this task:\n" +
                        currTask +
                        "\nNow you have " + this.totalTasks + " tasks in the list.");
            }
            line = this.scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
