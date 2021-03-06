/* MARTIN PUGA EGEA & PABLO RODRIGUEZ PEREZ --- RO 1516/66 */


import java.net.InetAddress;
import java.net.UnknownHostException;

public class Intro_Sockets {
	public static void main (String args[]){
		String aplicacion=args[0].toLowerCase();
		switch (aplicacion) {
		case "s": //SERVIDOR
			Integer puerto = Integer.parseInt(args[1]);
			new Servidor(puerto);
			break;
		case "c": //CLIENTE
			InetAddress IPServidor;
			Integer puertoServer = Integer.parseInt(args[2]);
			try {
				IPServidor = InetAddress.getByName(args[1]);
				new Cliente(IPServidor, puertoServer);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			break;
		}
	}
}