import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Red {
  private int vcont;
	private int acont;
	private ArrayList<Vertice> lvertices;
  private ArrayList<Arista> laristas;
  public Vertice s;
  public Vertice t;

	public Red(){
    this.vcont = 0;
    this.acont = 0;
    //lista de vertices y aristas.
    this.lvertices = new ArrayList<Vertice>();
    this.laristas = new ArrayList<Arista>();
    this.s = new Vertice(0, "S");
    this.t = new Vertice(0, "T");
	}

  public ArrayList<Arista> getAristas(){
    return this.laristas;
  }

  public void agregaVertice(Vertice v){
    lvertices.add(v);
    vcont++;
  }

	public void agregaArista(Arista a){
    laristas.add(a);
    acont++;
	}

  /*Contadores para las aristas y los vertices*/
	public int vContador(){
		return vcont;
	}

	public int aContador(){
		return acont;
	}

  //nos dice si dos vertices son adyacentes
	public boolean esAdyacente(Vertice a, Vertice b){
    for(Arista arista: laristas){
      if(arista.s().equals(a) && arista.t().equals(b))
      return true;
    }
    return false;
  }

  //crea una red de flujo a partir de una grafica de circulacion con demanda
  public void creaRedFlujo(){
    //crea los nuevos vertices s y t para hacer Ford-Fulkenson
      for(Vertice v: lvertices){
        if(v.esFuente()){
          this.agregaArista(new Arista(this.s, v,(-1*v.getValor()) ));
        } else if(v.esPozo()){
          this.agregaArista(new Arista(this.t, v, (-1*v.getValor())));
        } else{
          continue;
        }
      }
  }

  public Vertice findVertice(String nombre){
    for(Vertice v: this.lvertices){
      if(v.getNombre().equals(nombre))
      return v;
    }
    return null;
  }

  public Arista findArista(Vertice a, Vertice b){
    for(Arista ar: this.laristas){
      if(ar.s().equals(a) && ar.t().equals(b))
      return ar;
    }
    return null;
  }

  public int sumaCap(){
    int val = 0;
    for(Vertice v: this.lvertices){
      val =+ v.getValor();
    }
    return val;
  }

  //implementa bfs
  public boolean bfs(Vertice v1, Vertice pozo){
    Queue<Vertice> cola = new LinkedList<Vertice>();
    cola.add(v1);
    v1.setVisitado(true);
    v1.setPadre(null);
    while (cola.size() != 0) {
      Vertice v2 = cola.poll();
      for(Vertice v3 : lvertices){
        if(v3.getVisitado() == false && this.esAdyacente(v2, v3))
        cola.add(v3);
        v3.setVisitado(true);
        v3.setPadre(v2);
      }
    }
    return (pozo.getVisitado() == true);
  }

}
