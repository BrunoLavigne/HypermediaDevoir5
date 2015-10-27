<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="Ressources/css/bootstrap.min.css" rel="stylesheet">
        <link href="Ressources/css/heroic-features.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <title>Liste des produits</title>
    </head>
    <body>
        
        <div class="container">
        
            <header class="jumbotron hero-spacer">
                                
                <h1 style="text-align:center">Articles de bureau</h1>
                       
            </header>

            <hr>
        
        </div>
        
        <div class="container">
            
            <div class="row" style="text-align:center">
            
            <div class="col-lg-8 col-md-offset-2">
                
                <h3>Liste des produits</h3></br>
                
                <table class="table">
                    <tr>
                        <th style="text-align:center">nom</th>
                        <th style="text-align:center">Description</th>
                        <th style="text-align:center">Prix</th>
                        <th style="text-align:center">Quantite</th>                
                        <th style="text-align:center">Order</th>
                    </tr>

                    <c:forEach var="items" items="${listeProduits}">

                        <form method="POST" action="accessServlet">

                            <input type="hidden" name="actionType" value="ajouterProduit">
                            <input type="hidden" name="productNumber" value="${items.code}" >

                            <tr style="text-align:center">

                                <td>${items.nom}</td>
                                <td>${items.description}</td>
                                <td>${items.baseUnitPrice} $<td>

                                <input type="text" value="1" id="qty" name="qty" style="text-align:center">

                                <td><input class="btn-default" type="submit" value="Submit" name='addToCart' id='btnCart'></td>

                            </tr>

                        </form>

                    </c:forEach>

                </table>
                
            </div>
                
        </div>
        
        </div>
       
    </body>
</html>
