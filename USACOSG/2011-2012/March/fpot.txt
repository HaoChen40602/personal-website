import java.io.*;
import java.util.*;
import java.lang.Math;
public class fpot{
  @SuppressWarnings("unchecked")
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("fpot.in"));
    StringTokenizer s = new StringTokenizer(f.readLine());
    int N = Integer.parseInt(s.nextToken());
    int D = Integer.parseInt(s.nextToken());
    
    Water[] drops = new Water[N];
    for(int i = 0; i < N; i++){
        s = new StringTokenizer(f.readLine());
        int x = Integer.parseInt(s.nextToken());
        int y = Integer.parseInt(s.nextToken());
        drops[i] = new Water(y, x);
    }
    Arrays.sort(drops);
    int right = 1;
    int left = 0;
    
    int minLength = Integer.MAX_VALUE;
    LinkedList<Node> max = new LinkedList<Node>();
    LinkedList<Node> min = new LinkedList<Node>();
    
    for(right = 1; right <= N; right++){
        //process min
        while(min.isEmpty() == false && min.getLast().value > drops[right - 1].time){
            min.removeLast();
        }
        min.add(new Node(drops[right - 1].time, right - 1));
        
        //process max
        while(max.isEmpty() == false && max.getLast().value < drops[right - 1].time){
            max.removeLast();
        }
        max.add(new Node(drops[right - 1].time, right - 1));
        
        // increase left bound
        while(max.peekFirst().value - min.peekFirst().value >= D){
            minLength = Math.min(minLength, drops[right - 1].x - drops[left].x);
            left++;
            while(max.isEmpty() == false && max.peekFirst().r < left){
                max.pollFirst();
            }
            while(min.isEmpty() == false && min.peekFirst().r < left){
                min.pollFirst();
            }
        }
    }
    
    if(minLength == Integer.MAX_VALUE){
        System.out.println(-1);
    }else{
        System.out.println(minLength);
    }
  }
}
 
class Node{
    int value;
    int r;
    public Node(int v, int rank){
        value = v;
        r = rank;
    }
    
}

class Water implements Comparable<Water>{
    int time;
    int x;
    public Water(int t, int a){
        time = t;
        x = a;
    }
    public int compareTo(Water other){
        return this.x - other.x;
    }
}