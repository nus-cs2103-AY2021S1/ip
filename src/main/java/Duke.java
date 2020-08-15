import java.util.Calendar;

public class Duke {
    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);

        String logo =
                "         ,---._                                                            \n" +
                "       .-- -.' \\    ,---,       ,-.----.                 ,---,  .--.--.    \n" +
                "       |    |   :  '  .' \\      \\    /  \\        ,---.,`--.' | /  /    '.  \n" +
                "       :    ;   | /  ;    '.    ;   :    \\      /__./||   :  :|  :  /`. /  \n" +
                "       :        |:  :       \\   |   | .\\ : ,---.;  ; |:   |  ';  |  |--`   \n" +
                "       |    :   ::  |   /\\   \\  .   : |: |/___/ \\  | ||   :  ||  :  ;_     \n" +
                "       :         |  :  ' ;.   : |   |  \\ :\\   ;  \\ ' |'   '  ; \\  \\    `.  \n" +
                "       |    ;   ||  |  ;/  \\   \\|   : .  / \\   \\  \\: ||   |  |  `----.   \\ \n" +
                "   ___ l         '  :  | \\  \\ ,';   | |  \\  ;   \\  ' .'   :  ;  __ \\  \\  | \n" +
                " /    /\\    J   :|  |  '  '--'  |   | ;\\  \\  \\   \\   '|   |  ' /  /`--'  / \n" +
                "/  ../  `..-    ,|  :  :        :   ' | \\.'   \\   `  ;'   :  |'--'.     /  \n" +
                "\\    \\         ; |  | ,'        :   : :-'      :   \\ |;   |.'   `--'---'   \n" +
                " \\    \\      ,'  `--''          |   |.'         '---\" '---'                \n" +
                "  \"---....--'                   `---'                                      \n";
        if (hour < 12) {
            System.out.println("Good morning boss! This is\n" + logo);
        } else if (hour < 20) {
            System.out.println("Good afternoon boss! This is\n" + logo);
        } else {
            System.out.println("Good evening boss! This is\n" + logo);
        }
    }
}
