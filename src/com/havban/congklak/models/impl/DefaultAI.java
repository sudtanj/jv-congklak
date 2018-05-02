package com.havban.congklak.models.impl;

import java.util.ArrayList;
import java.util.Arrays;

import com.havban.congklak.models.Board;
import com.havban.congklak.models.Player;
import com.havban.congklak.models.Position;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class DefaultAI implements Player {

	private int id;
    private String name;
	private ArrayList<DefaultBoard> simulatedBoard;
    
	public DefaultAI(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.simulatedBoard= new ArrayList<DefaultBoard>();
	}
	
	private void copyBoard(Board o) {
		for(int i=0;i<7;i++) 
			this.simulatedBoard.add(new DefaultBoard((DefaultBoard) o));
	}
	
	public Integer aiScanning(Board o) {
		this.copyBoard(o);
		int maxPosition=0;
		ArrayList<Integer> possibleMove=new ArrayList<Integer>(),numberOfSeed=new ArrayList<Integer>();
		for(int i=0;i<7;i++) { 
			numberOfSeed.add(this.simulatedBoard.get(i).getHole(new Position(this,i)).getNumberOfSeed());
			this.simulatedBoard.get(i).walk(this, new Position(this,i));
			possibleMove.add(this.simulatedBoard.get(i).getHole(new Position(this,7)).getNumberOfSeed());
			//System.out.println(this.simulatedBoard.get(i).getHole(new Position(this,7)));
		}
		
		System.out.println("Possible AI Move");
		for(int i=0;i<possibleMove.size();i++) {
		System.out.println(i+". "+possibleMove.get(i));
			if(possibleMove.get(maxPosition)<=possibleMove.get(i))
				if(numberOfSeed.get(i)>0)
					maxPosition=i;	
		}
		int temporaryMax=possibleMove.get(0);
		Boolean allSame=true;
		for(int i=0;i<possibleMove.size();i++) {
			if(!possibleMove.get(maxPosition).equals(temporaryMax)) {
				allSame=false;
			}
		}
		if(allSame==true) {
			for(int i=0;i<numberOfSeed.size();i++) {
				if(numberOfSeed.get(i)>0) {
					maxPosition=i;
					break;
				}
			}
		}
		Boolean isAllZero=true;
		for(int i=0;i<numberOfSeed.size();i++) {
			if(!numberOfSeed.get(i).equals(0)) {
				isAllZero=false;
			}
		}
		if(isAllZero==true) {
			maxPosition=7;
		}
		System.out.println("Ai choose "+maxPosition);
		this.simulatedBoard.clear();
		return maxPosition;
	}


	@Override
    public String toString(){
        return "AI "+id+" - "+name;
    }

	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		 if (this == o)
	            return true;
	        if (o == null)
	            return false;
	        if (getClass() != o.getClass())
	            return false;
	        Player p = (Player) o;
	        if (id != p.getId())
	            return false;
	        return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
