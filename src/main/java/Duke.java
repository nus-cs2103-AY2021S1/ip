import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> stringStore = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println(" ____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(" ____________________________________________________________");

        if (sc.hasNext()) {
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                if (input.equals("list")) {
                    System.out.println(" ____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    int sizeStore = stringStore.size();
                    for (int i = 1; i < sizeStore + 1; i++) {
                        System.out.println(i + "." + stringStore.get(i - 1));
                    }
                    System.out.println(" ____________________________________________________________");
                } else if (input.split(" ")[0].equals("done")) {
                    stringStore.get(Integer.parseInt(input.split(" ")[1]) - 1).markAsDone();
                } else if (input.split(" ")[0].equals("todo")) {
                    String tasker = stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
                    Todo todoTask = new Todo(tasker);
                    stringStore.add(todoTask);
                    printer(todoTask);
                } else if (input.split(" ")[0].equals("deadline")) {
                    String deadliner = stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
                    String[] deadlinerparts = deadliner.split("/by");
                    Deadline deadlineTask = new Deadline(deadlinerparts[0], deadlinerparts[1]);
                    stringStore.add(deadlineTask);
                    printer(deadlineTask);
                } else if (input.split(" ")[0].equals("event")) {
                    String eventer = stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
                    String[] eventParts = eventer.split("/at");
                    Event eventTask = new Event(eventParts[0], eventParts[1]);
                    stringStore.add(eventTask);
                    printer(eventTask);
                } else {
                    System.out.println(" ____________________________________________________________");
                    System.out.println("added: " + input);
                    Task a = new Task(input);
                    System.out.println(" ____________________________________________________________");
                    System.out.println("");
                    stringStore.add(a);
                }
                if(sc.hasNext()) {
                    input = sc.nextLine();
                }
            }
            System.out.println(" ____________________________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(" ____________________________________________________________");

        }
    }

    public static String stringBuilder(String[] arr, int start, int end){
        String store = "";
        for (int i = start; i <= end; i++) {
            if(i == end){
                store += arr[i];
            } else {
                store += arr[i] + " ";
            }

        }
        return store;
    }

    public static void printer(Task task){
        System.out.println(" ____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + String.valueOf(stringStore.size()) + " tasks in the list.");
        System.out.println(" ____________________________________________________________");

    }



}
