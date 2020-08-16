import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    static char line  = (char) 	0x2501 ;
    static List<Task> tasks = new ArrayList<>();
    static void loopMethod() throws EmptyArgumentException, InvalidArgumentException {

        //gets input and displays it
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        if (word.equals("bye")) {
            printTopLine();
            System.out.println("Bye. Hope to see you again soon!");
            printBottomLine();
            return;
        }
        else if (word.equals("list")) {
            printTopLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            printBottomLine();
        }
        else if (word.startsWith("done")) {
            try {
                int index = Character.getNumericValue(word.charAt(5));
                Task originalTask = tasks.get(index);
                Task nextTask = originalTask.setTaskAsCompleted();
                tasks.set(index, nextTask);
                printTopLine();
                System.out.println("Nice! I've marked this task as done: \n" + nextTask);
                printBottomLine();
            }
            catch (IndexOutOfBoundsException e) {
               throw new InvalidArgumentException("Sorry, the task cannot be found!");
            }
        }
        else if (word.startsWith("todo")) {
            try {
                int index = word.indexOf(" ");
                if (index == -1) {
                    throw new EmptyArgumentException("No task name given");
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
             throw new EmptyArgumentException("No task name given", e);
            }
        }

    }

    static void printTopLine() {
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    static void printBottomLine() {
        System.out.println();
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    public static void main(String[] args) {
        printTopLine();
        System.out.println("Hello! I'm Duke \n What can I do for you?");
        printBottomLine();
        try {
            loopMethod();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
