public class Ui {
    public void showLine() {
        System.out.println("________________________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("    Hello, I'm Duke");
        System.out.println("    I can help you keep track of all your tasks! ☆*:.｡.o(≧▽≦)o.｡.:*☆");
        showLine();
        System.out.println("    How to add tasks to the list:");
        System.out.println("    ToDo - type 'todo' followed by the description");
        System.out.println("    Deadline - type 'deadline' followed by the description,");
        System.out.println("    then '/by', then due date in yyyy-MM-dd format");
        System.out.println("    Event - type 'event' followed by the description,");
        System.out.println("    then '/at', then timing in yyyy-MM-dd format");
        System.out.println("    Type 'done' followed by the task number " +
                "and I'll mark it as done");
        System.out.println("    Type 'list' to see the list");
        System.out.println("    Type 'bye' to exit");
        showLine();
    }
}
