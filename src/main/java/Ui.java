public class Ui {
    String curr;
    int currNum = 0;
    public void showLoadingError(){
        System.out.println("File absent!");
    }
    public void showWelcome(){
        System.out.println("  ____________________________________________________________\n" +
                "  Hello! I'm Duke\n" + "  What can I do for you?");
    }
    public void showLine(){
        System.out.println("  ____________________________________________________________\n");
    }
    public String readCommand(){
        return this.curr;
    }
    public void curr(){
        System.out.println(this.curr);
    }
    public void showError(String s){
        System.out.println(s);
    }
}
