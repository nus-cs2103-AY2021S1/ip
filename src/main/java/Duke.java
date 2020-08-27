import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    // constant SPACE and LINE for format purposes
    public static String SPACE = "     ";
    public static String LINE = "____________________________________________________________\n";
    // add outer frame lines
    public static String format(String input) {
        return SPACE + LINE +
               SPACE + " " + input + "\n" +
               SPACE + LINE;
    }

    public static void main(String[] args) {
        // greeting and exit messages strings
        // list and mark strings
        final String messageHello = "Hello! I'm Duke\n" + SPACE + " " + "What can I do for you?";
        final String messageBye = "Bye. Hope to see you again soon!";
        final String messageList = "Here are the task(s) in your list:\n";
        final String messageMarked = "Nice! I've marked this task as done:\n";
        final String messageAdded = "Got it. I've added this task:\n";
        final String messageDelete = "Noted. I've removed this task:\n";

        // set up scanner
        Scanner scanner = new Scanner(System.in);

        // set up empty list of things to do
        List<Task> list = new ArrayList<>();

        // start to serve
        System.out.println(format(messageHello));

        // continue if have more commands (that are not "bye")
        // echo the command or say bye
        while (scanner.hasNextLine()) {
            String currentCommand = scanner.nextLine();
            String priorCommand = currentCommand.split(" ")[0];

            if (currentCommand.equals("bye")) {
                System.out.println(format(messageBye));
                scanner.close();
                break;
            } else if (currentCommand.equals("list")) {
                System.out.print(SPACE + LINE);
                System.out.print(SPACE + messageList);
                int counter = 1;
                for (Task task : list) {
                    System.out.println(SPACE + " " + counter + "." + task.getTypeLetter()
                            + task.getStatusIcon() + task.getMessage());
                    counter++;
                }
                System.out.println(SPACE + LINE);
            } else {
                try {
                    String extraCommand = currentCommand.split(" ", 2)[1];
                    if (priorCommand.equals("done")) {
                        int index = Integer.parseInt(extraCommand) - 1;
                        if (index < 0 || index >= list.size()) {
                            throw new InvalidDoneException();
                        }
                        Task task = list.get(index);
                        task.setDone();
                        System.out.println(format(messageMarked + SPACE + "   "
                                + task.getTypeLetter() + task.getStatusIcon() + task.getMessage()));
                    } else if (priorCommand.equals("delete")) {
                        int index = Integer.parseInt(extraCommand) - 1;
                        if (index < 0 || index >= list.size()) {
                            throw new InvalidDeletionException();
                        }
                        Task task = list.get(index);
                        list.remove(index);
                        String messageNum = "\n      Now you have " + list.size() + " task(s) in the list.";
                        System.out.println(format(messageDelete + "        " + task.getTypeLetter()
                                + task.getStatusIcon() + task.getMessage() + messageNum));

                    } else {
                        Task task;
                        switch (priorCommand) {
                            case "deadline":
                                task = new Deadline(Convert.by(extraCommand));
                                break;
                            case "event":
                                task = new Event(Convert.at(extraCommand));
                                break;
                            case "todo":
                                task = new Todo(extraCommand);
                                break;
                            default:
                                // throw exception
                                throw new IndexOutOfBoundsException();
                        }

                        list.add(task);
                        System.out.println(format(messageAdded + SPACE + "   "
                                + task.getTypeLetter() + task.getStatusIcon() + task.getMessage()
                                + "\n " + SPACE + "Now you have " + list.size() + " task(s) in the list."));
                    }
                } catch (IndexOutOfBoundsException e) {
                    // handle exception
                    IndexOutOfBoundsException ex;
                    switch (priorCommand) {
                        case "todo":
                            ex = new TodoEmptyBodyException();
                            break;
                        case "event":
                            ex = new EventEmptyBodyException();
                            break;
                        case "deadline":
                            ex = new DeadlineEmptyBodyException();
                            break;
                        case "delete":
                            ex = new DeleteEmptyBodyException();
                            break;
                        case "done":
                            ex = new DoneEmptyBodyException();
                            break;
                        default:
                            ex = new UnknownCommandException();
                            break;
                    }
                    System.out.println(format(ex.toString()));
                } catch (Exception e) {
                    System.out.println(format(e.toString()));
                }
            }
        }
    }
}