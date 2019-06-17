import java.util.*;


//implementacion interactiva de Quicksort y Shellsort con números pseudoaleatorios.
public class Ordenamientos {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int k;
    System.out.println("Introduce un entero k entre 1 y 20");
    k = sc.nextInt();
    double r[] = fifthy_random(k, 1);
    double r2[] = fifthy_random(k, 2);
    System.out.println("Peor caso quickSort: \n");
    System.out.println(Arrays.toString(r));
    quicksort(r, 0, r.length-1);
    System.out.println("Arreglo ordenado : \n");
    System.out.println(Arrays.toString(r));
    System.out.println("Peor caso shellsort: \n");
    System.out.println(Arrays.toString(r2));
    shellsort(r2);
    System.out.println("Arreglo ordenado : \n");
    System.out.println(Arrays.toString(r2));
  }

  public static void quicksort(double arr[],int l, int h){
    if (l < h) {
      int index = partition(arr, l, h);
      quicksort(arr, l, index - 1);
      quicksort(arr, index + 1, h);
    }
  }

  public static int partition(double arr[],int l,int h){
    double p = arr[h];
    int i = l -1;
    for (int j = l; j <= h- 1; j++) {
        if (arr[j] <= p){
            i++;
            swap(arr, i, j);
        }
    }
    swap(arr,i + 1,h);
    return (i + 1);
  }

  public static void swap(double a[],int i, int b){
    double temp = a[i];
    a[i] = a[b];
    a[b] = temp;
  }

  public static void shellsort(double arr[]){
    int j;
    double temp;
    int n = arr.length;
    for(int gap = n/2; gap > 0; gap /= 2){
      for(int i = gap; i<n; i++){
        temp = arr[i];
        for(j = i; j>=gap && temp<arr[j-gap]; j-=gap){
          arr[i] = arr[j-gap];
        }
        arr[j] = temp;
      }
    }
  }


  public static double[] fifthy_random(int k, int flag){
    int i, j;
    j= 50 * k;
    double r = 0;
    double x[] = new double[j];
    //caso quicksort
    if (flag == 1){
      for(i = 0; i<j;i++){
        r+= Math.random() * 11 + 1;
        x[i] = r;
        x[i] = Math.round(x[i] * Math.pow(10, 2)) / Math.pow(10,2);
      }
    //caso shellsort
    }else {
      for(i = 0; i<j; i++){
      if(j%2 == 0){
        r+=1;
        x[i] = r*Math.random() * 2 + 1;
        x[i] = Math.round(x[i] * Math.pow(10, 2)) / Math.pow(10,2);
      }else{
        x[i] = r;
        x[i] = Math.round(x[i] * Math.pow(10, 2)) / Math.pow(10,2);
        r+= Math.random() * 11 + 1;
      }
     }
    }
    return x;
   }

}
