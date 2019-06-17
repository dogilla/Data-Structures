import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

//Algoritmo de la mochila para encontrar la agenda optima. Este es un algoritmo de optimización.
//Este archivo esta conectado a Schedule.java

public class MainTarea4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n;
    System.out.print("Introduzca un número entero: ");
    n = sc.nextInt();
    System.out.println("Tareas :");
    ArrayList<Schedule> req = createREQ(n-1);
    for(Schedule s: req){
      System.out.println(s.toString());
    }
    String ffinalr = "";
    ArrayList<ArrayList<Schedule>> sch = scheduleAll(req, n);
    System.out.println("Calendarizado: ");
    for(int r = 0; r < n; r++){
      ffinalr += "{";
      for(Schedule sp: sch.get(r)){
        ffinalr += sp.toString();
      }
      ffinalr += "}\n";
    }
    System.out.println(ffinalr);
  }

  //crea de manera aleatoria la lista de listas req.
  public static ArrayList<Schedule> createREQ(int i){
    int r1,r2;
    ArrayList<Schedule> req = new ArrayList<Schedule>();
    while(i>=0){
      r1 = randomNumberInRange(1,24);
      r2 = randomNumberInRange(1,24);
      req.add(new Schedule(r1,r2));
      i--;
    }
    Collections.sort(req);
    return req;
  }

  public static int randomNumberInRange(int min, int max) {
    Random random = new Random();
    return random.nextInt((max - min) + 1) + min;
  }

  public static Schedule deleteMinStarting(ArrayList<Schedule> minimo){
    return minimo.get(0);
  }


  public static ArrayList<ArrayList<Schedule>> scheduleAll(ArrayList<Schedule> req, int n){
    ArrayList<ArrayList<Schedule>> sch = new ArrayList<ArrayList<Schedule>>();
    for(int q = 1; q<=n; q++){
      sch.add(new ArrayList<Schedule>());
    }
    int min = 1;
    Schedule reqi;
    boolean asignado;
    boolean lock = true;
    ArrayList<Schedule> wait = new ArrayList<Schedule>();
    wait = req;
    while(wait.size() >= 1){
      reqi = deleteMinStarting(wait);
      wait.remove(0);
      asignado = false;
      for(int j=0;j<min;j++){
        for(Schedule reqk: sch.get(j)){
          if(reqk.areCompatible(reqi)){
            lock = true;
          }else{
            lock = false;
          }
        }
        if(lock == true)
          sch.get(j).add(reqi);
          asignado = true;
          break;
      }
      if(asignado == false){
        min += 1;
        sch.get(min).add(reqi);
        asignado = true;
      }
    }
    return sch;
  }


}
