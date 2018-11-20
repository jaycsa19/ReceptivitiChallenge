package routeplanner;

public class TripPlanner {

    public int tripsPossibleWithGivenStops(int[][] adjMatrix, int src, int dest, int noOfStops, boolean exactMatch) {
        return tripsGivenStops(adjMatrix, src, dest, noOfStops, new int[1], exactMatch);
    }

    public int tripsPossibleWithGivdestistance(int[][] adjMatrix, int src, int dest, int distance) {
        return tripsGivenDistance(adjMatrix, src, dest, distance, new int[1]);
    }

    private int tripsGivenStops(int[][] adjMatrix, int src, int dest, int hops, int[] count, boolean exactMatch) {
        if (hops == 0) {
            return count[0];
        }
        for (int current = 0; current < adjMatrix[0].length; current++) {
            if (adjMatrix[src][current] != -1) {
                if (current == dest) {
                    if (exactMatch) {
                        if (hops == 1) {
                            count[0] = count[0] + 1;
                        }
                    } else {
                        count[0] = count[0] + 1;
                    }
                }
                tripsGivenStops(adjMatrix, current, dest, hops - 1, count, exactMatch);
            }
        }
        return count[0];

    }

    private int tripsGivenDistance(int[][] adjMatrix, int src, int dest, int distance, int[] count) {
        if (distance <= 0) {
            return count[0];
        }
        for (int j = 0; j < adjMatrix[0].length; j++) {
            if (adjMatrix[src][j] != -1) {
                int currentDistance = distance - adjMatrix[src][j];
                if (j == dest && currentDistance > 0) {
                    count[0] = count[0] + 1;
                }
                tripsGivenDistance(adjMatrix, j, dest, currentDistance, count);
            }
        }
        return count[0];

    }
}
