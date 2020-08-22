package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Commands {
    private boolean shouldBreak = true;
    private ArrayList<Task> taskList = new ArrayList<>();

    enum Input {
        LIST, BYE, DONE, TODO, DEADLINES, EVENTS, DELETE
    }

    public void start() {
        this.greet();
        WriteData.getPreviousTask(taskList);

        Scanner scanner = new Scanner(System.in);

        String inputs = scanner.nextLine().trim();

        while (shouldBreak) {
            String[] inputArray = inputs.split(" ", 2);
            try {
                if (find(inputArray[0])) {
                    switch (Input.valueOf(inputArray[0].toUpperCase())) {
                    case LIST:
                        this.lst();
                        inputs = scanner.nextLine().trim();
                        break;
                    case BYE:
                        System.out.println("~ \n I will be back \n~ ");
                        shouldBreak = !shouldBreak;
                        break;
                    case DONE:
                        markDone(inputArray);
                        inputs = scanner.nextLine().trim();
                        break;
                    case TODO:
                        addTodo(inputArray);
                        inputs = scanner.nextLine().trim();
                        break;
                    case DEADLINES:
                        addDeadline(inputArray);
                        inputs = scanner.nextLine().trim();
                        break;
                    case EVENTS:
                        addEvent(inputArray);
                        inputs = scanner.nextLine().trim();
                        break;
                    case DELETE:
                        deleteItem(inputArray);
                        inputs = scanner.nextLine().trim();
                        break;
                    }
                } else {
                    throw new DukeException("~\n ERROR... INPUT NOT RECOGNIZED. \n PLEASE TRY AGAIN \n~");
                }
            } catch (DukeException dukeException) {
                System.out.println(dukeException.getMessage());
                inputs = scanner.nextLine().trim();
            }
        }
        WriteData.writeToFile(taskList);
    }

    private void markDone(String[] inputs) throws DukeException {
        if (inputs.length > 1 && Character.isDigit(inputs[1].charAt(0))) {
            int taskNumber = Character.getNumericValue(inputs[1].charAt(0)) - 1;
            if (!taskList.isEmpty() && taskNumber < taskList.size()) {
                taskList.get(taskNumber).doneTask();
                System.out.println("~ \n Nice! Target Eliminated: \n   "
                        + taskList.get(taskNumber).toString() + "\n~");
            } else {
                throw new DukeException("~\n ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN \n~");
            }
        } else {
            throw new DukeException("~\n ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n " +
                    "PLEASE TRY AGAIN \n~");
        }
    }

    private void greet() {
        System.out.println("~ \n Hello I'm the Terminator \n What can I do for you? \n~");
    }

    private void lst() {
        System.out.println("~ \n Here are targets in your kill list: ");
        if (!taskList.isEmpty()) {
            for (int i = 0; i < taskList.size(); i++) {
                int count = i + 1;
                System.out.println(String.format("   %d. ", count) + taskList.get(i).toString());
            }
        }
        System.out.println("\n~ ");
    }

    private void addTodo(String[] inputs) throws DukeException {
        if (inputs.length > 1) {
            System.out.println("~ \n Got it. I've added this task: ");
            ToDos toDo = new ToDos(inputs[1]);
            taskList.add(toDo);
            System.out.println(String.format("   %s \n Now you have %d tasks in the list. \n~",
                    toDo, taskList.size()));
        } else {
            throw new DukeException("~\n ERROR... TODO DESCRIPTION EMPTY. \n PLEASE TRY AGAIN \n~");
        }
    }

    private void addDeadline(String[] inputs) throws DukeException {
        if (inputs.length > 1) {
            String[] stringArray = inputs[1].split("/", 2);
            if (stringArray.length > 1 && stringArray[1].split(" ", 2).length > 1) {
                String day = stringArray[1].split(" ", 2)[1];
                System.out.println("~ \n Got it. I've added this task: ");
                Deadlines deadline = new Deadlines(stringArray[0], day);
                taskList.add(deadline);
                System.out.println(String.format("   %s \n Now you have %d tasks in the list. \n~",
                        deadline, taskList.size()));
            } else {
                throw new DukeException("~\n ERROR... DEADLINE DATE EMPTY. \n PLEASE TRY AGAIN \n~");
            }
        } else {
            throw new DukeException("~\n ERROR... DEADLINE DESCRIPTION EMPTY . \n PLEASE TRY AGAIN \n~");
        }
    }

    private void addEvent(String[] inputs) throws DukeException {
        if (inputs.length > 1) {
            String[] stringArray = inputs[1].split("/", 2);
            if (stringArray.length > 1 && stringArray[1].split(" ", 2).length > 1) {
                String at = stringArray[1].split(" ", 2)[1];
                System.out.println("~ \n Got it. I've added this task: ");
                Events event = new Events(stringArray[0], at);
                taskList.add(event);
                System.out.println(String.format("   %s \n Now you have %d tasks in the list. \n~",
                        event, taskList.size()));
            } else {
                throw new DukeException("~\n ERROR... EVENT DATE EMPTY. \n PLEASE TRY AGAIN \n~");
            }
        } else {
            throw new DukeException("~\n ERROR... EVENT DESCRIPTION EMPTY. \n PLEASE TRY AGAIN \n~");
        }
    }

    private void deleteItem(String[] inputs) throws DukeException {
        if (inputs.length > 1 && Character.isDigit(inputs[1].charAt(0))) {
            int taskNumber = Character.getNumericValue(inputs[1].charAt(0)) - 1;
            if (!taskList.isEmpty() && taskNumber < taskList.size()) {
                Task removedTask = taskList.remove(taskNumber);
                System.out.println( String.format("~ \n Noted. Target Scraped: \n   %s \n " +
                        "Now you have %d tasks in the list. \n~", removedTask.toString(), taskList.size()));
            } else {
                throw new DukeException("~\n ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN \n~");
            }
        } else {
            throw new DukeException("~\n ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n " +
                    "PLEASE TRY AGAIN \n~");
        }
    }

    private static boolean find(String input) {
        for (Input i : Input.values()) {
            if (input.toUpperCase().equals(i.toString())) {
                return true;
            }
        }
        return false;
    }
}
