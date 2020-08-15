import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String SPACES = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        Bot bot = new Bot(SPACES);
        bot.welcomeMessage();

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
               bot.goodByeMessage();
                break;
            } else if (next.equals("list")) {
                bot.displayActivities();
            } else if (next.length() > 5 && next.substring(0,4).equals("done")) {
                try {
                    int taskNum = Integer.parseInt(next.substring(5));
                    bot.completeTask(taskNum);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                bot.addActivity(next);
            }
        }
    }
}