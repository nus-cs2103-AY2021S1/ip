import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static Scanner input = new Scanner(System.in);
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String line = "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
    static TaskList taskList = new TaskList();


    private static class Task {

        private final String name;
        private boolean done;

        Task(String name) {
            this.name = name;
            this.done = false;
        }

        public String getStatusIcon() {
            return (done ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public void complete() {
            this.done = true;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", this.getStatusIcon(), this.name);
        }

    }


    private static class TaskList extends ArrayList<Task> {

        public void printList() {
            printWithLines(this.toString());
        }

        public void completeTask(int i) {
            String prefix = "Roger roger! I'm gonna mark this task as done:\n";
            Task task = super.get(i);
            task.complete();
            printWithLines(String.format("  %s%s\n", prefix, task));
        }

        public void addTask(Task newTask) {
            super.add(newTask);
            printWithLines(String.format("added: %s\n", newTask));
        }

        @Override
        public String toString() {
            StringBuilder list = new StringBuilder();
            StringBuilder prefix = new StringBuilder("Here are the tasks in your list:\n");
            int l = super.size();
            for (int i = 0; i < l; i++) {
                list.append(i + 1).append(".").append(super.get(i).toString()).append("\n");
            }
            return prefix.append(list).toString();
        }
    }


    public static void main(String[] args) {
        printWithLines("Hello! My name is Duketh Puketh III, but you can call me\n" + logo +
                "\n How may I help you today? :)\n");
        processInput();
        printWithLines("Bye! I'll see you again next time!\n");
    }

    private static void processInput() {

        String nextInput = input.nextLine();
        String[] inputParts = nextInput.split(" ", 2);
        String inputPrefix = inputParts[0];
        String inputSuffix = inputParts.length == 1 ? "" : inputParts[1];

        while (!nextInput.equals("bye")) {

            switch (inputPrefix) {
                case "list":
                    taskList.printList();
                    break;
                case "done":
                    taskList.completeTask(Integer.parseInt(inputSuffix) - 1);
                    break;
                default:
                    taskList.addTask(new Task(nextInput));

            }
            nextInput = input.nextLine();
            inputParts = nextInput.split(" ", 2);
            inputPrefix = inputParts[0];
            inputSuffix = inputParts.length == 1 ? "" : inputParts[1];
        }

    }

    private static void printWithLines(String output) {
        System.out.println(line + "\n" + output + line);
    }

}