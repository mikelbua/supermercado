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
		registros.add(new Producto(1,"Vodka",100f,"https://media-verticommnetwork1.netdna-ssl.com/wines/absolut-vodka-434778.jpg","El mejor vodka ruso a su disposicion.",20));
		registros.add(new Producto(2,"Ron",15.5f,"https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA02/CONTENIDOS/201508/14/00118733302311____2__600x600.jpg","Ron cubano de primera calidad.",15));
		registros.add(new Producto(3,"Gym",13.99f,"https://www.chopinmol.com/14902-large_default/ginebra-bombay-sapphire-750ml.jpg","Ginebra finlandesa.",10));
		registros.add(new Producto(4,"Patatas",12.99f,"https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201909/11/00120952600565____1__600x600.jpg","Patats fritas con suave sabor a jamon.",10));
		registros.add(new Producto(5,"Galletas",15.99f,"https://www.lacasadelasgolosinas.com/4366-large_default/galletas-chips-ahoy-con-chocolate-12-paquetes.jpg","Galletas con trozos de chocolate.",5));
		registros.add(new Producto(6,"Cereales",11.5f,"https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201710/25/00120633900236____1__600x600.jpg","Cereales multicolor.",5));
		registros.add(new Producto(7,"Nocilla",11.99f,"https://yourspanishcorner.com/5453-thickbox_default/crema-de-cacao-190g-nocilla.jpg","El sabor de la autentica nocilla sin haceite de palma",5));
		registros.add(new Producto(8,"Nutella",10.99f,"https://images-na.ssl-images-amazon.com/images/I/71IGjnEIKcL._SX569_.jpg","Crema de cacao con sabor a avellana",20));
		registros.add(new Producto(9,"Mistol",8.5f,"https://tuotrosuper.com/84868-large_default/mistol-lavavajillas-a-mano-600-ml.jpg","Lavaplatos ",12));
		registros.add(new Producto(10,"Estropajo",19.5f,"https://www.herbolarionavarro.es/media/catalog/product/cache/5ca9ce70a921ce7409c54fc53692dab8/3/7/3760138833129.jpg","Estropajo de doble superficie.",10));
		
	}
	
	public synchronized static ProductoDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ProductoDAO();
		}

		return INSTANCE;
	}

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return registros;
	}

	@Override
	public Producto getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto update(int id, Producto pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto create(Producto pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
