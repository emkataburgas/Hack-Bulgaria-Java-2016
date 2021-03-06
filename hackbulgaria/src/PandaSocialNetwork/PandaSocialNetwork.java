package PandaSocialNetwork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PandaSocialNetwork implements PSNInterface{
	private class Account {
		private Panda accOwner;
		private ArrayList<Panda> friends = new ArrayList<Panda>();
	
		private Account(Panda p) {
			accOwner = p;
		}
		
		private boolean equals(Account o) {
			return accOwner.equals( o.accOwner );
		}
		
		private void setFriend(Panda o) {
			if(!friends.contains(o)) {
				friends.add(o);				
			}
		}
	}
	
	private ArrayList<Account> pandas = new ArrayList<Account>();
	
	public void add(Panda p) {
		pandas.add(new Account(p));
	}
	
	public boolean hasPanda(Panda p) {
		boolean result = false;
		for (Account acc : pandas) {
			if ( acc.accOwner.equals(p) ) {
				result = true;
			}
		}
		return result;
	}
	
	public void makeFriends(Panda p1, Panda p2) {
		if(!hasPanda(p1)) {
			add(p1);
		}
		
		if(!hasPanda(p2)) {
			add(p2);
		}
		
		getAccount(p1).setFriend(p2);
		getAccount(p2).setFriend(p1);
	}
	
	private Account getAccount(Panda p1) {
		Account result = null;
		
		for (Account acc : pandas) {
			if(acc.equals(new Account(p1))) {
				result = acc;
				break;
			}
		}
		
		return result;
	}
	
	public boolean areFriends(Panda p1, Panda p2) throws Exception {
		Account accPanda1 = getAccount(p1);
		if (accPanda1 == null) {
			throw new Exception("No such panda" + p1);
		}
		
		return accPanda1.friends.contains(p2);
	}
	
	public ArrayList<Panda> friendsOf(Panda p) throws Exception {
		for (Account acc : pandas) {
			if ( acc.accOwner.equals(p) ) {
				return acc.friends;
			}
		}
		
		String message = "No such panda - " + p;
		throw new Exception(message);
	}
	
	
	public void printNetwork() {
		for (Account acc : pandas) {
			System.out.println(acc.accOwner);
			System.out.println("-------------");
		}
	}
	
	public int connectionLevel(Panda p1, Panda p2) throws Exception {
		Queue<Account> toBeChecked = new LinkedList<Account>();
		toBeChecked.add(getAccount(p1));
		ArrayList<Panda> checked = new ArrayList<Panda>(); 	
		int connectionLevel = 1;
		
		while(toBeChecked.peek() != null) {
			Queue<Account> newToBeChecked = new LinkedList<Account>();
			
			while(toBeChecked.size() != 0) {
				Account current = toBeChecked.poll();
				if (areFriends(current.accOwner, p2)) {
					return connectionLevel;
				} else {
					checked.add(current.accOwner);
					
					for(Panda p : current.friends) {
						if ( !(newToBeChecked.contains(getAccount(p)) || checked.contains(p) || toBeChecked.contains(getAccount(p))) ){
							newToBeChecked.add(getAccount(p));
						}
					}
				}
			}
			connectionLevel++;
			toBeChecked = newToBeChecked;
		}
		return -1;
	}
	
	public boolean areConnected(Panda p1, Panda p2) throws Exception {
		if ( connectionLevel(p1, p2) == -1 ) {
			return false;
		}
		return true;
	
	}
	
	public int howManyGenderInNetwork(int level, Panda panda, String gender) {
		ArrayList<Account> toBeChecked = new ArrayList<>();
		ArrayList<Account> checked = new ArrayList<>();
		
		ArrayList<Account> newToBeChecked = new ArrayList<>();
		toBeChecked.add(getAccount(panda));
		while(level > 0) {
			newToBeChecked = new ArrayList<>();
			for (Account acc : toBeChecked) {
				checked.add(acc);
				for(Panda friend : acc.friends) {
					Account toAdd = getAccount(friend);
					if( !(toBeChecked.contains(toAdd) || checked.contains(toAdd) || newToBeChecked.contains(toAdd))){
						newToBeChecked.add(toAdd);						
					}
				}
			}
			toBeChecked = newToBeChecked;
			level--;
		}
		
		int result = countGender(toBeChecked, gender);
		return result;
		
	}
	
	private int countGender(ArrayList<Account> accounts, String gender) {
		int result = 0;

		for(Account acc : accounts) {
			if(acc.accOwner.gender().equals(gender)) {
				result++;
			} 
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		Panda goshko = new Panda("goshko", "debel@sam.bg", "male");
		Panda emo = new Panda("emo", "ucha@java.az", "male");
		Panda ivan = new Panda("ivan", "ivan@van.az", "male");
		Panda ivan2 = new Panda("ivan2", "ivan2@van.az", "male");
		Panda ivan3 = new Panda("ivan3", "ivan3@van.az", "male");
		Panda ivan4 = new Panda("ivan4", "ivan4@van.az", "male");
		Panda ivan5 = new Panda("ivan5", "ivan5@van.az", "male");
		Panda jenskapanda = new Panda("jena", "drama@sigurna.bg", "female");

		PandaSocialNetwork psn = new PandaSocialNetwork();
		psn.add(goshko);
		psn.add(emo);
		psn.add(jenskapanda);
		
		psn.printNetwork();
		
		System.out.println( psn.hasPanda(emo) );
		System.out.println( psn.hasPanda(ivan) );
		psn.makeFriends(ivan, ivan2);
		psn.makeFriends(ivan, emo);
		System.out.println( psn.hasPanda(ivan) );
		System.out.println( psn.areFriends(ivan2, ivan) );
		System.out.println( psn.areFriends(emo, goshko) );
		System.out.println( psn.areFriends(jenskapanda, goshko) );
		
		System.out.println(psn.friendsOf(ivan));
		psn.makeFriends(goshko, emo);
		psn.makeFriends(ivan2, emo);
		psn.makeFriends(ivan3, ivan2);
		psn.makeFriends(ivan4, emo);
		psn.makeFriends(ivan4, ivan5);
		psn.makeFriends(ivan4, jenskapanda);
		System.out.println(psn.connectionLevel(ivan3, goshko));
		System.out.println(psn.connectionLevel(ivan3, ivan5));
		System.out.println(psn.connectionLevel(ivan5, goshko));
		System.out.println(psn.areConnected(jenskapanda,goshko));
		System.out.println(psn.howManyGenderInNetwork(2, ivan, "male"));
		System.out.println(psn.howManyGenderInNetwork(1, emo, "male"));
	}
}
