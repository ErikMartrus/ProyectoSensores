package pem.proyectoSensores.films.model;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccessFileFilms {

	public static String leerReceta(String nombre) {
		File tarjeta = Environment.getExternalStorageDirectory();
		String path = tarjeta.getAbsolutePath()+"/recetas/";
		File archivo = new File(path,nombre);
		try {
			FileInputStream in = new FileInputStream(archivo);
			InputStreamReader canal = new InputStreamReader(in,"ISO-8859-1");
			BufferedReader br = new BufferedReader(canal);
			String linea = br.readLine();
			String infoReceta = "";
			while (linea != null) {
				infoReceta = infoReceta + linea + "\n";
				linea = br.readLine();
			}
			br.close();
			canal.close();
			in.close();
			return infoReceta;
		} catch (IOException e) {}
		return "";	
	}
}
