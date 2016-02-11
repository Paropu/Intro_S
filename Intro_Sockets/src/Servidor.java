import java.io.*;
import java.net.*;
import java.util.Calendar;

public class Servidor {
	public Servidor(Integer puerto){
		try{
			ServerSocket socketServidor = new ServerSocket(puerto);
			InetAddress ipLocalHost = InetAddress.getLocalHost();
			System.out.println("\n\t---------- SERVIDOR EN FUNCIONAMIENTO ---------- \n\n\t>>> IP Local: " + ipLocalHost + "\n\t>>> Escuchando el puerto: " + puerto);
			while(true){
				Socket socketCliente = socketServidor.accept();
				System.out.print("\n\t>>> Nuevo cliente:\n\t\tIP: ");
				System.out.println(socketCliente.getRemoteSocketAddress());
				//System.out.println(socketCliente.getRemoteSocketAddress());

				Runnable nuevoCliente = new HiloCliente(socketCliente); 
				Thread hilo = new Thread(nuevoCliente);
				hilo.start();  
			}
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}
}