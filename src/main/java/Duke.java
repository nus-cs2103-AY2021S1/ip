import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String LINE = Colour.Blue("____________________________________________________________");
    private static final String INDENT = "    ";
    private static List<Task> tasks = new ArrayList<>();

    public static void printLine() {
        System.out.println(INDENT + LINE);
    }

    public static void respond(List<String> responses) {
        printLine();
        for (String s : responses) {
            System.out.print(INDENT);
            System.out.println(s);
        }
        printLine();
    }

    private static String[] parseWithDate(String s, String sep) throws DukeException {
        String[] split = s.split(sep);

        if (split.length < 2) {
            throw new DukeException("Please specify a date");
        }

        return split;
    }

    private static String getNumberOfTasks() {
        return "Now you have " + Duke.tasks.size() + " tasks in the list.";
    }

    public static void main(String[] args) {
        List<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Hello! I'm Duke");
        welcomeMessage.add("What can I do for you?");
        respond(welcomeMessage);

        boolean run = true;
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.nextLine();
            List<String> responses = new ArrayList<>();
            String[] inputSplit = input.split(" ");
            String option = inputSplit[0];
            try {
                switch (option) {
                    case "bye":
                        responses.add("Bye. Hope to see you again soon!");
                        run = false;
                        break;
                    case "list":
                        responses.add("Here are the tasks in your list");
                        for (int i = 0; i < Duke.tasks.size(); i++) {
                            String item = "" + (i + 1) + ". " + Duke.tasks.get(i).getStatus();
                            responses.add(item);
                        }
                        break;
                    case "done":
                        if (inputSplit.length != 2) {
                            throw new DukeException("Please provide a task number!");
                        }

                        try {
                            int taskNumber = Integer.parseInt(inputSplit[1]);

                            if (taskNumber > Duke.tasks.size()) {
                                throw new DukeException("No such task with that number!");

                            } else {
                                responses.add("Nice! I've marked this as " + Colour.Green("done"));
                                Task task = Duke.tasks.get(taskNumber - 1);
                                task.markDone();
                                responses.add("  " + task.getStatus());
                            }
                        } catch (NumberFormatException e) {
                            throw new DukeException("Invalid number provided");
                        }

                        break;
                    case "delete":
                        if (inputSplit.length != 2) {
                            throw new DukeException("Please provide a task number!");
                        }

                        try {
                            int taskNumber = Integer.parseInt(inputSplit[1]);

                            if (taskNumber > Duke.tasks.size()) {
                                throw new DukeException("No such task with that number!");

                            } else {
                                responses.add("Noted. I've removed this task");
                                Task task = Duke.tasks.get(taskNumber - 1);
                                Duke.tasks.remove(taskNumber - 1);

                                responses.add("  " + task.getStatus());
                                responses.add(Duke.getNumberOfTasks());
                            }
                        } catch (NumberFormatException e) {
                            throw new DukeException("Invalid number provided");
                        }

                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        Task newTask;

                        String description = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));

                        if (description.length() == 0) {
                            throw new DukeException("The description field cannot be empty!");
                        }

                        if (option.equals("todo")) {
                            newTask = new Todo(description);
                        } else if (option.equals("deadline")) {

                            String[] parsedString = Duke.parseWithDate(description, "/by");
                            String desc, date;
                            desc = parsedString[0].strip();
                            date = parsedString[1].strip();

                            newTask = new Deadline(desc, date);
                        } else {
                            String[] parsedString = Duke.parseWithDate(description, "/at");
                            String desc, date;
                            desc = parsedString[0].strip();
                            date = parsedString[1].strip();
                            newTask = new Event(desc, date);
                        }

                        Duke.tasks.add(newTask);
                        responses.add("Got it. I've added this task");
                        responses.add("  " + newTask.getStatus());
                        responses.add(Duke.getNumberOfTasks());
                        break;
                    default:
                        throw new DukeException("Instruction not recognized");

                }
            } catch (DukeException e) {
                responses.add(e.toString());
            } finally {
                respond(responses);
            }
        }
    }
}

