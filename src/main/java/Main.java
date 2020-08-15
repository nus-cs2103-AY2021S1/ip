import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        String line = bot.getHorizontalLine();
        Task[] tasks = bot.tasks;
        int numOfTasks = bot.numOfTasks;
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            System.out.println(line);
            String str = sc.nextLine();
            String[] res = bot.parseStringBySpace(str);
            if (str.equals("bye")) {
                // exit
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                sc.close();
                return;
            } else if (str.equals("list")) {
                // list all tasks
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numOfTasks; i++) {
                    int taskNo = i + 1;
                    Task task = tasks[i];
                    System.out.println(taskNo + "." +
                            "[" + task.getStatusIcon() + "] " +
                            task.description);
                }
                System.out.println(line);
            } else if(res[0].equals("done")) {
                // mark as done
                System.out.println("Nice! I've marked this task as done:");
                int taskNo = Integer.parseInt(res[1]);
                Task completedTask = tasks[taskNo - 1];
                completedTask.markAsDone();
                System.out.println(" " + " " +
                        "[" + completedTask.getStatusIcon() + "] " +
                        completedTask.description);
                System.out.println(line);
            } else {
                // add tasks
                Task newTask = new Task(str);
                tasks[numOfTasks] = newTask;
                numOfTasks++;
                System.out.println("added: " + str);
                System.out.println(line);
            }
        }
    }
}
