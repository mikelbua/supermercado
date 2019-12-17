package com.ipartek.formacion.supermercado.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.supermercado.modelo.dao.IDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;

public class ProductoDAO implements IDAO<Producto>{

	private static ProductoDAO INSTANCE;
	private ArrayList<Producto> registros;
	private int indice=1;
	
	
	
	private ProductoDAO() {
		
		super();
		registros = new ArrayList<Producto>();
		//int id, String nombre, float precio, String foto, String descripcion, int descuento
		registros.add(new Producto(indice,"Vodka",100f,"https://media-verticommnetwork1.netdna-ssl.com/wines/absolut-vodka-434778.jpg","El mejor vodka ruso a su disposicion.",20));
		registros.add(new Producto(++indice,"Ron",15.5f,"https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA02/CONTENIDOS/201508/14/00118733302311____2__600x600.jpg","Ron cubano de primera calidad.",15));
		registros.add(new Producto(++indice,"Gym",13.99f,"https://www.chopinmol.com/14902-large_default/ginebra-bombay-sapphire-750ml.jpg","Ginebra finlandesa.",10));
		registros.add(new Producto(++indice,"Patatas",12.99f,"https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201909/11/00120952600565____1__600x600.jpg","Patats fritas con suave sabor a jamon.",10));
		registros.add(new Producto(++indice,"Galletas",15.99f,"https://www.lacasadelasgolosinas.com/4366-large_default/galletas-chips-ahoy-con-chocolate-12-paquetes.jpg","Galletas con trozos de chocolate.",5));
		registros.add(new Producto(++indice,"Cereales",11.5f,"https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201710/25/00120633900236____1__600x600.jpg","Cereales multicolor.",5));
		registros.add(new Producto(++indice,"Nocilla",11.99f,"https://yourspanishcorner.com/5453-thickbox_default/crema-de-cacao-190g-nocilla.jpg","El sabor de la autentica nocilla sin haceite de palma",5));
		registros.add(new Producto(++indice,"Nutella",10.99f,"https://images-na.ssl-images-amazon.com/images/I/71IGjnEIKcL._SX569_.jpg","Crema de cacao con sabor a avellana",20));
		registros.add(new Producto(++indice,"Mistol",8.5f,"https://tuotrosuper.com/84868-large_default/mistol-lavavajillas-a-mano-600-ml.jpg","Lavaplatos ",12));
		registros.add(new Producto(++indice,"Estropajo",19.5f,"https://www.herbolarionavarro.es/media/catalog/product/cache/5ca9ce70a921ce7409c54fc53692dab8/3/7/3760138833129.jpg","Estropajo de doble superficie.",10));
		registros.add(new Producto(++indice,"Estropajo",19.5f,"https://www.herbolarionavarro.es/media/catalog/product/cache/5ca9ce70a921ce7409c54fc53692dab8/3/7/3760138833129.jpg","Estropajo de doble superficie.",10));
		registros.add(new Producto(++indice,"Estropajo",19.5f,"https://www.herbolarionavarro.es/media/catalog/product/cache/5ca9ce70a921ce7409c54fc53692dab8/3/7/3760138833129.jpg","Estropajo de doble superficie.",10));
		registros.add(new Producto(++indice,"Estropajo",19.5f,"https://www.herbolarionavarro.es/media/catalog/product/cache/5ca9ce70a921ce7409c54fc53692dab8/3/7/3760138833129.jpg","Estropajo de doble superficie.",10));
		
		
	}
	
	public synchronized static ProductoDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ProductoDAO();
		}

		return INSTANCE;
	}

	@Override
	public List<Producto> getAll() {
		
		return registros;
	}

	@Override
	public Producto getById(int id) {
		
		Producto resul = null;

		for (Producto producto : registros) {
			if (id == producto.getId()) {
				resul = producto;
				break;
			}
		}

		return resul;
	}

	@Override
	public Producto delete(int id) throws Exception {
		Producto resul = null;

		for (Producto producto : registros) {
			if (id == producto.getId()) {
				resul = producto;
				registros.remove(producto);
				break;
			}
		}
		if (resul == null) {
			throw new Exception("no ha encontrado el producto que habia que borrar" + id);
		}

		return resul;
	}

	@Override
	public Producto update(int id, Producto pojo) throws Exception {
		
		Producto resul = null;
		
		for (int i = 0; i < registros.size(); i++) {
			if (id == registros.get(i).getId()) {
				registros.remove(i);
				registros.add(pojo);
				resul = pojo;
				break;
			}
		}
		if (resul == null) {
			throw new Exception("no ha encontrado el perro qeu habia que borrar" + id);
		}
		return resul;
	}

	@Override
	public Producto create(Producto pojo) throws Exception {
		Producto resul = null;
		if (pojo != null) {
			pojo.setId(++indice);
			registros.add(pojo);
		} else {
			throw new Exception("perro NULL sin datos");
		}
		// TODO comprobar campos del pojo.
		return resul;
	}
}
