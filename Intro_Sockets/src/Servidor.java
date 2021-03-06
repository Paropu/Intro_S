/* MARTIN PUGA EGEA & PABLO RODRIGUEZ PEREZ --- RO 1516/66 */

import java.io.*;
import java.net.*;

public class Servidor {
	public Servidor(Integer puerto){
		System.out.println("\n\n\t|-----------------------------------<[  SERVIDOR  ]>-----------------------------------|");
		System.out.println("\n\n\t\t>>> Iniciando...");
		try{
			ServerSocket socketServidor = new ServerSocket(puerto);
			new Shutdown(socketServidor).prevenirApagado();
			System.out.println("\n\t\t>>> En servicio:\n\t\t\tIP Local: "+InetAddress.getLocalHost()+"\n\t\t\tPuerto: "+puerto);
			while(true){
				Socket socketCliente = socketServidor.accept();
				System.out.print("\n\t\t>>> Nuevo cliente:\n\t\t\tIP: "+socketCliente.getRemoteSocketAddress()+"\n"+"\t\t\tDetalles de la conexión: "+socketCliente+"\n");
				Runnable nuevoCliente = new HiloCliente(socketCliente); 
				Thread hilo = new Thread(nuevoCliente);
				hilo.start();  
			}
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}
	public class Shutdown {
		ServerSocket socketServidor;
		public void prevenirApagado(){
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run(){
					System.out.println("\n\t\t>>> Desconectando servidor...\n");
					try {
						socketServidor.close();
						socketServidor=null;
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("\t\t>>> Servidor apagado.\n\n");
					System.out.println("\t|-------------------------------------------------------------------------------------|\n\n");
				}
			});
		}
		public Shutdown(ServerSocket socketServidor) {
			super();
			this.socketServidor = socketServidor;
		}

	}
}