package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private BordersDAO dao;
	private Map<Integer, Country> mappaStati;
	private Graph<Country, DefaultEdge> grafo;

	public Model() {
		dao = new BordersDAO();
		mappaStati = new HashMap<Integer, Country>();
	}

	public void createGraph(int anno) {
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);

		// inserisco i vertici
		dao.loadAllCountries(mappaStati);
		//Graphs.addAllVertices(grafo, mappaStati.values());

		// inserisco gli archi
		List<Border> countryPairs = dao.getCountryPairs(anno, mappaStati);
		for (Border b : countryPairs) {
			if(!grafo.vertexSet().contains(b.getC1()))
				grafo.addVertex(b.getC1());
			if(!grafo.vertexSet().contains(b.getC2()))
				grafo.addVertex(b.getC2());
			grafo.addEdge(b.getC1(), b.getC2());
		}
	}

	public String elencoStatiConfini() {
		String s = "";
		for (Country c : grafo.vertexSet()) {
			if(grafo.degreeOf(c)!=0)
				s += c.toString() + " - Numero stati confinati: " + grafo.degreeOf(c) + "\n";
		}
		return s;
	}

	public int componentiConnesse() {
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(grafo);
		//System.out.println(ci.connectedSets());
		return ci.connectedSets().size();
	}

	public int vertexNumber() {
		return grafo.vertexSet().size();
	}

	public int edgeNumber() {
		return grafo.edgeSet().size();
	}

}
