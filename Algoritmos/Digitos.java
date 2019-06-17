import java.lang.Math;
import java.util.Scanner;

public class Digitos{

public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  System.out.print("Número de digitos: ");
  int k = sc.nextInt();
  System.out.print("entero: ");
  int n = sc.nextInt();
  String r = decimall(k,n);
  System.out.print(r + "\n");
}

//algoritmo que calcula los primeros k digitos de la operacion 1/n
public static String decimall(int k, int n){
    int r = 1;
    int count  = 0;
    int arr[];
    arr = new int[k];
    while(count < k){
      if(r<n){
        r = r*10;
        if(r<n){
          arr[count] = 0;
        }else{
          int digit = (int) (r/n);
          arr[count] = digit;
          r = r-(n*digit);
        }
      }
      count = count +1;
    }
    String result = "0.";
    for (int i = 0; i < arr.length; i++) {
      result += Integer.toString(arr[i]);
    }
    return result;
  }



}
