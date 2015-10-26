
<%@page import="java.util.ArrayList"%>
<%@page import="views.Produits"%>
<%@page import="models.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ajouter Produits</h1>
        <% 
            System.out.println("Loading product list...");
            ArrayList<Produits> listeProduits = (ArrayList<Produits>) session.getAttribute("listeProduits");
            System.out.println("Product list loaded: " + listeProduits);
            
            int nbProduits = (Integer) session.getAttribute("produit");
        %>
        
        <table border="1">
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Quantite</th>
                    <th>Order</th>
                </tr>
                
                <% for(int i = 0; i < listeProduits.size(); i++){ %>
                
                    <form method="POST" action="accessServlet">
                        <input type="hidden" name="actionType" value="ajouterProduit">
                        <input type="hidden" name="productNumber" value="<%= listeProduits.get(i).getCode() %>" >
                    <tr>
                        <td><%= listeProduits.get(i).getNom() %></td>
                        <td><%= listeProduits.get(i).getDescription() %></td>
                        <td><%= listeProduits.get(i).getBaseUnitPrice()%></td>
                        <td> 
                            <select name="qty">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                          </select> 
                        </td>
                        <td><input type="submit" value="Submit" name='addToCart' id='btnCart'></td>
                        
                    </tr>
                    </form>

                <% } %>
        </table>
        
        </br>
        </br>
        
        <% if(nbProduits != 0 ){ %>
        
                <h1>Cart : <%= session.getAttribute("produit") %> items</h1>
        
        <form method="POST" action="accessServlet">
            <input type="hidden" name="actionType" value="checkout">
            <input type="submit" value="Checkout" name='checkout' id='btnCheckout'>
        </form>
        <form method="POST" action="accessServlet">
            <input type="hidden" name="actionType" value="viderCart">
            <input type="submit" value="Clear Cart" name='clearCart' id='btnClear'>
        </form>
        
        
        <% } %>

        
    </body>
</html>
