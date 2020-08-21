import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Duke {
    private static final String horizontalLine = "\t=================================================================================";
    private static int n = -1;
    private static int m = -1;

    private static String output(String message) {
        return horizontalLine + "\n\t  " + message + "\n" + horizontalLine + "\n";
    }

    private static boolean isDoneCommand(String cmd, int count) throws InvalidCommandException {
        if (cmd.startsWith("done ")) {
            if (cmd.length() < 6) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task to mark as done cannot be empty.");
            }
            try {
                n = Integer.parseInt(cmd.substring(5));
                if (n < 1) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a positive integer.");
                } else if (n > count) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index does not exist.");
                }
                return true;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a number.");
            }
        } else {
            return false;
        }
    }

    private static boolean isDeleteCommand(String cmd, int count) throws InvalidCommandException {
        if (cmd.startsWith("delete ")) {
            if (cmd.length() < 8) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task to mark to delete cannot be empty.");
            }
            try {
                m = Integer.parseInt(cmd.substring(7));
                if (m < 1) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a positive integer.");
                } else if (m > count) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index does not exist.");
                }
                return true;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a number.");
            }
        } else {
            return false;
        }
    }

    private static Task generate(String cmd) throws InvalidCommandException {
        if (cmd.startsWith("todo")) {
            if (cmd.length() < 5) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            } else if (cmd.charAt(4) != ' ') {
                throw new InvalidCommandException("Do you mean 'todo " + cmd.substring(4) + "'");
            } else if (cmd.length() < 6) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            return new ToDo(cmd.substring(5));
        } else if (cmd.startsWith("deadline")) {
            if (cmd.length() < 9) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
            } else if (cmd.charAt(8) != ' ') {
                throw new InvalidCommandException("Do you mean 'deadline " + cmd.substring(8) + "'");
            } else if (cmd.length() < 10) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
            }
            String description = cmd.substring(9);
            int s = description.indexOf("/by");
            if (s == -1) {
                throw new InvalidCommandException("\u2639 OOPS!!! Time should be specified");
            }
            if (description.length() - s < 4) {
                throw new InvalidCommandException("The time specification cannot be empty.");

            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Deadline(description, time);
        } else if (cmd.startsWith("event")) {
            if (cmd.length() < 6) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a event cannot be empty.");
            } else if (cmd.charAt(5) != ' ') {
                throw new InvalidCommandException("Do you mean 'event " + cmd.substring(5) + "'");
            } else if (cmd.length() < 7) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a event cannot be empty.");
            }
            String description = cmd.substring(6);
            int s = description.indexOf("/at");
            if (s == -1) {
                throw new InvalidCommandException("\u2639 OOPS!!! Time should be specified");
            }
            if (description.length() - s < 4) {
                throw new InvalidCommandException("The time specification cannot be empty.");

            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Event(description, time);
        } else {
            throw new InvalidCommandException();
        }
    }

    private static void addToList(Task task) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter("../data/tasks.txt", true);
            fw.write(task.output());
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    private static void deleteTask(List<Task> list) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter("../data/tasks.txt");
            for (Task task : list) {
                fw.write(task.output());
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    private static void printList(int count, List<Task> list, Predicate<Task> predicate, String note) {
        System.out.println(horizontalLine + "\n\t  " + "Here are the tasks " + note + "in your list:");
        for (int i = 0; i < count; i++) {
            Task task = list.get(i);
            if (predicate.test(task)) {
                System.out.println("\t  " + (i + 1) + "." + list.get(i));
            }
        }
        System.out.println(horizontalLine + "\n");
    }

    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        List<Task> list = new ArrayList<>();
        FileWriter fw = new FileWriter("../data/tasks.txt");
        fw.write("");
        fw.close();
        int count = 0;
        boolean flag = true;
        while (flag) {
            try {
                String input = ui.readInput();
                Input type;
                if (input.equals("bye")) {
                    type = Input.BYE;
                } else if (input.equals("list")) {
                    type = Input.LIST;
                } else if (isDeleteCommand(input, count)) {
                    type = Input.DELETE;
                } else if (isDoneCommand(input, count)) {
                    type = Input.DONE;
                } else if (input.startsWith("happens on ")) {
                    type = Input.HAPPENS;
                } else {
                    type = Input.TASK;
                }
                switch (type) {
                    case LIST:
                        printList(count, list, t -> true, "");
                        break;
                    case DONE:
                        list.get(n - 1).markAsDone();
                        System.out.println(output("Nice! I've marked this task as done:\n\t    " + list.get(n - 1)));
                        break;
                    case DELETE:
                        Task toDelete = list.get(m - 1);
                        list.remove(toDelete);
                        deleteTask(list);
                        System.out.println(output("Noted. I've removed this task:\n\t    " + toDelete +
                                "\n\t  Now you have " + list.size()));
                        count--;
                        break;
                    case HAPPENS:
                        try {
                            LocalDate date = LocalDate.parse(input.substring(11),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            printList(count, list, t -> t.happenOnDate(date), "happening on " +
                                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ");
                        } catch (Exception e) {
                            throw new InvalidCommandException("Invalid date format. Please use yyyy-MM-dd");
                        }
                        break;
                    case TASK:
                        Task task = generate(input);
                        addToList(task);
                        list.add(count++, task);
                        String temp = count <= 1 ? " task" : " tasks";
                        System.out.println(output("Got it. I've added this task:\n\t    " + task +
                                "\n\t  Now you have " + count + temp + " in the list."));
                        break;
                    case BYE:
                        flag = false;
                        break;
                }
            } catch (InvalidCommandException e) {
                System.out.println(output(e.getMessage()));
            }
        }
        System.out.println(output("Bye. Hope to see you again soon!"));
    }
}
