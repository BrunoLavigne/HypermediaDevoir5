/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
import views.Produits;

/**
 *
 * @author bruno
 */
public class Cart implements Serializable{
    
    // ATTRIBUTS
    private static final long serialVersionUID = 1L;
    private int nbItems;
    private Produits item;
    private String clientName;
    private ArrayList<Produits> cartClient;   
    
    // CONSTRUCTEURS
    
    public Cart(){
        setCartClient(new ArrayList<Produits>());
        setClientName("guest");
        setNbItems(0);
    }
    
    public Cart(String clientName){
        setClientName(clientName);
        setNbItems(0);
        setCartClient(new ArrayList<Produits>());
    }
    
    // METHODES
    
    /**
     * Ajoute un item à la Cart du client
     * @param prod Le produit à ajouter
     * @param qty La quantiter commandée du produit
     */
    public void addItem(Produits prod, int qty){
        
        if(getCartClient().contains(prod)){
            for(Produits p : getCartClient()){
                if(p.getCode().equals(prod.getCode())){
                    p.setQty(p.getQty() + qty);
                }
            }           
        }
        else{
            prod.setQty(qty);
            getCartClient().add(prod);
        }   

        setNbItems(getNbItems() + qty);
    }   
    
    /**
     * Retour le produit qui se trouve à l'index <code>index</code>
     * @param index L'index du produit recherché
     * @return 
     */
    public Produits getCartProduct(int index){
        return getCartClient().get(index);
    }
    
    /**
     * Retire un produit de la Cart du client
     * @param code Le code du produit à retirer
     */
    public void removeProduct(String code){

        for(int i = 0; i < getCartClient().size(); i++){
            if(getCartClient().get(i).getCode().equals(code)){
                getCartClient().remove(i);
            }
        }
    }
    
    /**
     * Vide la Cart du client complètement
     */
    public void eraseCart(){
        getCartClient().clear();
    }
    
    /**
     * Calcul le prix total des produits qui se trouve sur la Cart
     * @return Le prix total
     */
    public float getTotal(){
        float total = 0;
        for(Produits p : getCartClient()){
            total += (p.getBaseUnitPrice() * p.getQty());
        }
        
        return total;
    }
    
    @Override
    public String toString(){
        String message = "";
        for(Produits p : cartClient){
            message += p.getDescription() + "\"&emsp;\"" + p.getQty() + "\"&emsp;\"" + p.getBaseUnitPrice() + " $" + "</br>";
        }
        return message;
    }
    
    // GETTER ET SETTER

    public int getNbItems() {
        return nbItems;
    }

    private void setNbItems(int nbItems) {
        this.nbItems = nbItems;
    }

    public Produits getItem() {
        return item;
    }

    private void setItem(Produits item) {
        this.item = item;
    }

    public String getClientName() {
        return clientName;
    }

    private void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ArrayList<Produits> getCartClient() {
        return cartClient;
    }

    private void setCartClient(ArrayList<Produits> cartClient) {
        this.cartClient = cartClient;
    }  
    
}
