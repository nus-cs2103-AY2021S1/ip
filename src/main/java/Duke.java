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

        Task(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

    }


    private static class TaskList extends ArrayList<Task> {

        public void printList() {
            printWithLines(this.toString());
        }

        @Override
        public String toString() {
            StringBuilder list = new StringBuilder();
            int l = super.size();
            for (int i = 0; i < l; i++) {
                list.append(i + 1).append(". ").append(super.get(i).toString()).append("\n");
            }
            return list.toString();
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
        while (!nextInput.equals("bye")) {

            switch (nextInput) {
                case "list":
                    taskList.printList();
                    break;
                default:
                    Task newTask = new Task(nextInput);
                    taskList.add(newTask);
                    printWithLines("added: " + newTask.toString() + "\n");
            }
            nextInput = input.nextLine();
        }

    }

    private static void printWithLines(String output) {
        System.out.println(line + "\n" + output + line);
    }

}