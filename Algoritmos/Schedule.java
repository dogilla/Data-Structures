import java.util.*;
//clase publica que representa una tarea
public class Schedule implements Comparable<Schedule> {
  private int a;
  private int b;

  public Schedule(int a, int b){
    this.a = a;
    this.b = b;
  }

  public int getInicio(){
    return a;
  }

  public int getFinal(){
    return b;
  }

  public boolean areCompatible(Schedule otro){
    return (this.b <= otro.getInicio() || otro.getFinal() <= this.a) ? true : false;
  }

  @Override
  public String toString(){
    String s = "[";
    s+= Integer.toString(this.a) + ":" + Integer.toString(this.b) + "]";
    return s;
  }

  //ordena por tiempo de inicio.
  @Override
  public int compareTo(Schedule otro){
    return (this.getInicio() - otro.getInicio());
  }
}
