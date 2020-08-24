import java.io.IOException;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String VISCOUNT_LOGO =
            "        _  _____  _____                  _    \n" +
                    "       (_)/ ____|/ ____|                | |   \n" +
                    " __   ___| (___ | |     ___  _   _ _ __ | |_  \n" +
                    " \\ \\ / / |\\___ \\| |    / _ \\| | | | '_ \\| __| \n" +
                    "  \\ V /| |____) | |___| (_) | |_| | | | | |_  \n" +
                    "   \\_/ |_|_____/ \\_____\\___/ \\__,_|_| |_|\\__|";
    private static final String HORIZONTAL_LINE = "__________________________________________________";
    
    private Scanner scanner;
    
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    
    public void showWelcome() {
        System.out.println(Ui.VISCOUNT_LOGO);
        speak("Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?");
    }
    
    public void showExit() {
        speak("Farewell my friend, I hope to see you again!");
    }
    
    public void showErrorExit() {
        System.out.println(">> Viscount shutting down.");
    }
    
    public String readInput() {
        return scanner.nextLine();
    }
    
    public void showList(List<Task> tasks, String modifier, String dateString) {
        String finalDateString = dateString.isEmpty()
                ? dateString
                : ("occurring " + (dateString.equals("today")
                        ? dateString
                        : "on " + dateString) + " ");
                
        
        speak(String.format("Here are the %ss %sin your list:\n%s",
                modifier.isEmpty() ? "task" : modifier,
                finalDateString,
                convertTaskListToString(tasks)));
    }
    
    public void showAdd(Task task, int tasksSize) {
        speak(String.format("Very well. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task.toString(),
                tasksSize));
    }
    
    public void showDone(Task task) {
        speak(String.format("Very good! I have marked this task as done:\n%s", task.toString()));
    }
    
    public void showDelete(Task task, int tasksSize) {
        speak(String.format("Very well. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                task.toString(),
                tasksSize));
    }
    
    public void showError(String errorMessage) {
        speak(errorMessage);
    }
    
    private void speak(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private String convertTaskListToString(List<Task> tasks) {
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result += (i == tasks.size() - 1)
                    ? String.format("%d.%s", i + 1, tasks.get(i).toString())
                    : String.format("%d.%s\n", i + 1, tasks.get(i).toString());
        }

        return result;
    }
}
