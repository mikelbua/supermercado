<%@ include file="/includes/header.jsp"%>


<%@ page contentType="text/html; charset=UTF-8" %>
  
    	
	<h1>TABLA seguridad/productos/index.jsp</h1>
	
	
	
<hr>

            <!-- esta clase ya esta definida en el css bootstrap la centra y tiene
            mediaquerys para los diferentes tamaños de dispositivos-->
            <h1>Nuestros Productos</h1>
            <a href="seguridad/productos?accion=formulario">Nuevo Producto</a>
            <div class="container-fluid">

                <!-- Page Heading -->
                <p class="mb-4">En esta tabla estan todos los Productos.</p>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Listado Productos</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>id</th>
                                        <th>nombre</th>
                                        <th>precio</th>
                                        <th>descuento</th>
                                        <th>descripcion</th>
                                        <th>Precio final</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>id</th>
                                        <th>nombre</th>
                                        <th>precio</th>
                                        <th>descuento</th>
                                        <th>descripcion</th>
                                        <th>Precio final</th>
                                        <th></th>
                                    </tr>
                                </tfoot>
                                <tbody>
	                                <c:forEach items="${productos}" var="p">
	                                    <tr>
	                                        <td>${p.id}</td>
	                                        <td>${p.nombre}</td>
	                                        <td>${p.precio}€</td>
	                                        <td>${p.descuento}%</td>
	                                        <td>${p.descripcion}</td>
	                                        <td>${p.precioDescuento}€</td>
	                                        <td><a href="/seguridad/productos?accion=formulario&id=${p.id}">Editar</a></td>
	                                    </tr>
	                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
<%@ include file="/includes/footer.jsp"%>