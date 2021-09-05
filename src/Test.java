package TableEntities;
import java.util.*;
import java.io.*;

public class Test {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ip[]=Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		int N=ip[0];
		int M=ip[1];
		int mat[][]=new int[N][M];
		for(int i=0;i<N;i++) {
			mat[i]=Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.println(mat[i][j]);
			}
		}
	}

}
