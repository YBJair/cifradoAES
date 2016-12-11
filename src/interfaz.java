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
		
		lvl1 = new Label("Mensaje");
		lvl2 = new Label("Mensaje");
		lvl3 = new Label("Mensaje cifrado");
		lvl4 = new Label("Mensaje cifrado");
		
		add(lbl1);
		add(lbl2);
		add(lbl3);
		add(lbl4);
		
		btn1 = new Button("Enviar");
		btn2 = new Button("Enviar");
		
		add(btn1);
		add(btn2);
		
		txt1 = new TextField("Escriba aqui su mensaje");
		txt2 = new TextField("Escriba aqui su mensaje");
		txt3 = new TextField("Cifrado");
		txt4 = new Textfield("Cifrado");
		
		add(txt1);
		add(txt2);
		add(txt3);
		add(txt4);
		
		
		
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
		// TODO Auto-generated method stub
		
	}

}