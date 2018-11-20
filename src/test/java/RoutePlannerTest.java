import org.junit.Before;
import org.junit.Test;
import routeplanner.ShortestPath;
import routeplanner.TripPlanner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RoutePlannerTest {

    public static int[][] adjacencyMatrix = new int[5][5];
    ShortestPath shortestPath = new ShortestPath();
    TripPlanner tripPlanner = new TripPlanner();

    @Before
    public void populateGraph() {
        Map<Character, Integer> cityIndex = new HashMap<>();
        cityIndex.put('A', 0);
        cityIndex.put('B', 1);
        cityIndex.put('C', 2);
        cityIndex.put('D', 3);
        cityIndex.put('E', 4);
        String routes[] = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7".split(",");

        for (String route : routes) {
            adjacencyMatrix[cityIndex.get(route.charAt(0))][cityIndex.get(route.charAt(1))] = Character.getNumericValue(route.charAt(2));
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (adjacencyMatrix[i][j] == 0)
                    adjacencyMatrix[i][j] = -1;

            }
        }
    }

    @Test
    public void testShortestPathForRoute() {
        int result = shortestPath.shortestPathDistance(adjacencyMatrix, 0, 2);
        assertEquals(9, result);
    }

    @Test
    public void testShortestPathWithoutAnyRoute() {
        int result = shortestPath.shortestPathDistance(adjacencyMatrix, 1, 0);
        assertEquals(-1, result);
    }

    @Test
    public void testTripsPossibleWithMaxStops() {
        int result = tripPlanner.tripsPossibleWithGivenStops(adjacencyMatrix, 2, 2, 3, false);
        assertEquals(2, result);
    }

    @Test
    public void testTripsPossibleWithExactStops() {
        int result = tripPlanner.tripsPossibleWithGivenStops(adjacencyMatrix, 0, 2, 4, true);
        assertEquals(3, result);
    }

    @Test
    public void testNoTripsPossibleWithLessStops() {
        int result = tripPlanner.tripsPossibleWithGivenStops(adjacencyMatrix, 0, 2, 1, false);
        assertEquals(0, result);
    }

    @Test
    public void testTripsPossibleStopsWithDistance() {
        int result = tripPlanner.tripsPossibleWithGivdestistance(adjacencyMatrix, 2, 2, 30);
        assertEquals(7, result);
    }

    @Test
    public void testNoTripsPossibleStopsWithLessDistance() {
        int result = tripPlanner.tripsPossibleWithGivdestistance(adjacencyMatrix, 0, 2, 7);
        assertEquals(0, result);
    }

}