<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            th {
                text-align: center;
                vertical-align: middle;
            }
        </style>
        
        <title>Checkout</title>

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
                
                <h3>Checkout</h3>
                
                </br>

                <h4>Votre s&eacute;lection</h4>
                <hr>
                <p>Client : ${username}</p>
                
                
                <c:if test="${total gt 0}">
                
                    <hr>
                    <p>Veuillez faire une derni&egrave;re v&eacute;rification</p>
                    <hr>

                    <table class="table-responsive">
                        <tr>
                            <th>Nom</th>
                            <th>Description</th>
                            <th>Prix</th>
                            <th>Quantite</th>      
                            <th>Total</th>
                            <th>Cancel</th>

                        </tr>
                        

                        <c:forEach var="check" items="${requestScope.cart2}">

                            <form method="POST" action="accessServlet" value="retirerProduit">
                                <input type="hidden" name="actionType" value="retirerProduit">
                                <input type="hidden" name="productNumber" value="${check.code}" >
                            <tr>
                                <td align="center">${check.nom}</td>
                                <td align="center">${check.description}</td>
                                <td align="center"><fmt:formatNumber value="${check.baseUnitPrice}" type="currency"/></td>
                                <td align="center">${check.qty}</td>    
                                <td align="center"><fmt:formatNumber value="${check.qty * check.baseUnitPrice}" type="currency"/></td>
                                <td align="center"><input type="submit" name="retirerProduit" value="x" ></td>
                            </tr>
                            </form>   

                        </c:forEach>


                    </table>

                    <hr>

                    <p>Facture total : <fmt:formatNumber value="${total}" type="currency"/></p>


                    <form method="POST" action="accessServlet" value="done">
                        <input type="hidden" name="actionType" value="confirmation">
                        <input class="btn-primary" type="submit" name="confirmation" value="Confirmer" >
                    </form>
                
                </c:if>
                
                <form method="POST" action="accessServlet" value="final">
                    <input type="hidden" name="actionType" value="cancel">
                    <input class="btn-danger" type="submit" name="cancelOrder" value="Cancel" >
                </form>
                
            </div>
                
        </div>
        
        </div>

    </body>
</html>
