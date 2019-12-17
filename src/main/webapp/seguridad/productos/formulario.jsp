<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/includes/header.jsp" %>   
    	
	<h1>FORMULARIO</h1>
	
	<c:if test="${producto.id == 0}">
		<h3>Nuevo Producto</h3>
	</c:if>
	<c:if test="${producto.id > 0}">
		<h3>Editar Producto</h3>
	</c:if>
	<c:if test="${not empty mensajeAlerta }"/>
	
	<div class="row justify-content-center">
            <div class="col-4 mt-5 bg-warn">

                    <form action="seguridad/productos" method="post">
                    

                        <div class="form-group">
                            <label for="id">id</label>
                            <input type="text"
                            	   value="${producto.id}"
                                   class="form-control" 
                                   name="id"
                                   readonly
                                   required>
                        </div>
                        
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" 
                            	   autofocus
                            	   value="${producto.nombre}"
                                   class="form-control" 
                                   name="nombre" 
                                   required
                                   placeholder="Mínimo 2 Máximo 50"
                                   pattern=".{2,50}">
                        </div>
                        
                        <input type="text" hidden name="accion" value="guardar">
                        
                        <button type="submit" class="btn btn-block btn-outline-primary">Listo</button>
                    	
                    </form>

            </div>
        </div>  

<%@ include file="/includes/footer.jsp" %> 