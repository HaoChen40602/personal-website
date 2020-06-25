import java.util.*;
import java.io.*;
public class crazy{
    @SuppressWarnings("unchecked")
    static int[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader f = new BufferedReader(new FileReader("crazy.in"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int C = Integer.parseInt(s.nextToken());
        Line[] fences = new Line[N];
        visited = new int[N];
        Point[] cows = new Point[C];
        for(int i = 0; i < N; i++){
            s = new StringTokenizer(f.readLine());
            int a1 = Integer.parseInt(s.nextToken());
            int a2 = Integer.parseInt(s.nextToken());
            int b1 = Integer.parseInt(s.nextToken());
            int b2 = Integer.parseInt(s.nextToken());
            fences[i] = new Line(new Point(a1, a2), new Point(b1, b2));
        }
        for(int i = 0; i < C; i++){
            s = new StringTokenizer(f.readLine());
            int a1 = Integer.parseInt(s.nextToken());
            int a2 = Integer.parseInt(s.nextToken());
            cows[i] = new Point(a1, a2);
        }
        for(int i = 0; i < fences.length; i++){
            for(int j = i + 1; j < fences.length; j++){
                if((fences[i].p1.equals(fences[j].p1)) || (fences[i].p1.equals(fences[j].p2)) ||
                   (fences[i].p2.equals(fences[j].p1)) || (fences[i].p2.equals(fences[j].p2))){
                    fences[i].connect.add(j);
                    fences[j].connect.add(i);
                }
            }
        }
        int num = 1;
        for(int i = 0; i < fences.length; i++){
            if(visited[i] == 0){
                floodFill(i, num, fences);
                num++;
            }
        }
        
        Polygon[] polygons = new Polygon[num];
        for(int i = 1; i < polygons.length; i++){
            polygons[i] = new Polygon();
        }
        for(int i = 0; i < visited.length; i++){
            polygons[visited[i]].lines.add(fences[i]);
        }
        for(int i = 0; i < cows.length; i++){
            Line temp = new Line(cows[i], new Point(2000000, (int) (cows[i].y + 1)));
            for(int p = 1; p < num; p++){
                if(isIn(temp, polygons[p])){
                    cows[i].str += "" + p;
                }
            }
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < cows.length; i++){
            if(map.containsKey(cows[i].str)){
                map.put(cows[i].str, map.get(cows[i].str) + 1);
            }else{
                map.put(cows[i].str, 1);
            }
        }
        int ans = 0;
        for(String ss : map.keySet()){
            ans = Math.max(ans, map.get(ss));
        }
        PrintWriter out = new PrintWriter(new File("crazy.out"));
        out.println(ans);
        out.close();
    }
    public static boolean isIn(Line l, Polygon p){
        int count = 0;
        for(Line i : p.lines){
            if(intersect(i, l)){
                count++;
            }
        }
        return count % 2 == 0;
    }
    public static void floodFill(int start, int fill, Line[] fences){
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(start);
        while(q.isEmpty() == false){
            int current = q.poll();
            visited[current] = fill;
            if(visited[fences[current].connect.get(0)] == 0){
                q.add(fences[current].connect.get(0));
            }
            if(visited[fences[current].connect.get(1)] == 0){
                q.add(fences[current].connect.get(1));
            }
        }
    }
    public static int ccw(Point p0, Point p1, Point p2){
        long dx1, dx2, dy1, dy2;
        dx1 = p1.x - p0.x;
        dy1 = p1.y - p0.y;
        dx2 = p2.x - p0.x;
        dy2 = p2.y - p0.y;
        if(dx1 * dy2 > dy1 * dx2){
            return 1;
        }
        if(dx1* dy2 < dy1 * dx2){
            return -1;
        }
        if((dx1 * dx2 < 0) || (dy1 * dy2 < 0)){
            return -1;
        }
        if((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2)){
            return 1;
        }
        return 0;
    }
    
    public static boolean intersect(Line l1, Line l2){
        return ((ccw(l1.p1, l1.p2, l2.p1) 
                * ccw(l1.p1, l1.p2, l2.p2)) <= 0)
                && ((ccw(l2.p1, l2.p2, l1.p1) 
                * ccw(l2.p1, l2.p2, l1.p2)) <= 0);
    }
}

class Point{
    long x;
    long y;
    String str = "";
    public Point(int a, int b){
        x = a;
        y = b;
    }
    public boolean equals(Point other){
        return (this.x == other.x) && (this.y == other.y);
    }
}

class Line{
    Point p1;
    Point p2;
    ArrayList<Integer> connect;
    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
        connect = new ArrayList<Integer>(2);
    }
}

class Polygon{
    ArrayList<Line> lines;
    public Polygon(){
        lines = new ArrayList<Line>();
    }
}