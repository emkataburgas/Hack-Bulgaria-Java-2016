package DungeonsAndZombies;

public class Potion implements Treasureable{
	int healAmount;
		
	public Potion (int healAmount) {
		this.healAmount = healAmount;
	}
		
	public int getHealAmount() {
		return healAmount;
	}
		
	public void activate(Hero h) {
		int heal = getHealAmount();
		h.takeHealing(heal);
		h.takeMana(heal);
		System.out.println(h.knownAs() + "was healed by " + heal + "!");
	}
	
	public String toString() {
		return "a potion that restores " + getHealAmount() + " hp and mp!";
	}
}

