
public class Arista {

  private int flujo;
  private final Vertice fuente;
  private final Vertice destino;

 /*contructor de la arista*/
  public Arista(Vertice fuente, Vertice destino, int flujo){
		this.fuente = fuente;
		this.destino = destino;
		this.flujo = flujo;
	}

  public Vertice s(){
		return fuente;
	}

	public Vertice t(){
		return destino;
	}


	public int getflujo(){
		return flujo;
	}

	public void setflujo(int flujo){
		this.flujo=flujo;
	}


	@Override
	public String toString(){
		return "["+fuente+"-->"+destino+" (flujo="+flujo+")]";
	}

}
