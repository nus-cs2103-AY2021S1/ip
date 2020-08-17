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
            try {
                if (input.equals("bye")) { // quit
                    quit = true;
                    System.out.println("Goodbye!");
                } else if (input.equals("list")) { // list
                    System.out.println(tasks);
                } else if (input.startsWith("done")) { // mark something as done
                    try {
                        int idx = Integer.parseInt(input.substring(input.length() - 1)); // input number
                        tasks.getTasks().get(idx - 1).setDone(true);
                        System.out.println("Congrats, you've finished this task!");
                        System.out.println(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        throw new TaskNotFoundException("Sorry, I couldn't find that task.");
                    }
                } else if (input.startsWith("todo")) { // add a todo
                    if (input.strip().equals("todo")) {
                        throw new BadTaskException("Your task cannot be empty!");
                    } else {
                        int splitPrefix = input.indexOf(" ") + 1; // index removing prefix, +1 for whitespace
                        String content = input.substring(splitPrefix);
                        tasks.addItem(new Todo(content));
                        System.out.println("Added new task: " + content);
                        printListSize();
                    }
                } else if (input.startsWith("deadline") || input.startsWith("event")) {
                    if (input.strip().equals("deadline") || input.strip().equals("event")) {
                        throw new BadTaskException("Your task cannot be empty!");
                    } else {
                        int splitPrefix = input.indexOf(" ") + 1;
                        int splitTime = input.indexOf("/"); // index splitting the string into name and time
                        if (splitTime < 0 || input.length() - splitTime < 4) {
                            throw new InvalidTimeException("Please specify a date or time for this task!");
                        } else {
                            String time = input.substring(splitTime + 3); // +3 to remove the /by or /at
                            String name = input.substring(splitPrefix, splitTime - 1); // -1 to remove whitespace
                            if (input.startsWith("deadline")) {
                                tasks.addItem(new Deadline(name, time));
                            } else {
                                tasks.addItem(new Event(name, time));
                            }
                            System.out.println("Added new task: " + name);
                            printListSize();
                        }
                    }
                } else {
                    throw new UnknownCmdException("Unknown command entered!");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printListSize() {
        System.out.println("You now have " + tasks.size() + (tasks.size() == 1 ? " task in your list." : " tasks in your list."));
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
