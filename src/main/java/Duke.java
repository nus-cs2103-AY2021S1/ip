package main.java;


import java.security.InvalidParameterException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
        parser = new Parser();
        ui = new Ui();
    }

    private void run() {

        ui.welcomeWord();
        ui.showHistory(taskList);

        Scanner inputScanner = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = inputScanner.nextLine();
            if (userInput.equals("bye")) {
                ui.goodBye();
            } else if (userInput.equals("list")) {
                ui.listTask(taskList);
            } else if (userInput.startsWith("done")) {
                try {
                    int doneNumber = parser.parseDoneOrder(userInput, taskList);
                    taskList.markDone(doneNumber);
                    ui.taskDone(taskList.getTask(doneNumber));
                } catch (IndexOutOfBoundsException e) {
                    ui.taskDoesNotExist();
                } catch (Exception e) {
                    ui.invalidDoneOrder();
                }
            } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput
                    .startsWith("event")) {
                try {
                    Task newTask = parser.parseTask(userInput);
                    taskList.addNewTask(newTask);
                    ui.newTaskAdded(newTask, taskList);
                } catch (InvalidParameterException e) {
                    ui.parseFail(userInput);
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    int deletingIndex = parser.parseDeleteOrder(userInput, taskList);
                    Task deletingTask = taskList.getTask(deletingIndex);
                    taskList.deleteTask(deletingIndex);
                    ui.taskDeleted(taskList, deletingTask);
                } catch (IndexOutOfBoundsException e) {
                    ui.taskDoesNotExist();
                } catch (Exception e) {
                    ui.invalidDeleteOrder();
                }
            } else {
                ui.generalError();
            }
        }
        inputScanner.close();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

}
