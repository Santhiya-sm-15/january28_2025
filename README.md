# january28_2025
The problems that I solved today

1.You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents: A land cell if grid[r][c] = 0, or A water cell containing grid[r][c] fish, if grid[r][c] > 0. A fisher can start at any water cell (r, c) and can do the following operations any number of times: Catch all the fish at cell (r, c), or Move to any adjacent water cell. Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists. An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.

Code:
class Solution {
    public int findMaxFish(int[][] grid) {
        int i,j,n=grid.length,m=grid[0].length;
        boolean[][] visited=new boolean[n][m];
        int[][] dir={{0,-1},{-1,0},{0,1},{1,0}};
        int max=0;
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(!visited[i][j] && grid[i][j]!=0)
                    max=Math.max(max,bfs(i,j,visited,grid,dir));
            }
        }
        return max;
    }
    public int bfs(int i,int j,boolean[][] visited,int[][] grid,int[][] dir)
    {
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{i,j});
        visited[i][j]=true;
        int sum=0,n=grid.length,m=grid[0].length;
        sum+=grid[i][j];
        while(!q.isEmpty())
        {
            int[] x=q.poll();
            for(int[] d:dir)
            {
                int nr=x[0]+d[0];
                int nc=x[1]+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc] && grid[nr][nc]!=0)
                {
                    visited[nr][nc]=true;
                    q.add(new int[]{nr,nc});
                    sum+=grid[nr][nc];
                }
            }
        }
        return sum;
    }
}

2.Given an adjacency list for a Directed Acyclic Graph (DAG) where adj[u] contains a list of all vertices v such that there exists a directed edge u -> v. Return topological sort for the given graph. Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u -> v, vertex u comes before v in the ordering.
Note: As there are multiple Topological orders possible, you may return any of them. If your returned Topological sort is correct then the output will be 1 else 0. 

Code:
class Solution {
    static void dfs(int src,boolean[] visited,ArrayList<ArrayList<Integer>> adj,Stack<Integer> st)
    {
        visited[src]=true;
        for(int a:adj.get(src))
        {
            if(!visited[a])
                dfs(a,visited,adj,st);
        }
        st.push(src);
    }
    static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        int n=adj.size();
        Stack<Integer> st=new Stack<>();
        int i;
        boolean[] visited=new boolean[n];
        for(i=0;i<n;i++)
        {
            if(!visited[i])
                dfs(i,visited,adj,st);
        }
        ArrayList<Integer> res=new ArrayList<>();
        while(!st.isEmpty())
            res.add(st.pop());
        return res;
    }
}

3.There are a total of n tasks you have to pick, labelled from 0 to n-1. Some tasks may have prerequisite tasks, for example to pick task 0 you have to first finish tasks 1, which is expressed as a pair: [0, 1] Given the total number of n tasks and a list of prerequisite pairs of size m. Find a ordering of tasks you should pick to finish all tasks. Note: There may be multiple correct orders, you just need to return any one of them. If it is impossible to finish all tasks, return an empty array. Driver code will print "No Ordering Possible", on returning an empty array. Returning any correct order will give the output as 1, whereas any invalid order will give the output 0. 

Code:
class Solution
{
    static ArrayList<Integer>[] adj;
    static void topo(int[] indegree,List<Integer> l)
    {
        Queue<Integer> q=new LinkedList<>();
        int i;
        for(i=0;i<indegree.length;i++)
        {
            if(indegree[i]==0)
                q.add(i);
        }
        while(!q.isEmpty())
        {
            int x=q.poll();
            l.add(x);
            for(int a:adj[x])
            {
                indegree[a]--;
                if(indegree[a]==0)
                    q.add(a);
            }
        }
    }
    static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) 
    {
        adj=new ArrayList[n];
        int i;
        for(i=0;i<n;i++)
            adj[i]=new ArrayList<>();
        int[] indegree=new int[n];
        for(ArrayList<Integer> x:prerequisites)
        {
            adj[x.get(1)].add(x.get(0));
            indegree[x.get(0)]++;
        }
        List<Integer> l=new ArrayList<>();
        topo(indegree,l);
        int[] res=new int[l.size()];
        for(i=0;i<l.size();i++)
            res[i]=l.get(i);
        if(res.length!=n)
            return new int[0];
        return res;
    }
}

4.A directed graph of V vertices and E edges is given in the form of an adjacency list adj. Each node of the graph is labelled with a distinct integer in the range 0 to V - 1. A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node. You have to return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

Code:
class Solution {
    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        ArrayList<Integer>[] ad=new ArrayList[V];
        int i;
        for(i=0;i<V;i++)
            ad[i]=new ArrayList<>();
        int[] indegree=new int[V];
        for(i=0;i<V;i++)
        {
            for(int a:adj.get(i))
            {
                ad[a].add(i);
                indegree[i]++;
            }
        }
        Queue<Integer> q=new LinkedList<>();
        for(i=0;i<V;i++)
        {
            if(indegree[i]==0)
                q.add(i);
        }
        List<Integer> res=new ArrayList<>();
        while(!q.isEmpty())
        {
            int x=q.poll();
            res.add(x);
            for(int a:ad[x])
            {
                indegree[a]--;
                if(indegree[a]==0)
                    q.add(a);
            }
        }
        Collections.sort(res);
        return res;
    }
}
