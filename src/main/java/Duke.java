package main.java;

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
            switch (command) {
                case "list":
                    System.out.println(border);
                    for (int i = 0; i < storedItems.size(); i ++) {
                        System.out.println(String.format("%d. %s", i + 1, storedItems.get(i)));
                    }
                    System.out.println(border);
                    break;
                case "done":
                    int taskNum = Integer.parseInt(arr[1]);
                    if (taskNum > 0 && taskNum <= storedItems.size()) {
                        Task task = storedItems.get(taskNum - 1);
                        task.doTask();
                        System.out.println(border + "\n" + "Nice I've digested the following:\n"
                                + task + "\n" + "Now I'm hungry again! FEED ME MORE :3\n" + border);

                    } else {
                        System.out.println("done with what? please specify valid task number :)");
                    }
                    break;
                case "uwu":
                    System.out.println(border + "\n" + "owo\n" + border);
                    break;
                case "owo":
                    System.out.println(border + "\n" + "uwu\n" + border);
                    break;
                case "exit":
                    System.out.println(border + "\n" + "bb cya again!\n" + border);
                    check = false;
                    break;
                case "todo":
                    Task newTask = new Todo(arr[1]);
                    storedItems.add(newTask);
                    System.out.println(border + "\n"
                            + "*Gobble gobble* the following has been eated OwO:\n"
                            + newTask.toString() + "\n"
                            + "I now have " + storedItems.size() + " thing(s) in my belly\n"
                            + border);
                    break;
                case "deadline":
                    String temp1[] = arr[1].split(" /by ", 2);
                    Task newTask1 = new Deadline(temp1[0], "best consumed by: " + temp1[1]);
                    storedItems.add(newTask1);
                    System.out.println(border + "\n"
                            + "*Gobble gobble* the following has been eated OwO:\n"
                            + newTask1.toString() + "\n"
                            + "I now have " + storedItems.size() + " thing(s) in my belly\n"
                            + border);
                    break;
                case "event":
                    String temp2[] = arr[1].split(" /at ", 2);
                    Task newTask2 = new Event(temp2[0], "bought at: " + temp2[1]);
                    storedItems.add(newTask2);
                    System.out.println(border + "\n"
                            + "*Gobble gobble* the following has been eated OwO:\n"
                            + newTask2.toString() + "\n"
                            + "I now have " + storedItems.size() + " thing(s) in my belly\n"
                            + border);
                    break;
                default:
            }
        }
    }
}
