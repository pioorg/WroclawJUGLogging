package org.przybyl.logs.producer;

import java.security.SecureRandom;


/**
 * Created by Piotr Przybył (piotr@przybyl.org).
 */
public class LogProducer {

    public static void main(String[] args) {
        System.out.println("INFO: Starting the app.");

        new ActualWorker().doHeavyStuff();
    }
}

class ActualWorker {
    private static SecureRandom random = new SecureRandom();

    void doHeavyStuff() {
        while (!Thread.interrupted()) {
            try {
                doAndLogSomeStuff();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("INFO: Interrupted during sleep, going to quit.");
                break;
            } catch (Throwable t) {
                System.out.println("ERROR: Should not happen, but my stupid leader insists on this log. Anyway.");
                t.printStackTrace();
                System.exit(42);
            }
        }
    }

    private void doAndLogSomeStuff() {
        int nextActionIndex = random.nextInt(50) / 10;
        switch (nextActionIndex) {
            case 0:
                System.out.println("ERROR: Things went out of control.");
                break;
            case 1:
                System.out.println("WARN: Didn't I warn you?");
                break;
            case 2:
                System.out.println("INFO: For your information: do it as ASAP as possible!");
                break;
            case 3:
                System.out.println("DEBUG: Let's see what's going on...");
                break;
            case 4:
                System.out.println("TRACE: This is micro management.");
                break;
            default:
                throw new IllegalArgumentException("Action code outside range!");
        }
    }
}
