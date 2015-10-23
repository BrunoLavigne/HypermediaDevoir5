
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
        
        <h1>Modifier Panier</h1>
        
        <table border="1">
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Quantite</th>
                    <th>Order</th>
                </tr>
        
        <% 
            Cart cart = (Cart) session.getAttribute("cart");
            System.out.println("Cart found! " + cart);
            int cartLength = cart.getCartClient().size();
            System.out.println("Cart size detected: " + cartLength);
            for (int i=0; i<cartLength; i++){ %>

                <form method="POST" action="accessServlet">
                    <input type="hidden" name="actionType" value="retirerProduit">
                    <input type="hidden" name="productNumber" value="<%= cart.getCartClient().get(i).getCode() %>" >
                    <% 
                    System.out.println("Product found in cart: " + cart.getCartClient().get(i).getNom()); 
                    System.out.println("With quntity: " + cart.getCartClient().get(i).getQty());
                    %>
                    <tr>
                        <td><%= cart.getCartClient().get(i).getNom() %></td>
                        <td><%= cart.getCartClient().get(i).getDescription() %></td>
                        <td><%= cart.getCartClient().get(i).getBaseUnitPrice()%></td>
                        <td> 
                            <select name="qty">
                                <% for (int j=1; j<=cart.getCartClient().get(i).getQty(); j++){ 
                                System.out.println("Adding substract option for quantity " + j); %>
                                <option value="-<%= j %>"><%= j*-1 %></option>
                                <% } %>
                          </select> 
                        </td>
                        <td><input type="submit" value="Submit" name='removeFromCart' id='btnCart'></td>
                        
                    </tr>
                    </form>

            <% } %>
        </table>
        <h1>Cart : <%-- out.println(session.getAttribute("produit")); --%> items</h1>
        
        <form method="POST" action="accessServlet">
            <input type="hidden" name="actionType" value="checkout">
            <input type="submit" value="Checkout" name='checkout' id='btnCheckout'>
        </form>
        <form method="POST" action="accessServlet">
            <input type="hidden" name="actionType" value="viderCart">
            <input type="submit" value="Clear Cart" name='clearCart' id='btnClear'>
        </form>
        
    </body>
</html>
