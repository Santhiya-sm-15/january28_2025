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