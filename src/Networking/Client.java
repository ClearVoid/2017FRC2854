package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

public class Client extends Thread {

	private Socket s;
	private BufferedReader in;
	private String value;
	private boolean newData = false;

	public Client(String host, int port) {
		try {
			s = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getLatest() {
		newData = false;

		return value;
	}

	@Override
	public void run() {
		try {
			while (!s.isClosed() || s.isConnected()) {
				String value2 = in.readLine();
				newData = newData || (!value2.equals(value));
				value = value2;
				if (value == null) {
					break;
				}
			}
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public boolean isNewData() {
		return newData;
	}

	public void setNewData(boolean newData) {
		this.newData = newData;
	}

	public static void main(String[] args) {
		Client c = new Client("10.28.54.31", 44);
		System.out.println("Connected to: " + c.s.getLocalAddress());
		try {
			while (true) {
				System.out.println(Arrays.deepToString(VisualData.decode(c.getIn().readLine())));
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// while(c.newData) {
		// System.out.println(c.getLatest());
		// }
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

}