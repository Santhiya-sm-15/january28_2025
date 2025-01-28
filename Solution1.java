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