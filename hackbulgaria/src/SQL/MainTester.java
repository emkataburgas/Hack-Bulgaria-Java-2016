package SQL;

public class MainTester {
	public static void main(String[] args) {
		Client kamen = new Client("Kamen", 22);
		
		MySQLHelper db = new MySQLHelper("//localhost/sqTesting", "root", "omg");
		
		db.insertInto("Clients", kamen);
		db.deleteFrom("Clients", kamen.id);
		db.update("Clients", kamen);
		db.selectFrom("Clients", "");
	}
}
