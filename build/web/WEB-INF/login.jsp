<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="templates/header.jsp" %>
<main class="m-5">
    <div class="row">
        <section class="col-lg-4"></section>
        <section class="col-12 col-lg-4 p-4 pe-lg-3">
            <form class="bg-light p-4 rounded" method="post" action="login">
                <h2 class="text-primary">Connexion</h2>
                <div class="d-flex flex-column">
                    <label class="my-2 form-label" for="idUser">Nom d'utilisateur</label>
                    <input class="my-2 form-label" type="text" name="idUser" id="idUser" value="${userName}" required>
                </div>
                <div class="d-flex flex-column">
                    <label class="my-2 form-label" for="mdpUser">Mot de passe</label>
                    <input class="my-2 form-label" type="password" name="mdpUser" id="mdpUser" value="${userPassword}" required>
                </div>
                <div class="d-flex justify-content-evenly mt-3 mb-1">
                    <input type="submit" id="submit" value="Se connecter" name="submit" class="flex-fill btn btn-primary">
                </div>
                <div class="text-danger text-end mt-3">${errorMessage}</div>
            </form>
        </section>
        <section class="col-lg-4"></section>
    </div>
</main>
<%@include file="templates/footer.jsp" %>

