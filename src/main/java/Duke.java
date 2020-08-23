import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Pattern done = Pattern.compile("done ([0-9]+)");
        Pattern delete = Pattern.compile("delete ([0-9]+)");


        while (sc.hasNextLine()) {
            try {
                String next = sc.nextLine();
                if (next.equals("bye")) {
                    bot.goodByeMessage();
                    break;
                } else if (next.equals("list")) {
                    bot.displayActivities();
                } else if (next.split(" ")[0].equals("done")) {
                    Matcher doneMatcher = done.matcher(next);
                    if (doneMatcher.find()) {
                        int taskNum = Integer.parseInt(doneMatcher.group(1));
                        if (taskNum != 0 && taskNum <= bot.activityList.size()) {
                            bot.completeTask(taskNum);
                        }
                        else {
                            throw new InvalidInputException("Number provided is too small or too large, Please provide a valid task number");
                        }
                    } else {
                        throw new EmptyDescriptionException("done");
                    }
                } else if (next.split(" ")[0].equals("delete")) {
                    Matcher deleteMatcher = delete.matcher(next);
                    if (deleteMatcher.find()) {
                        int taskNum = Integer.parseInt(deleteMatcher.group(1));
                        if (taskNum != 0 && taskNum <= bot.activityList.size()) {
                            bot.deleteTask(taskNum);
                        }
                        else {
                            throw new InvalidInputException("Number provided is too small or too large, Please provide a valid task number");
                        }
                    } else {
                        throw new EmptyDescriptionException("done");
                    }
                }
                else if(next.split(" ")[0].equals("todo")) {
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