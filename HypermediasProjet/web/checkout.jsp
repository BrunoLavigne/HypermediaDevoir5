
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="models.Cart"%>
<%@page import="views.Produits"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>

    </head>
    <body>
        <h1>Votre s&eacute;lection</h1>
        <p><%= session.getAttribute("username") %></p>
        <p>Veuillez faire une derni&egrave;re v&eacute;rification</p>
                
        <% Cart cart = (Cart) request.getAttribute("cart"); %>
        <% float total = (Float) request.getAttribute("total"); %>
        <% session.setAttribute("total", total); %>
        <% NumberFormat formatter = new DecimalFormat("#0.00"); %>
             
        <table border="0">
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Quantite</th>      
                    <th>Total</th>
                    <th>Cancel</th>
                    
                </tr>
                                                
                <% for(int i = 0; i < cart.getCartClient().size(); i++){ %>
                                                 
                    <form method="POST" action="accessServlet" value="final">
                        <input type="hidden" name="productNumber" value="<%= cart.getCartClient().get(i).getCode() %>" >
                    <tr>
                        <td align="center"><%= cart.getCartClient().get(i).getNom() %></td>
                        <td align="center"><%= cart.getCartClient().get(i).getDescription() %></td>
                        <td align="center"><%= cart.getCartClient().get(i).getBaseUnitPrice() %> $</td>
                        <td align="center"><%= cart.getCartClient().get(i).getQty() %></td>    
                        <td align="center"><%= (cart.getCartClient().get(i).getBaseUnitPrice()) * (cart.getCartClient().get(i).getQty())%>$</td>
                        <td align="center"><input type="submit" name="retirerProduit" value="x" ></td>
                    </tr>
                    </form>                                              
                        
                <% } %>  
        
        </table>
                
        <p>Facture total : <%= formatter.format(total) %> $</p>
        
        <form method="POST" action="accessServlet" value="final">
            <input type="hidden" name="actionType" value="cancel">
            <input type="submit" name="cancelOrder" value="Cancel" >
        </form>
        
        <form method="POST" action="accessServlet" value="done">
            <input type="hidden" name="actionType" value="confirmation">
            <input type="submit" name="confirmation" value="Confirmer" >
        </form>
        
    </body>
</html>
