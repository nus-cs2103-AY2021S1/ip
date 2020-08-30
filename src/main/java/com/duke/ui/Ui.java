package com.duke.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.duke.exceptions.DukeException;
import com.duke.parser.Parser;
import com.duke.storage.Storage;
import com.duke.tasklist.TaskList;
import com.duke.tasks.Deadlines;
import com.duke.tasks.Events;
import com.duke.tasks.Task;
import com.duke.tasks.ToDos;

/**
 * Deals with responding to the user.
 * Calls methods of other classes to save/pull entries into/from session-based storage.
 */
public class Ui {
    private TaskList taskList;
    private Scanner scanner = new Scanner(System.in);

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public Ui() {
        this.taskList = null;
    }

    /**
     * Adds entry into Duke and replies with response.
     */
    private String addItem(String taskType, String task) throws DukeException {
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
        default:
            throw new DukeException(Ui.errorMessage());
        }
        String result = "\tGot it. I've added this task:\n";
        result += "\t\t" + this.taskList.getList().get(this.taskList.size() - 1).toString() + "\n";
        result += "\tNow you have " + this.taskList.size() + " tasks in the list.";
        this.reply(result);
        Ui.sectionize();
        return result;
    }

    private void reply(String reply) {
        System.out.println(reply);
    }

    /**
     * Prints entries stored in Duke.
     */
    private String listItems() {
        System.out.println(this.taskList);
        Ui.sectionize();
        String res = "";
        res += "\tHere are the tasks in your list:";
        int counter = 1;
        for (int i = 0; i < this.taskList.size(); i++) {
            res += "\n";
            res += "\t" + counter + "." + this.taskList.getItem(i).toString();
            counter++;
        }
        reply(res);
        Ui.sectionize();
        return res;
    }

    /**
     * Prints entries that match input keyword.
     *
     * @param input input keyword to match.
     */
    private String findItem(String input) {
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
            return this.replyNoTaskFound(input);
        } else {
            return this.replyTasksFound(matchingTaskList);
        }
    }

    private String replyNoTaskFound(String input) {
        Ui.sectionize();
        String result = "\tI'm sorry, there are no tasks found with keyword " + input + ".";
        this.reply(result);
        Ui.sectionize();
        return result;
    }

    /**
     * Lists entries that match input keyword.
     *
     * @param taskList list of tasks that match input keyword.
     */
    private String replyTasksFound(List<Task> taskList) {
        Ui.sectionize();
        String result = "\tHere are the matching tasks in your list:";
        for (Task task : taskList) {
            result += "\n\t";
            result += task.toString();
        }
        this.reply(result);
        Ui.sectionize();
        return result;
    }

    private String sayBye() {
        String result = "";
        try {
            Storage.saveListToFile(this.taskList);
            Ui.sectionize();
            result = "\tBye. Hope to see you again soon!";
            this.reply(result);
            Ui.sectionize();
            return result;
        } catch (IOException e) {
            result = "Sorry! The file failed to save. Please try again.";
            this.reply(result);
            return result;
        } finally {
            exit();
        }
    }

    private String markDone(int index) {
        String result = "";
        this.taskList.setDone(index);
        Ui.sectionize();
        result += "\tNice! I've marked this task as done:\n";
        result += "\t" + this.taskList.getItem(index).toString();
        this.reply(result);
        Ui.sectionize();
        return result;
    }

    /**
     * Removes specified entry from Duke and prints reply.
     *
     * @param index index of entry to be removed.
     */
    private String remove(int index) {
        String result = "";
        try {
            Task task = this.taskList.remove(index);
            Ui.sectionize();
            result += "\tNoted. I've removed this task:\n";
            result += "\t\t" + task.toString() + "\n";
            result += "\tNow you have " + this.taskList.size() + " tasks in the list.";
            this.reply(result);
            Ui.sectionize();
            return result;
        } catch (IndexOutOfBoundsException e) {
            Ui.sectionize();
            result += "\t☹ OOPS!!! I'm sorry, this task does not exist in your list!";
            this.reply(result);
            Ui.sectionize();
            return result;
        }
    }

    /**
     * Prints welcome message for Duke.
     */
    public String showWelcome() {
        String result = "";
        result += "Hello! I'm DukeBot\n";
        result += "What can I do for you?";
        this.reply(result);
        return result;
    }

    private static void sectionize() {
        System.out.println("\t____________________________________________________________");
    }

    private static String errorMessage() {
        return "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
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

    /**
     * Tells user that persistent file failed to load.
     */
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
    public void listen() {
        String input = scanner.nextLine();
        handleCommand(input);
        this.listen();
    }

    /**
     * Listens from commands from user.
     * Possible commands include: "done 1", "delete 2", "bye", "list".
     * Possible event command: "event halloween party /at 2/12/2019 1800".
     * Possible deadline command: "deadline add comments /by 2/12/2019 1800".
     * Possible todo command: "todo read book".
     */
    public String listen(String input) {
        String res = handleCommand(input);
        return res;
    }

    private String handleCommand(String input) {
        if (Parser.isDone(input)) {
            int index = Integer.parseInt(input.substring(5, 6)) - 1;
            return this.markDone(index);
        } else if (Parser.isDelete(input)) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return this.remove(index);
        } else if (input.equals("bye")) {
            return this.sayBye();
        } else if (input.equals("list")) {
            return this.listItems();
        } else if (Parser.isFind(input)) {
            String item = input.split(" ", 2)[1];
            return this.findItem(item);
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
                    return this.addItem(taskType, task);
                case ("deadline"):
                    // date = 'by Sunday'
                    return this.addItem(taskType, task);
                case ("event"):
                    return this.addItem(taskType, task);
                default:
                    throw new DukeException(Ui.errorMessage());
                }
            } catch (DukeException e) {
                Ui.sectionize();
                String errorMessage = "\t\t" + e.getMessage();
                this.reply(errorMessage);
                Ui.sectionize();
                return errorMessage;
            }
        }
    }
}
