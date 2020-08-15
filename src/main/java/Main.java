import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        String line = bot.getHorizontalLine();
        String[] tasks = bot.tasks;
        int numOfTasks = bot.numOfTasks;
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            System.out.println(line);
            String str = sc.nextLine();
            if (str.equals("bye")) {
                // exit
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                sc.close();
                return;
            } else if (str.equals("list")) {
                // list all tasks
                for (int i = 0; i < numOfTasks; i++) {
                    int taskNo = i + 1;
                    System.out.println(taskNo + ". " + tasks[i]);
                }
                System.out.println(line);
            } else {
                // add tasks
                tasks[numOfTasks] = str;
                numOfTasks++;
                System.out.println("added: " + str);
                System.out.println(line);
            }
        }
    }
}
