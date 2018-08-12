package trolleyRoute;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DijkstraAlgo {
	private double[] oilCost;
	private int[][] roads;  
	private City[] cities;
	private int citiesCount;
	
	private class City {
		private int id;
		private double cost = Double.MAX_VALUE;
		private boolean visited = false;
		private City prevCity = null;
		City(int id) {
			this.id = id; 
		}
	}
	
	public DijkstraAlgo(double[] oilCost, Set<TrainRoad> trainRoads) {		
		this.oilCost = oilCost;
		this.citiesCount = oilCost.length;
		buildRoadMatrix(trainRoads);
		
		cities = new City[citiesCount];
		for (int i = 0; i < citiesCount; i++) {
			cities[i] = new City(i);
		}
	} 
	
	private void buildRoadMatrix(Set<TrainRoad> trainRoads) {
		roads = new int[citiesCount][citiesCount];
		for (TrainRoad road : trainRoads) {
			roads[road.getCityIdFrom() - 1][road.getCityIdTo() - 1] = 1;
			roads[road.getCityIdTo() - 1][road.getCityIdFrom() - 1] = 1;			
		}
	}	
	
	public List<Integer> getRoute() {		
		cities[0].cost = 0;
		for (int i = 0; i < citiesCount; i++) {			
			City currentCity = findUnvisitedCityWithMinCost(); 
			
			for (City city: cities) {
				if (city.visited) {
					continue;
				}
				if (roads[currentCity.id][city.id] == 1) {
					if (city.cost > oilCost[currentCity.id] + currentCity.cost) {
						city.cost = oilCost[currentCity.id] + currentCity.cost;
						city.prevCity = currentCity;
					}
				}				
			}
			currentCity.visited = true;
		}		
		return generateCityList();
	}
	
	private City findUnvisitedCityWithMinCost() {
		double min_val = Double.MAX_VALUE;
		City cityMin = null;
		for (City city : cities) {
			if (city.visited) {
				continue;
			}
			
			if (city.cost <= min_val) {
				min_val = city.cost;
				cityMin = city;
			}
		}
		assert (cityMin != null);
		return cityMin;
	}	
	
	private List<Integer> generateCityList() {
		City city = cities[citiesCount - 1];		
		List<Integer> citiesList = new LinkedList<>();
		while (city != null) {
			citiesList.add(city.id);
			city = city.prevCity;
		}
		// city id will be start with 1 
		citiesList = citiesList.stream().map(x -> x + 1).collect(Collectors.toList());
		Collections.reverse(citiesList);
		return citiesList;
	}
}
