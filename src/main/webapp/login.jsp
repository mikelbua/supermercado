<%@ include file="/includes/header.jsp"%>


<%@page contentType="text/html" charset="UTF-8" %>

<div class="row justify-content-center">
            <div class="col-4 mt-5 bg-warn">

                    <form action="login" method="post">

                        <div class="form-group">
                            <label for="usuaio">Usuario</label>
                            <input type="text" 
                            	   autofocus
                                   class="form-control" 
                                   name="usuario" 
                                   required
                                   placeholder="Mínimo 2 Máximo 150"
                                   pattern=".{2,150}"
                                   aria-describedby="nombreHelp">
                        </div>

                        
                        <div class="form-group">
                                <label for="contrasenia">Contraseña</label>
                                <input type="password" 
                                       class="form-control" 
                                       name="contrasenia" 
                                       required
                                       pattern=".{2,150}"
                                       aria-describedby="precioHelp">
                         </div>
                        <button type="submit" class="btn btn-block btn-outline-primary">login</button>
                    </form>

            </div>
        </div>  



<%@ include file="/includes/footer.jsp"%>