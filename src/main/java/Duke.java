/*
 * Duke is a retired old uncle who likes to speak in Singlish.
 */

public class Duke {
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Eh yo, I am Duke! Some kids call me 'Lao Duke' (which means old Duke).\n" +
                "I like to speak Singlish, come talk to me now lah!";
        System.out.println(logo);
        System.out.println(intro);
    }

    public static void exit() {
        String bye = "Alamak why so fast end the convo with me? Ok lah I also need to sleep now Zzzz.\n" +
                "Goodbye!";
        System.out.println(bye);
    }
    
    public static void main(String[] args) {
       // System.out.println("Hello from\n" + logo);
    }
}
