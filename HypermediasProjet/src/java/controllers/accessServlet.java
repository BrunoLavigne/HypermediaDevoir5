/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.ListeDesClients;
import views.Client;
import views.Produits;

/**
 *
 * @author Bruno, Marc-Éric
 */
@WebServlet("/controllers/accessServlet")
public class accessServlet extends HttpServlet {
    
    private HttpSession session;
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
        System.out.println("Executing doGet() method...");
        System.out.println("Exception: doGet() method not supported!\nCalling doPost() method...");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Executing doPost() method...");
        
        // Obtention des paramètres de la requête.
        Map<String, String> map = new HashMap<>();
        System.out.println("Reading request parameters...");
        Enumeration nomsParametres = request.getParameterNames();   
        
        while (nomsParametres.hasMoreElements()) {
            
            String key = (String) nomsParametres.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
            System.out.println("Parameter found: [" + key + "] with value: [" + value + "]");
            
	}
        
        // Separation selon le but de la requête (actionType)
        if(map.containsKey("actionType")){
            
            String actionType = request.getParameter("actionType");
            
            switch (actionType) {
                
                case "login":
                    System.out.println("Request login action type found...");
                    System.out.println("Beginning log-in function...");
                    login(request, response);
                    break;
                
                case "newLogin":
                    newLogin(request, response);
                    break;
                
                case "registerCustomer":
                    registerCustomer(request, response);
                    break;
                    
                case "returnToLogin":
                    System.out.println("Request return to login action type found...");
                    System.out.println("Beginning return to index function...");
                    returnToLogin(request, response);
                    break;
                    
                case "ajouterProduit":
                    System.out.println("Request add product to cart action type found...");
                    System.out.println("Beginning add-to-cart function...");
                    addToCart(request, response);
                    break;
                    
                case "retirerProduit":
                    System.out.println("Request substract product from cart action type found...");
                    System.out.println("Beginning substract-from-cart function...");
                    removeFromCart(request, response);
                    break;
                    
                case "checkout":
                    System.out.println("Request checkout action type found...");
                    System.out.println("Beginning checkout function...");
                    loadCheckout(request, response);
                    break;
                    
                case "viderCart":
                    System.out.println("Request empty-the-cart action type found...");
                    System.out.println("Beginning empty-the-cart function...");
                    clearCart(request, response);
                    break;
                    
                case "cancel":
                    System.out.println("Request checkout cancellation action type found...");
                    System.out.println("Beginning checkout cancellation function...");
                    clearCart(request, response);
                    break;
                    
                case "confirmation":
                    System.out.println("Request checkout confirmation action type found...");
                    System.out.println("Beginning checkout confirmation function...");
                    finalizeOrder(request, response);
                    break;
                    
            }
        }  
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    public void login(HttpServletRequest request, HttpServletResponse response){
        
        //request.getSession(false).invalidate();
        session = request.getSession();
        
        // TODO implementer des classes utilisateur et administrateur puis une liste d'usagers.
        
        //utilisateur
        String userName = "utilisateur";
        String userPassword = "utilisateur";
        String adminName = "administrateur";
        String adminPassword = "administrateur";
        
        //administrateur
        String username = request.getParameter("loginName");
        System.out.println("Login name received: " + username);
        byte[] encryptedPWbytes = request.getParameter("loginPW").getBytes(StandardCharsets.UTF_8);
        System.out.println("Crypted login password byte array received: " + Arrays.toString(encryptedPWbytes));
        System.out.println("Decrypting password bytes...");
        String decryptedPW = new String(Base64.getDecoder().decode(encryptedPWbytes));
        
        //String decryptedPW = request.getParameter("loginPW");
        System.out.println("Password decrypted! : " + decryptedPW);
        
        if (username.equals(userName)){
            
            System.out.println("User Name found! checking password...");
            
            if (userPassword.equals(decryptedPW)){
                
                System.out.println("Password accepted!");
                System.out.println("User is now connected.");
                session.setAttribute("username", username);
                // session.setAttribute("password", encryptedPWbytes);
                session.setAttribute("password", decryptedPW);
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                
            } else {
                
                System.out.println("Password rejected!");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                
            }
            
        } else if (username.equals(adminName)){
            
            if (adminPassword.equals(decryptedPW)){
                
                System.out.println("Password accepted!");
                System.out.println("User is now connected as administrator.");
                session.setAttribute("username", username);
                // session.setAttribute("password", encryptedPWbytes);
                session.setAttribute("password", decryptedPW);
                session.setAttribute("isAdministrator", true);
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                
            } else {
                
                System.out.println("Password rejected!");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                
            }
            
        } else if(findClientName(username) != null){
            Client client = findClientName(username);
            
            byte [] clientPW = client.getPassword();
            String decryptedClientPW = new String(Base64.getDecoder().decode(clientPW));
            
            if(decryptedClientPW.equals(decryptedPW)){
                session.setAttribute("username", username);
                session.setAttribute("password", decryptedPW);
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                
            } else {
                System.out.println("Password rejected!");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);  
            }
        }
        
