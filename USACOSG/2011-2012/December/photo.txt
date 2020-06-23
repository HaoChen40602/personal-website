// Hao Chen
// 6/21/2020

import java.io.*;
import java.util.*;
import java.lang.Math;
public class photo{
    static HashMap<Integer, Integer>[] map;
    @SuppressWarnings("unchecked")
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("photo.in"));
    map = new HashMap[6];
    for(int i = 0; i < 6; i++){
        map[i] = new HashMap<Integer, Integer>();
    }
    StringTokenizer s = new StringTokenizer(f.readLine());
    int N = Integer.parseInt(s.nextToken());
    int[] array = new int[N];
    for(int j = 1; j < 6 ; j++){
        for(int i = 0; i < N; i++){
            s = new StringTokenizer(f.readLine());
            int temp = Integer.parseInt(s.nextToken());
            map[j].put(temp, i);
            if(j == 1){
                array[i] = temp;
            }
        }
    }
    sort(array, 0, array.length - 1);
    PrintWriter out = new PrintWriter(new File("photo.out"));
    for(int i : array){
        out.println(i);
    }
    out.close();
  }
  @SuppressWarnings("unchecked")
  static int partition(int arr[], int low, int high){ 
        int pivot = arr[high];  
        int i = (low-1);
        for (int j=low; j<high; j++) 
        { 
            if (compareTo(arr[j], pivot)) 
            { 
                i++; 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
        return i+1; 
    } 
  
  @SuppressWarnings("unchecked")
    static void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            int pi = partition(arr, low, high);
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
    @SuppressWarnings("unchecked")
  public static boolean compareTo(int a, int b){
      int f = 0;
      for(int i = 1; i <= 5; i++){
          if(map[i].get(a) < map[i].get(b)){
              f++;
          }
      }
      return f >= 3;
  }
}

