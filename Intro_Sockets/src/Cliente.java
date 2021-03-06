/* MARTIN PUGA EGEA & PABLO RODRIGUEZ PEREZ --- RO 1516/66 */

import java.io.*;
import java.net.*;
import java.net.SocketException;
import java.net.ConnectException;

public class Cliente {
	public Cliente(InetAddress dirIP, Integer puerto){

		try{
			System.out.println("\n\n\t|-----------------------------------<[  CLIENTE  ]>-----------------------------------|");
			System.out.println("\n\n\t\t>>> IP Local: "+InetAddress.getLocalHost());
			System.out.println("\n\t\t>>> Conectando al servidor... :\n\t\t\tIP: "+dirIP+"\n\t\t\tPuerto: "+puerto);		
			Socket socketCliente = new Socket (dirIP, puerto);
			new Shutdown(socketCliente).prevenirApagado();
			BufferedReader flujoEntrada = new BufferedReader (new InputStreamReader(socketCliente.getInputStream()));
			System.out.println("\n\t\t>>> Conexión realizada correctamente.");
			System.out.println("\n\t\t>>> Recibiendo servicio:\n");
			while(flujoEntrada!=null){
				System.out.println(flujoEntrada.readLine());
			}
			System.exit(0);
		} catch(ConnectException c){
			System.out.println("\n\n\t\t>>> ERROR: Imposible conectar al servidor indicado.\n");
			System.out.println("\t\t>>> Cliente apagado.\n\n");
			System.out.println("\t|-------------------------------------------------------------------------------------|\n\n");
		}
		catch(SocketException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	public class Shutdown {
		Socket socketCliente;
		public void prevenirApagado(){
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run(){	
					System.out.println("\n\t\t>>> Desconectando del servidor...\n");
					try {
						socketCliente.close();
						socketCliente=null;
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("\t\t>>> Cliente apagado.\n\n");
					System.out.println("\t|-------------------------------------------------------------------------------------|\n\n");
				}
			});
		}
		public Shutdown(Socket socketCliente) {
			super();
			this.socketCliente = socketCliente;
		}
	}
}
