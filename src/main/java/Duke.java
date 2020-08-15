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


        while (sc.hasNextLine()) {
            try {
                String next = sc.nextLine();
                if (next.equals("bye")) {
                    bot.goodByeMessage();
                    break;
                } else if (next.equals("list")) {
                    bot.displayActivities();
                } else if (next.split(" ")[0].equals("done")) {
                    try {
                        int taskNum = Integer.parseInt(next.substring(5));
                        bot.completeTask(taskNum);
                    } catch (NumberFormatException e1) {
                        throw new InvalidInputException("Please input an Integer for the task!");
                    } catch (StringIndexOutOfBoundsException e2) {
                        throw new EmptyDescriptionException("done");
                    } catch (IndexOutOfBoundsException e3) {
                    throw new InvalidInputException("Number provided is too small or too large, Please input a valid task number");
                }
                } else if(next.split(" ")[0].equals("todo")) {
                    Matcher todoMatcher = todo.matcher(next);
                    if (todoMatcher.find()) {
                        bot.addTodo(todoMatcher.group(1));
                    } else {
                        throw new EmptyDescriptionException("todo");
                    }
                } else if(next.split(" ")[0].equals("deadline")) {
                    Matcher deadlineMatcher = deadline.matcher(next);
                    if (deadlineMatcher.find()) {
                        bot.addDeadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
                    } else {
                        throw new EmptyDescriptionException("deadline");
                    }
                } else if(next.split(" ")[0].equals("event")) {
                    Matcher eventMatcher = event.matcher(next);
                    if (eventMatcher.find()) {
                        bot.addEvent(eventMatcher.group(1), eventMatcher.group(2));
                    } else {
                        throw new EmptyDescriptionException("event");
                    }
                } else {
                    throw new InvalidInputException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}