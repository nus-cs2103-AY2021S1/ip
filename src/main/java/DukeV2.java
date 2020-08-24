import java.util.Scanner;

public class DukeV2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        UserInterface UI = new UserInterface();
        while(UI.getStop() == false){
            UI.input(sc.nextLine());
            UI.parse();
        }
        sc.close();
    }
}
