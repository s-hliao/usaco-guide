import java.io.*;
import java.util.*;

class Art {
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("art.in"));
        //BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("art.out")));
        
        int N = Integer.parseInt(br.readLine());

        int[][]art = new int[N][N];

        boolean[] inPic = new boolean[10];
        int[]x1 = new int[10];
        int[]y1 = new int[10];
        int[]x2 = new int[10]; 
        int[]y2 = new int[10];

        Set<Integer> possible = new HashSet<>();

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < N; j++){
                int num = line.charAt(j) -'0';
                if(num != 0){
                    art[i][j] = num;

                    if(!inPic[num]){
                        x1[num] = j;
                        x2[num] = j;
                        y1[num] = i;
                        y2[num] = i;
                        inPic[num] = true;

                        possible.add(num);
                    } 

                    if(j < x1[num]) x1[num] = j;
                    if(j > x2[num]) x2[num] = j;
                    if(i < y1[num]) y1[num] = i;
                    if(i > y2[num]) y2[num] = i;
                }
                
            }
        }

        
        for(int num = 1; num< 10; num++){
            if(inPic[num]){
                for(int i = y1[num]; i <= y2[num]; i++){
                    for(int j = x1[num]; j<= x2[num]; j++){
                        if(art[i][j] != num && possible.contains(art[i][j])){
                            possible.remove(art[i][j]);
                            System.out.println(num+" contains "+art[i][j]);
                        }
                    }
                }
            }
        }

        pw.println(possible.size());
        System.out.println(possible.size());
    
        br.close();
        pw.close();
        
    }
    
}
