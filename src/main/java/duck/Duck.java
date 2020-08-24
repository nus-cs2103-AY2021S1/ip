package duck;

import duck.exception.DuckException;
import duck.task.Deadline;
import duck.task.Event;
import duck.task.Task;
import duck.task.Todo;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duck {

    private Ui userInterface;
    private List<Task> tasks = new ArrayList<>();

    public Duck(Ui ui) {
        this.userInterface = ui;
    }

    private String[] parseWithDate(String s, String sep) throws DuckException {
        String[] split = s.split(sep);

        if (split.length < 2) {
            throw new DuckException("Please specify a date");
        }

        return split;
    }

    private String getNumberOfTasks() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    public void run() {
        List<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Hello! I'm Duck");
        welcomeMessage.add("What can I do for you?");
        userInterface.respond(welcomeMessage);

        boolean run = true;
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.nextLine();
            List<String> responses = new ArrayList<>();
            String[] inputSplit = input.split(" ");
            Option option = Option.from(inputSplit[0]);
            try {
                switch (option) {
                case BYE:
                        responses.add("Bye. Hope to see you again soon!");
                        run = false;
                        break;
                case LIST:
                        responses.add("Here are the tasks in your list");
                        for (int i = 0; i < this.tasks.size(); i++) {
                            String item = "" + (i + 1) + ". " + this.tasks.get(i).getStatus();
                            responses.add(item);
                        }
                        break;
                case DONE:
                        if (inputSplit.length != 2) {
                            throw new DuckException("Please provide a task number!");
                        }

                        try {
                            int taskNumber = Integer.parseInt(inputSplit[1]);

                            if (taskNumber > this.tasks.size()) {
                                throw new DuckException("No such task with that number!");

                            } else {
                                responses.add("Nice! I've marked this as " + Colour.Green("done"));
                                Task task = this.tasks.get(taskNumber - 1);
                                task.markDone();
                                responses.add("  " + task.getStatus());
                            }
                        } catch (NumberFormatException e) {
                            throw new DuckException("Invalid number provided");
                        }

                        break;
                case DELETE:
                        if (inputSplit.length != 2) {
                            throw new DuckException("Please provide a task number!");
                        }

                        try {
                            int taskNumber = Integer.parseInt(inputSplit[1]);

                            if (taskNumber > this.tasks.size()) {
                                throw new DuckException("No such task with that number!");

                            } else {
                                responses.add("Noted. I've removed this task");
                                Task task = this.tasks.get(taskNumber - 1);
                                this.tasks.remove(taskNumber - 1);

                                responses.add("  " + task.getStatus());
                                responses.add(getNumberOfTasks());
                            }
                        } catch (NumberFormatException e) {
                            throw new DuckException("Invalid number provided");
                        }

                        break;
                case TODO:
                case DEADLINE:
                case EVENT:
                        Task newTask;

                        String description = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));

                        if (description.length() == 0) {
                            throw new DuckException("The description field cannot be empty!");
                        }

                        if (option.equals(Option.TODO)) {
                            newTask = new Todo(description);
                        } else if (option.equals(Option.DEADLINE)) {

                            String[] parsedString = parseWithDate(description, "/by");
                            String desc, date;
                            desc = parsedString[0].strip();
                            date = parsedString[1].strip();

                            newTask = new Deadline(desc, date);
                        } else {
                            String[] parsedString = parseWithDate(description, "/at");
                            String desc, date;
                            desc = parsedString[0].strip();
                            date = parsedString[1].strip();
                            newTask = new Event(desc, date);
                        }

                        this.tasks.add(newTask);
                        responses.add("Got it. I've added this task");
                        responses.add("  " + newTask.getStatus());
                        responses.add(getNumberOfTasks());
                        break;
                case UNRECOGNIZED:
                default:
                        throw new DuckException("Instruction not recognized");

                }
            } catch (DuckException e) {
                responses.add(e.toString());
            } finally {
                userInterface.respond(responses);
            }
        }
    }
}

