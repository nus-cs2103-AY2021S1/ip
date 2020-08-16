import java.util.Scanner;

public class Duke {

    private static final int DONE_INDEX = 5;
    private static final int TODO_INDEX = 5;
    private static final int DEADLINE_INDEX = 9;
    private static final int EVENT_INDEX = 6;
    private static final int DATE_INDEX = 4;

    private static void botStart() {
        Scanner sc = new Scanner(System.in);
        Task[] taskStorage = new Task[100];
        int storageCount = 0;
        System.out.println("=========================================="
                + "\nHi, my name is Duke."
                + "\nWhat can I do for you today?"
                + "\n==========================================");
        while (true) {
            try {
                String command = sc.nextLine().trim();
                String[] commandWordArray = command.split(" ");
                if (command.equals("bye")) {
                    System.out.println("Thanks for chatting with me, see you soon!"
                            + "\n==========================================");
                    break;
                } else if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < storageCount; i++) {
                        System.out.println(i + 1 + "." + taskStorage[i]);
                    }
                } else if (commandWordArray[0].equals("done") && !command.equals("done")) {
                    try {
                        int taskNumber = Integer.parseInt(command.substring(DONE_INDEX));
                        taskStorage[taskNumber - 1].markAsDone();
                        System.out.println("I have marked this task as done: "
                                + "\n" + taskStorage[taskNumber - 1]);
                    } catch (NullPointerException e) {
                        throw new DukeException("That task does not exist in the list!");
                    }
                } else {
                    Task task;
                    if (commandWordArray[0].equals("todo")) {
                        if (command.substring(TODO_INDEX - 1).isBlank()) {
                            throw new DukeException("Description of todo cannot be empty!");
                        }
                        task = new Todo(command.substring(TODO_INDEX));
                    } else {
                        if (commandWordArray[0].equals("deadline")) {
                            if (command.substring(DEADLINE_INDEX - 1).isBlank()) {
                                throw new DukeException("Description of deadline cannot be empty!");
                            } else {
                                try {
                                    String date = command.substring(command.indexOf("/") + DATE_INDEX);
                                    task = new Deadline(command.substring(DEADLINE_INDEX, command.indexOf("/") - 1), date);
                                } catch (StringIndexOutOfBoundsException e) {
                                    throw new DukeException("Please enter a valid date!");
                                }
                            }
                        } else if (commandWordArray[0].equals("event")) {
                            if (command.substring(EVENT_INDEX - 1).isBlank()) {
                                throw new DukeException("Description of event cannot be empty!");
                            } else {
                                try {
                                    String date = command.substring(command.indexOf("/") + DATE_INDEX);
                                    task = new Event(command.substring(EVENT_INDEX, command.indexOf("/") - 1), date);
                                } catch (StringIndexOutOfBoundsException e) {
                                    throw new DukeException("Please enter a valid date!");
                                }
                            }
                        } else {
                            throw new DukeException("Invalid command!");
                        }
                    }
                    taskStorage[storageCount] = task;
                    System.out.println(">" + "added: " + task + "<");
                    storageCount++;
                    System.out.println("You now have " + storageCount + " task(s) in your list.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke.botStart();
    }
}
