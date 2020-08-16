package main.java;


import java.util.ArrayList;
import java.util.Scanner;

public class Commands {
    private boolean shouldBreak = true;
    private ArrayList<Task> taskList = new ArrayList<>();

    private final String INPUT_LIST = "list";
    private final String INPUT_BLAH = "blah";
    private final String INPUT_BYE = "bye";
    private final String INPUT_DONE = "done";
    private final String INPUT_TODO = "todo";
    private final String INPUT_DEADLINES = "deadline";
    private final String INPUT_EVENTS = "event";

    public void start() {
        this.greet();

        Scanner scanner = new Scanner(System.in);

        String inputs = scanner.nextLine().trim();

        while (shouldBreak) {
            String[] inputArray = inputs.split(" ", 2);
            switch (inputArray[0].toLowerCase()) {
            case INPUT_LIST:
                this.lst();
                inputs = scanner.nextLine().trim();
                break;
            case INPUT_BLAH:
                this.blah();
                inputs = scanner.nextLine().trim();
                break;
            case INPUT_BYE:
                System.out.println("~ \n I will be back \n~ ");
                shouldBreak = !shouldBreak;
                break;
            case INPUT_DONE:
                markDone(inputs.charAt(5));
                inputs = scanner.nextLine().trim();
                break;
            case INPUT_TODO:
                addTodo(inputArray[1]);
                inputs = scanner.nextLine().trim();
                break;
            case INPUT_DEADLINES:
                addDeadline(inputArray[1]);
                inputs = scanner.nextLine().trim();
                break;
            case INPUT_EVENTS:
                addEvent(inputArray[1]);
                inputs = scanner.nextLine().trim();
                break;
            default:
                taskList.add(new Task(inputs));
                System.out.println("~ \n added: " + inputs + "\n~ ");
                inputs = scanner.nextLine().trim();
            }
        }
    }

    private void markDone(char input) {
        if (Character.isDigit(input)) {
            int taskNumber = Character.getNumericValue(input) - 1;
            if (!taskList.isEmpty() && taskNumber < taskList.size()) {
                taskList.get(taskNumber).doneTask();
                System.out.println("~ \n Nice! Target Eliminated: \n   "
                        + taskList.get(taskNumber).toString() + "\n~");
            } else {
                System.out.println("Please choose a task to mark as done, with \"done <task number>\"");
            }
        } else {
            System.out.println("Please input a valid integer");
        }
    }

    public void greet() {
        System.out.println("~ \n Hello I'm the Terminator \n What can I do for you? \n~");
    }

    public void lst() {
        System.out.println("~ \n Here are targets in your kill list: ");
        if (!taskList.isEmpty()) {
            for (int i = 0; i < taskList.size(); i++) {
                int count = i + 1;
                System.out.println(String.format("   %d. ", count) + taskList.get(i).toString());
            }
        }
        System.out.println("\n~ ");
    }

    public void blah() {
        System.out.println("~ \n blah \n~");
    }

    public void addTodo(String input) {
        System.out.println("~ \n Got it. I've added this task: ");
        ToDos toDo = new ToDos(input);
        taskList.add(toDo);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. \n~",
                toDo, taskList.size()));
    }

    public void addDeadline(String input) {
        System.out.println("~ \n Got it. I've added this task: ");
        String[] stringArray = input.split("/", 2);
        String day = stringArray[1].split(" ", 2)[1];
        Deadlines deadline = new Deadlines(stringArray[0], day);
        taskList.add(deadline);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. \n~",
                deadline, taskList.size()));
    }

    public void addEvent(String input) {
        System.out.println("~ \n Got it. I've added this task: ");
        String[] stringArray = input.split("/", 2);
        String at = stringArray[1].split(" ", 2)[1];
        Events event = new Events(stringArray[0], at);
        taskList.add(event);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. \n~",
                event, taskList.size()));
    }
}
