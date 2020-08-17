public class Salutations {

    enum type {
        WELCOME, GOODBYE
    }

    type typeOfMessage;

    public Salutations(type typeOfMessage) {
        this.typeOfMessage = typeOfMessage;
    }

    public void printMessage() {
        switch(typeOfMessage) {
            case WELCOME:
                System.out.println("  Hello! I'm Duke\n  What can I do for you?");
                break;
            case GOODBYE:
                System.out.println("  Bye!!! Hope to see you again real soon.");
                break;
        }

    }


}
