<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="templates/header.jsp" %>
<main class="container-fluid">
    <div class="row">
        <section class="col-12 col-lg-4 p-lg-4 p-2 pe-lg-3">
            <form class="bg-light text-dark rounded p-4 mb-4" method="post" action="client">
                <label for="inputSearch" class="form-label fs-5 text-primary">Recherche d'un client</label>
                <div class="d-flex">
                    <input type="search" id="inputSearch" class="form-control">
                    <input type="submit" id="submit" value="Chercher" name="submit" class="flex-fill btn btn-primary ms-3">
                </div>
            </form>
            <form class="bg-light text-dark p-4 rounded" method="post" autocomplete="off">
                <h3 class="text-primary">Ajout d'un nouveau client</h3>
                <div class="d-flex flex-column">
                    <label class="my-2 form-label" for="idClient">Identification</label>
                    <input class="my-2 form-control" type="text" name="idClient" id="idClient" required>
                </div>
                <div class="d-flex flex-column">
                    <label class="my-2 form-label" for="nomClient">Nom du client</label>
                    <input class="my-2 form-control" type="text" name="nomClient" id="nomClient" required>
                </div>
                <div class="d-flex justify-content-evenly mt-3 mb-1">
                    <input type="reset" id="reset" value="Effacer" name="reset" class="flex-fill btn btn-outline-secondary me-2">
                    <input type="submit" id="submit" value="Ajouter" name="submit" formaction="client" class="flex-fill btn btn-primary ms-2">
                </div>
            </form>
        </section>
        <section class="col-12 col-lg-8 p-lg-4 p-2 ps-lg-2">
            <table class="table table-responsive table-hover table-striped w-100">
                <thead class="bg-primary">
                <th>ID</th>
                <th>Nom</th>
                <th class="text-center">Actions</th>
                </thead>
                <tbody class="align-middle">
                    <c:forEach var="client" items="${ listClient }">
                        <tr>
                            <th scope="row"><c:out value="${ client.id } ${ client.num }" /></th>
                            <td><c:out value="${ client.nom }" /></td>
                            <td class="d-flex align-items-center justify-content-evenly">
                                <a type="button" class="btn btn-primary position-relative rounded-circle d-flex align-items-center justify-content-center p-3" data-bs-toggle="modal" href="#modalUpdate" role="button" onclick="selectClient(<c:out value="${ client.num }" />)"><img src="assets/img/ellipsis.svg" class="position-absolute" width="15" height="15"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <%@include file="templates/pagination.jsp" %>
            <%@include file="templates/modalClient.jsp" %>

        </section>

    </div>
</main>
<%@include file="templates/footer.jsp" %>

