public class DukeException {
    protected static void classCastException() {
        System.out.println(new Formating<String>(Exceptions.CLASSCASTEXCEPTION.toString()));
    }

    protected static void numberFormatException() {
        System.out.println(new Formating<String>(Exceptions.NUMBERFORMATEXCEPTION.toString()));
    }

    protected static void numberExcessException() {
        System.out.println(new Formating<String>(Exceptions.NUMBEREXCESSEXCEPTION.toString()));
    }

    protected static void emptyTaskException() {
        System.out.println(new Formating<String>(Exceptions.EMPTYTASKEXCEPTION.toString()));
    }

    protected static void inputFormatException() {
        System.out.println(new Formating<String>(Exceptions.INPUTFORMATEXCEPTION.toString()));
    }

    protected static void timeMissingException() {
        System.out.println(new Formating<String>(Exceptions.NOTIMEEXCEPTION.toString()));
    }
}
