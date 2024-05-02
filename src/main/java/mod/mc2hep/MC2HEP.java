package mod.mc2hep;

import com.fazecast.jSerialComm.SerialPort;
import mod.mc2hep.block.Mc2HEPBlock;
import net.fabricmc.api.ModInitializer;

import org.apache.logging.slf4j.Log4jMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class MC2HEP implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("mc2hep");
	public static SerialPort sp = SerialPort.getCommPort("/dev/ttyACM0"); // device name TODO: must be changed
	public static int ledOn = 0;
	@Override
	public void onInitialize() {
		LOGGER.info("Initializing MC2HEP");
		Mc2HEPBlock.registerBlocks();

		MyThread thread = new MyThread();

		// Start the thread
		thread.start();
	}

	public class MyThread extends Thread {
		@Override
		public void run() {
			// Define the task that the thread will execute
			System.out.println("Thread is executing a task");


			sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
			sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

			if (sp.openPort()) {
				System.out.println("Port is open :)");
			} else {
				System.out.println("Failed to open port :(");
			}
		}
	}
}
