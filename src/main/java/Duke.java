import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    static char line  = (char) 	0x2501 ;
    static List<Task> tasks = new ArrayList<>();
    static void loopMethod() {

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
                return;
            }
        }
        else if (word.startsWith("todo")) {
            Task toDo = new Todo(word);
            tasks.add(toDo);
            printTopLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(toDo);
            String numberTasks = String.format("Now you have %s %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
            System.out.println(numberTasks);
            printBottomLine();
        }
        else if (word.startsWith("deadline") || word.startsWith("event")) {
            int index = word.indexOf(' ');
            boolean isDeadline = word.startsWith("deadline");
            if (index == -1 || index == word.length() - 1) {
                //nothing after the space
                return;
            }
            //get stuff after the space if there are still characters after the space
            String content = word.substring(word.indexOf(' ') + 1);
            index = content.indexOf('/');
            if (index == -1 || index == content.length() - 1) {
                //nothing after the slash
                return;
            }
            //if there are words after the slash
            String taskName = content.substring(0,index);
            String keyword = content.substring(index + 1, index + 3);
            boolean matches = isDeadline && keyword.equals("by") || !isDeadline && keyword.equals("at");
            if (!matches) {
                //no "by" or "at"
                return;
            }
            try {
                String time = content.substring(index + 4);
                Task newTask = isDeadline ? new Deadline(taskName, time) : new Event(taskName, time);
                tasks.add(newTask);
                printTopLine();
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                String numberTasks = String.format("Now you have %s %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
                System.out.println(numberTasks);
                printBottomLine();
            } catch (IndexOutOfBoundsException e) {
                return;
            }
        }

        else {
            Task newTask = new Task(word);
            tasks.add(newTask);
            printTopLine();
            System.out.println("added: " + word);
            printBottomLine();
        }
        loopMethod();
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
        loopMethod();
    }
}
