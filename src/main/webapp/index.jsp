<%@ include file="/includes/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
  
		
        <div class="row contenedor-productos">
        
		<c:forEach items="${productos}" var="p">
		
            <div class="col">

                <!-- producto -->
                <div class="producto">
                    <span class="descuento">${p.descuento}%</span>
                    <img src="${p.getFoto()}" alt="imagen de bottela de vodka">
					
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
                    </div>

                    <div class="botones">
                        <button class="minus">-</button>
                        <input type="text" value="1">
                        <button class="plus">+</button>
                    </div>

                    <button class="carro">aÃ±adir al carro</button>

                </div>
                <!-- /.producto -->

            </div>   <!-- /.col -->

        </c:forEach>

        </div><!-- /row contenedor-productos -->
<%@ include file="/includes/footer.jsp"%>