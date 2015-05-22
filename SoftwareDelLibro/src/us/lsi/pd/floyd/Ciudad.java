package us.lsi.pd.floyd;

import us.lsi.graphs.StringVertexFactory;

public class Ciudad implements StringVertexFactory<Ciudad> {

	private static Ciudad r = new Ciudad("");

	@Override
	public Ciudad createVertex(String[] formato) {
		return new Ciudad(formato[0]);
	}

	public static StringVertexFactory<Ciudad> getFactory() {
		return r;
	}

	private String nombre;

	public Ciudad(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
