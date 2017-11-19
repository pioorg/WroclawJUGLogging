package org.przybyl.logs.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Piotr Przybył (piotr@przybyl.org).
 */
public class LogProducer {

    private static final Logger logger = LoggerFactory.getLogger(LogProducer.class);

    public static void main(String[] args) {

        logger.info("Starting the app.");

        new ActualWorker().doHeavyStuff();
    }
}

class ActualWorker {
    private static final Logger logger = LoggerFactory.getLogger(LogProducer.class);
    private static SecureRandom random = new SecureRandom();

    void doHeavyStuff() {
        while (!Thread.interrupted()) {
            try {
                doAndLogSomeStuff();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.info("Interrupted during sleep, going to quit.");
                break;
            } catch (Throwable t) {
                logger.error("Should not happen, but my stupid leader insists on this log. Anyway.", t);
                System.exit(42);
            }
        }
    }

    private void doAndLogSomeStuff() {
        int nextActionIndex = random.nextInt(50) / 10;
        switch (nextActionIndex) {
            case 0:
                logger.error("Things went out of control.");
                break;
            case 1:
                logger.warn("Didn't I warn you? {} {} {}", ene(), due(), like());
                break;
            case 2:
                logger.info("For your information: do it as ASAP as possible!");
                break;
            case 3:
                logger.debug("Let's see what's going on...");
                break;
            case 4:
                logger.trace("This is micro management.");
                break;
            default:
                throw new IllegalArgumentException("Action code outside range!");
        }
    }

    private StringBuilder ene() {
        return new StringBuilder("ene");
    }

    private Object due() {
        return new Object() {
            @Override
            public String toString() {
                return "due";
            }
        };
    }

    private List<Object> like() {
        return Arrays.asList("like", "fake");
    }
}
