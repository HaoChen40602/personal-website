import java.util.*;
import java.io.*;
public class msched2{
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("msched.in"));
        int n = stdin.nextInt();
        cow[] cows = new cow[n];
        for (int i = 0; i < n; i++) {
            int milk = stdin.nextInt();
            int last = stdin.nextInt();
            cows[i] = new cow(milk, last);
        }
        Arrays.sort(cows);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        int sub = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += cows[i].milk;
            queue.offer(cows[i].milk);
            if(queue.size() > cows[i].last){
                sub += queue.poll();
            }
        }
        PrintWriter out = new PrintWriter(new FileWriter("msched.out"));
        out.println(sum - sub);
        out.close();
        stdin.close();
    }
}

class cow implements Comparable<cow> {
    public int milk;
    public int last;
    public cow(int m, int l) {
        milk = m;
        last = l;
    }
    public int compareTo(cow other) {
        if(this.last != other.last){
            return this.last - other.last;
        }
        return other.milk - this.milk;
    }
}