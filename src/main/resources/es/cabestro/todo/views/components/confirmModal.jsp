<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="modal fade" id="confirmTaskDeleteModal" tabindex="-1" role="dialog" 
     aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" 
                        aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="confirmDeleteModalLabel">
                    ¿Estas seguro?
                </h4>
            </div>
            <div class="modal-body">
                Estas seguro de eliminar esta tarea.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Cancelar
                </button>
                <a id="taskDeleteModal" class="btn btn-danger">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    <spring:message code="views.tasks.index.delete" />
                </a>
            </div>
        </div>
    </div>
</div>
