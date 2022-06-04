<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="modalUpdate" aria-hidden="true" aria-labelledby="modalLabelUpdate" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabelUpdate">Modification</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Show a second modal and hide this one with the button below.
            </div>
            <div class="modal-footer d-flex justify-content-between">
                <button class="btn btn-outline-danger" data-bs-target="#modalDelete" data-bs-toggle="modal">Supprimer</button>
                <div>
                    <button class="me-1 btn btn-outline-secondary" data-bs-dismiss="modal" aria-label="Close">Annuler</button>
                    <button class="ms-2 btn btn-primary" data-bs-toggle="modal">Modifier</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modalDelete" aria-hidden="true" aria-labelledby="modalLabelDelete" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabelDelete">Suppression</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Voulez-vous vraiment supprimer cette ligne ?
            </div>
            <div class="modal-footer">
                <button class="me-2 btn btn-outline-secondary" data-bs-target="#modalUpdate" data-bs-toggle="modal">Annuler</button>
                <button class="ms-2 btn btn-danger" data-bs-toggle="modal">Supprimer</button>
            </div>
        </div>
    </div>
</div>
