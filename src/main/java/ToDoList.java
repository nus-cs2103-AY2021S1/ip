import java.util.Scanner;
public class ToDoList{

    private Command action;

    public static void start(){
        Scanner sc = new Scanner(System.in);
        Command command = Command.INIT;
        while (command != Command.BYE){      //when last command was bye
            command = Command.getCommand(sc.nextLine());
            runCommand(command);
        }
        sc.close();
    }

    private static void runCommand(Command command) {
        switch (command) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                break;
            default:
                System.out.println(command.echo());
        }
    }
}