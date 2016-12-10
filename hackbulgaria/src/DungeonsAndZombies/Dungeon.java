package DungeonsAndZombies;

public class Dungeon {
	private class Position {
		int x;
		int y;
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	private class Treasure {
		Weapon[] armory = {new Weapon("Silver lance", 40), new Weapon("Silver bow", 40), new Weapon("Silver sword", 40)};
		Spell[] library = {new Spell("Fire", 45, 45, 2), new Spell("Nosferatu", 30, 45, 2), new Spell("Shine", 40, 40, 2)};
		int[] potions = {25, 50, 100};
		
		
		public Treasure() {
			
		}
		
		
	}
	
	Position coords = new Position();
	Hero hero;
	char[][] map = {
			{'S', '.', '#', '#', '.', '.', '.', '.', '.', 'T'},
			{'#', 'T', '#', '#', '.', '.', '#', '#', '#', '.'},
			{'#', '.', '#', '#', '#', 'E', '#', '#', '#', 'E'},
			{'#', '.', 'E', '.', '.', '.', '#', '#', '#', '.'},
			{'#', '#', '#', 'T', '#', '#', '#', '#', '#', 'G'}
	};
	
	
	
	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------");
	}
	
	public boolean spawn(Hero hero) {
		this.hero = hero;
		boolean success = false;
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if ( map[i][j] == 'S' || map[i][j] == '.' ) {
					map[i][j] = 'H';
					coords.setX(i);
					coords.setY(j);
					success = true;
					break;
				}
				if (success) {
					break;
				}
			}
		}
		
		return success;
	}
	
	public void moveHero(String direction) {
		switch(direction) {
		case "up": 
			if (coords.getX() > 0 && map[coords.getX() - 1][coords.getY()] != '#') {
				map[coords.getX()][coords.getY()] = '.';
				coords.setX(coords.getX() - 1);
				trigger(coords.getX(), coords.getY());
			}
			break;
		case "down": 
			if (coords.getX() < map[0].length && map[coords.getX() + 1][coords.getY()] != '#') {
				map[coords.getX()][coords.getY()] = '.';
				coords.setX(coords.getX() + 1);
				trigger(coords.getX(), coords.getY());
			}
			break;
		case "left": 
			if (coords.getY() > 0 && map[coords.getX()][coords.getY() - 1] != '#') {
				map[coords.getX()][coords.getY()] = '.';
				coords.setY(coords.getY() - 1);
				trigger(coords.getX(), coords.getY());
			}
			break;
		case "right": 
			if (coords.getY() < map.length && map[coords.getX()][coords.getY() + 1] != '#') {
				map[coords.getX()][coords.getY()] = '.';
				coords.setY(coords.getY() + 1);
				trigger(coords.getX(), coords.getY());
			}
			break;
		}
	}
	
	private void trigger(int x, int y){
		char tyle = map[x][y];
		map[x][y] = 'H';
		
		switch(tyle){
		case 'E':
			System.out.println("You encountered an enemy!");
			startBattle(new Enemy(100, 100, 100));
			break;
		case 'T':
			System.out.println("You picked up a treasure!");
			Treasure luck = new Treasure();
			System.out.println(luck);
			break;
		case 'G':
			System.out.println("You cleared the map!");
			
			break;
		}
	}
	
	private void startBattle(Enemy enemy) {
		while(hero.getHealth() > 0 && enemy.getHealth() > 0) {
			enemy.takeDamage(hero.attack("weapon"));
			System.out.println(hero.knownAs() + " attack the enemy for " + hero.attack("weapon") + " damage.");
			System.out.println("Enemy was left at " + enemy.getHealth() + "hp.");
			
			if(enemy.getHealth() > 0) {
				hero.takeDamage(enemy.attack("weapon"));
				System.out.println("The enemy attacked " + hero.knownAs() + " for " + enemy.attack("weapon") + " damage.");
				System.out.println(hero.knownAs() + " was left at " + hero.getHealth() + "hp.");
			}
		}
		
		if(enemy.getHealth() <= 0) {
			System.out.println(hero.knownAs() + " defeated the enemy!" );
		} else {
			System.out.println(hero.knownAs() + " was defeated!" );	
			hero.death();
		}
	}

	
	public static void main(String[] args) {
		Dungeon level1 = new Dungeon();
		Hero emo = new Hero("Emo", "Hacker", 100, 100, 2);
		
		Weapon sword = new Weapon("Sword of Seals", 50);
		emo.equip(sword);
		Spell dark = new Spell("Luna", 50, 20, 2);
		emo.learn(dark);
		
		level1.spawn(emo);
		level1.printMap();
		
		level1.moveHero("up");
		level1.printMap();
		level1.moveHero("left");
		level1.printMap();
		level1.moveHero("right");
		level1.printMap();
		level1.moveHero("up");
		level1.printMap();
		level1.moveHero("down");
		level1.printMap();
		level1.moveHero("down");
		level1.printMap();
		level1.moveHero("down");
		level1.printMap();
		level1.moveHero("right");
		level1.printMap();
		level1.moveHero("right");
		level1.moveHero("right");
		level1.moveHero("right");
		level1.moveHero("up");
	}
}