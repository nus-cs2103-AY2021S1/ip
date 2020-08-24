public class Ui {

    Ui() { }

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }
    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i ++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }
}