        else {
            
            System.out.println("Invalid unsername!");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            
        }
                                      
        try {
            
            if(response.getStatus() != HttpServletResponse.SC_ACCEPTED){
                connectionRefused(request, response);
            } else {
                loadProducts(request, response);
            }     
            
        } catch (ServletException | IOException ex) {
            Logger.getLogger(accessServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void registerCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        session = request.getSession();
        
        String nom = request.getParameter("loginName");
        byte[] password = request.getParameter("loginPW").getBytes(StandardCharsets.UTF_8);;
        String addresse = request.getParameter("addresse");
        int age = Integer.parseInt(request.getParameter("age"));
        
        Client client = new Client(nom, password, addresse, age);
        
        if(session.getAttribute("listeClients") == null){
            setListeDesClients(new ArrayList<>());
        }
        
        getListeDesClients().add(client);
        
        String url = "/index.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
    }
    
    private void newLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = "/newCustomer.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    protected void connectionRefused(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        
        response.setContentType("text/html;charset=UTF-8");
        
        String url = "/connectionRefused.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    
    }
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void loadProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletContext();
        ArrayList<Produits> listeDesProduits = new ArrayList<>();
        
        InputStream inputStream = context.getResourceAsStream("/Ressources/listeProduits.txt");
        InputStreamReader inputStrmReader = new InputStreamReader(inputStream);
        BufferedReader productFileBuffer = new BufferedReader(inputStrmReader);
        String line = productFileBuffer.readLine();
        
        do {
            String[] parameters = line.split(";");
            Produits tempProduit = new Produits(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], Float.parseFloat(parameters[5]), parameters[6], 0);
            listeDesProduits.add(tempProduit);
            
