import java.util.*;
import java.io.*;
public class nocow{
    static ArrayList<String>[] sets;
    static ArrayList<String> dontHave;
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("nocow.in"));
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int K = Integer.parseInt(s.nextToken());
        dontHave = new ArrayList<String>();
        String sstr = f.readLine();
        sstr = sstr.replaceAll("Farmer John has no ", "");
        sstr = sstr.replaceAll(" cow.", "");
        dontHave.add(sstr);
        s = new StringTokenizer(sstr);
        int num = s.countTokens();
        sets = new ArrayList[num];
        for(int i = 0; i < num; i++){
            sets[i] = new ArrayList<String>();
            sets[i].add(s.nextToken());
        }
        for(int i = 1; i < N; i++){
            String str = f.readLine();
            str = str.replaceAll("Farmer John has no ", "");
            str = str.replaceAll(" cow.", "");
            dontHave.add(str);
            s = new StringTokenizer(str);
            for(int j = 0; j < num; j++){
                String sss = s.nextToken();
                if(sets[j].contains(sss) == false){
                    sets[j].add(sss);
                }
            }
        }
        
        Collections.sort(dontHave);
        for(int i = 0; i < num; i++){
            Collections.sort(sets[i]);
        }
        int k = K - 1;
        // this is K - 1 because I in my system the first type if type 0, not type 1.
        while(k - num_before_on_fj_list(get_kth_cow(k, num)) < K - 1){
            k++;
        }
        PrintWriter out = new PrintWriter(new File("nocow.out"));
        out.println(get_kth_cow(k, num));
        out.close();
    }
    
    public static int num_before_on_fj_list(String s){
       int total = 0;
       for (int i = 0; i < dontHave.size(); i++){
           if (dontHave.get(i).compareTo(s) <= 0){
               total++;
           }
       }
       return total;
    }
    
    public static String get_kth_cow(int k, int num){
      String s = "";
      for(int p = 0; p < num; p++) {
          if(p > 0){
              s = s + " ";
          }
          s = s + sets[p].get(k / num_choices(p + 1, num - 1));
          k = k % num_choices(p + 1, num - 1); 
      }
      return s;
    }
    
    public static int num_choices(int pos1, int pos2){
        int total = 1;
        for (int p = pos1; p <= pos2; p++){
            total *= sets[p].size();
        }
        return total;
    }
}
