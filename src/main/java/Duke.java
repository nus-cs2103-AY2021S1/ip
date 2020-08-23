import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        String line = bot.getHorizontalLine();
        bot.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            System.out.println(line);
            String str = sc.nextLine();
            try {
                String[] res = bot.parseStringBySpace(str);
                if (res[0].equals("bye")) {
                    // exit
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    sc.close();
                    return;
                } else if (res[0].equals("list")) {
                    // list all tasks
                    bot.listTasks();
                    System.out.println(line);
                } else if(res[0].equals("done")) {
                    // mark as done
                    String numStr = res[1].replaceAll(" ", "");
                    int taskNo = Integer.parseInt(numStr);
                    bot.doneTasks(taskNo);
                    System.out.println(line);
                } else if (res[0].equals("delete")) {
                    // delete
                    String numStr = res[1].replaceAll(" ", "");
                    int taskNo = Integer.parseInt(numStr);
                    bot.deleteTask(taskNo);
                    System.out.println(line);
                } else {
                    // add tasks
                    String taskType = res[0];
                    String taskDescription = res[1];
                    if (res.length == 2) {
                        bot.addTask(taskType, taskDescription);
                    } else {
                        String time = res[2];
                        bot.addTask(taskType, taskDescription, time);
                    }
                    System.out.println(line);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
    }
}
