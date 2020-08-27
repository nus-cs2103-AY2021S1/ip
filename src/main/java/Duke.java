import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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

    public static void main(String[] args) throws Exception{
        // greeting and exit messages strings
        // list and mark strings
        final String messageHello = "Hello! I'm Duke\n" + SPACE + " " + "What can I do for you?";
        final String messageBye = "Bye. Hope to see you again soon!";
        final String messageList = "Here are the task(s) in your list:\n";
        final String messageMarked = "Nice! I've marked this task as done:\n";
        final String messageAdded = "Got it. I've added this task:\n";
        final String messageDelete = "Noted. I've removed this task:\n";
        final String home = System.getProperty("user.home");

        java.nio.file.Path path = java.nio.file.Paths.get(home, "ip","start.txt");
        boolean directoryExists = java.nio.file.Files.exists(path);
        File file = new File(path.toString());

        // set up scanner
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInitial = new Scanner(file);

        // set up empty list of things to do
        List<Task> list = new ArrayList<>();

        // add tasks to init list
        while (scannerInitial.hasNextLine()) {
            String task = scannerInitial.nextLine();
            list.add(Convert.add(task));
        }

        System.out.println(format(messageHello));
        System.out.print(SPACE + LINE);
        System.out.print(SPACE + messageList);
        int i = 1;
        for (Task task : list) {
            System.out.println(SPACE + " " + i + "." + task.getTypeLetter()
                    + task.getStatusIcon() + task.getPrintMessage());
            i++;
        }
        System.out.println(SPACE + LINE);

        if (!directoryExists) {
            System.out.println(format("☹ OOPS!!! Cannot find start file"));
            throw new FileNotFoundException();
        }


        // continue if have more commands (that are not "bye")
        while (scanner.hasNextLine()) {
            String currentCommand = scanner.nextLine();
            String priorCommand = currentCommand.split(" ")[0];

            if (currentCommand.equals("bye")) {
                System.out.println(format(messageBye));
                scanner.close();
                FileWriter fileWriter = new FileWriter("start.txt");
                String content;
                for (i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    content = task.getPureTypeLetter() + " ; " + task.getStatusNum()
                            + " ; " + task.getStoreMessage() + "\n";
                    fileWriter.write(content);
                }
                fileWriter.close();
                break;
            } else if (currentCommand.equals("list")) {
                System.out.print(SPACE + LINE);
                System.out.print(SPACE + messageList);
                int counter = 1;
                for (Task task : list) {
                    System.out.println(SPACE + " " + counter + "." + task.getTypeLetter()
                            + task.getStatusIcon() + task.getPrintMessage());
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
                                + task.getTypeLetter() + task.getStatusIcon() + task.getPrintMessage()));
                    } else if (priorCommand.equals("delete")) {
                        int index = Integer.parseInt(extraCommand) - 1;
                        if (index < 0 || index >= list.size()) {
                            throw new InvalidDeletionException();
                        }
                        Task task = list.get(index);
                        list.remove(index);
                        String messageNum = "\n      Now you have " + list.size() + " task(s) in the list.";
                        System.out.println(format(messageDelete + "        " + task.getTypeLetter()
                                + task.getStatusIcon() + task.getPrintMessage() + messageNum));

                    } else {
                        Task task;
                        switch (priorCommand) {
                        case "deadline":
                            task = new Deadline(extraCommand);
                            break;
                        case "event":
                            task = new Event(extraCommand);
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
                                + task.getTypeLetter() + task.getStatusIcon() + task.getPrintMessage()
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
                } catch (java.time.format.DateTimeParseException e) {
                    System.out.println(format(new InvalidDateException().toString()));
                } catch (Exception e) {
                    System.out.println(format(e.toString()));
                }
            }
        }
    }
}