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
                
                <h3>Produits vedette </h3></br>
                      
                <form method="POST" action="accessServlet">
                    
                    <input type="hidden" name="actionType" value="ajouterProduit">
                    <input type="hidden" name="productNumber" value="00000000001" >
                    
                <table class="table">
                              <colgroup>
                                <col class="col-md-6">
                                <col class="col-md-4">
                                <col class="col-md-1">
                                <col class="col-md-1">
                              </colgroup>

                                <tr>
                                  <td><img src="Ressources/images/stickers.png" class="img-thumbnail" alt="pencil" width="400" height="400"></td>
    
                                  <td style="vertical-align:middle">Beau crayon &agrave; l'encre bleu avec une fine pointe pour signer des ch&egrave;ques ou des contrats</td>
                                  <td style="vertical-align:middle">50.50$</td>

                                  <td style="vertical-align:middle">
                                    <input class="btn-primary" type="submit" value="Submit" name='addToCart' id='btnCart'>
                                  </td>
                                </tr>

                            </table>
                </form>
         
                <h3>Liste des produits</h3></br>
                
                <table class="table">
                    <tr>
                        <th>nom</th>
                        <th>Description</th>
                        <th>Prix</th>
                        <th>Quantite</th>                
                        <th>Order</th>
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
                
            </div>
                
        </div>
        
        </div>
       
    </body>
</html>
