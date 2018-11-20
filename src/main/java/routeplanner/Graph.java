package routeplanner;

import java.util.HashMap;
import java.util.Map;

//Main class to read the command line argument to construct the graph
public class Graph {
    public static Map<Character, Integer> cityIndex = new HashMap<>();
    public static int[][] adjacencyMatrix = new int[5][5];
    public static final int CITY_COUNT = 5;
    public static void main(String[] args){
        populateCityIndex();
        String[] routes = args[0].split(",");
        populateAdjacencyMatrix(routes);

        System.out.println(computePathDistance("A-B-C"));
        System.out.println(computePathDistance("A-D"));
        System.out.println(computePathDistance("A-D-C"));
        System.out.println(computePathDistance("A-E-B-C-D"));
        System.out.println(computePathDistance("A-E-D"));
        System.out.println(numberOfTrips('C','C', 3, false, true));
        System.out.println(numberOfTrips('A','C', 4, true, true));
        System.out.println(shortestPathBetweenNodes('A', 'C'));
        System.out.println(shortestPathBetweenNodes('B', 'B'));
        System.out.println(numberOfTrips('C','C', 30, false,false));
    }

    //Computes the path distance between two cities
    public static String computePathDistance(String path) {
        int pathLength = 0;
        String[] vertices = path.split("-");
        for (int index = 0; index < vertices.length - 1; index++) {
            int src = Graph.cityIndex.get(vertices[index].charAt(0));
            int dest = Graph.cityIndex.get(vertices[index + 1].charAt(0));
            if (Graph.adjacencyMatrix[src][dest] == -1) {
                return "NO SUCH ROUTE";
            } else {
                pathLength += Graph.adjacencyMatrix[src][dest];
            }
        }
        return String.valueOf(pathLength);
    }

    public static int numberOfTrips(char src, char dest, int noOfStops, boolean exactMatch, boolean basedOnStops) {
        TripPlanner tripPlanner = new TripPlanner();
        if(basedOnStops)
            return (tripPlanner.tripsPossibleWithGivenStops(adjacencyMatrix, cityIndex.get(src), cityIndex.get(dest),
                    noOfStops, exactMatch));
        else{
            return (tripPlanner.tripsPossibleWithGivdestistance(adjacencyMatrix, cityIndex.get(src), cityIndex.get(dest),
                    noOfStops));
        }
    }

    public static int shortestPathBetweenNodes(char src, char dest) {
        ShortestPath sPath = new ShortestPath();
        return (sPath.shortestPathDistance(adjacencyMatrix, cityIndex.get(src), cityIndex.get(dest)));
    }


    private static void populateCityIndex(){
        for (int i = 65; i <= 65+CITY_COUNT; i++) {
            cityIndex.put((char)i, i-65);
        }

    }

    //Populates the citi graph into an adjacency matrix
    private static void populateAdjacencyMatrix(String[] routes){
        for(String route:routes){
            adjacencyMatrix[cityIndex.get(route.charAt(0))][cityIndex.get(route.charAt(1))] = Character.getNumericValue(route.charAt(2));
        }
        for(int i=0;i<CITY_COUNT;i++){
            for(int j=0;j<CITY_COUNT;j++){
                if(adjacencyMatrix[i][j] == 0 )
                    adjacencyMatrix[i][j] = -1;

            }
        }
    }
}
