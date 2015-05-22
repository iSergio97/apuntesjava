package us.lsi.graphs;


import java.io.*;
import java.util.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;

/**
 * Clase adecuada para salvar un grafo en un fichero en un formato gv.
 * 
 * @author Miguel Toro
 *
 */
public class GraphsFileExporter {	
	
	
	public static <V, E> void saveFile(Graph<V, E> graph, String s, VertexNameProvider<V> vertexIDProvider,
            VertexNameProvider<V> vertexLabelProvider,
            EdgeNameProvider<E> edgeLabelProvider,
            ComponentAttributeProvider<V> vertexAttributeProvider,
            ComponentAttributeProvider<E> edgeAttributeProvider){
		File f = new File(s);
		Writer wr = null;
		try {
			wr = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("No se ha podido crear el fichero "+s);
		}
		DOTExporter<V,E> de = new DOTExporter<V,E>(vertexIDProvider,
                vertexLabelProvider,
                edgeLabelProvider,
                vertexAttributeProvider,
                edgeAttributeProvider);
		de.export(wr, graph);
		try {
			wr.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("No se ha podido cerrar el fichero "+s);
		}
	}
	
	/**
	 * Se construye un fichero con información asociada a vértices y 
	 * aristas dada por sus respectivas toString()
	 * 
	 * @param graph Grafo
	 * @param file Fichero
	 * @param <V> El tipo de los vértices
	 * @param <E> El tipo de las aristas
	 */
	public static <V, E> void saveFile(Graph<V, E> graph, String file){
		saveFile(graph, file, new StringNameProvider<V>(),null,null,null,null);
	}
	
	/**
	 * Se construye un fichero con información asociada a vértices y 
	 * aristas dada por sus respectivas toString()
	 * 
	 * Los vértices especiales tienen iconos diferentes, 
	 * igualmente ocurre con las aristas especiales
	 * 
	 * @param graph Grafo 
	 * @param file Fichero
	 * @param specialVertexSet Vértices especiales
	 * @param specialEdgeSet Aristas especiales 
	 * @param <V> El tipo de los vértices
	 * @param <E> El tipo de las aristas
	 */
	public static <V, E> void saveFile(Graph<V, E> graph, String file, Set<V> specialVertexSet, Set<E> specialEdgeSet){
		GraphsFileExporter.saveFile(graph, file, new StringNameProvider<V>(),
				null,null,
				new EtiquetasVerticesEspeciales<V>(specialVertexSet),
				new EtiquetasAristasEspeciales<E>(specialEdgeSet));
	}
	
	
	private static class EtiquetasVerticesEspeciales<V> implements ComponentAttributeProvider<V>{
		
		private Set<V> set;
	
		public EtiquetasVerticesEspeciales(Set<V> set) {
			super();
			this.set = set;
		}
		
		@Override
		public Map<String, String> getComponentAttributes(V v) {
			Map<String, String> map = new HashMap<>();
			if(set.contains(v)){
				map.put("style", "dotted");
			}
			return map;
		}
		
	}
	
	private static class EtiquetasAristasEspeciales<E> implements ComponentAttributeProvider<E>{
		
		private Set<E> set;
	
		public EtiquetasAristasEspeciales(Set<E> set) {
			super();
			this.set = set;
		}
		
		@Override
		public Map<String, String> getComponentAttributes(E e) {
			Map<String, String> map = new HashMap<>();
			if(set.contains(e)){
				map.put("style", "dotted");
			}
			return map;
		}
		
	}
	
	
	
}
