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
                Task currTask = new Task(line);
                this.add(currTask);
                System.out.println("added: " + line);
            }
            line = this.scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
