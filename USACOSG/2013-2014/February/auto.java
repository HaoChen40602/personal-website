import java.util.*;
import java.io.*;
public class auto{
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("auto.in"));
        BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int W = Integer.parseInt(s.nextToken());
        int N = Integer.parseInt(s.nextToken());
        word[] dic = new word[W];
        for(int i = 0; i < W; i++){
            dic[i] = new word(f.readLine(), i + 1);
        }
        Arrays.sort(dic);
        PrintWriter out = new PrintWriter(new File("auto.out"));
        for(int i = 0; i < N; i++){
            s = new StringTokenizer(f.readLine());
            int k = Integer.parseInt(s.nextToken());
            String w = s.nextToken();
            int index = downK(dic, w);
            if(index == -1){
                out.println(-1);
                continue;
            }else{
                index += k - 1;
                if(index < W && dic[index].str.indexOf(w) == 0){
                    out.println(dic[index].pos);
                }else{
                    out.println(-1);
                }
            }
        }
        out.close();
    }
    
    public static int downK(word[] array, String key){
        	int low = 0;
 	int high = array.length - 1;
        while (low < high){
            int mid = (low + high) / 2;
            if (array[mid].str.compareTo(key) < 0){
                low = mid +1;
            }else{
                high = mid;
            }
        }
        
        
        if(array[high].str.compareTo(key) >= 0){
            return high;
        }else{
            return -1;
        }
    }
}

class word implements Comparable<word>{
    String str;
    int pos;
    public word(String s, int p){
        str = s;
        pos = p;
    }
    
    public int compareTo(word other){
        return this.str.compareTo(other.str);
    }
}
