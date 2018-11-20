package routeplanner;

//Computes the shortest distance path between two cities
public class ShortestPath {

    public int shortestPathDistance(int[][] adjMatrix, int src, int dest) {
        int numOfVer = adjMatrix[0].length;
        boolean visited[] = new boolean[numOfVer];
        int shortestDist[] = new int[numOfVer];

        for (int i = 0; i < numOfVer; i++) {
            shortestDist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        findSrcNeighbours(src, adjMatrix, shortestDist);
        for (int count = 0; count < numOfVer - 1; count++) {
            int u = minDistance(shortestDist, visited, numOfVer);
            visited[u] = true;
            for (int v = 0; v < numOfVer; v++) {
                if (!visited[v] && adjMatrix[u][v] != -1 && shortestDist[u] != Integer.MAX_VALUE && shortestDist[u] + adjMatrix[u][v] < shortestDist[v])
                    shortestDist[v] = shortestDist[u] + adjMatrix[u][v];
            }
        }
        return shortestDist[dest] == Integer.MAX_VALUE ? -1 : shortestDist[dest];
    }

    private void findSrcNeighbours(int src, int[][] adjMatrix, int shortestDist[]) {
        for (int index = 0; index < adjMatrix[0].length; index++) {
            if (adjMatrix[src][index] != -1) {
                shortestDist[index] = adjMatrix[src][index];
            }
        }
    }

    private int minDistance(int shortestDist[], boolean visited[], int numOfVer) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int index = 0; index < numOfVer; index++) {
            if (visited[index] == false && shortestDist[index] <= min) {
                min = shortestDist[index];
                min_index = index;
            }
        }
        return min_index;
    }


}
