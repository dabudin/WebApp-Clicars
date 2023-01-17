<%@page import="model.Marca"%>
<%@page import="model.Coche"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Clicars</title>
    <!-- Required meta tags -->
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
    <!-- Mi CSS -->
    <link rel="stylesheet" href="css/mycss.css"/>
  </head>
  
  <%
  	ArrayList<Coche> coches = (ArrayList<Coche>) session.getAttribute("coches");
  	ArrayList<Marca> marcas = (ArrayList<Marca>) session.getAttribute("marcas");
  	String marca = String.valueOf(session.getAttribute("marca"));
  %>
  
  <body>

    <div id="maincontainer" class="container px-5 pt-3">

        <!-- NAVBAR -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">

            <!-- LOGO CLICARS -->
            <img src="img/logo.png" alt="logo clicars"/>
            
            <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId"
                aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavId">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">

                    <!-- DROPDOWN MARCA -->
                    <li class="nav-item dropdown mr-4">
                        <a class="nav-link dropdown-toggle text-primary" href="#" id="ddmarca" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Marca</a>
                        <div class="dropdown-menu" aria-labelledby="ddmarca">
                        
                        	<a class="dropdown-item" href="Controller?op=getcochesbymarca&marca=">Todos</a>

                            <%
                            	for (Marca it : marcas) {
                            %>
                            
                            <a class="dropdown-item" href="Controller?op=getcochesbymarca&marca=<%=it.getId()%>"><%=it.getNombre() %></a>
                            
                            <%} %>

                        </div>
                    </li>
                    <!-- DROPDOWN MARCA -->

                    <!-- DROPDOWN ORDENAR POR... -->
                    <li class="nav-item dropdown mr-4">
                        <a class="nav-link dropdown-toggle text-primary" href="#" id="ddordenar" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ordenar por...</a>
                        <div class="dropdown-menu" aria-labelledby="ddordenar">

                            <a class="dropdown-item" href="Controller?op=ordercoches&orden=">Nada</a>
                            <a class="dropdown-item" href="Controller?op=ordercoches&orden=nombre">Nombre</a>
                            <a class="dropdown-item" href="Controller?op=ordercoches&orden=modelo">Modelo</a>
                            <a class="dropdown-item" href="Controller?op=ordercoches&orden=anio">Año</a>
                            <a class="dropdown-item" href="Controller?op=ordercoches&orden=km">Kilometros</a>
                            <a class="dropdown-item" href="Controller?op=ordercoches&orden=cv">Caballos</a>

                        </div>
                    </li>
                    <!-- DROPDOWN ORDENAR POR... -->

                </ul>

                <!-- SEARCH -->
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="text" placeholder="Nombre, Modelo, Año" 
                    		name="search" id="search"/>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
                    <input type="hidden" name="op" value="getcoches">
                </form>
                <!-- SEARCH -->

            </div>
        </nav>
        <!-- NAVBAR -->

        
        <!-- CARDS COCHES -->
        <div id="coches" class="row my-5">

            <%
            	for (Coche coche : coches) {
            %>

            <div class="col-12 col-md-6 col-lg-4 mb-4">
                <div class="card">

                    <!-- DISENO CARD BODY-->
                    <div class="card-body mb-3 p-0">

                        <!-- Imagen coche-->
                        <img src="<%=coche.getFoto()%>" alt="<%=coche.getNombre() %>"/>

                        <!-- ESTRELLA RATING-->
                        <div class="text-right">
                        
                        	<% 
                        		if (coche.getFav() == 0) {
                        	%>
                        	
                            <a class="rating mr-1" href="Controller?op=updatefav&fav=1&id=<%=coche.getId()%>">&#9733;</a>
                            
                            <%	
                            	} else if (coche.getFav() == 1) {
                            %>
                            
                            <a class="unrating mr-1" href="Controller?op=updatefav&fav=0&id=<%=coche.getId()%>">&#9733;</a>
                            
                            <%	} %>
                            
                        </div>
                        <!-- ESTRELLA RATING-->
                        
                        <!-- Row Nombre - Precio antes -->
                        <div class="row mt-3 mb-2 pl-3">
                            <div class="col-9">
                                <%=coche.getNombre() %>
                            </div>
                            <div class="col-3 text-right text-danger pr-0">
                                <%=coche.getPrecioantes() %> &euro;
                            </div>
                        </div>

                        <!-- Row Modelo - Precio -->
                        <div class="row mb-2 pl-3">
                            <div class="col-9">
                                <%=coche.getModelo() %>
                            </div>
                            <div class="col-3 text-right text-success pr-0">
                                <%=coche.getPrecio() %> &euro;
                            </div>
                        </div>

                        <!-- Row Anio - Km - Cv -->
                        <div class="row mb-2 pl-3">
                            <div class="col-12">
                                <%=coche.getAnio() %> | <%=coche.getKm() %> km | <%=coche.getCv() %> CV
                            </div>
                        </div>

                    </div>
                    <!-- DISENO CARD BODY-->

                </div>
            </div>
            
            <%} %>

        </div>
        <!-- CARDS COCHES -->

    </div>
      
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>