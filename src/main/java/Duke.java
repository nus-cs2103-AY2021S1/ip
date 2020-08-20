package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class Task {
        private String task;
        private boolean done;

        Task(String task) {
            this.task = task;
            this.done = false;
        }

        public void doTask() {
            this.done = true;
        }

        @Override
        public String toString() {
            String check = done ? "✓" : "✗";
            return String.format("[%s] %s", check, task);
        }
    }
    public static void main(String[] args) {
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storedItems = new ArrayList<>();
        String border = "____________________________________________________________";

        String logo = " ____        ____  \n"
                + "|  _ \\  ___ |  _ \\\n"
                + "| | | |/ _ \\| | | |\n"
                + "| |_| || __/| |_| |\n"
                + "|____/ \\___||____/\n";
        System.out.println("Hello I am\n" + logo + "\n" + "Feed me some input! :3\n");

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
                case "exit":
                    System.out.println(border + "\n" + "bb cya again!\n" + border);
                    check = false;
                    break;
                default:
                    storedItems.add(new Task(input));
                    System.out.println(border + "\n" + "*Gobble gobble* " + input + " has been eated OwO\n" + border);
            }
        }
    }
}
