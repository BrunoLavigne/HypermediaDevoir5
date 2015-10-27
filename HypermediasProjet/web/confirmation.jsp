<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="Ressources/css/bootstrap.min.css" rel="stylesheet">
        <link href="Ressources/css/heroic-features.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <style>
            th{
                text-align: center;
            }
        </style>
        
        <title>Confirmation</title>
    </head>
    <body>
        
        <c:set var="shipping" value="15.00"/>
        
        <div class="container">
        
            <header class="jumbotron hero-spacer">
                                
                <h1 style="text-align:center">Articles de bureau</h1>
                       
            </header>

            <hr>
        
        </div>
        
        <div class="container">
            
            <div class="row" style="text-align:center">
            
            <div class="col-lg-8 col-md-offset-2">
            
                <h1>Merci pour votre commande!</h1>
                
                <br>
                
            <table class="table">
                <tr>
                    
                    <th>Client</th>
                    <th>${username}</th>
 
                    
                </tr>
                <c:if test="${not empty adresse}">
                <tr>
                    
                    <th>Infos</th>
                    <th>${adresse} <br> ${age} ans</th>

                </tr>
                </c:if>
                <tr>
                    
                    <th>Subtotal</th>
                    <th><fmt:formatNumber value="${total}" type="currency"/></th>
                    
                </tr>
                
                <tr>
                    
                    <th>Taxes</th>
                    <c:set var="taxes" value="${total * 0.14975}"/>
                    <th><fmt:formatNumber value="${taxes}" type="currency"/></th>
                    
                </tr>
                
                <tr>
                    
                    <th>Shipping</th>
                    <th><fmt:formatNumber value="${shipping}" type="currency"/></th>
                    
                </tr>
                
                <tr>
                    
                    <th>Total</th>
                    <th><fmt:formatNumber value="${total + taxes + shipping}" type="currency"/></th>
                    
                </tr>
        </table>
        <br>
        <br>
        <br>
        <form action="http://localhost:8080/HypermediasProjet/">
            <input type="submit" value="Retour à la page de départ">
        </form>

                
            </div>
                
        </div>
        
        </div>

    </body>
</html>
