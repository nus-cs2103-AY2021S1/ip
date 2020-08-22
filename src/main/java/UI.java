import java.util.Scanner;

public class UI {
    protected Scanner myObj;

    public UI() {
        this.myObj = new Scanner(System.in);
    }

    public String nextInput() {
        return this.myObj.nextLine();
    }

    public static void dividerLine() {
        System.out.println("______________________________________________________");
    }

    public static void intro() {
        //Intro
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
