import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    static char line = '*';
    static List<Task> tasks = new ArrayList<>();

    static void loopMethod() {
        //gets input and displays it
        Scanner sc = new Scanner(System.in);
        String word = "";
        while (sc.hasNext()) {
            word = sc.nextLine();

            if (word.equals("bye")) {
                printTopLine();
                System.out.println("Bye. Hope to see you again soon!");
                printBottomLine();
                return;
            } else if (word.equals("list")) {
                printTopLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                printBottomLine();
            } else if (word.startsWith("done")) {
                try {
                    int index = Character.getNumericValue(word.charAt(5));
                    Task originalTask = tasks.get(index);
                    Task nextTask = originalTask.setTaskAsCompleted();
                    tasks.set(index, nextTask);
                    printTopLine();
                    System.out.println("Nice! I've marked this task as done: \n" + nextTask);
                    printBottomLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new InvalidArgumentException("Sorry, the task cannot be found!"));
                    continue;
                }
            } else if (word.startsWith("todo")) {
                try {
                    int index = word.indexOf(" ");
                    if (index == -1) {
                        System.out.println(new EmptyArgumentException("No task name given"));
                        continue;
                    }
                    word = word.substring(index + 1);
                    Task toDo = new Todo(word);
                    tasks.add(toDo);
                    printTopLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(toDo);
                    String numberTasks = String.format("Now you have %s %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
                    System.out.println(numberTasks);
                    printBottomLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new EmptyArgumentException("No task name given"));
                    continue;
                }
            } else if (word.startsWith("deadline") || word.startsWith("event")) {
                int index = word.indexOf(' ');
                boolean isDeadline = word.startsWith("deadline");
                if (index == -1 || index == word.length() - 1) {
                    System.out.println(new EmptyArgumentException("No task name given"));
                    continue;
                }
                //get stuff after the space if there are still characters after the space
                String content = word.substring(word.indexOf(' ') + 1);
                index = content.indexOf('/');
                if (index == -1 || index == content.length() - 1) {
                    //nothing after the slash
                    System.out.println( new InvalidArgumentException("No deadline given!"));
                    continue;
                }
                //if there are words after the slash
                String taskName = content.substring(0, index);
                String keyword = content.substring(index + 1, index + 3);
                boolean matches = isDeadline && keyword.equals("by") || !isDeadline && keyword.equals("at");
                if (!matches) {
                    System.out.println(new EmptyArgumentException("Task name does not start with proper arguments"));
                    continue;
                }
                try {
                    String time = content.substring(index + 4);
                    LocalDate dateTime = LocalDate.parse(time);
                    Task newTask = isDeadline ? new Deadline(taskName, dateTime) : new Event(taskName, dateTime);
                    tasks.add(newTask);
                    printTopLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    String numberTasks = String.format("Now you have %s %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
                    System.out.println(numberTasks);
                    printBottomLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new EmptyArgumentException("No task name given"));
                    continue;
                }
                catch (DateTimeException e) {
                    System.out.println(new InvalidArgumentException("Time given must be in the format yyyy-mm-dd"));
                    continue;
                }
            } else if (word.startsWith("delete")) {
                int index;
                try {
                    index = Integer.parseInt(word.substring(7));
                } catch (IndexOutOfBoundsException e) {
                    //no proper number given
                    System.out.println(new EmptyArgumentException("Sorry! Duke could not find the task number"));
                    continue;
                }
                try {
                    Task toBeDeleted = tasks.get(index);
                    tasks.remove(index);
                    printTopLine();
                    System.out.println("Noted. I've removed this task:\n" + toBeDeleted);
                    String numberTasks = String.format("Now you have %s %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
                    System.out.println(numberTasks);
                    printBottomLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new InvalidArgumentException("Sorry! Duke could not find a task at the specified index"));
                    continue;
                }
            } else {
               System.out.println(new InvalidArgumentException());
                continue;
            }
        }
    }

    //Prints the top line, e.g. *******************
    static void printTopLine() {
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    //Prints the bottom line e.g. ****************
    static void printBottomLine() {
        System.out.println();
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    //Restarts program after an exception is thrown
    //Asks for input again
    static void restartAfterException() {
        try {
            loopMethod();
        } catch (Exception e) {
            System.out.println(e);
            restartAfterException();
        }
    }

    public static void main(String[] args) {
        printTopLine();
        System.out.println("Hello! I'm Duke \n What can I do for you?");
        printBottomLine();
        loopMethod();
    }
}
