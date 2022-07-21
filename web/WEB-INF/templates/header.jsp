<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion des commandes</title>
        <link rel="icon" href="favicon.png">
        <link href="assets/style/css/custom-bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/jquery.min.js" type="text/javascript"></script>
        <script src="assets/js/bootstrap/bootstrap.bundle.js" type="text/javascript"></script>
    </head>
    <body class="">
        <header class="container-fluid text-center text-primary text-light p-3">
            <div class="d-flex align-items-center justify-content-between p-0">
                <%
                    if (Boolean.valueOf(String.valueOf(session.getAttribute("isLogging")))) {
                %>
                <h4 class="text-primary m-0 me-4 d-none d-lg-block">Bonjour, ${ connectedUser.userName }</h4>
                <ul class="nav flex-fill flex-lg-row flex-column align-items-start justify-content-start fs-4">

                    <li class="nav-item">
                        <a class="nav-link py-0 ps-0" href="/CommandeJ2E/client">Clients</a>
                    </li>
                    <li class="mx-3 d-none d-lg-block">
                        <img src="assets/img/dot.svg" width="5" height="5"/>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link py-0 ps-0" href="/CommandeJ2E/produit">Produits</a>
                    </li>
                    <li class="mx-3 d-none d-lg-block">
                        <img src="assets/img/dot.svg" width="5" height="5"/>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link py-0 ps-0" href="/CommandeJ2E/commande">Commandes</a>
                    </li>
                </ul>
                <div class="d-flex align-items-center">
                    <a class="nav-link py-0 ps-0" href="/CommandeJ2E/login">Se déconnecter</a>
                    <img src="assets/img/java.svg" width="40" height="40"/>  
                </div>
                <%
                } else {
                %>
                <h4 class="text-primary m-0 me-4 d-none d-lg-block">Gestion des commandes</h4>
                <ul class="nav flex-fill flex-lg-row flex-column align-items-start justify-content-start fs-4">

                    <li class="nav-item">
                        <a class="nav-link py-0 text-center px-lg-3 px-0" href="/CommandeJ2E/client">Clients</a>
                    </li>
                    <li class="mx-3 d-none d-lg-block">
                        <img src="assets/img/dot.svg" width="5" height="5"/>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link py-0 text-center px-lg-3 px-0" href="/CommandeJ2E/produit">Produits</a>
                    </li>
                    <li class="mx-3 d-none d-lg-block">
                        <img src="assets/img/dot.svg" width="5" height="5"/>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link py-0 text-center px-lg-3 px-0" href="/CommandeJ2E/commande">Commandes</a>
                    </li>
                </ul>
                <div class="d-flex align-items-center justify-content-center position-relative bg-light p-1 rounded-circle">
                    <img src="assets/img/user.svg" width="25" height="25"/>  
                </div>
                <%
                    }
                %>
            </div>

        </header>
