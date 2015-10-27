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
        
        <style>
            .table tbody th {
                text-align: center;
                vertical-align: middle;
            }
        </style>
        
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
            
            <div class="col-lg-10 col-md-offset-1">
                
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

                                <c:set var="imagePath" value="Ressources/images/${items.imageURL}"/>
                                <td><img src="${imagePath}" class="img-rounded" alt="Cinque Terre" width="100" height="100"></td>
                                <td>${items.description}</td>
                                <td>${items.baseUnitPrice} $<td>

                                <input type="text" value="1" id="qty" name="qty" style="text-align:center">

                                <td><input class="btn-default" type="submit" value="Submit" name='addToCart' id='btnCart'></td>

                            </tr>

                        </form>

                    </c:forEach>

                </table>
                
                <hr>
                
                <c:if test="${produit gt 0}">

                <h4>Cart : ${produit} items</h4>
                
                    <form method="POST" action="accessServlet">
                        <input type="hidden" name="actionType" value="checkout">
                        <input class="btn btn-default" type="submit" value="Checkout" name='checkout' id='btnCheckout'>
                    </form>

                    <br>
                
                    <form method="POST" action="accessServlet">
                        <input type="hidden" name="actionType" value="viderCart">
                        <input class="btn btn-default" type="submit" value="Clear Cart" name='clearCart' id='btnClear'>
                    </form>
                
                </c:if>
                
                
            </div>
                
        </div>
        
        </div>
       
    </body>
</html>
