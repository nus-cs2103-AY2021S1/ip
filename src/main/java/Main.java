import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        String line = bot.getHorizontalLine();
        Task[] tasks = bot.tasks;
        int numOfTasks = bot.numOfTasks;
        System.out.println(line);
        System.out.println("Hello! I'm Moomin\n" + "What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            System.out.println(line);
            String str = sc.nextLine();
            String[] res = bot.parseStringBySpace(str);
            if (res[0].equals("bye")) {
                // exit
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                sc.close();
                return;
            } else if (res[0].equals("list")) {
                // list all tasks
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numOfTasks; i++) {
                    int taskNo = i + 1;
                    Task task = tasks[i];
                    System.out.println(taskNo + "." + task);
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
                // remove from list and decrement number of tasks?
            } else if (res[0].equals("todo")) {
                // add a todo
                Todo newTodo = new Todo(str);
                tasks[numOfTasks] = newTodo;
                numOfTasks++;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTodo);
                System.out.println("Now you have " + numOfTasks + " tasks in the list.");
                System.out.println(line);
            } else {
                // add tasks
                Task newTask;
                switch (res[0]) {
                    case "todo":
                        newTask = new Todo(res[1]);
                        break;
                    case "deadline":
                        newTask = new Deadline(res[1], res[2]);
                        break;
                    case "event":
                        newTask = new Event(res[1], res[2]);
                        break;
                    default:
                        newTask = new Task(str);
                        break;
                }
                tasks[numOfTasks] = newTask;
                numOfTasks++;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + numOfTasks + " tasks in the list.");
                System.out.println(line);
            }
        }
    }
}
