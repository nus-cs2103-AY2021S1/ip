package main.java;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storedItems = new ArrayList<>();
        String border = "____________________________________________________________";

        String logo = " ____        ____\n"
                + "|  _ \\  ___ |  _ \\\n"
                + "| | | |/ _ \\| | | |\n"
                + "| |_| || __/| |_| |\n"
                + "|____/ \\___||____/\n";
        System.out.println("Hello I am\n" + logo + "\n" + "Feed me some stuff! :3\n");

        while (check) {
            String input = sc.nextLine();
            String arr[] = input.split(" ", 2);
            String command = arr[0];
            System.out.println(command);
            try {
                if (command.equals("list")) {
                    System.out.println(border);
                    for (int i = 0; i < storedItems.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, storedItems.get(i)));
                    }
                    System.out.println(border);
                } else if (command.equals("done")) {
                    if (arr.length < 2) {
                        throw new DukeException("The description of a done cannot be empty!");
                    }
                    int taskNum = Integer.parseInt(arr[1]);
                    if (taskNum > 0 && taskNum <= storedItems.size()) {
                        Task task = storedItems.get(taskNum - 1);
                        task.doTask();
                        System.out.println(border + "\n" + "Nice I've digested the following:\n"
                                + task + "\n" + "Now I'm hungry again! FEED ME MORE :3\n" + border);
                    } else {
                        throw new DukeException("What are you done with? Gotta specify a valid task number!");
                    }
                } else if (command.equals("delete")) {
                    if (arr.length < 2) {
                        throw new DukeException("The description of a delete cannot be empty!");
                    }
                    int deleteNum = Integer.parseInt(arr[1]);
                    if (deleteNum > 0 && deleteNum <= storedItems.size()) {
                        Task task = storedItems.get(deleteNum - 1);
                        storedItems.remove((deleteNum - 1));
                        System.out.println(border + "\n" + "Nooo you can't take away what you've already given me...\n"
                                + "Okay fine. It's in my stomach tho... ASDFGUUVHHH!!\n"
                                + "The following has been removed:\n"
                                + task + "\n" + "Now I'm feeling sick :( there's " + storedItems.size()
                                + " thing(s) in my belly now...HUNGRY!\n"
                                + border);
                    } else {
                        throw new DukeException("What are you deleting? Gotta specify a valid task number!");
                    }
                } else if (command.equals("uwu")) {
                    System.out.println(border + "\n" + "owo\n" + border);
                } else if (command.equals("owo")) {
                    System.out.println(border + "\n" + "uwu\n" + border);
                } else if (command.equals("exit")) {
                    System.out.println(border + "\n" + "bb cya again!\n" + border);
                    check = false;
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    if (arr.length < 2) {
                        throw new DukeException("The description of a task cannot be empty!");
                    }
                    Task newTask;
                    if (!command.equals("todo")) {
                        if (!arr[1].contains(" /by ") && !arr[1].contains(" /at ")) {
                            throw new DukeException("The date has to be specified!");
                        }
                        if (command.equals("deadline")) {
                            String temp[] = arr[1].split(" /by ", 2);
                            newTask = new Deadline(temp[0], temp[1].replace('/', '-'));
                        } else {
                            String temp[] = arr[1].split(" /at ", 2);
                            newTask = new Event(temp[0],  temp[1].replace('/', '-'));
                        }
                    } else {
                        newTask = new Todo(arr[1]);
                    }
                    storedItems.add(newTask);
                    System.out.println(border + "\n"
                            + "*Gobble gobble* the following has been eated OwO:\n"
                            + newTask.toString() + "\n"
                            + "I now have " + storedItems.size() + " thing(s) in my belly\n"
                            + border);
                } else {
                        throw new DukeException("I don't understand what you're saying HMM...");
                }
            } catch (DukeException e) {
                System.out.println(border + "\n" + e + "\n" + border);
            } catch (DateTimeParseException e) {
                System.out.println("☹ OOPSIE!! " + "Time format should be yyyy/mm/dd or yyyy-mm-dd.");
            }
        }
    }
}
