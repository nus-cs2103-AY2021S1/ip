import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> todoList = new ArrayList<>();
    public static String line = "---------------------------------------------------";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
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
            } else if (word.length() >=4 && word.substring(0,4).equals("todo")) {
                try {
                    //String todo = word.substring(5);
                    Todo toDo = storeTodo(word);
                    System.out.println(toDo == null ? "Failed!" : "\nGot it. I've have added this task:\n   " + toDo.toString()
                            + "\nNow you have " + todoList.size() + " tasks in the list.\n" + line);
                } catch (EmptyDukeException e) {
                    System.out.println(e.toString());
                }
            } else if (word.length() >=8 && word.substring(0,8).equals("deadline")) {
                //String todo = word.substring(9);
                try {
                    Deadline toDo = storeDeadline(word);
                    System.out.println(toDo == null ? "Failed!" : "\nGot it. I've have added this task:\n   " + toDo.toString()
                            + "\nNow you have " + todoList.size() + " tasks in the list.\n" + line);
                } catch (EmptyDukeException e) {
                    System.out.println(e.toString());
                }
            } else if (word.length() >=5 && word.substring(0,5).equals("event")) {
                //String todo = word.substring(6);
                try {
                    Event toDo = storeEvent(word);
                    System.out.println(toDo == null ? "Failed!" : "\nGot it. I've have added this task:\n   " + toDo.toString()
                            + "\nNow you have " + todoList.size() + " tasks in the list.\n" + line);
                } catch (EmptyDukeException e) {
                    System.out.println(e.toString());
                }
            } else if (word.length() > 4 && word.substring(0,5).equals("done ")) {
                int taskNo = Character.getNumericValue(word.charAt(5)) - 1;
                Task task = todoList.get(taskNo);
                task.isDone = true;
                System.out.println(line + "\nNice! I have marked this task as done: \n  " + task.toString());
            } else {
                if (word.length() > 6 && word.substring(0,7).equals("delete ")) {
                    int taskNo = Character.getNumericValue(word.charAt(7)) - 1;
                    delete(taskNo);
                } else {
                    try {
                        storeTask(word);
                        System.out.println(line + "\nadded: " + word);
                    } catch (BlahException e){
                        System.out.println(e.toString());
                    }
                }
            }
            word = scan.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    public static void storeTask(String item) throws BlahException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
        } else if (item.equals("blah")) {
            throw new BlahException();
        } else {
            todoList.add(new Task(item, count));
        }
    }
    public static Todo storeTodo(String todo) throws EmptyDukeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (todo.length() <= 4) {
            throw new EmptyDukeException("The description of your todo is empty.");
        } else {
            Todo curr = new Todo(todo.substring(5), count);
            todoList.add(curr);
            return curr;
        }
    }
    public static Deadline storeDeadline(String deadline) throws EmptyDukeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (deadline.length() <= 8) {
            throw new EmptyDukeException("The description of your deadline is empty.");
        } else {
            Deadline curr = new Deadline(deadline.substring(9), count);
            todoList.add(curr);
            return curr;
        }
    }
    public static Event storeEvent(String event) throws EmptyDukeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (event.length() <= 5) {
            throw new EmptyDukeException("The description of your event is empty.");
        } else {
            Event curr = new Event(event.substring(6), count);
            todoList.add(curr);
            return curr;
        }
    }

    public static void delete(int n) {
        Task task = todoList.remove(n);
        for (int i = n; i < todoList.size(); i++) {
            todoList.get(i).index = todoList.get(i).index - 1;
        }
        System.out.println(line + "\nNoted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + todoList.size() + " tasks in the list.\n" + line);
    }
}
