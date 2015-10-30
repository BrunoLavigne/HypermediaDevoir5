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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
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
import views.Client;
import views.Produits;

/**
 *
 * @author Bruno, Marc-Éric
 */
@WebServlet("/controllers/accessServlet")
public class accessServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(accessServlet.class.getName());
    private static FileHandler fh = null;
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
                
                case "selectProduitVedette":
                    System.out.println("Request selectProduitVedette action type found...");
                    System.out.println("Beginning selectProduitVedette function...");
                    selectProductVedette(request, response);
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
        logger.log(Level.INFO, "Login name received: " + username);
        byte[] encryptedPWbytes = request.getParameter("loginPW").getBytes(StandardCharsets.UTF_8);
        logger.log(Level.INFO, "Crypted login password byte array received: " + Arrays.toString(encryptedPWbytes));
        logger.log(Level.INFO, "Decrypting password bytes...");
        String decryptedPW = new String(Base64.getDecoder().decode(encryptedPWbytes));
        
        //String decryptedPW = request.getParameter("loginPW");
        logger.log(Level.INFO, "Password decrypted! : " + decryptedPW);
        
        if (username.equals(userName)){
            
            logger.log(Level.INFO, "User Name found! checking password...");
            
            if (userPassword.equals(decryptedPW)){
                
                logger.log(Level.INFO, "Password accepted!");
                logger.log(Level.INFO, "User is now connected.");
                session.setAttribute("username", username);
                // session.setAttribute("password", encryptedPWbytes);
                session.setAttribute("password", decryptedPW);
                session.setAttribute("isAdministrator", false);
                session.setAttribute("adresse", "");
                session.setAttribute("age", "");
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                
            } else {
                
                logger.log(Level.INFO, "Password rejected!");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                
            }
            
        } else if (username.equals(adminName)){
            
            if (adminPassword.equals(decryptedPW)){
                
                logger.log(Level.INFO, "Password accepted!");
                logger.log(Level.INFO, "User is now connected as administrator.");
                session.setAttribute("username", username);
                // session.setAttribute("password", encryptedPWbytes);
                session.setAttribute("password", decryptedPW);
                session.setAttribute("isAdministrator", true);
                session.setAttribute("adresse", "");
                session.setAttribute("age", "");
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                
            } else {
                
                logger.log(Level.INFO, "Password rejected!");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                
            }
            
        } else if(findClientName(username) != null){
            Client client = findClientName(username);
            
            String clientPW = client.getPassword();
            
            if(clientPW.equals(decryptedPW)){
                session.setAttribute("username", username);
                session.setAttribute("password", decryptedPW);
                session.setAttribute("adresse", client.getAddresse());
                session.setAttribute("age", client.getAge());
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                
            } else {
                logger.log(Level.INFO, "Password rejected!");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);  
            }
        }
        
        else {
            
            logger.log(Level.INFO, "Invalid unsername!");
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
        byte[] password = request.getParameter("loginPW").getBytes(StandardCharsets.UTF_8);
        String decryptedPW = new String(Base64.getDecoder().decode(password));
        String addresse = request.getParameter("addresse");
        int age = Integer.parseInt(request.getParameter("age"));
        
        Client client = new Client(nom, decryptedPW, addresse, age);
        logger.log(Level.INFO, "Client créé dans registerCustomer...");
        DBaccess("CHECK", client);
        logger.log(Level.INFO, "Client vérifié / créé dans la base de données");
        session.setAttribute("isAdministrator", false);
        if(session.getAttribute("listeClients") == null){
            setListeDesClients(new ArrayList<>());
        }
        
        getListeDesClients().add(client);
        logger.log(Level.INFO, "Client vérifié / créé dans la base de données");
        // loadProducts(request, response);
        
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
    
    private void DBaccess(String operation, Client client){
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hypermedias?zeroDateTimeBehavior=convertToNull&"
              + "user=system&password=system");
            logger.log(Level.INFO, "Connected to database!");
            try {
                if (operation.equals("ADD")){
                    /*
                    logger.log(Level.INFO, "Operation recognised as ADD...");
                    PreparedStatement prepStmt = connect.prepareStatement("INSERT INTO utilisateurs VALUES (?, ?, ?, ?)");
                    logger.log(Level.INFO, "Adding client name to ADD query: " + client.getNom());
                    prepStmt.setString(1, client.getNom());
                    logger.log(Level.INFO, "Adding client Password to ADD query: " + client.getPassword());
                    prepStmt.setBytes(2, client.getPassword());
                    logger.log(Level.INFO, "Adding client Address to ADD query: " + client.getAddresse());
                    prepStmt.setString(3, client.getAddresse());
                    logger.log(Level.INFO, "Adding client Age to ADD query: " + client.getAge());
                    prepStmt.setInt(4, client.getAge());
                    prepStmt.executeUpdate();
                    connect.commit();
                    */
                } else if (operation.equals("CHECK")){
                    logger.log(Level.INFO, "Operation recognised as CHECK...");
                    Statement stmt = connect.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT username, password FROM utilisateurs WHERE username = '" + client.getNom() + "'");
                    logger.log(Level.INFO, "QUERY INITIATED: SELECT username, password FROM utilisateurs WHERE username = '" + client.getNom() + "'");
                    if (!rs.next()){
                        logger.log(Level.WARNING, "Aucun utilisateur avec ce nom.");
                        logger.log(Level.INFO, "Ajout de l'utilisateur...");
                        PreparedStatement prepStmt = connect.prepareStatement("INSERT INTO utilisateurs (username, password, adresse, age) VALUES(?, ?, ?, ?) ");
                        logger.log(Level.INFO, "Adding client name to ADD query: " + client.getNom());
                        prepStmt.setString(1, client.getNom());
                        logger.log(Level.INFO, "Adding client Password to ADD query: " + client.getPassword());
                        prepStmt.setString(2, new String(client.getPassword()));
                        logger.log(Level.INFO, "Adding client Address to ADD query: " + client.getAddresse());
                        prepStmt.setString(3, client.getAddresse());
                        logger.log(Level.INFO, "Adding client Age to ADD query: " + client.getAge());
                        prepStmt.setInt(4, client.getAge());
                        prepStmt.executeUpdate();
                        logger.log(Level.INFO, "Utilisateur ajouté!");
                    } else {
                        do {
                            if (rs.getString("password").getBytes(StandardCharsets.UTF_8).equals(client.getPassword())){
                                logger.log(Level.INFO, "Utilisateur authentifié!");
                                // TODO faire de quoi avec ça
                            }
                        } while (rs.next());
                    }
                }
            } catch (SQLException sqle){
                logger.log(Level.SEVERE, "Erreur SQL:");
                logger.log(Level.SEVERE, sqle.getMessage(), sqle);
            }
        } catch (ClassNotFoundException cnfe){
            logger.log(Level.SEVERE, "Failed to connect to database: driver error");
            logger.log(Level.SEVERE, cnfe.getMessage(), cnfe);
        } catch (SQLException sqle){
            logger.log(Level.SEVERE, "Failed to connect to database: connexion error");
            logger.log(Level.SEVERE, sqle.getMessage(), sqle);
        } finally {
            try {
                connect.close();
                fh.close();
            } catch (Exception e){
                logger.log(Level.WARNING, e.getMessage(), e);
            }
        }
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
        
        setCart(new Cart());
        
        session.setAttribute("listeProduits", listeDesProduits);
        session.setAttribute("cart", getCart());
        String url = "";
        if((boolean)session.getAttribute("isAdministrator")){
            url = "/selectProduitVedette.jsp";
        } else{
            url = "/listerProduits.jsp";
        }
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
        request.setAttribute("cart2", getCartv2());
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
        request.setAttribute("cart2", getCartv2());
        request.setAttribute("listeProduits", getListeDesProduits());
        request.setAttribute("total", getCart().getTotal());
        dispatcher.forward(request, response);
    }
    
    private void selectProductVedette(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        String code = request.getParameter("productNumber");
        Produits leProduitVedette = getProductByCode(code);
        leProduitVedette.setProduitVedette(true);
        
        String url = "/index.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        request.setAttribute("listeProduits", getListeDesProduits());
        session.setAttribute("produitVedette", leProduitVedette);

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

        request.setAttribute("cart2", getCartv2());

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
    public ArrayList<Produits> getCartv2(){
        
        Cart cart = (Cart) session.getAttribute("cart");
        return cart.getCartClient();
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
