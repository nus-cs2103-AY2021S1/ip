import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "    _____                 ________  .__\n"
                + "   /     \\_______  ______ \\______ \\ |__| ____   ____\n"
                + "  /  \\ /  \\_  __ \\/  ___/  |    |  \\|  |/    \\ /  _ \\ \n"
                + " /    Y    \\  | \\/\\___ \\   |    |   \\  |   |  (  <_> ) \n"
                + " \\____|__  /__|  /____  > /_______  /__|___|  /\\____/ \n"
                + "         \\/           \\/          \\/        \\/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome!");
        System.out.println("What can Mrs Dino do for you?");
//        Echo.initialise();
        TodoList.initialise();
    }
}
