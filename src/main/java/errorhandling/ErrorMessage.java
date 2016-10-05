
package errorhandling;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorMessage {
private int code;
private String message;

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
        
//        if (debug)
//        {
//            StringWriter sw = new StringWriter();
//            ex.printStackTrace(new PrintWriter(sw));
//            this.stackTrace = sw.toString();
//        }
        
    }


}
