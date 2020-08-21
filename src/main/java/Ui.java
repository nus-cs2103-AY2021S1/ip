import java.util.List;

public class Ui {


    public Ui() {
    }

    public void displayThis(String s) {
        displayLines();
        displayString(s);
        displayLines();
    }


    public void displayList(List<Task> toDoList) {
        displayLines();
        displayString("Here are the tasks in your list:");
        for (int i = 0; i < toDoList.size(); i++) {
            displayString((i + 1) + ". " + toDoList.get(i));
        }
        displayLines();
    }


    private void displayString(String s) {
        System.out.println("    " + s);
    }


    private void displayLines() {
        System.out.println("\n    ___________________________________________________");
    }


}