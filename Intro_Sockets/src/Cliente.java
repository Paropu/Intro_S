import java.io.*;
import java.net.*;

public class Cliente {
	public Cliente(InetAddress dirIP, Integer puerto){
		try{
			Socket socketCliente = new Socket (dirIP, puerto);
			InputStream aux = socketCliente.getInputStream();
			DataInputStream flujo = new DataInputStream(aux);
			InetAddress ipLocalHost = InetAddress.getLocalHost();
			for(;;)System.out.println(flujo.readUTF());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}