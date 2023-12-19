import java.io.*;
import java.util.*;

class Rut {


    static class Event{

        int time;
        int id1;
        int id2;
        boolean north;
    }


    public static void main(String[]args) throws IOException {
        //BufferedReader br = new BufferedReader(new FileReader("in.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        boolean[]north = new boolean[N];
        int[]x = new int[N];
        int[]y = new int[N];

        for(int i = 0; i< N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            north[i] = st.nextToken().equals("N");
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }


        Map<Integer, Set<Event>>events = new TreeMap<>();


        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                if(north[i] != north[j]){
                    if(north[i]){
                        Event e = checkCollision(x[i], y[i], x[j], y[j], i, j);

                        if(e != null){
                            if(!events.containsKey(e.time)){
                                events.put(e.time, new HashSet<>());
                            } 
                            events.get(e.time).add(e);
                        }
                    } else{
                        Event e = checkCollision(x[j], y[j], x[i], y[i], j, i);
                        if(e != null){
                            if(!events.containsKey(e.time)){
                                events.put(e.time, new HashSet<>());
                            } 
                            events.get(e.time).add(e);
                        }
                        
                    }
                }
                
            }
        }
        int[]outs = new int[N];
        int lastTime = 0;


        for(int time: events.keySet()){
            for(int i = 0; i< N; i++){
                if(outs[i] == 0){
                    if(north[i]){
                        y[i] += time-lastTime;
                    } else{
                        x[i] += time-lastTime;
                    }
                }

            }
            //System.out.println(time);
            for(Event e: events.get(time)){
                
                //System.out.println(e.id1 + " " + e.id2);
                if(e.north){
                    if(x[e.id2] > x[e.id1] && y[e.id1] == y[e.id2]){
                        outs[e.id1] = time;
                    }
                } else{
                    if(y[e.id1] > y[e.id2] && x[e.id2] == x[e.id1]){
                        outs[e.id2] = time;
                    }

                }
            }
            lastTime = time;

        }

        for(int eat: outs){
            if(eat == 0){
                System.out.println("Infinity");
            } else{
                System.out.println(eat);
            }
        }


    }

    public static Event checkCollision(int x1, int y1, int x2, int y2, int id1, int id2) {
        //System.out.println(x1 +" "+y1 +" "+ x2 +" "+y2);
        if(y1 <= y2 && x2 <= x1){
            if(y2 - y1 > x1-x2){
                Event e = new Event();
                e.time = y2-y1;
                e.id1 = id1;
                e.id2 = id2;
                e.north = true;

                return e;

            } else if(x1-x2 > y2 - y1){
                Event e = new Event();
                e.time = x1-x2;
                e.id1 = id1;
                e.id2 = id2;
                e.north = false;

                return e;
            }
        } 
        return null;
    }
}