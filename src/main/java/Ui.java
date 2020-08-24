import java.util.Scanner;

public class Ui {

    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readInputs() {
        String input = sc.nextLine().strip();
        return input;
    }

    public void close() {
        sc.close();
    }

    public static void formatPrint(String str) {
        String divider = "----------------------------\n";
        String newStr = ("   " + divider + str + "\n" + divider).replaceAll("(\r\n|\n)", "\r\n   ");
        System.out.println(newStr);
    }

    public static void printError(String str) {
        String divider = "############################\n";
        String newStr = ("   " + divider + str + "\n" + divider).replaceAll("(\r\n|\n)", "\r\n   ");
        System.out.println(newStr);
    }

    public void printGreetings() {
        String greeting = "Hello I'm Duke, your favourite chatbot! \n\n"
                + "Type 'help' to see the list of command I support. ";
        formatPrint(greeting);
    }

    public void printHelp() {
        String supportedCommands = "I support these commands: \n"
                + "todo: \n"
                + "    add a todo item with a description. \n    format: todo {description} \n"
                + "deadline: \n"
                + "    add a deadline with a description and date. \n"
                + "    format: deadline {description} /by {yyyy-mm-dd} \n"
                + "event: \n"
                + "    add an event with a description, date, start time and end time. \n"
                + "    format: event {description} /at {yyyy-mm-dd} {hh:mm} {hh:mm}\n"
                + "done: \n"
                + "    mark an item as done. \n    format: done {taskNumber} \n"
                + "delete: \n"
                + "    delete an item. \n    format: delete {taskNumber} \n"
                + "list: \n"
                + "    see all the tasks you have now. \n"
                + "bye: \n"
                + "    say goodbye to me :<";
        formatPrint(supportedCommands);
    }

    public void printBye() {
        formatPrint("Bye! Hope to see you again soon! ");
    }

    public void showList(TaskList taskList) {
        formatPrint(taskList.printList());
    }

    public void showAddedTask(Task task, TaskList taskList) {
        formatPrint(String.format("Got it. I've added this task: \n   %s\nNow you have %d task%s in the list.",
                task, taskList.getSize(), taskList.getSize() > 1 ? "s": ""));
    }

    public void showDeletedTask(Task task, TaskList taskList) {
        formatPrint(String.format("Got it. I've deleted this task: \n   %s\nNow you have %d task%s in the list.",
                task, taskList.getSize(), taskList.getSize() > 1 ? "s": ""));
    }

    public void showMarkedAsDone(Task task) {
        formatPrint("Nice! I've marked this task as done: \n   " + task);
    }

}
