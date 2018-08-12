package trolleyRoute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BellmanFordAlgo {

	private double[] oilCost;
	private double[][] roads;	
	private int[][] prevCities;

	private int citiesCount;
	
	public BellmanFordAlgo(double[] oilCost, Set<TrainRoad> trainRoads) {		
		this.oilCost = oilCost;
		this.citiesCount = oilCost.length;
		buildRoadMatrix(trainRoads);
	} 
	
	private void buildRoadMatrix(Set<TrainRoad> trainRoads) {
		roads = new double[citiesCount][citiesCount];
		for (int i = 0; i < citiesCount; i++) {			
			for (int j = 0; j < citiesCount; j++) {
				roads[i][j] = Double.MAX_VALUE;
			}
			roads[i][i] = 0;
		}
		for (TrainRoad road : trainRoads) {
			roads[road.getCityIdFrom() - 1][road.getCityIdTo() - 1] = oilCost[road.getCityIdFrom() - 1];
			roads[road.getCityIdTo() - 1][road.getCityIdFrom() - 1] = oilCost[road.getCityIdTo() - 1];			
		}
	}	
	
	public List<Integer> getRoute() {
		prevCities = new int[citiesCount][citiesCount];
		for (int i = 0; i < citiesCount; i++) {
			for (int j = 0; j < citiesCount; j++) {
				prevCities[i][j] = -1;
			}
		}
		prevCities[0][0] = 0;
		
		double[][] distances = new double[citiesCount][citiesCount];
		for (int i = 0; i < citiesCount; i++) {
			distances[i][0] = Double.MAX_VALUE;
		}
		distances[0][0] = 0;
		
		for (int k = 1; k< citiesCount; k++) {
			for (int i = 0; i< citiesCount; i++) {
				double min = Double.MAX_VALUE;
				int prevCity = -1;
				for (int j = 0; j< citiesCount; j++) {
					if ( distances[j][k - 1] + roads[j][i] < min) {
						min = distances[j][k - 1] + roads[j][i];
						prevCity = j;
					}
				}
				distances[i][k] = min;
				prevCities[i][k] = prevCity;
			}
		}
				
		return generateCityList();
	}
	
	private List<Integer> generateCityList() {
		List<Integer> citiesList = new ArrayList<>();
		int prevCity = citiesCount - 1; 
		citiesList.add(prevCity);
		for (int i = citiesCount - 1; i >= 0; i--) {
			if (prevCity != prevCities[prevCity][i]) {
				citiesList.add(prevCities[prevCity][i]);
				prevCity = prevCities[prevCity][i];
			}
		}
		// city id will be start with 1 
		citiesList = citiesList.stream().map(x -> x + 1).collect(Collectors.toList());		
		Collections.reverse(citiesList);
		return citiesList;
	}
}
