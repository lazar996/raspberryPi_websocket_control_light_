import com.pi4j.io.gpio.*;

public class Main {

    public static void main(String[] args) throws InterruptedException{

        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

        pin.setShutdownOptions(true, PinState.LOW);

        WServer ws = new WServer(3333, pin);
        ws.start();

        Thread.sleep(9999999);


    }
}
