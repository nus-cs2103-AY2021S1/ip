import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern todo = Pattern.compile("todo (.+)");
        Pattern deadline = Pattern.compile("deadline (.+?) /by (.+)");
        Pattern event = Pattern.compile("event (.+?) /at (.+)");

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
                Matcher todoMatcher = todo.matcher(next);
                Matcher deadlineMatcher = deadline.matcher(next);
                Matcher eventMatcher = event.matcher(next);
                if (todoMatcher.find()) {
                    bot.addTodo(todoMatcher.group(1));
                }
                else if (deadlineMatcher.find()) {
                    bot.addDeadline(deadlineMatcher.group(1),deadlineMatcher.group(2));
                } else if (eventMatcher.find()) {
                    bot.addEvent(eventMatcher.group(1),eventMatcher.group(2));
                } else {
                    System.out.println("Invalid Msg");
                }


            }
        }
    }
}