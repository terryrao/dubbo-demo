package xyz.raowei.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ${DESCRIPTION}
 * create: 2016-06-30 14:44
 *
 * @author admin
 */
public class SimpleLogger {

    private Logger logger;


    public static SimpleLogger getLogger() {
        return new SimpleLogger();
    }

    public static SimpleLogger getLogger(Class<?> clazz) {
        return new SimpleLogger(clazz);
    }

    private SimpleLogger() {
        logger = null;
        logger = LoggerFactory.getLogger("Logger");
    }

    private SimpleLogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }


    public void error(String message) {
        logger.error(message);
    }

    public void error(String message, Throwable t) {
        logger.error(message, t);
    }

    public void error(String format, Object arg1, Object arg2) {
        logger.error(format, arg1, arg2);
    }

    public void error(String format, Object... args) {
        logger.error(format, args);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void debug(String message, Throwable t) {
        logger.debug(message, t);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void warn(String message, Throwable t) {
        logger.warn(message, t);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void info(String message, Throwable t) {
        logger.info(message, t);
    }

    public void info(String format, Object arg) {
        logger.info(format, arg);
    }

    public void info(String format, Object arg1, Object arg2) {
        logger.info(format, arg1, arg2);
    }

    public void info(String format, Object[] argArray) {
        logger.info(format, argArray);
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }


}
