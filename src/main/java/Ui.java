import java.util.List;

public class Ui {
    TaskList tasks;

    Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append("hello! i'm duke :-)\n")
                .append("how may i help you?\n")
                .append("--------------------------------------------------------------");
        System.out.println(sb.toString());
    }

    public void showLoadingError() {
        System.out.println("There was an error loading the file");
    }

    public String exit() {
        return "byebye! hope to see you again soon :-)";
    }
}
