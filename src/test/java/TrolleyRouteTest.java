import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import trolleyRoute.BellmanFordAlgo;
import trolleyRoute.DijkstraAlgo;
import trolleyRoute.FloydWarshallAlgo;
import trolleyRoute.TrainRoad;

public class TrolleyRouteTest {

	@Test 
	public void MinCityCounts() {
		double[] oilCost = new double[] {2, 3}; 
		Set<TrainRoad> trainRoads = new HashSet<>();
		trainRoads.add(new TrainRoad(1, 2));
		
		List<Integer> result = new ArrayList<>();
		result.add(1);
		result.add(2);
		
		DijkstraAlgo tr = new DijkstraAlgo(oilCost, trainRoads);
		assertEquals(result, tr.getRoute());
		BellmanFordAlgo bf = new BellmanFordAlgo(oilCost, trainRoads);
		assertEquals(result, bf.getRoute());		
		FloydWarshallAlgo fw = new FloydWarshallAlgo(oilCost, trainRoads);
		assertEquals(result, fw.getRoute());
	}
	
	
	@Test 
	public void TrolleyRoute() {
		double[] oilCost = new double[] {5, 10, 1}; 
		Set<TrainRoad> trainRoads = new HashSet<>();
		trainRoads.add(new TrainRoad(1, 2));
		trainRoads.add(new TrainRoad(2, 3));
		trainRoads.add(new TrainRoad(3, 1));
		
		List<Integer> result = new ArrayList<>();
		result.add(1);
		result.add(3);
		
		DijkstraAlgo tr = new DijkstraAlgo(oilCost, trainRoads);
		assertEquals(result, tr.getRoute());
		BellmanFordAlgo bf = new BellmanFordAlgo(oilCost, trainRoads);
		assertEquals(result, bf.getRoute());		
		FloydWarshallAlgo fw = new FloydWarshallAlgo(oilCost, trainRoads);
		assertEquals(result, fw.getRoute());
	}	

	
	@Test 
	public void TrolleyRoute2() {
		double[] oilCost = new double[] {1, 200, 3, 4, 5, 6}; 
		Set<TrainRoad> trainRoads = new HashSet<>();
		trainRoads.add(new TrainRoad(1, 2));
		trainRoads.add(new TrainRoad(2, 6));
		trainRoads.add(new TrainRoad(3, 1));
		trainRoads.add(new TrainRoad(3, 4));
		trainRoads.add(new TrainRoad(4, 5));
		trainRoads.add(new TrainRoad(5, 6));
		trainRoads.add(new TrainRoad(2, 5));
		
		List<Integer> result = new ArrayList<>();
		result.add(1);
		result.add(3);
		result.add(4);
		result.add(5);
		result.add(6);		
		
		DijkstraAlgo tr = new DijkstraAlgo(oilCost, trainRoads);
		assertEquals(result, tr.getRoute());
		BellmanFordAlgo bf = new BellmanFordAlgo(oilCost, trainRoads);
		assertEquals(result, bf.getRoute());		
		FloydWarshallAlgo fw = new FloydWarshallAlgo(oilCost, trainRoads);
		assertEquals(result, fw.getRoute());
	}
}
