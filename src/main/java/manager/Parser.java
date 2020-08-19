package main.java.manager;

import main.java.exceptions.InvalidCommandException;
import main.java.exceptions.InvalidNumberException;

import java.util.Scanner;

public class Parser {

    TaskList taskList = new TaskList();

    public void handleUserInput() {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            try {

                if (input.equals("list")) {
                    this.taskList.listTasks();

                } else if (isNumberedCommand(input)) {
                    String[] words = input.split(" ");

                    if (words[0].equals("done")) {
                        int index = Integer.parseInt(words[1]) - 1;
                        this.taskList.markTaskAsDone(index);

                    } else if (words[0].equals("delete")) {
                        int index = Integer.parseInt(words[1]) - 1;
                        this.taskList.deleteTask(index);

                    } else {
                        throw new InvalidCommandException("Command is invalid. Try again?");
                    }

                } else {
                    this.taskList.addTask(input);
                }

            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }

            if (sc.hasNextLine()) {
                input = sc.nextLine();
            } else {
                break;
            }
        }

        sc.close();
    }

    private boolean isNumberedCommand(String input) throws InvalidNumberException {

        String[] words = input.split(" ");
        boolean checkBackNumber = words.length == 2 &&
                words[1].chars().allMatch(Character::isDigit);

        if (checkBackNumber) {
            int index = Integer.parseInt(words[1]) - 1;
            boolean isValidNumber = index < this.taskList.getNumberOfTasks();

            if (!isValidNumber) {
                throw new InvalidNumberException("The number entered is invalid. " +
                        "You have " + this.taskList.getNumberOfTasks() + " tasks in your list.");
            }
            return true;

        } else {
            return false;
        }
    }

}
