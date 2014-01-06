/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver.utilities;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.kth.id2212.project.gameserver.GameBean;
import se.kth.id2212.project.gameserver.entities.Player;

/**
 * Used for sending refresh queries to clients through GCM
 * @author Adam
 */
public class GCMHandler {
    /**
     * Sends the request to refresh to a player
     * @param player which should refresh
     */
    public static void sendGMC(Player player){
        
        try {
            URL url = new URL("https://android.googleapis.com/gcm/send");            
            
            HttpURLConnection httpConn= (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Authorization", "key=AIzaSyCIjwBvbJo6iWDZ5dJwuhT6_c9KrG8sDU0");
            System.out.println(httpConn);
            
            OutputStream os = httpConn.getOutputStream();         
            BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(os));
            
            osw.write("{ \"data\": {\n" +
                "    \"refresh\": \"true\",\n" +
                "  },\"registration_ids\": [ \""+ player.getGCMId()+"\" ] }");
            osw.flush();
            osw.close();
            
            System.out.println(httpConn.getResponseCode());
        } catch (IOException ex) {
            Logger.getLogger(GameBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
