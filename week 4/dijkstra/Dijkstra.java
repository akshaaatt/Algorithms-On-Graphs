import java.util.*;

public class Dijkstra {
	
	private static final int inf = Integer.MAX_VALUE;
	
	public static class Node implements Comparable<Node> {
		int index;
		long distance;
		
		public Node(int index, long distance) {
            this.index = index;
            this.distance = distance;
        }
		
		@Override
		public int compareTo(Node o) {
            return Long.compare(this.distance, o.distance);
		}
	}
	
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
		int[] dist = new int[adj.length];
        Arrays.fill(dist, inf);
		dist[s] = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(new Node(s, dist[s]));
		while(!queue.isEmpty()){
			Node u = queue.remove();
			int u_index = u.index;
			for (int v : adj[u_index]) {
				int v_index = adj[u_index].indexOf(v);
				if (dist[v] > dist[u_index] + cost[u_index].get(v_index)) {
                    dist[v] = dist[u_index] + cost[u_index].get(v_index);
                    queue.add(new Node(v, dist[v]));
                }
			}
		}
		if(dist[t] == inf)
			return -1;
        return dist[t];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

