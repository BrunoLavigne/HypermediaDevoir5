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
                <h4 style="text-align:center">Administrator access</h4>
                       
            </header>

            <hr>
        
        </div>
        
        
        <div class="container">
            
            <div class="row" style="text-align:center">
            
            <div class="col-lg-10 col-md-offset-1">

                      

         
                <h3>Liste des produits</h3>
                
                </br>
                
                <h5>Choix du produit vedette</h5>
                
                <br>
                
                <table class="table">
                    <tr>
                        <th>Code</th>
                        <th>Image</th>
                        <th>Description</th>          
                        <th>Selectionner</th>
                    </tr>

                    <c:forEach var="items" items="${listeProduits}">

                        <form method="POST" action="accessServlet">

                            <input type="hidden" name="actionType" value="selectProduitVedette">
                            <input type="hidden" name="productNumber" value="${items.code}" >

                            <tr style="text-align:center">

                                <c:set var="imagePath" value="Ressources/images/${items.imageURL}"/>
                                
                                <td>${items.code}</td>
                                <td><img src="${imagePath}" class="img-rounded" alt="Cinque Terre" width="100" height="100"></td>
                                <td>${items.description}</td>
                                <td><input class="btn-danger" type="submit" value="Selectionner" name='selectProduitVedette' id='btnStarItem'></td>

                            </tr>

                        </form>

                    </c:forEach>

                </table>
                
            </div>
                
        </div>
        
        </div>
       
    </body>
</html>
