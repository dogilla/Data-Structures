import static java.lang.Double.min;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;


//implementa el algoritmo de busqueda exponencial

 public class ExpSearch{
  public static void main(String[] args) {
    //crea arreglo aleatorio.
    ArrayList<Double> arr = createArray();
      for (Double s : arr) {
          System.out.println(s);
      }
      int i = exp_search(arr, arr.size(), 1);
 }

 public static ArrayList<Double> createArray(){
  int randoml = (int) (Math.random() * 50);
  ArrayList<Double> arr = new ArrayList<>();
  //crea numeros pseudo aleatorios
  for(int c = 0; c<=randoml; c++){
    double n = Math.random() * 49 + 1;
    //redondea a 2 decimales
    double round = (double) Math.round(n * 100) / 100;
    arr.add(round);
   }
  Collections.sort(arr);
   return arr;
 }
/*
*arr -arreglo de busqueda
*size - tamaño del arreglo
*key - elemento que hay que buscar
*/
 public static int exp_search(ArrayList<Double> arr, int size, int key){
   if (size == 0)
   return 0;
   int index = 1;
   while(index < size && arr.get(index) < key){
     index *= 2;
   }
   return binary_search(arr,index/2, (int) min(index, size),key);
 }

/*
* beg-incio del arreglo, end - fin del arreglo
* elem - elemento a buscar
*/
public static int binary_search(ArrayList<Double> arr, int beg, int end, double elem){
  if(beg >= end) {
    int mid = beg + (end - beg);
    double res = arr.get(mid);
  if(res == elem)
    return mid;
  if(res > elem){
      return binary_search(arr, beg, mid-1, elem);
  }
  return binary_search(arr, mid+1, end, elem);
  }
  //no se encontro el elemento
  return -1;
}
}
