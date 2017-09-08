package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.Normalizer;
import java.util.ArrayList;

public class Dimayor {
	
	
	private ArrayList<Equipo> equipos;
	private ArrayList<String> titulosF;

	public Dimayor() throws Exception {
		
		equipos=new ArrayList<Equipo>();
		cargarEquipos();
		
	}
	
	
	public ArrayList<String> nombreEquipos()
	{
		
		ArrayList<String> f=new ArrayList<String>();
		for (int i = 0; i < equipos.size(); i++) {
			f.add(equipos.get(i).getNombre());
		}
		return f;
	}
	
	
	private void cargarEquipos() throws Exception
	{
		asignarTitulos();
		String equipos=bajarEquipos();
		String notAccent=stripAccents(equipos).toLowerCase().replace(" ", "-").replace("equidad", "la-equidad")
				.replace("jaguares-f.c.", "sucre-fc").replace("junior", "atletico-junior")
				.replace("patriotas-boyaca-s.a.", "80").replace("rionegro-aguilas", "itagui")
				.replace("tigres-fc", "expreso-rojo");
			
		String[] e=notAccent.split("\n");
		String[] rFull=equipos.split("\n");
		for(int i=0; i<e.length;i++)
		{
			Equipo nuevo=new Equipo(rFull[i], fechaFundacion(e[i]), darTitulos(rFull[i]));
			this.equipos.add(nuevo);
			
		}
	}
	
	private String darTitulos(String equipo)
	{
		
		String num="0";
		for (int i = 0; i < titulosF.size()&&num.equals("0"); i++) {
			
			String e=titulosF.get(i).split(" ")[0].trim();
			String t="";
			if(titulosF.get(i).split(" ").length==2)
				t=titulosF.get(i).split(" ")[1].trim();
			else
				t=titulosF.get(i).split(" ")[2].trim();
			
			
			if(equipo.contains(e))
			{
				num=t;
			}
			
		}
		
		return num;
	}
	
	  private String bajarEquipos() throws IOException {
	        URL url = new URL("http://dimayor.com.co/category/club-liga-aguila/");
	        URLConnection uc = url.openConnection();
	        uc.addRequestProperty("User-Agent", 
	        		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
	        uc.connect();
	        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
	        String inputLine;
	        String contenido = "";
	        while ((inputLine = in.readLine()) != null) {
	        	
	        	if(inputLine.contains("<h2 class=\"name\">"))
	            contenido += inputLine.replaceAll("<h2 class=\"name\">", "").replaceAll("</h2>", "").split("&")[0].trim() + "\n";
	        }
	        in.close();
	        return contenido;
	    
	    }
	  
	  
	  private String fechaFundacion(String equipo ) throws Exception
	    {
	    	  URL url = new URL("http://dimayor.com.co/?club="+equipo);
	          URLConnection uc = url.openConnection();
	          uc.addRequestProperty("User-Agent", 
	          		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
	          uc.connect();
	          BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
	          String inputLine;
	          String contenido = "";
	          while ((inputLine = in.readLine()) != null&&contenido.equals("")) {
	          	
	          	if(inputLine.contains("Fundaci"))
	              contenido += inputLine.replace("<p><b>Fundación:</b>", "").replace("</p>", "").split("-")[0].trim();
	          }
	          in.close();
	          return contenido;
	    }
	  
	  private static String stripAccents(String s) 
	    {
	        s = Normalizer.normalize(s, Normalizer.Form.NFD);
	        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	        return s;
	    }
	  
	  private String numTitulos() throws IOException {
	        URL url = new URL("http://www.colombia.com/futbol/nacional/sdi/105864/los-equipos-colombianos-con-mas-titulos");
	        URLConnection uc = url.openConnection();
	        uc.addRequestProperty("User-Agent", 
	        		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
	        uc.connect();
	        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
	        String inputLine;
	        String contenido = "";
	        while ((inputLine = in.readLine()) != null) {
	        	
	        	if(inputLine.contains("estrellas"))
	            contenido += inputLine.split(",")[0] + "\n";
	        }
	        in.close();
	        return contenido;
	    
	    }
	  
	  
	  private void asignarTitulos() throws Exception
	  {
		  String titulos=numTitulos();
			String[] all=titulos.split("\n");
			titulosF=new ArrayList<String>();
			for (int i = 0; i < all.length; i++) {
				String actual = all[i];
				String[] full=actual.split(":");
				String equipo=full[0].split("-")[1].trim().split("\\(")[0].replace("Medell&iacute;n", "Médellin")
						.replace("Atl&eacute;tico Junior", "Junior").replace("Am&eacute;rica", "AMÉRICA").toUpperCase();
				String tit=full[1].split(" ")[1].trim();
				titulosF.add(equipo+tit);
			}
	  }


	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}


	public void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}
	  
	public Equipo buscarEquipo(String nombre)
	{
		nombre=nombre.toUpperCase();
		Equipo n=null;
		for (int i = 0; i < equipos.size()&&n==null; i++) {
			
			if(equipos.get(i).getNombre().contains(nombre)||nombre.contains(equipos.get(i).getNombre()))
			{
				n=equipos.get(i);
			}
			
		}
		return n;
	}
	  

}
