package main.java;

import java.util.Scanner;

public class Ui {
    static final String LINE = "     ___________________________________________________________________________\n";
    static final String DOUBLE_TAB = "      ";

    public Ui() {}

    public String readCommand() {
        enterCommand();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();;

        return command;
    }

    public  void messageTemplate(String input) {
        System.out.print(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

    public void exceptionTemplate(Exception exception) {
        System.out.print(LINE);
        System.out.println(formatExceptionMessage(exception.toString()));
        System.out.println(LINE);
    }

    public String formatCommandList(String command, String format) {
        String indentation = "%-20s%s%n" ;
        return formatMessage(String.format(indentation,command,format));
    }

    public void commandList(){
        messageTemplate( formatCommandList("COMMAND", "FORMAT")
                + formatCommandList("todo","todo <TASK_NAME>")
                + formatCommandList("event","event <EVENT_NAME> /at <yyyy-MM-dd> <HH:mm>")
                + formatCommandList("deadline", "deadline <DEADLINE_NAME> /by <yyyy-MM-dd> <HH:mm>")
                + formatCommandList("delete", "delete <TASK_NUMBER>")
                + formatCommandList("done", "done <TASK_NUMBER>")
                + formatCommandList("show after", "show after <yyyy-MM-dd>")
                + formatCommandList("show before", "show before <yyyy-MM-dd>")
        );
    }

    public String formatMessage(String input) {
        return DOUBLE_TAB + input;
    }

    public static String formatExceptionMessage(String message) {
        return DOUBLE_TAB + message;
    }

    public TaskList execute(TaskList tasks, Ui ui,Storage storage) {

        return tasks;
    }

    public void greet() {
        messageTemplate(formatMessage("Hello! I'm Rich.\n")
                + formatMessage( "What can I do for you?"));
    }

    public void enterCommand() {
        System.out.print("Enter command here: ");
    }

    public void taskMessage(Task task, int size) {
        messageTemplate(formatMessage("Got it. I've added this task :\n")
                + formatMessage(task +"\n")
                + formatMessage("Now you have " + size + " tasks in the list"));
    }




}
