/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author bruno
 */
public class Client {
    
    private String nom;
    private byte[] password;
    private String addresse;
    private int age;
    
    public Client(String nom, byte[] password, String addresse, int age){
        setNom(nom);
        setPassword(password);
        setAddresse(addresse);
        setAge(age);
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getPassword() {
        return password;
    }

    private void setPassword(byte[] password) {
        this.password = password;
    }

    public String getAddresse() {
        return addresse;
    }

    private void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }
    
    
    
}
