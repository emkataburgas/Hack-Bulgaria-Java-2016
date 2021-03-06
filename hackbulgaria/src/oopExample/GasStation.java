package oopExample;

import java.util.PriorityQueue;

public class GasStation {
	private int income;
	private PriorityQueue<Vignette> vignettes;
	
	public int getIncome() {
		return income;
	}
	
	public Vignette sellVignette(VehicleClass vehicleClass, TimeFrame timeFrame) {
		Vignette result = null;
		for(Vignette vignette : vignettes) {
			if (vignette.getColor().represents(vehicleClass) && vignette.getTimeFrame().equals(timeFrame)) {
				result = vignette;
				break;
			}
		}
		vignettes.remove(result);
		income += result.getPrice();
		return result;
	}

	public GasStation() {
		income = 0;
		vignettes = Vignette.generateRandomVignettes(10000);
	}

	public PriorityQueue<Vignette> getVignettes() {
		return vignettes;
	}

	public int checkPriceForVignette(VehicleClass vehicleClass, TimeFrame timeFrame) {
		int price = -1;
		for(Vignette vignette : vignettes) {
			if (vignette.getColor().represents(vehicleClass) && vignette.getTimeFrame().equals(timeFrame)) {
				price = vignette.getPrice();
				break;
			}
		}
		return price;
	}
}
