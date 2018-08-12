package trolleyRoute;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FloydWarshallAlgo {

	private double[] oilCost;
	private double[][] roads;
	private int[][] cities;	

	private int citiesCount;
	
	public FloydWarshallAlgo(double[] oilCost, Set<TrainRoad> trainRoads) {		
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
		cities = new int[citiesCount][citiesCount];
		for (int i = 0; i < citiesCount; i++) {
			for (int j = 0; j < citiesCount; j++) {
				cities[i][j] = -1;
			}
		}
		
		for (int k = 0; k< citiesCount; k++) {
			for (int i = 0; i< citiesCount; i++) {
				for (int j = 0; j< citiesCount; j++) {
					if (roads[i][j] > roads[i][k] + roads[k][j]) {
						roads[i][j] = roads[i][k] + roads[k][j];
						cities[i][j] = k;
					}
				}
			}
		}
			
		return generateCityList();
	}
	
	private List<Integer> generateCityList() {
		List<Integer> citiesList = generateCityList(0, citiesCount - 1);
		// city id will be start with 1 
		citiesList = citiesList.stream().map(x -> x + 1).collect(Collectors.toList());		
		// Remove duplicates
		Set<Integer> s = new LinkedHashSet<>(citiesList);				
		return new ArrayList<>(s);
	}
	
	
	private List<Integer> generateCityList(int cityFrom, int cityTo) {
		List<Integer> citiesList = new ArrayList<>();
		int middleCity = cities[cityFrom][cityTo];
		if (middleCity == -1) {
			citiesList.add(cityFrom);
			citiesList.add(cityTo);
			return citiesList;
		}
		List<Integer> leftList = generateCityList(cityFrom, middleCity);
		citiesList.addAll(leftList);
		List<Integer> rightList = generateCityList(middleCity, cityTo);
		citiesList.addAll(rightList);		
		return citiesList;
	}
}
