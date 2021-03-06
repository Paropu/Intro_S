/* MARTIN PUGA EGEA & PABLO RODRIGUEZ PEREZ --- RO 1516/66 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;

public class HiloCliente implements Runnable {
Socket socket;

@Override
public void run() {
Socket socketDesconectado=this.socket;
	try {
		PrintWriter flujoSalida = new PrintWriter(this.socket.getOutputStream(), true);
		while(!flujoSalida.checkError()){
			Calendar calendario = Calendar.getInstance();
			int hora =calendario.get(Calendar.HOUR_OF_DAY);
			int minutos = calendario.get(Calendar.MINUTE);
			int segundos = calendario.get(Calendar.SECOND);
			flujoSalida.println("\t\t\t" + hora + ":" + minutos + ":" + segundos);
			Thread.sleep (1000);	
		}
		flujoSalida.close();
		this.socket.close();
		this.socket=null;	
	} catch (IOException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println("\n\t\t>>> Cliente desconectado:\n\t\t\tIP: "+socketDesconectado.getRemoteSocketAddress());
	socketDesconectado=null;
}

	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public HiloCliente(Socket socket) {
		super();
		this.socket = socket;
	}
}
