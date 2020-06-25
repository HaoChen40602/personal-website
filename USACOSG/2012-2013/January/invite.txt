import java.util.*;
import java.io.*;
public class invite{
    public static void main(String[] args) throws Exception{
        BufferedReader f = new BufferedReader(new FileReader("invite.in"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int G = Integer.parseInt(s.nextToken());
        ArrayList<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();
        
        for(int i = 0; i < G; i++){
            HashSet<Integer> sss = new HashSet<Integer>();
            s = new StringTokenizer(f.readLine());
            int num = Integer.parseInt(s.nextToken());
            for(int j = 0; j < num; j++){
                sss.add(Integer.parseInt(s.nextToken()));
            }
            sets.add(sss);
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> ans = new HashSet<Integer>();
        queue.add(1);
        while(queue.isEmpty() == false){
            int current = queue.poll();
            for(int i = sets.size() - 1; i >= 0; i--){
                sets.get(i).remove(current);
                if(sets.get(i).size() == 1){
                    for(int a : sets.get(i)){
                        queue.add(a);
                        ans.add(a);
                    }
                    sets.remove(i);
                }
            }
        }
        PrintWriter out = new PrintWriter(new File("invite.out"));
        out.println(ans.size() + 1);
        out.close();
    }
}