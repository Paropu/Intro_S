import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;

public class HiloCliente implements Runnable {
Socket socket;

	@Override
	public void run() {
		OutputStream aux;
		try {
			aux = this.socket.getOutputStream();
			DataOutputStream flujo =new DataOutputStream(aux);
			for(;;){
				Calendar calendario = Calendar.getInstance();
				int hora =calendario.get(Calendar.HOUR_OF_DAY);
				int minutos = calendario.get(Calendar.MINUTE);
				int segundos = calendario.get(Calendar.SECOND);
				flujo.writeUTF(hora + ":" + minutos + ":" + segundos);
				Thread.sleep (1000); 
			}
		} catch (IOException e){
			e.printStackTrace();
			}
		 catch (InterruptedException e) {
			e.printStackTrace();
		}
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
