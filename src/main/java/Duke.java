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
            switch (option) {
                case "bye":
                    responses.add("Bye. Hope to see you again soon!");
                    run = false;
                    break;
                case "list":
                    responses.add("Here are the tasks in your list");
                    for (int i = 0; i < Duke.tasks.size(); i++) {
                        String item = "" + (i+1) + ". " + Duke.tasks.get(i).getStatus();
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
                            responses.add(Colour.Red("No such task with that number!"));
                            responses.add(usage);
                            break;
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

                    if (option.equals("todo")) {
                        newTask = new Todo(description);
                    } else if (option.equals("deadline")) {
                        String[] split = description.split("/by");
                        String desc, date;

                        if (split.length < 2) {
                            responses.add(Colour.Red("Please specify a date"));
                            break;
                        }

                        desc = split[0].strip();
                        date = split[1].strip();
                        newTask = new Deadline(desc, date);
                    } else {
                        String[] split = description.split("/at");
                        String desc, date;

                        if (split.length < 2) {
                            responses.add(Colour.Red("Please specify a date"));
                            break;
                        }

                        desc = split[0].strip();
                        date = split[1].strip();
                        newTask = new Event(desc, date);
                    }

                    Duke.tasks.add(newTask);
                    responses.add("Got it. I've added this task");
                    responses.add("  " + newTask.getStatus());
                    responses.add("Now you have " + Duke.tasks.size() + " tasks in the list.");
                    break;
                default:
                   responses.add(Colour.Red("Instruction not recognized!"));

            }

            respond(responses);
        }
    }
}