            line = productFileBuffer.readLine();
            if (line==null) {
                System.out.println("End of products file reached...");
                break;
            }
        } while (true);
        
        session.setAttribute("listeProduits", listeDesProduits);
        
        String url = "/listerProduits.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
    
    // METHODES
    /**
     * Adding products to the cart
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("Adding product...");
        session = request.getSession();
        if (session.getAttribute("cart")==null) {
            System.out.println("Non-existant cart, creating a new one...");
            setCart(new Cart());
        } 
        String code = request.getParameter("productNumber");
        System.out.println("productNumber parameter calue: " + code);
        int qty = Integer.parseInt(request.getParameter("qty"));
        System.out.println("qty parameter calue: " + qty);
        getCart().addItem(getProductByCode(code), qty);
        System.out.println("Item added to cart!");
        int nbItems = getCart().getNbItems();
        System.out.println("Number of items in cart: " + nbItems);
        session.setAttribute("produit", nbItems);
        System.out.println("Redirecting to afficherCart page...");
        String url = "/afficherCart.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        session = request.getSession();
        if (session.getAttribute("cart")==null) {
            System.out.println("Error: Cart is non-existant!!!");
            return;
        } 
        String code = request.getParameter("productNumber"); 
        //int qty = Integer.parseInt(request.getParameter("qty")); 
        //getCart().addItem(getProductByCode(code), qty);
        getCart().removeProduct(code);
        int nbItems = getCart().getNbItems();
        session.setAttribute("produit", nbItems);
        request.setAttribute("cart", getCart());
        request.setAttribute("total", getCart().getTotal());
        request.setAttribute("listeProduits", getListeDesProduits());
        
        String url = "/checkout.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);      
        dispatcher.forward(request, response);
    }
    
    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("Erasing the cart NOW!");
        setCart(new Cart());
        
        System.out.println("Cart erased: nbItems :" + getCart().getNbItems());
        System.out.println("Redirecting to cart modification page...");
        String url = "/listerProduits.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    public void loadCheckout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String url = "/checkout.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        request.setAttribute("cart", getCart());
        request.setAttribute("listeProduits", getListeDesProduits());
        request.setAttribute("total", getCart().getTotal());
        dispatcher.forward(request, response);
    }
    
    /*
    private void loadCatalogue(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            if(request.getParameter("sessionSetter").equals("1")){
                session = request.getSession();
                session.setAttribute("loginUsername", request.getParameter("loginName"));
                session.setAttribute("loginPassword", request.getParameter("loginPW"));
            }
        } catch (Exception e){
            System.err.println("old session");
        }
        loadProducts(request, response);
        setCart(new Cart());
    }
    */
    
    private void modificationCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Checkout cancelled...");
        System.out.println("Redirecting to cart modification page...");
        String url = "/afficherCart.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        request.setAttribute("listeProduits", getListeDesProduits());
        dispatcher.forward(request, response);
        
        /*
        String url = "/checkout.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        String code = request.getParameter("productNumber");
        getCart().removeProduct(code);
        request.setAttribute("cart", getCart());
        request.setAttribute("listeProduits", getListeDesProduits());
        request.setAttribute("total", getCart().getTotal());
        dispatcher.forward(request, response);
        */
    }
    
    private void finalizeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = "/confirmation.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        String code = request.getParameter("productNumber");
        getCart().removeProduct(code);
        request.setAttribute("cart", getCart());
        request.setAttribute("listeProduits", (ArrayList<Produits>) session.getAttribute("listeProduits"));
        request.setAttribute("total", getCart().getTotal());
        dispatcher.forward(request, response);
        
    }
    
    
    private void returnToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = "/index.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    // GETTER ET SETTER
    
    public Produits getProductByCode(String code){
        Produits prod = null;
        for(Produits p : (ArrayList<Produits>) session.getAttribute("listeProduits")){
            if(p.getCode().equals(code)){
                prod = p;
            }
        }
        
        return prod;
    }

    public Cart getCart() {
        return (Cart) session.getAttribute("cart");
    }

    public void setCart(Cart cart) {
        session.setAttribute("cart", cart);
    }

    private void setListeDesProduits(ArrayList<Produits> liste) {
        session.setAttribute("listeProduits", liste);
    }
    
    private ArrayList<Produits> getListeDesProduits() {
        return (ArrayList<Produits>) session.getAttribute("listeProduits");
    }
    
    private void setListeDesClients(ArrayList<Client> liste) {
        session.setAttribute("listeClients", liste);
    }
    
    private ArrayList<Client> getListeDesClients() {
        return (ArrayList<Client>) session.getAttribute("listeClients");
    }
    
    private Client findClientName(String nomClient){
        
        Client clientRecherche = null;
        for(Client client : getListeDesClients()){
            if(nomClient.equals(client.getNom())){
                clientRecherche = client;
            }
        }
        
        return clientRecherche;
    }
}
