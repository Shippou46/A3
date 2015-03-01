package Assign3;


import java.util.ArrayList;

import DB.MyDB;

public class Inventory {
	private ArrayList<Part> parts;
	private ArrayList<InventoryObserver> observers;
	public MyDB db;
	
	public Inventory(MyDB db) {
		parts = new ArrayList<Part>();
		observers = new ArrayList<InventoryObserver>();
		this.db = db;
	}
	
	public void deletePart(Part p) {
		parts.remove(p);
		updateObservers();
		p.updateDeleted();//signal observing views that part has been deleted
	}
	
	public ArrayList<Part> getParts() {
		return parts;
	}
	
	public int getNumParts() {
		return parts.size();
	}
	
	public boolean partNameExists(String pName, Part part) {
		for(Part p : parts) {
			if(pName.equalsIgnoreCase(p.getPartName()) && (p != part || part == null))
				return true;
		}
		return false;
	}
	
	public Part addPart(Part part, String pNum, String pName, String v, int q, int id, String ex, String loc) throws IllegalArgumentException {
		if(partNameExists(pName, part))
			throw new IllegalArgumentException("Part Name already exists!");
		Part p = new Part(pNum, pName, v, q, id, ex, loc);
		parts.add(p);
		updateObservers();
		return p;
	}
	
	public void registerObserver(InventoryObserver o) {
		observers.add(o);
	}
	
	public void updateObservers() {
		for(InventoryObserver o : observers) {
			try {
				o.updateObserver(this);
			} catch(Exception e) {
				//ignore for now
			}
		}
	}
}
