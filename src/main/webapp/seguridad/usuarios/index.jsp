<%@ include file="/includes/header.jsp"%>


<%@ page contentType="text/html; charset=UTF-8" %>
  
    	
	<h1>TABLA seguridad/usuarios/index.jsp</h1>
	
	
	
<hr>

            <!-- esta clase ya esta definida en el css bootstrap la centra y tiene
            mediaquerys para los diferentes tamaños de dispositivos-->
            <h1>Usuarios</h1>
            <div class="container-fluid mt-3">

                <!-- Page Heading -->

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                    <span>
                        <h6 class="m-0 font-weight-bold text-primary float-left"><i class="fa fa-list" aria-hidden="true"></i> Listado Usuarios</h6>
                        </span>
                        <a class="float-right" href="seguridad/usuarios?accion=formulario&id=0" ><i class="fas fa-plus-circle"></i>Nuevo Usuarios</a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>id</th>
                                        <th>nombre</th>
                                        <th>contrasenia</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>id</th>
                                        <th>nombre</th>
                                        <th>contrasenia</th>
                                    </tr>
                                </tfoot>
                                <tbody>
	                                <c:forEach items="${usuarios}" var="u">
	                                    <tr>
	                                        <td>${u.id}</td>
	                                        <td>${u.nombre}</td>
	                                        <td>${u.contrasenia}</td>
	                                        <td><a href="seguridad/usuarios?accion=formulario&id=${u.id}" >Editar <i class="fas fa-pencil-alt"></i></a>
	                                        </td>
	                                    </tr>
	                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div><!-- container-fluid -->


<%@ include file="/includes/footer.jsp"%>