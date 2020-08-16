import java.util.Scanner;

public class Duke {
    Scanner sc;
    TaskList tasks;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new TaskList();
    }

    void takeInputs() {
        boolean quit = false;
        while (!quit) {
            String input = sc.nextLine();
            if (input.equals("bye")) { // quit
                quit = true;
                System.out.println("Goodbye!");
            } else if (input.equals("list")) { // list
                System.out.println(tasks);
            } else if (input.startsWith("done")) { // mark something as done
                int idx = Integer.parseInt(input.substring(input.length() - 1)); // input number
                tasks.getTasks().get(idx - 1).setDone(true);
                System.out.println("Congrats, you've finished this task!");
                System.out.println(tasks);
            } else { // add items
                int splitPrefix = input.indexOf(" "); // index to remove the prefix
                String content = input.substring(splitPrefix);
                if (input.startsWith("todo")) { // add a todo
                    tasks.addItem(new Todo(content));
                    System.out.println("Added new task: \n" + content);
                } else {
                    int splitTime = input.indexOf("/"); // index to split the string into name and time
                    String time = input.substring(splitTime + 4);
                    String name = input.substring(splitPrefix, splitTime);
                    if (input.startsWith("deadline")) { // add a deadline
                        tasks.addItem(new Deadline(name, time));
                        System.out.println("Added new task: \n" + name);
                    } else if (input.startsWith("event")) { // add a event
                        tasks.addItem(new Event(name, time));
                        System.out.println("Added new task: \n" + name);
                    }
                }
                System.out.println("You now have " + tasks.size() + (tasks.size() == 1 ? " task in your list" : " tasks in your list."));
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        duke.takeInputs();
    }
}
