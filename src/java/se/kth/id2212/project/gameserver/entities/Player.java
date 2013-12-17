/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package se.kth.id2212.project.gameserver.entities;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
*
* @author Adam
*/
@Entity
public class Player {
    @Id
    private String GCMId;
    private String name;

    public Player() {
    }
    
    

    public Player(String gCMId, String name) {
		super();
		GCMId = gCMId;
		this.name = name;
	}

	public String getGCMId() {
            return GCMId;
        }	

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((GCMId == null) ? 0 : GCMId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (GCMId == null) {
			if (other.GCMId != null)
				return false;
		} else if (!GCMId.equals(other.GCMId))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Player{" + "GCMId=" + GCMId + ", name=" + name + '}';
    }
        
        
    
    
    
}