public class Ui {
    public void printReply(String text) {
        String line = "\t____________________________________________________________________________________";
        System.out.println(line + "\n\t\t" + text.replaceAll("\\n", "\n\t\t") + "\n" + line);
    }

    public void greet() {
        printReply("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        printReply("Bye. Hope to see you again soon!");
    }
}