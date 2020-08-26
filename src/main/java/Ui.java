import java.util.Scanner;

public class Ui {

    public void greet() {
        String greeting = "Hello mah dud, itza handsome robo speakin\n" +
                "What duh hell du yu wan?";
        System.out.println(greeting);
    }

    public void farewell() {
        String farewell = "Never come back,\n" +
                "dun wanna see yu ever agin";
        System.out.println(farewell);
    }

//    public String readCommand() {
//        Scanner sc = new Scanner(System.in);
//        String nextLine = sc.nextLine();
//        sc.close();
//        return nextLine;
//    }

    public void showLine() {
        System.out.println("________________________________________");
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public void showLoadingError(String e) {
        System.out.println(e);
    }
}
