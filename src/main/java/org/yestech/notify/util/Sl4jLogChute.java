package org.yestech.notify.util;

import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author A.J. Wright
 */
public class Sl4jLogChute implements LogChute {

    /**
     * Property key for specifying the name for the log instance
     */
    public static final String LOGCHUTE_SLF4J_LOG_NAME =
            "runtime.log.logsystem.slf4j.logging.name";

    /**
     * Default name for the slf4j-logging instance
     */
    public static final String DEFAULT_LOG_NAME = "org.apache.velocity";

    protected Logger log;


    @Override
    public void init(RuntimeServices rs) throws Exception {
        String name =
                (String) rs.getProperty(LOGCHUTE_SLF4J_LOG_NAME);

        if (name == null) {
            name = DEFAULT_LOG_NAME;
        }
        log = LoggerFactory.getLogger(name);
        log(LogChute.DEBUG_ID, "SLF4JLogLogChute name is '" + name + "'");
    }


    /**
     * Send a log message from Velocity.
     */
    public void log(int level, String message) {
        switch (level) {
            case LogChute.WARN_ID:
                log.warn(message);
                break;
            case LogChute.INFO_ID:
                log.info(message);
                break;
            case LogChute.TRACE_ID:
                log.trace(message);
                break;
            case LogChute.ERROR_ID:
                log.error(message);
                break;
            case LogChute.DEBUG_ID:
            default:
                log.debug(message);
                break;
        }
    }

    /**
     * Send a log message from Velocity with an error.
     */
    public void log(int level, String message, Throwable t) {
        switch (level) {
            case LogChute.WARN_ID:
                log.warn(message, t);
                break;
            case LogChute.INFO_ID:
                log.info(message, t);
                break;
            case LogChute.TRACE_ID:
                log.trace(message, t);
                break;
            case LogChute.ERROR_ID:
                log.error(message, t);
                break;
            case LogChute.DEBUG_ID:
            default:
                log.debug(message, t);
                break;
        }
    }

    /**
     * Checks whether the specified log level is enabled.
     */
    public boolean isLevelEnabled(int level) {
        switch (level) {
            case LogChute.DEBUG_ID:
                return log.isDebugEnabled();
            case LogChute.INFO_ID:
                return log.isInfoEnabled();
            case LogChute.TRACE_ID:
                return log.isTraceEnabled();
            case LogChute.WARN_ID:
                return log.isWarnEnabled();
            case LogChute.ERROR_ID:
                return log.isErrorEnabled();
            default:
                return true;
        }
    }
}
