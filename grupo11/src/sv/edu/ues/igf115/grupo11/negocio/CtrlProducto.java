package sv.edu.ues.igf115.grupo11.negocio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import sv.edu.ues.igf115.grupo11.datos.ProductoDAO;
import sv.edu.ues.igf115.grupo11.dominio.Producto;
import sv.edu.ues.igf115.grupo11.dominio.Proveedor;

public class CtrlProducto {
	private ProductoDAO daoEmp = new ProductoDAO();
	public boolean crearProducto(String idProducto, String descripcion,Date fecha, BigDecimal preciounitario, Proveedor proveedor) {
		if (daoEmp.daProductoByDescripcion(descripcion)== null) {
			if (proveedor != null) {
				Producto producto = new Producto(idProducto, descripcion, fecha, preciounitario, proveedor);
				producto.setProveedor(proveedor);
				daoEmp.guardaActualiza(producto);
				return true;
			} else
				return false;
		} else
			return false;
	}
	
	public boolean borrarProductoByDescripcion(String descripcion) {
		if (daoEmp.daProductoByDescripcion(descripcion) != null) {
			Producto producto = daoEmp.daProductoByDescripcion(descripcion);
			daoEmp.eliminar(producto);
			return true;
		} else
			return false;
	}
	public boolean modificarProducto(String descAntigua,String idProducto, String descripcion,Date fecha, BigDecimal preciounitario, Proveedor proveedor) {
		if (daoEmp.daProductoByDescripcion(descAntigua) != null) {
			Producto producto = daoEmp.daProductoByDescripcion(descAntigua);
			producto.setIdProducto(idProducto);
			producto.setDescripcion(descripcion);
			producto.setFechaInicio(fecha);
			producto.setPrecioUnitario(preciounitario);
			producto.setProveedor(proveedor);
			daoEmp.guardaActualiza(producto);
			
			return true;
		} else
			return false;
	}

	public List<Producto> daProductos() {
		return daoEmp.daProductos();
	}

	public List daProductosByProveedor(String idProveedor) {
		return daoEmp.daProductosByProveedor(idProveedor);
	}

	public Producto daProductoById(String idPro) {
		return daoEmp.daProductoById(idPro);
	}
}
