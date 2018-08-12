package trolleyRoute;

public class TrainRoad {

	private int cityIdFrom;
	private int cityIdTo;
	
	public TrainRoad(int cityIdFrom, int cityIdTo) {
		this.cityIdFrom = cityIdFrom;
		this.cityIdTo = cityIdTo;
	}

	public int getCityIdFrom() {
		return cityIdFrom;
	}
	
	public int getCityIdTo() {
		return cityIdTo;
	}	
}
