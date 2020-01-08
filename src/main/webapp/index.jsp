<%@ include file="/includes/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
  	
  	
		<div class="form-group mt-4">		
			<label>Filtro categoria : </label>
			<select name="categoriaId" class="custom-select filtroCategori">
				<c:forEach items="${categorias}" var="c">
				    <!-- ${(c.id eq producto.usuario.id)?"selected":""} -->
					<option value="${c.id}">${c.nombre}</option>	
				</c:forEach>
			</select>
			
			<label class="ml-4">Filtro nombre : </label>
			<select name="categoriaId" class="custom-select filtroCategori">
				<c:forEach items="${productos}" var="p">
					<option value="${p.id}">${p.nombre}</option>	
				</c:forEach>
			</select>
		</div>
		
		
        <div class="row contenedor-productos">
        
		<c:forEach items="${productos}" var="p">
		
            <div class="col">

                <!-- producto -->
                <div class="producto">
                    <span class="descuento">${p.descuento}%</span>
                    <img src="${p.getFoto()}" alt="imagen de producto">
					
                    <div class="body">
                        <p>
                            <span class="precio-descuento">
                            	<fmt:formatNumber type="currency" currencySymbol="€" value="${p.precio}" />
                            </span>
                            <span class="precio-original text-truncate">
                            	<fmt:formatNumber type="currency" currencySymbol="€" value="${p.precioDescuento}" />
                            </span>
                        </p>
                        <p class="text-muted precio-unidad">(18,50â¬ / litro)</p>
                        <p class="descripcion">${p.descripcion}</p>
                        <p class="descripcion">id : ${p.id} | ${p.nombre}</p>
                        <p class="descripcion">Categoria : ${p.categoria.nombre}</p>
                    </div>

                    <div class="botones">
                        <button class="minus">-</button>
                        <input type="text" value="1">
                        <button class="plus">+</button>
                    </div>

                    <button class="carro">añadir al carro</button>

                </div>
                <!-- /.producto -->

            </div>   <!-- /.col -->

        </c:forEach>

        </div><!-- /row contenedor-productos -->
<%@ include file="/includes/footer.jsp"%>