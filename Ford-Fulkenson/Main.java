import java.util.Scanner;

public class Main{
  public static void main(String[] args) {
    Red CirConDem = creaRed();
    CirConDem.creaRedFlujo();
    int i = FordFulkenson(CirConDem, CirConDem.s, CirConDem.t);
    boolean decision;
    if(i == CirConDem.sumaCap()){
      decision = true;
      System.out.println("Tiene solución");
    } else{
      decision = false;
      System.out.println("Tiene solución");
    }
  }

  public static Red creaRedResidual(Red r){
    Red residual = new Red();
    for(Arista a: r.getAristas()){
      residual.agregaArista(new Arista(a.t(), a.s(), a.getflujo()));
    }
    return residual;
  }



  //implementacion de algoritmo de FordFulkenson, regresa el flujo máximo
  public static int FordFulkenson(Red r, Vertice s, Vertice t){
    int max_flow = 0;
    Red residual = creaRedResidual(r);
    int flujoPath = Integer.MAX_VALUE;
    //se cumple mientras haya un camino de s a t. De la fuente al pozo
    while(residual.bfs(s,t)){
      for(Vertice z = t; !z.equals(s); z = z.getPadre()){
        flujoPath = Math.min(flujoPath, residual.findArista(z,z.getPadre()).getflujo());
      }
      for(Vertice z = t; !z.equals(s); z = z.getPadre()){
        Arista ari = residual.findArista(z.getPadre(), z);
        residual.findArista(z.getPadre(), z).setflujo(ari.getflujo() - flujoPath);
        residual.findArista(z, z.getPadre()).setflujo(ari.getflujo() + flujoPath);
      }
      max_flow += flujoPath;
    }
    return max_flow;
  }

  //pide los parametros para crear la red.
  public static Red creaRed(){
    Scanner sc = new Scanner(System.in);
    Red CirConDem = new Red();
    System.out.print("Inserta los vertices de la gráfica: "+ "\n");
    String st;
    while(true){
      System.out.println("Nombre: " + "\n");
      String str = sc.next();
      System.out.println("Demanda: "+ "\n");
      int i = sc.nextInt();
      System.out.println("Salir o insertar otro s/o: "+ "\n");
      CirConDem.agregaVertice(new Vertice(i,str));
      st = sc.next();
      if(st.equals("S")){
        break;
      } else {
        continue;
      }
    }

    String n1, n2, str;
    while(true){
      System.out.print("Inserta las aristas de la gráfica: ");
      System.out.println("1er nodo ");
      n1 = sc.next();
      System.out.println("2do nodo ");
      n2 = sc.next();
      System.out.println("flujo: ");
      int ff = sc.nextInt();
      CirConDem.agregaArista(new Arista(CirConDem.findVertice(n1),CirConDem.findVertice(n2),ff));
      System.out.print("Salir o insertar otro s/o" + "\n");
      str = sc.next();
      if(str.equals("S")){
        break;
      }else{
        continue;
      }
    }
    sc.close();
    return CirConDem;
  }

}
