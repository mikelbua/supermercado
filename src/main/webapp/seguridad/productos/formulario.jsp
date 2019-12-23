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
                        <div class="form-group">
                            <label for="precio">Precio</label>
                            <input type="text" 
                            	   value="${producto.precio}"
                                   class="form-control" 
                                   name="precio" 
                                   required
                                   placeholder="precio del producto">
                        </div>
                        
                        <div class="form-group">
                            <label for="descuento">Descuento</label>
                            <input type="text" 
                            	   value="${producto.descuento}"
                                   class="form-control" 
                                   name="descuento" 
                                   required
                                   placeholder="descuento del producto">
                        </div>
                        
                        <input type="text" hidden name="accion" value="guardar">
                        
                        <button type="submit" class="btn btn-block btn-outline-primary">Listo</button>
                    	
                    </form>
					<c:if test="${producto.id > 0}">
		
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-outline-danger mb-3 mt-3" data-toggle="modal" data-target="#exampleModal">
						  Eliminar
						</button>
						
						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						       ¿Esta seguro de que quiere eliminar el producto?
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">cerrar</button>
						        <a class="btn btn-danger" href="seguridad/productos?id=${producto.id}&accion=eliminar" style="margin-top: 5px">Eliminar</a>
						      </div>
						    </div>
						  </div>
						</div>
					</c:if>
				
            </div>
        </div>  

<%@ include file="/includes/footer.jsp" %> 