import java.awt.*;
import java.awt.event.*;
import java.security.Key; //para poder generar claves(Key key)

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;//para poder generar claves Secretas

import static org.apache.commons.codec.binary.Base64.encodeBase64;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

public class interfaz extends Frame implements ActionListener{
	
	private static String algoritmo;//variable para almacenar el tipo de algoritmo que vamos a utilizar
	private static byte[] clave;
	private static Key claveSecretaAES;
	private Lavel lbl1, lbl2, lbl3, lbl4;
	private Button btn1, btn2;
	private TextField txt1, txt2, txt3, txt4;

    public interfaz(){
    	
    	algoritmo="AES";
		clave=new byte[]{'M','E','J','O','R','C','L','A','V','E','S','E','C','R','E','T'};
		setLayout(new FlowLayout());
		
		lbl1 = new Label("Mensaje");
		lbl2 = new Label("Mensaje");
		lbl3 = new Label("Mensaje cifrado");
		lbl4 = new Label("Clave");
		
		add(lbl1);
		add(lbl2);
		add(lbl3);
		add(lbl4);
		
		btn = new Button("Enviar");
		add(btn);

		
		txt1 = new TextField("Escriba aqui su mensaje");
		txt2 = new TextField("Reciba aqui su mensaje");
		txt3 = new TextField("Cifrado");
		txt4 = new Textfield("Clave");
		
		add(txt1);
		add(txt2);
		add(txt3);
		add(txt4);
		
		btn.addActionListener(this);
		
		
		setTitle("AES messenger");
		setSize(800,800);
		
		setVisible(true);
		
    }

    //Poned aqui vuestros metodos.
    
    public static String encriptar(String texto) throws Exception
	{
		String cifrado=null;
		Cipher cifrar;
		byte[] valorEncriptado;
		
		generarClaveSecreta();//llamamos a esta funcion para generar una clave Secreta para AES
		
		cifrar=Cipher.getInstance(algoritmo);
		cifrar.init(Cipher.ENCRYPT_MODE, claveSecretaAES);
		
		valorEncriptado=cifrar.doFinal(texto.getBytes());
		
		cifrado=new String(encodeBase64(valorEncriptado));
		
		
		return cifrado;
	}
	
	public static String desencriptar(String textoCifrado)throws Exception
	{
		String descifrado=null;
		Cipher descifrar;
		byte[] valorEncriptado,valorDecodificado;
		
		
		
		descifrar=Cipher.getInstance(algoritmo);
		descifrar.init(Cipher.DECRYPT_MODE, claveSecretaAES);//desciframos con la misma clave que hemos generado al cifrar
		
		valorEncriptado=decodeBase64(textoCifrado);
		valorDecodificado=descifrar.doFinal(valorEncriptado);
		
		descifrado= new String(valorDecodificado);
		
		
		return descifrado;
	}
	
	public static void generarClaveSecreta()
	{
		//mediante esta funcion generamos una clave secreta para AES, mediante el array de bytes de nuestra clave y el algoritmo especificado
		claveSecretaAES=new SecretKeySpec(clave,algoritmo); 	
	}
	
    //
    public static void main(String[] args){
        new interfaz();
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String msg, msgCif;
		
		msg = txt1.getText();
		msgCif = this.encriptar(msg);
		txt4.setText(claveSecretaAES);
		msg = this.desencriptar(msgCif);
		txt3.setText(msgCif);
		txt2.setText(msg);
		
		
	}

}