/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adam
 */
public class Player {
    String GCMId;

    public Player(String GCMId) {
        this.GCMId = GCMId;
    }

    public String getGCMId() {
        return GCMId;
    }
    
}
