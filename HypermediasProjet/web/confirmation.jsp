<%-- 
    Document   : confirmation
    Created on : 29-Sep-2015, 6:03:27 PM
    Author     : bruno
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <% 
            
            NumberFormat formatter = new DecimalFormat("#0.00"); 
            
            double subTotal = (Float) session.getAttribute("total");
            double taxes = 0.1495 * subTotal;
            double shipping = 15.00;
                  
            double totalBill = subTotal + taxes + shipping;
        
        %>
                
        <h1>Merci pour votre commande!</h1>
        
        <table>
            <table border="0">
                <tr>
                    
                    <th>Client</th>
                    <th><%= session.getAttribute("loginUsername") %></th>
                    
                </tr>
                
                <tr>
                    
                    <th>Subtotal</th>
                    <th><%= session.getAttribute("total") %> $</th>
                    
                </tr>
                
                <tr>
                    
                    <th>Taxes</th>
                    <th><%= formatter.format(taxes) %> $</th>
                    
                </tr>
                
                <tr>
                    
                    <th>Shipping</th>
                    <th><%= formatter.format(shipping) %> $</th>
                    
                </tr>
                
                <tr>
                    
                    <th>Total</th>
                    <th><%= formatter.format(totalBill) %> $</th>
                    
                </tr>
        </table>
        <br>
        <br>
        <br>
        <form action="http://localhost:8080/HypermediasProjet/">
            <input type="submit" value="Retour à la page de départ">
        </form>
    </body>
</html>
