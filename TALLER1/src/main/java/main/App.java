package main;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.Normalizer;
import java.util.ArrayList;

import javax.xml.bind.JAXBElement.GlobalScope;

import com.google.gson.Gson;

import model.Dimayor;
import model.Equipo;
import spark.Spark;

/**
 * Hello world!
 *
 */
public class App {
	private Dimayor dima;

	public static void main(String[] args) {
		Spark.staticFileLocation("/public");

		
		Spark.options("/*",	(request,	response)	->
		{
						String accessControlRequestHeaders	= request.headers("Access-ControlRequest-Headers");
						if (accessControlRequestHeaders	!= null)
						{
										response.header("Access-Control-Allow-Headers",	
accessControlRequestHeaders);
						}
						String accessControlRequestMethod	= request.headers("Access-ControlRequest-Method");
						if (accessControlRequestMethod	!= null)
						{
										response.header("Access-Control-Allow-Methods",	
accessControlRequestMethod);
						}
						return "OK";
		});
	
		Spark.before((request,	response)	->
		{
						response.header("Access-Control-Allow-Origin",	"*");
		});
		new App();

	}
	
	

	public App() {
		try {
			dima = new Dimayor();
			Gson gson = new Gson();
			
			
			//DAR EQUIPOS
			get("/equipos", (req, res) -> gson.toJson(dima.getEquipos()));
			
			//BUSCAR EQUIPOS
			get("/equipos/:nom", (req, res) -> {
				String id = req.params(":nom");
				Equipo eq = dima.buscarEquipo(id);
				return gson.toJson(eq);
			});
			
			//BORRAR EQUIPOS
			delete("/equipos/:nom", (req, res) -> {
				String id = req.params(":nom");
				Equipo eq = dima.buscarEquipo(id);
				dima.getEquipos().remove(eq);
				return gson.toJson("EQUIPO BORRADO");
			});

			
			//AGREGAR EQUIPO
			post("/equipos", (req, res) -> {

				Equipo eq = new Equipo(req.queryParams("nombre").toUpperCase(), req.queryParams("anoFundacion"),
						req.queryParams("titulos"));
				dima.getEquipos().add(eq);
				return gson.toJson("EQUIPO AGREGADO");

			});
			
			//MODIFICAR EQUIPO
			put("/equipos/:nom", (req, res) -> {
				String nomEquipo=req.params(":nom");
				Equipo encontrado=dima.buscarEquipo(nomEquipo);
				encontrado.setNombre(req.queryParams("nombre").toUpperCase());
				encontrado.setAnoFundacion( req.queryParams("anoFundacion"));
				encontrado.setNumTitulos(req.queryParams("titulos"));
				return gson.toJson("EQUIPO MODIFICADO");

			});

			

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
