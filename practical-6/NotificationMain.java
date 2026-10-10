@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

class UrgentEmail implements Notifier, Urgent {
    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class UrgentSMS implements Notifier, Urgent {
    @Override
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

public class NotificationMain {

    public static void main(String[] args) {

        Notifier email = message ->
                System.out.println("Email: " + message);

        Notifier sms = message ->
                System.out.println("SMS: " + message);

        Notifier[] senders = {
            email,
            sms
        };

        String message = "Your account has been updated.";

        for (Notifier sender : senders) {
            sender.send(message);
        }

        Notifier urgentEmail = new UrgentEmail();
        Notifier urgentSMS = new UrgentSMS();

        Notifier[] urgentSenders = {
            urgentEmail,
            urgentSMS
        };

        System.out.println("Urgent Notification:");

        for (Notifier sender : urgentSenders) {
            sender.send(message);

            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }
    }
}