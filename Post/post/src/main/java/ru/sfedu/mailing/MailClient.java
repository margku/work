package ru.sfedu.mailing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailClient {
    private static Logger log = LogManager.getLogger(MailClient.class);

    MailClient() {
        log.debug("mail[0]: starting application.........");
    }

    private void logBasicSystemInfo() {
        log.info("Launching the application...");
        log.info(
                "Operating System: " + System.getProperty("os.name") + " "
                        + System.getProperty("os.version")
        );
        log.info("JRE: " + System.getProperty("java.version"));
        log.info("Java Launched From: " + System.getProperty("java.home"));
        log.info("Class Path: " + System.getProperty("java.class.path"));
        log.info("Library Path: " + System.getProperty("java.library.path"));
        log.info("User Home Directory: " + System.getProperty("user.home"));
        log.info("User Working Directory: " + System.getProperty("user.dir"));
        log.info("Test INFO logging.");
    }
}
