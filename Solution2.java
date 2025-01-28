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