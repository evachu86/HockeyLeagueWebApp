package com.hockeyleague.model;

public enum Role {

	CENTER, DEFANSE, GOALIE, WINGER; 
	
	@Override
	public String toString() {
		
		return this.name().substring(0, 1) +
				this.name().substring(1).toLowerCase();
	}
}
