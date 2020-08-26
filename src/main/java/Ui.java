import java.util.List;

public class Ui {
    TaskList tasks;

    Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public String showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append("hello! i'm duke :-)\n")
                .append("how may i help you?\n")
                .append("--------------------------------------------------------------");
        return sb.toString();
    }

    public void showLoadingError() {
        System.out.println("There was an error loading the file");
    }

    public String exit() {
        return "byebye! hope to see you again soon :-)";
    }

    public void responder() {
        String input = ReadIn.readIn();
        String firstWord = input.split(" ")[0];

        while (true) {
            StringBuilder sb = new StringBuilder();
            sb.append("--------------------------------------------------------------\n");

            try {
                if (firstWord.equals("bye")) {
                    sb.append(this.exit());
                    sb.append("\n--------------------------------------------------------------");
                    System.out.println(sb.toString());
                    break;
                } else if (firstWord.equals("list")) {
                    sb.append(this.tasks.returnList());
                } else if (firstWord.equals("done")) {
                    int i = Integer.valueOf(input.substring(5));
                    sb.append(this.tasks.done(i));
                } else if (firstWord.equals("delete")) {
                    int i = Integer.valueOf(input.substring(7));
                    sb.append(this.tasks.delete(i));
                } else if (firstWord.equals("todo")) {
                    sb.append(new ToDo(input));
                } else if (firstWord.equals("deadline")) {
                    sb.append(new Deadline(input));
                } else if (firstWord.equals("event")) {
                    sb.append(new Event(input));
                } else {
                    throw new DukeException("oops! im sorry, but i do not know what that means :-(");
                }
            } catch (EmptyDescriptionException e) {
                sb.append(e.getMessage());
            } catch (DukeException e) {
                sb.append(e.getMessage());
            }
            sb.append("\n--------------------------------------------------------------");
            System.out.println(sb.toString());
            input = ReadIn.readIn();
            firstWord = input.split(" ")[0];
        }
    }
}
