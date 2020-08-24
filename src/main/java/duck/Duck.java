package duck;

import duck.exception.DuckException;
import duck.task.Task;
import duck.task.TaskFactory;
import duck.task.TaskList;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duck {

    private Ui userInterface;
    private TaskList taskList;
    private List<String> responses;

    public Duck(Ui ui) {
        this.userInterface = ui;
        this.taskList = new TaskList();
        this.responses = new ArrayList<>();
    }


    private String getNumberOfTasks() {
        return "Now you have " + this.taskList.getLength() + " tasks in the list.";
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
        String[] statuses = this.taskList.getStatuses();
        for (int i = 0; i < statuses.length; i++) {
            responses.add(statuses[i]);
        }
    }

    public void markTaskAsDone(String input) throws DuckException {
        int taskNumber = Parser.parseTaskNumber(input);
        Task task = this.taskList.markDone(taskNumber);
        responses.add("Nice! I've marked this as " + Colour.Green("done"));
        responses.add("  " + task.getStatus());
    }

    public void deleteTask(String input) throws DuckException {
        int taskNumber = Parser.parseTaskNumber(input);
        Task task = this.taskList.deleteTask(taskNumber);
        responses.add("Noted. I've removed this task");
        responses.add("  " + task.getStatus());
        responses.add(getNumberOfTasks());

    }

    public void createNewTask(String input) throws DuckException {

        Task newTask = TaskFactory.createTaskFromInput(input);
        this.taskList.addTask(newTask);
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

