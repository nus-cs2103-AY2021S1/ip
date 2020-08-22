package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Duke {

    private static String horizontalLine = "    ____________________________________________________________";
    private static String textIndentation = "     ";
    private static String greetMessage = Duke.textIndentation + "Hello! I'm Duke\n" +
            Duke.textIndentation + "What can I do for you?";
    private static String exitMessage = Duke.textIndentation + "Bye. Hope to see you again soon!";
    private static String addTaskMessage = Duke.textIndentation + "Got it. I've added this task:";
    private static String completeTaskMessage = Duke.textIndentation + "Nice! I've marked this task as done:";
    private static String invalidSyntaxMessage = Duke.textIndentation + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static String deleteTaskMessage = Duke.textIndentation + "Noted. I've removed this task:";
    private static TaskList taskList;

    public static void main(String[] args) {

        Duke.taskList = new TaskList();
        File dukeData = new File("./data/duke_data.csv");

        if (dukeData.exists()) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(dukeData));
                String eachRow;
                while ((eachRow = csvReader.readLine()) != null) {
                    String[] dataStringArray = eachRow.split(",");
                    System.out.println(dataStringArray[0] + dataStringArray[1] + dataStringArray[2]);
                    Task eachTask = Duke.genTask(dataStringArray);
                    if (eachTask != null) {
                        Duke.taskList.addToList(eachTask);
                    }
                }
            } catch (java.io.FileNotFoundException e) {
                System.out.println("(FILE NOT FOUND) THIS MESSAGE SHOULD NEVER APPEAR");
            } catch (java.io.IOException e) {
                System.out.println("(ERROR READING FILE) THIS MESSAGE SHOULD NEVER APPEAR");
            }
        }

        Duke.printText(Duke.greetMessage);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String inputMessage = sc.nextLine();

            if (inputMessage.toLowerCase().equals("bye")) {
                Duke.printText(exitMessage);
                return;

            } else if (inputMessage.toLowerCase().equals("list")) {
                Duke.printText(Duke.taskList.toString());
            } else if (inputMessage.toLowerCase().startsWith("done ")) {
                String taskQueryIndexString = inputMessage.substring(4).trim();

                try {
                    int taskQueryIndex = Integer.parseInt(taskQueryIndexString) - 1;
                    Task completedTask = Duke.taskList.getTaskAt(taskQueryIndex);
                    completedTask.completeTask();
                    Duke.printText(Duke.completeTaskMessage + "\n" + Duke.textIndentation + completedTask);
                } catch (Exception e) {
                    Duke.printText(Duke.textIndentation + "Please enter a valid task index.");
                }

            } else if (inputMessage.toLowerCase().startsWith("todo ")) {
                ToDo newToDo = Duke.genToDo(inputMessage);
                if (newToDo != null) {
                    Duke.taskList.addToList(newToDo);
                    Duke.printText(Duke.addTaskMessage + "\n  " + Duke.textIndentation + newToDo + "\n" +
                            Duke.numOfTasksMessage());
                }

            } else if (inputMessage.toLowerCase().startsWith("deadline ")) {
                Deadline newDeadline = Duke.genDeadline(inputMessage);
                if (newDeadline != null) {
                    Duke.taskList.addToList(newDeadline);
                    Duke.printText(Duke.addTaskMessage + "\n  " + Duke.textIndentation + newDeadline + "\n" +
                            Duke.numOfTasksMessage());
                }

            } else if (inputMessage.toLowerCase().startsWith("event ")) {
                Event newEvent = Duke.genEvent(inputMessage);
                if (newEvent != null) {
                    Duke.taskList.addToList(newEvent);
                    Duke.printText(Duke.addTaskMessage + "\n  " + Duke.textIndentation + newEvent + "\n" +
                            Duke.numOfTasksMessage());
                }

            } else if (inputMessage.toLowerCase().startsWith("delete ")) {
                String taskQueryIndexString = inputMessage.substring(7).trim();

                try {
                    int taskQueryIndex = Integer.parseInt(taskQueryIndexString) - 1;
                    Task deletedTask = Duke.taskList.getTaskAt(taskQueryIndex);
                    Duke.taskList.deleteTaskAt(taskQueryIndex);
                    Duke.printText(Duke.deleteTaskMessage + "\n  " + Duke.textIndentation + deletedTask + "\n" +
                            Duke.numOfTasksMessage());
                } catch (Exception e) {
                    Duke.printText(Duke.textIndentation + "Please enter a valid task index.");
                }

            } else {
                Duke.printText(Duke.invalidSyntaxMessage);
            }
        }
    }

    static void printText(String text) {
        System.out.println(Duke.horizontalLine);
        System.out.println(text);
        System.out.println(Duke.horizontalLine + "\n");
    }

    private static String numOfTasksMessage() {
        return Duke.textIndentation + "Now you have " + Duke.taskList.getNumOfTasks() + " tasks in the list";
    }

    static ToDo genToDo(String inputText) {
        if (inputText.toLowerCase().startsWith("todo ")) {
            String description = inputText.substring(5).trim();
            if (description.isBlank()) {
                return null;
            } else {
                return new ToDo(description);
            }
        } else {
            return null;
        }
    }

    static Deadline genDeadline(String inputText) {
        if (inputText.toLowerCase().startsWith("deadline ")) {
            String argumentText = inputText.substring(9).trim();
            if (argumentText.isBlank()) {
                return null;
            } else {
                String[] refinedInputText = argumentText.split(" /by ");
                if (refinedInputText.length == 1) {
                    return null;
                } else if (refinedInputText.length == 2) {
                    return new Deadline(refinedInputText[0], refinedInputText[1]);
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    static Event genEvent(String inputText) {
        if (inputText.toLowerCase().startsWith("event ")) {
            String argumentText = inputText.substring(6).trim();
            if (argumentText.isBlank()) {
                return null;
            } else {
                String[] refinedInputText = argumentText.split(" /at ");
                if (refinedInputText.length == 1) {
                    return null;
                } else if (refinedInputText.length == 2) {
                    return new Event(refinedInputText[0], refinedInputText[1]);
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    static Task genTask(String[] stringArray) {
        try {
            if (stringArray.length == 0) {
                return null;
            } else {
                String taskType = stringArray[0];
                if (taskType.equals("todo") && stringArray.length == 3) {
                    return new ToDo(Boolean.parseBoolean(stringArray[1]), stringArray[2]);
                } else if (taskType.equals("deadline") && stringArray.length == 4) {
                    return new Deadline(Boolean.parseBoolean(stringArray[1]), stringArray[2], stringArray[3]);
                } else if (taskType.equals("event") && stringArray.length == 4) {
                    return new Event(Boolean.parseBoolean(stringArray[1]), stringArray[2], stringArray[3]);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }
}
