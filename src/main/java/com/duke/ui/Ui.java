package com.duke.ui;

import com.duke.tasks.Deadlines;
import com.duke.tasks.Events;
import com.duke.tasks.Task;
import com.duke.tasks.ToDos;
import com.duke.exceptions.DukeException;
import com.duke.parser.Parser;
import com.duke.storage.Storage;
import com.duke.tasklist.TaskList;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private TaskList taskList;
    private Scanner scanner = new Scanner(System.in);

    private Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public Ui() {
        this.taskList = null;
    }

    /**
     * Adds entry into Duke and replies with response.
     */
    private void addItem(String taskType, String task) throws DukeException {
        Ui.sectionize();
        String date = "";
        switch (taskType) {
        case ("todo"):
            this.taskList.addItem(new ToDos(task));
            break;
        case ("event"):
            String[] taskAndDateArr = Parser.splitTaskAndDate(task);
            task = taskAndDateArr[0];
            date = taskAndDateArr[1];
            taskList.addItem(new Events(task, date));
            break;
        case ("deadline"):
            taskAndDateArr = Parser.splitTaskAndDate(task);
            task = taskAndDateArr[0];
            date = taskAndDateArr[1];
            taskList.addItem(new Deadlines(task, date));
            break;
        }
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + this.taskList.getList().get(this.taskList.size() - 1).toString());
        System.out.println("\tNow you have " + this.taskList.size() + " tasks in the list.");
        Ui.sectionize();
    }

    /**
     * Prints entries stored in Duke.
     */
    private void listItems() {
        Ui.sectionize();
        System.out.println("\tHere are the tasks in your list:");
        int counter = 1;
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("\t" + counter + "." + this.taskList.getItem(i).toString());
            counter++;
        }
        Ui.sectionize();
    }

    /**
     * Prints entries that match input keyword.
     *
     * @param input input keyword to match.
     */
    private void findItem(String input) {
        int len = this.taskList.size();
        ArrayList<Task> matchingTaskList = new ArrayList<>();
        //loop through taskstring in tasklist and find matching keywords
        for (int i = 0; i < len; i++) {
            Task task = this.taskList.getItem(i);
            String taskDescription = task.getTask();
            if (taskDescription.contains(input)) {
                matchingTaskList.add(task);
            }
        }
        if (matchingTaskList.size() == 0) {
            this.replyNoTaskFound(input);
        } else {
            this.replyTasksFound(matchingTaskList);
        }
    }

    private void replyNoTaskFound(String input) {
        Ui.sectionize();
        System.out.println("\tI'm sorry, there are no tasks found with keyword " + input + ".");
        Ui.sectionize();
    }

    /**
     * Lists entries that match input keyword.
     *
     * @param taskList list of tasks that match input keyword.
     */
    private void replyTasksFound(List<Task> taskList) {
        Ui.sectionize();
        String reply = "\tHere are the matching tasks in your list:";
        for (Task task : taskList) {
            reply += "\n\t";
            reply += task.toString();
        }
        System.out.println(reply);
        Ui.sectionize();
    }

    private void sayBye() {
        try {
            Storage.saveListToFile(this.taskList);
            Ui.sectionize();
            System.out.println("\tBye. Hope to see you again soon!");
            Ui.sectionize();
        } catch (IOException e) {
            System.out.println("Sorry! The file failed to save. Please try again.");
        }
    }

    private void markDone(int index) {
        this.taskList.setDone(index);
        Ui.sectionize();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + this.taskList.getItem(index).toString());
        Ui.sectionize();
    }

    /**
     * Removes specified entry from Duke and prints reply.
     *
     * @param index index of entry to be removed.
     */
    private void remove(int index) {
        try {
            Task task = this.taskList.remove(index);
            Ui.sectionize();
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println("\t\t" + task.toString());
            System.out.println("\tNow you have " + this.taskList.size() + " tasks in the list.");
            Ui.sectionize();
        } catch (IndexOutOfBoundsException e) {
            Ui.sectionize();
            System.out.println("\t☹ OOPS!!! I'm sorry, this task does not exist in your list!");
            Ui.sectionize();
        }
    }

    /**
     * Prints welcome message for Duke.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm DukeBot");
        System.out.println("What can I do for you?");
    }

    private static void sectionize() {
        System.out.println("\t____________________________________________________________");
    }

    private static String errorMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Initializes Duke with no entries.
     */
    public static void initialize() {
        Ui ui = new Ui(new TaskList());
        ui.showWelcome();
        ui.listen();
    }

    /**
     * Initializes Duke from persistent file.
     */
    public static void initialize(TaskList taskList) {
        Ui ui = new Ui(taskList);
        ui.showWelcome();
        ui.listen();
    }

    public void showLoadingError() {
        System.out.println("File failed to load. Initializing new File...");
        initialize();
    }

    private static void exit() {
        System.exit(0);
    }

    /**
     * Listens from commands from user.
     * Possible commands include: "done 1", "delete 2", "bye", "list".
     * Possible event command: "event halloween party /at 2/12/2019 1800".
     * Possible deadline command: "deadline add comments /by 2/12/2019 1800".
     * Possible todo command: "todo read book".
     */
    private void listen() {
        String input = scanner.nextLine();
        if (Parser.isDone(input)) {
            int index = Integer.parseInt(input.substring(5, 6)) - 1;
            this.markDone(index);
        } else if (Parser.isDelete(input)) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            this.remove(index);
        } else if (input.equals("bye")) {
            this.sayBye();
            exit();
        } else if (input.equals("list")) {
            this.listItems();
        } else if (Parser.isFind(input)) {
            String item = input.split(" ", 2)[1];
            this.findItem(item);
        } else {
            try {
                if (!Parser.isCorrectInputFormat(input)) {
                    throw new DukeException(Ui.errorMessage());
                }

                //pull type of task and the task
                String taskType = input.substring(0, input.indexOf(" "));
                String task = input.substring(input.indexOf(" ") + 1);
                switch (taskType) {
                case ("todo"):
                    this.addItem(taskType, task);
                    break;
                case ("deadline"):
                    // date = 'by Sunday'
                    this.addItem(taskType, task);
                    break;
                case ("event"):
                    this.addItem(taskType, task);
                    break;
                default:
                    throw new DukeException(Ui.errorMessage());
                }
            } catch (DukeException e) {
                Ui.sectionize();
                System.out.println("\t\t" + e.getMessage());
                Ui.sectionize();
            }
        }
        this.listen();
    }
}
