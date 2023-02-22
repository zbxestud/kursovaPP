package kn11sp.yaremechko;
import org.apache.logging.log4j.*;

public class MailLogger {
    private static final Logger logger = LogManager.getLogger(MailLogger.class.getName());

    public void logMail(String mailString) {
        logger.error(mailString);
    }
}
