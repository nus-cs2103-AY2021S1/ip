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
                        String usage = Colour.Blue("Usage: done [taskNumber]");
                        if (inputSplit.length != 2) {
                            responses.add(Colour.Red("Please provide a task number!"));
                            responses.add(usage);
                            break;
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
                            responses.add(Colour.Red("Invalid number provided"));
                            responses.add(usage);
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
                        responses.add("Now you have " + Duke.tasks.size() + " tasks in the list.");
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

