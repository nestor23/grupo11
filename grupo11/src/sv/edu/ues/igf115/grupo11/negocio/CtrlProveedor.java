package sv.edu.ues.igf115.grupo11.negocio;

import java.util.List;

import sv.edu.ues.igf115.grupo11.datos.ProveedorDAO;
import sv.edu.ues.igf115.grupo11.dominio.Proveedor;


public class CtrlProveedor {
	private ProveedorDAO daoDepto= new ProveedorDAO();

	public boolean crearProveedor(String idPro, String nombreProveedor,String direccion, String telefono, String email) {
		if (daoDepto.daProveedorByNombre(nombreProveedor) == null) {
			Proveedor proveedor = new Proveedor(idPro,nombreProveedor,direccion, telefono, email);
			daoDepto.guardaActualiza(proveedor);
			return true;
		} else
			return false;
	}

	public boolean eliminarProveedor(String nombre) {
		if (daoDepto.daProveedorByNombre(nombre) != null) {
			Proveedor proveedor = daoDepto.daProveedorByNombre(nombre);
			daoDepto.eliminar(proveedor);
			return true;
		} else
			return false;
	}

	public boolean modificarProveedor(String nombreAntiguo,String idPro, String nombreProveedor,String direccion, String telefono, String email) {
		if (daoDepto.daProveedorByNombre(nombreAntiguo) != null) {
			Proveedor proveedor = daoDepto.daProveedorByNombre(nombreAntiguo);
			proveedor.setIdProveedor(idPro);
			proveedor.setNombreProveedor(nombreProveedor);
			proveedor.setDireccion(direccion);
			proveedor.setTelefono(telefono);
			proveedor.setEmail(email);
			daoDepto.guardaActualiza(proveedor);
			return true;
		} else
			return false;
	}

	public List<Proveedor> daProveedores() {
		return daoDepto.daProveedores();
	}

	public Proveedor daProveedorById(String id) {
		return daoDepto.daProveedorById(id);
	}

	public Proveedor daProveedorByNombre(String nombre) {
		return daoDepto.daProveedorByNombre(nombre);
	}
}
