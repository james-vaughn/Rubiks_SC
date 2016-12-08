/**
Logger singleton which can be used to log error messages and terminate the program;
Global error handler
 */
public class ErrorLogger {

    /**
    Singleton instance
     */
    private static final ErrorLogger _errorLogger = new ErrorLogger();

    /**
    Private constructor for the ErrorLogger singleton
     */
    private ErrorLogger() {

    }

    /**
    Getter for the singleton instance
    @return the only instance of ErrorLogger
     */
    public static ErrorLogger getInstance(){
        return _errorLogger;
    }

    /**
    Writes a message to stderr and then error out with a runtime exception
    @param errorMessage Message to log to stderr
    */
    public void die(String errorMessage) {
        System.err.println(errorMessage);
        throw new RuntimeException();
    }
}
