import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> todoList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "---------------------------------------------------";
        String welcome = line + "\nHello! I'm Duke!\n" +
                "What can I do for you?\n" + line ;
        //System.out.println(logo + "\n" + welcome);
        System.out.println(welcome);
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        while (!word.equals("bye")) {
            if (word.equals("list")) {
                for (Task task : todoList) {
                    System.out.println(task.getStatusWithIndex());
                }
            } else if (word.length() > 4 && word.substring(0,5).equals("todo ")) {
                String todo = word.substring(5);
                Todo toDo = storeTodo(todo);
                System.out.println(toDo == null ? "Failed!" : "\nGot it. I've have added this task:\n   " + toDo.toString()
                        + "\nNow you have " + todoList.size() + " tasks in the list.\n" + line);
            } else if (word.length() > 9 && word.substring(0,9).equals("deadline ")) {
                String todo = word.substring(9);
                Deadline toDo = storeDeadline(todo);
                System.out.println(toDo == null ? "Failed!" : "\nGot it. I've have added this task:\n   " + toDo.toString()
                        + "\nNow you have " + todoList.size() + " tasks in the list.\n" + line);
            } else if (word.length() > 5 && word.substring(0,6).equals("event ")) {
                String todo = word.substring(6);
                Deadline toDo = storeDeadline(todo);
                System.out.println(toDo == null ? "Failed!" : "\nGot it. I've have added this task:\n   " + toDo.toString()
                        + "\nNow you have " + todoList.size() + " tasks in the list.\n" + line);
            } else if (word.length() > 4 && word.substring(0,5).equals("done ")) {
                int taskNo = Character.getNumericValue(word.charAt(5)) - 1;
                Task task = todoList.get(taskNo);
                task.isDone = true;
                System.out.println("Nice! I have marked this task as done: \n  " + task.toString());
            } else {
                System.out.println("added: " + word);
                storeTask(word);
            }
            word = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void storeTask(String item) {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
        } else {
            todoList.add(new Task(item, count));
        }
    }
    public static Todo storeTodo(String todo) {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else {
            Todo curr = new Todo(todo, count);
            todoList.add(curr);
            return curr;
        }
    }
    public static Deadline storeDeadline(String deadline) {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else {
            Deadline curr = new Deadline(deadline, count);
            todoList.add(curr);
            return curr;
        }
    }
    public static Event storeEvent(String event) {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else {
            Event curr = new Event(event, count);
            todoList.add(curr);
            return curr;
        }
    }
}
