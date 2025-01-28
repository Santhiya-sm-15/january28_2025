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
