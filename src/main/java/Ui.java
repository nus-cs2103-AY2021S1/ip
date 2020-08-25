import java.util.Scanner;

public class Ui {
    private final String TAB = "  ";
    private final String LINE_BREAKER = TAB + "_________________________________________________________________";
    private final String GREET = TAB + " Hello! I'm Duke" + "\n" + TAB + " What can I do for you?";

    protected final Scanner sc;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    public void showWelcome() {
        System.out.println(LINE_BREAKER);
        System.out.println(GREET);
        System.out.println(LINE_BREAKER);
    }
    
    public void showLine() {
        System.out.println(LINE_BREAKER);
    }
    
    public void showLoadingError() {
        System.out.println(TAB + "Loading fails");
    }
    
    public void showError(String msg) {
        System.out.println(TAB + msg);
    }
    
    public String readCommand() {
        return sc.nextLine();
    }
}
