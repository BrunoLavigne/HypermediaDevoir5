/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.Serializable;

/**
 *
 * @author bruno
 */
public class Produits implements Serializable{
    private static final long serialVersionUID = 1L;
    private String code;
    private String categorie;
    private String nom;
    private String description;
    private String imageURL;
    private float baseUnitPrice;
    private String qtyType;
    private int qty;
    
    public Produits() {
        setCode("");
        setCategorie("");
        setNom("");
        setDescription("");
        setImageURL("");
        setBaseUnitPrice(0);
        setQtyType("");
        setQty(0);
    }
    
    public Produits(String code, String categorie, String nom, String description, String imageURL, float baseUnitPrice, String qtyType, int qty){
        
        setCode(code);
        setCategorie(categorie);
        setNom(nom);
        setDescription(description);
        setImageURL(imageURL);
        setBaseUnitPrice(baseUnitPrice);
        setQtyType(qtyType);
        setQty(qty);
    }
        
    private void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    private void setBaseUnitPrice(float baseUnitPrice) {
        this.baseUnitPrice = baseUnitPrice;
    }

    public float getBaseUnitPrice() {
        return baseUnitPrice;
    }

    private void setQtyType(String qtyType) {
        this.qtyType = qtyType;
    }

    public String getQtyType() {
        return qtyType;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    private void setImageURL(String linkedFileName) {
        this.imageURL = linkedFileName;
    }

    public String getImageURL() {
        return imageURL;
    }
    
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
}
