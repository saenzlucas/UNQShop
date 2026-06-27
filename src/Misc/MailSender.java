package Misc;

import java.io.File;

public class MailSender {
	public void enviarMail (String direccionDestino, String titulo, String mensaje, File adjunto) {
		System.out.println ("de: unqshop@gmail.com");
		System.out.println ("para: "+ direccionDestino);
		System.out.println ("asunto: "+ titulo);
		System.out.println ("mensaje: "+ mensaje);
		System.out.println (adjunto);
	}
}
