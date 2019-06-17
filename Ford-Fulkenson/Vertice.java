
public class Vertice {
  //demanda del vertice.
  public int valor;
  public String nombre;
  //boleanos que nos dicen si el vertice es un vertice fuente o pozo
  public boolean bfuente;
  public boolean bpozo;
  private boolean visitado;
  private Vertice padre;

  public Vertice(int valor, String nombre){
    this.valor = valor;
    this.nombre = nombre;
    setEstado();
    this.padre = null;
  }


  @Override
  public boolean equals(Object v){

    if (v == this) {
        return true;
    }

    if (!(v instanceof Vertice)) {
        return false;
    }
    Vertice vv = (Vertice) v;

    return this.valor == vv.getValor() && this.nombre.equals(vv.getNombre());
  }

  public Vertice getPadre(){
    return this.padre;
  }

  public void setPadre(Vertice p){
    this.padre = p;
  }

  public boolean getVisitado(){
    return this.visitado;
  }

  public String getNombre(){
    return this.nombre;
  }

  public void setVisitado(boolean a){
    this.visitado = a;
  }

  public int getValor(){
    return this.valor;
  }

  public boolean esFuente(){
    return bfuente == true;
  }

  public boolean esPozo(){
    return bpozo == true;
  }


  private void setEstado(){
    if(this.valor > 0){
      this.bfuente = false;
      this.bpozo = true;
    }else if(this.valor < 0){
      this.bfuente = true;
      this.bpozo = false;
    }else{
      this.bfuente = false;
      this.bpozo = false;
    }
  }


}
