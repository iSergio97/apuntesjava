package us.lsi.ag.anuncios;


import java.util.List;

import us.lsi.ag.Cromosoma;
import us.lsi.ag.ProblemaAGBag;
import us.lsi.bt.anuncios.Anuncio;
import us.lsi.bt.anuncios.ListaDeAnunciosAEmitir;
import us.lsi.bt.anuncios.ProblemaAnuncios;

public class ProblemaAnunciosAG extends ProblemaAnuncios implements ProblemaAGBag<ListaDeAnunciosAEmitir> {

	public ProblemaAnunciosAG(String file) {
		super();
		super.leeYOrdenaAnuncios(file);
	}	
	
	@Override
	public ListaDeAnunciosAEmitir getSolucion(Cromosoma<Integer> c) {		
		return  ListaDeAnunciosAEmitir.create(c.decode());
	}
	

	@Override
	public Double fitnessFunction(List<Integer> list) {		
		ListaDeAnunciosAEmitir ls = ListaDeAnunciosAEmitir.create(list);
		Double valor = ls.getValor();
		Integer nIncompatibles = ls.getNumAnunciosIncompatibles();
		Integer tr = ls.getTiempoRestante()>=0?0:-ls.getTiempoRestante();
		Integer nRepetidos = ls.getNumAnunciosRepetidos();
		Integer d = nIncompatibles*nIncompatibles+nRepetidos*nRepetidos+tr*tr;
		Double f = valor/1000000L  - 1000000000000L*d*d;
		return f;
	}

	
	public List<Anuncio> getObjetos() {
		return ProblemaAnuncios.todosLosAnunciosDisponibles;
	}

	@Override
	public Integer getNumeroDeObjetos() {
		return this.getObjetos().size();
	}

	
	
	
}
