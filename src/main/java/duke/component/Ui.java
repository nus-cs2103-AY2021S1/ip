package duke.component;

import java.util.Scanner;

public class Ui {
    private static final String divider = "\t____________________________________________________________\n";
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLoadingError(DukeException e) {
        giveResponse(e.getMessage());
    }

    //print out the response
    public void giveResponse(String response) {
        System.out.println(divider + "\t " + response + "\n" + divider);
    }

    // display the task list
    public void displayList(TaskList taskList){
        String list = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.size(); i++) {
            list += "\t " + i + ". " + taskList.get(i - 1) + "\n";
        }

        //remove the extra "\n"
        if(!list.isEmpty()) {
            list = list.substring(0, list.length() - 1);
        }

        giveResponse(list);
    }

    //get the next input
    public String getInput() {
        return sc.nextLine();
    }

    public void greeting() {
        String greeting = "Hello! I'm Duke\n" +
                "\t What can I do for you?";
        giveResponse(greeting);
    }

}
