<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="templates/header.jsp" %>
<main class="container-fluid">
    <div class="row">
        <section class="col-12 col-lg-4 p-lg-4 p-2 pe-lg-3">
            <form class="bg-light text-dark rounded p-4 mb-4" method="post" action="produit">
                <label for="inputSearch" class="form-label fs-5 text-primary">Recherche d'un produit</label>
                <div class="d-flex">
                    <input type="search" id="inputSearch" class="form-control">
                    <input type="submit" id="submit" value="chercher" name="submitProduit" class="flex-fill btn btn-primary ms-3">
                </div>
            </form>
            <form class="bg-light text-dark p-4 rounded" method="post" action="produit">
                <h3 class="text-primary">Ajout d'un nouveau produit</h3>
                <div class="d-flex flex-column">
                    <label class="my-2 form-label" for="idClient">Identification</label>
                    <input class="my-2 form-control" type="text" name="idProduit" id="idProduit" required>
                </div>
                <div class="d-flex flex-column">
                    <label class="my-2 form-label" for="designProduit">Désignation</label>
                    <input class="my-2 form-control" type="text" name="designProduit" id="designProduit" required>
                </div>
                <div class="d-flex justify-content-evenly">
                    <div class="flex-fill d-flex flex-column me-2">
                        <label class="my-2 form-label" for="nomClient">Prix Unitaire (Ar)</label>
                        <input class="my-2 form-control" type="number" name="puProduit" id="puProduit" min="0">
                    </div>
                    <div class="flex-fill d-flex flex-column ms-2">
                        <label class="my-2 form-label" for="nomClient">Stock</label>
                        <input class="my-2 form-control" type="number" name="stockProduit" id="stockProduit" value="0" min="0">
                    </div>
                </div>
                <div class="d-flex justify-content-evenly mt-3 mb-1">
                    <input type="reset" id="reset" value="Effacer" name="reset" class="flex-fill btn btn-outline-secondary me-2">
                    <input type="submit" id="submit" value="ajouter" name="submitProduit" class="flex-fill btn btn-primary ms-2">
                </div>

            </form>
        </section>
        <section class="col-12 col-lg-8 p-lg-4 p-2 ps-lg-2">
            <table class="table table-responsive table-hover table-striped w-100" id="tbl-produit">
                <thead class="bg-primary">
                <th>ID</th>
                <th>Désignation</th>
                <th class="text-end">Prix Unitaire (Ar)</th>
                <th class="text-end">Stock</th>
                <th class="text-center">Actions</th>
                </thead>
                <tbody class="align-middle">
                    <c:forEach var="produit" items="${ listProduit }">
                        <tr>
                            <th scope="row"><c:out value="${ produit.id } ${ produit.num }" /></th>
                            <td><c:out value="${ produit.designation }" /></td>
                            <td class="text-end"><c:out value="${ produit.prixU}" /></td>
                            <td class="text-end"><c:out value="${ produit.stock }" /></td>
                            <td class="d-flex align-items-center justify-content-evenly">
                                <a type="button" class="btn btn-primary position-relative rounded-circle d-flex align-items-center justify-content-center p-3" data-bs-toggle="modal" href="#modalUpdate" role="button"><img src="assets/img/ellipsis.svg" class="position-absolute" width="15" height="15"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <%@include file="templates/pagination.jsp" %>
            <%@include file="templates/modal.jsp" %>

        </section>
    </div>
</main>
<%@include file="templates/footer.jsp" %>

