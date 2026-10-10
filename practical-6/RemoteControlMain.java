interface Switchable {
    void on();
    void off();

    default void toggle() {
        on();
    }
}

class Fan implements Switchable {
    private boolean status = false;

    @Override
    public void on() {
        status = true;
        System.out.println("Fan is ON");
    }

    @Override
    public void off() {
        status = false;
        System.out.println("Fan is OFF");
    }

    public boolean isOn() {
        return status;
    }
}

class Light implements Switchable {
    private boolean status = false;

    @Override
    public void on() {
        status = true;
        System.out.println("Light is ON");
    }

    @Override
    public void off() {
        status = false;
        System.out.println("Light is OFF");
    }

    public boolean isOn() {
        return status;
    }
}

@FunctionalInterface
interface SwitchPermission {
    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControlMain {

    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        for (Switchable device : devices) {
            device.toggle();
        }

        SwitchPermission anonymousPermission = new SwitchPermission() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        SwitchPermission lambdaPermission =
                (device, hour) -> hour >= 8 && hour <= 20;

        System.out.println("Anonymous at 10: " +
                anonymousPermission.maySwitchOn(devices[0], 10));

        System.out.println("Lambda at 10: " +
                lambdaPermission.maySwitchOn(devices[1], 10));
    }
}