import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.wiringpi.Gpio;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class WServer extends WebSocketServer {

    private GpioPinDigitalOutput pin;

    public WServer(int i, GpioPinDigitalOutput pin) {
        super(new InetSocketAddress(i));
        this.pin = pin;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("Client connected! IP: " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("Client disconnected! IP: " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket webSocket, String msg) {
        System.out.println("Message: " + msg);

        if(msg.equals("on")) {
            pin.setState(PinState.HIGH);
        }
        else if(msg.equals("off")) {
            pin.setState(PinState.LOW);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
