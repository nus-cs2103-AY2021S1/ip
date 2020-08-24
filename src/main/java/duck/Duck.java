package duck;

import duck.exception.DuckException;
import duck.task.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duck {

    private Ui userInterface;
    private List<Task> tasks = new ArrayList<>();
    private List<String> responses = new ArrayList<>();

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

    public void greet() {
        List<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Hello! I'm Duck");
        welcomeMessage.add("What can I do for you?");
        userInterface.respond(welcomeMessage);
    }

    public void shutdown() {
        responses.add("Bye. Hope to see you again soon!");
    }

    public void listTasks() {
        responses.add("Here are the tasks in your list");
        for (int i = 0; i < this.tasks.size(); i++) {
            String item = "" + (i + 1) + ". " + this.tasks.get(i).getStatus();
            responses.add(item);
        }
    }

    public void markTaskAsDone(String input) throws DuckException {
        String[] inputSplit = input.split("\\s+");
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
    }

    public void deleteTask(String input) throws DuckException {
        String[] inputSplit = input.split("\\s+");
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
    }

    public void createNewTask(String input) throws DuckException {
        String[] inputSplit = input.split("\\s+");
        Option option = Parser.parseOption(input);

        Task newTask = TaskFactory.createTaskFromInput(input);

        this.tasks.add(newTask);
        responses.add("Got it. I've added this task");
        responses.add("  " + newTask.getStatus());
        responses.add(getNumberOfTasks());
    }


    public void run() {
        greet();

        boolean run = true;
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.nextLine();
            responses.clear();
            Option option = Parser.parseOption(input);
            try {
                switch (option) {
                case BYE:
                    shutdown();
                    run = false;
                    break;
                case LIST:
                    listTasks();
                    break;
                case DONE:
                    markTaskAsDone(input);
                    break;
                case DELETE:
                    deleteTask(input);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    createNewTask(input);
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

