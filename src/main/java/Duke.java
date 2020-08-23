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
                        int idx = Integer.parseInt(input.substring(input.length() - 1));
                        tasks.getTasks().get(idx - 1).setDone(true);
                        System.out.println("Congrats, you've finished this task!");
                        System.out.println(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        throw new TaskNotFoundException("Sorry, I couldn't find that task.");
                    } catch (NumberFormatException ex) {
                        throw new InvalidTaskNumberException("Please enter a valid number!");
                    }
                } else if (input.startsWith("delete")) { // delete a task
                    try {
                        int idx = Integer.parseInt(input.substring(input.length() -1));
                        System.out.println("The task " + tasks.getTasks().get(idx - 1) + " has been removed.");
                        tasks.getTasks().remove(idx - 1);
                        printListSize();
                    } catch (IndexOutOfBoundsException e) {
                        throw new TaskNotFoundException("Sorry, I couldn't find that task.");
                    } catch (NumberFormatException ex) {
                        throw new InvalidTaskNumberException("Please enter a valid number!");
                    }
                } else {
                    String[] info = extractInfo(input);
                    if (info[0].equals("todo")) {
                        tasks.addItem(new Todo(info[1]));
                        System.out.println("Added new task: " + info[1]);
                        printListSize();
                    } else if (info[0].equals("deadline")) {
                        tasks.addItem(new Deadline(info[1], info[2]));
                        System.out.println("Added new task: " + info[1]);
                        printListSize();
                    } else {
                        tasks.addItem(new Event(info[1], info[2]));
                        System.out.println("Added new task: " + info[1]);
                        printListSize();
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // method to handle the adding of items
    public String[] extractInfo(String str) throws DukeException {
        String[] store = new String[3];
        // Todo, Deadline and Event
        if (str.startsWith("todo")) {
            if (str.equals("todo") || str.strip().equals("todo"))  {
                throw new InvalidTaskException("Your task cannot be empty!");
            } else if (!str.startsWith("todo ")) {
                throw new UnknownCmdException("Unknown command entered!");
            } else {
                store[0] = "todo";
            }
        }
        else if (str.startsWith("deadline")) {
            if (str.equals("deadline") || str.strip().equals("deadline")) {
                throw new InvalidTaskException("Your task cannot be empty!");
            } else if (!str.startsWith("deadline ")) {
                throw new UnknownCmdException("Unknown command entered!");
            } else {
                store[0] = "deadline";
            }
        }
        else if (str.startsWith("event")) {
            if (str.equals("event") || str.strip().equals("event")) {
                throw new InvalidTaskException("Your task cannot be empty!");
            } else if (!str.startsWith("event ")) {
                throw new UnknownCmdException("Unknown command entered!");
            } else {
                store[0] = "event";
            }
        }
        else {
            throw new UnknownCmdException("Unknown command entered!");
        }

        int splitPrefix = str.indexOf(" ");
        String content = str.substring(splitPrefix).strip();
        if (content.length() <= 0) {
            throw new InvalidTaskException("Your task cannot be empty!");
        }
        if (store[0].equals("todo")) {
            store[1] = content;
            store[2] = "";
        } else {
            int splitTime = store[0].equals("deadline") ? content.indexOf("/by") : content.indexOf("/at");
            if (splitTime < 0) {
                throw new InvalidTimeException("Please indicate the date or time by using /by (for deadlines) or /at (for events)!");
            }
            String name = content.substring(0, splitTime).strip(); // stripping to remove whitespaces
            String time = content.substring(splitTime + 3).strip();
            if (name.length() <= 0) {
                throw new InvalidTaskException("Your task cannot be empty!");
            }
            if (time.length() <= 0) {
                throw new InvalidTimeException("Please specify a date or time for this task!");
            }
            store[1] = name;
            store[2] = time;
        }
        return store;
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
