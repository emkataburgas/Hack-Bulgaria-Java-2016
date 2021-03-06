package DungeonsAndZombies;

public class Hero extends Unit{
	String name;
	String title;
	int manaRegen;

	public Hero(String name, String title, int hp, int mp, int manaRegen) {
		super(hp, mp);
		this.name = name;
		this.title = title;
		this.manaRegen = manaRegen;
	}

	public String knownAs() {
		return name + " the " + title;
	}
	
	public int getManaRegen() {
		return manaRegen;
	}
	
	private void useMana(int amount) {
		mana -= amount;
	}
	
	public int getDamage(String weaponType) {
		int damage = 2;
		switch (weaponType) {
		case "weapon":
			damage = weapon.getDamage();
			break;
		case "spell":
			if (mana >= spell.getManaCost()) {
				damage = spell.getDamage();
			}
			break;
		}
		return damage;
	}
	
	@Override
	public int attack(String type) {
		int damage = 2;
		switch (type) {
		case "weapon": 
			if (weapon != null) {
				damage = weapon.damage;
			}
			break;
		case "spell": 
			int manaCost = spell.getManaCost();
			if (spell != null && getMana() >= manaCost) {
				damage = spell.damage;
				useMana(manaCost);
			}
			break;
		}
		
		return damage;
	}
	
	@Override
	public void death() {
		System.out.println("The mighty hero " + name + " the " + title + " was killed!");
	}
}
