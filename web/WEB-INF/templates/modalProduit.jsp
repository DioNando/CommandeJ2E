<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="modalUpdate" aria-hidden="true" aria-labelledby="modalLabelUpdate" tabindex="-1">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content text-dark">
            <div class="modal-header">
                <h5 class="modal-title text-success" id="modalLabelUpdate">Modification</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="alertHide()"></button>
            </div>
            <div class="modal-body">
                <div id="alertUpdate"></div>
                <div class="d-flex flex-column">
                    <label class="my-0 form-label" for="numProduitToUpdate">Numéro</label>
                    <input class="my-2 form-control" type="text" name="numProduitToUpdate" id="numProduitToUpdate" disabled readonly>
                </div>
                <div class="d-flex flex-column">
                    <label class="my-0 form-label" for="idProduitToUpdate">Identification</label>
                    <input class="my-2 form-control" type="text" name="idProduitToUpdate" id="idProduitToUpdate" autocomplete="off" required>
                </div>
                <div class="d-flex flex-column">
                    <label class="my-2 form-label" for="designProduitToUpdate">Désignation</label>
                    <input class="my-2 form-control" type="text" name="designProduitToUpdate" id="designProduitToUpdate" autocomplete="off" required>
                </div>
                <div class="d-flex justify-content-evenly">
                    <div class="flex-fill d-flex flex-column me-2">
                        <label class="my-2 form-label" for="puProduitToUpdate">Prix Unitaire (Ar)</label>
                        <input class="my-2 form-control" type="number" name="puProduitToUpdate" id="puProduitToUpdate" min="0" autocomplete="off">
                    </div>
                    <div class="flex-fill d-flex flex-column ms-2">
                        <label class="my-2 form-label" for="stockProduitToUpdate">Stock</label>
                        <input class="my-2 form-control" type="number" name="stockProduitToUpdate" id="stockProduitToUpdate" value="0" min="0" autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="modal-footer d-flex justify-content-between">
                <button class="btn btn-outline-danger" data-bs-target="#modalDelete" data-bs-toggle="modal">Supprimer</button>
                <div class="d-flex">
                    <button class="me-2 btn btn-outline-secondary" data-bs-dismiss="modal" aria-label="Close" onclick="alertHide()">Annuler</button>
                    <button class="ms-2 btn btn-primary" onclick="updateProduit()">Modifier</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modalDelete" aria-hidden="true" aria-labelledby="modalLabelDelete" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content text-dark">
            <div class="modal-header">
                <h5 class="modal-title text-success" id="modalLabelDelete">Suppression</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Voulez-vous vraiment supprimer cette ligne ?
            </div>
            <div class="modal-footer">
                <button class="me-2 btn btn-outline-secondary" data-bs-target="#modalUpdate" data-bs-toggle="modal" onclick="alertHide()">Annuler</button>
                <button class="ms-2 btn btn-danger" data-bs-dismiss="modal" aria-label="Close" onclick="deleteProduit()">Supprimer</button>
            </div>
        </div>
    </div>
</div>

<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
    <div class="toast align-items-center text-light bg-danger border-0" id="toastUpdateError" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Impossible d'afficher les details du produit selectionné !
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
    <div class="toast align-items-center text-dark bg-primary border-0" id="toastDelete" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Suppression réussi !
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
    <div class="toast align-items-center text-light bg-danger border-0" id="toastDeleteError" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Suppression impossible, veuillez reessayer !
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<script>
    function alertHide() {
        $("#alertUpdateContent").remove();
    }

    function selectProduit(num) {
        $(document).ready(function () {
            $.ajax({
                url: "produit",
                type: "GET",
                dataType: "json",
                data: {
                    action: "Selectionner",
                    numProduit: num
                },
                success: function (result) {
                    console.table(result);
                    $("#modalLabelUpdate").html("Modification du produit " + result.num);
                    $("#modalLabelDelete").html("Suppression du produit " + result.num);
                    $("#numProduitToUpdate").val(result.num);
                    $("#idProduitToUpdate").val(result.id);
                    $("#designProduitToUpdate").val(result.designation);
                    $("#puProduitToUpdate").val(result.prixU);
                    $("#stockProduitToUpdate").val(result.stock);
                },
                error: function () {
                    $("#toastUpdateError").toast("show");
                    $("#modalUpdate").modal("hide");
                }
            });
        });
    }

    function updateProduit() {
        $(document).ready(function () {
            $.ajax({
                url: "produit",
                type: "POST",
                data: {
                    submit: "Modifier",
                    numProduit: $("#numProduitToUpdate").val(),
                    idProduit: $("#idProduitToUpdate").val(),
                    designProduit: $("#designProduitToUpdate").val(),
                    puProduit: $("#puProduitToUpdate").val(),
                    stockProduit: $("#stockProduitToUpdate").val()
                },
                success: function () {
                    $(document).ready(function () {
                        $("#alertUpdate").html('<div class="alert alert-success" id="alertUpdateContent" role="alert">Modification réussie</div>');
                    });
                },
                error: function () {
                    $("#alertUpdate").html('<div class="alert alert-danger" id="alertUpdateContent" role="alert">Erreur lors de la modification</div>');
                }
            });
        });
    }

    function deleteProduit() {
        $(document).ready(function () {
            $.ajax({
                url: "produit",
                type: "POST",
                data: {
                    submit: "Supprimer",
                    numProduit: $("#numProduitToUpdate").val()
                },
                success: function () {
                    $(document).ready(function () {
                        $("#toastDelete").toast("show");
                    });
                },
                error: function () {
                    $("#toastDeleteError").toast("show");
                }
            });
        });
    }
</script>
