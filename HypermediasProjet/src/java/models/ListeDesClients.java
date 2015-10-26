/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import views.Client;

/**
 *
 * @author bruno
 */
public class ListeDesClients {
    
    private ArrayList<Client> listeClients;
    
    public ListeDesClients(){
        setListeClients(new ArrayList<>());
    }
    
    public void addToClientList(Client client){
        getListeClients().add(client);
    }

    public ArrayList<Client> getListeClients() {
        return listeClients;
    }

    private void setListeClients(ArrayList<Client> listeClients) {
        this.listeClients = listeClients;
    }
    
}