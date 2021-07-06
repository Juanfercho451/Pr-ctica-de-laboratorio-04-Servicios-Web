package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.CiudadFacade;
import ec.edu.ups.ejb.PedidoCabeceraFacade;
import ec.edu.ups.ejb.PedidoDetalleFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.ejb.ProvinciaFacade;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Ciudad;
import ec.edu.ups.entidades.PedidoCabecera;
import ec.edu.ups.entidades.PedidoDetalle;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.Producto;
import ec.edu.ups.entidades.Provincia;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class CreacionDatos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
    private CategoriaFacade ejbCategoria;
	@EJB
	private ProductoFacade ejbProducto;
	@EJB
	private BodegaFacade ejbBodega;
	@EJB
	private ProvinciaFacade ejbProvincia;
	@EJB
	private CiudadFacade ejbCiudad;
	@EJB
	private PersonaFacade ejbPersona;
	@EJB
	private PedidoCabeceraFacade ejbPedidoCabecera;
	@EJB
	private PedidoDetalleFacade ejbPedidoDetalle;
	
	public void creacionPrincipal() {
		
		Categoria cat=new Categoria(1, "Limpieza");
		Categoria cat2=new Categoria(2, "Jardineria");
		Categoria cat3=new Categoria(3, "Cuidado perdonal");
		Categoria cat4=new Categoria(4, "Cocina");
		Categoria cat5=new Categoria(5, "Electricidad");
		Categoria cat6=new Categoria(6, "Herramientas");
		Categoria cat7=new Categoria(7, "Electrodomesticos");
		Categoria cat8=new Categoria(8, "Licores");
		
		ejbCategoria.create(cat);
		ejbCategoria.create(cat2);
		ejbCategoria.create(cat3);
		ejbCategoria.create(cat4);
		ejbCategoria.create(cat5);
		ejbCategoria.create(cat6);
		ejbCategoria.create(cat7);
		ejbCategoria.create(cat8);
		
		
		Producto pord=new Producto(1, "Mr. Musculo",(float) 5.99, 30, 'S', cat);
		Producto pord2=new Producto(2, "Lavavajilla",(float) 1.99, 40, 'S', cat);
		Producto pord3=new Producto(3, "Papel Hijienico",(float) 0.89, 50, 'S', cat);
		Producto pord4=new Producto(4, "Detergente",(float) 2.59, 60, 'S', cat);
		
		Producto pord5=new Producto(5, "Corta cesped",(float) 145, 10, 'S', cat2);
		Producto pord6=new Producto(6, "Podadora",(float) 26, 10, 'S', cat2);
		Producto pord7=new Producto(7, "Pala",(float) 8, 15, 'S', cat2);
		
		Producto pord8=new Producto(8, "Toallas Sanitarias",(float) 4.99, 10, 'S', cat3);
		Producto pord9=new Producto(9, "Jabon Protex",(float) 2.59, 10, 'S', cat3);
		Producto pord10=new Producto(10, "Crema Dental",(float) 3.35, 10, 'S', cat3);
		
		Producto pord11=new Producto(11, "Platos",(float) 6, 100, 'S', cat4);
		Producto pord12=new Producto(12, "Cubiertos",(float) 1.50, 100, 'S', cat4);
		Producto pord13=new Producto(13, "Cuchillos",(float) 3, 70, 'S', cat4);
		
		Producto pord14=new Producto(14, "Foco Normal",(float) 1.50, 50, 'S', cat5);
		Producto pord15=new Producto(15, "Foco Ahorrador",(float) 2.50, 80, 'S', cat5);
		Producto pord16=new Producto(16, "Boquilla",(float) 0.75, 100, 'S', cat5);
		
		Producto pord17=new Producto(17, "Alicate",(float) 3.75, 70, 'S', cat6);
		Producto pord18=new Producto(15, "Destornillador plano",(float) 0.5, 100, 'S', cat6);
		Producto pord19=new Producto(19, "Destornillador estrella",(float) 0.5, 100, 'S', cat6);
		
		Producto pord20=new Producto(20, "Lavadora",(float) 769.99, 10, 'S', cat7);
		Producto pord21=new Producto(21, "Secadora",(float) 454.99, 10, 'S', cat7);
		Producto pord22=new Producto(22, "Refrigeradora",(float) 2400, 5, 'S', cat7);
		
		Producto pord23=new Producto(23, "Jagermeister",(float) 29, 20, 'S', cat8);
		Producto pord24=new Producto(24, "Blue Labet",(float) 250, 5, 'S', cat8);
		Producto pord25=new Producto(25, "Dandeglion Wine",(float) 1000, 2, 'S', cat8);
		
		Provincia provi = new Provincia(1, "Napo");
		Provincia provi2 = new Provincia(2, "Azuay");
		Provincia provi3 = new Provincia(3, "Esmeraldas");
		
		ejbProvincia.create(provi);
		ejbProvincia.create(provi2);
		ejbProvincia.create(provi3);
		
		Ciudad ciudad = new Ciudad(1, "El Chaco", provi);
		Ciudad ciudad2 = new Ciudad(2, "Paute", provi2);
		Ciudad ciudad3 = new Ciudad(3, "Esmeraldas", provi3);
		
		ejbCiudad.create(ciudad);
		ejbCiudad.create(ciudad2);
		ejbCiudad.create(ciudad3);
		
		Bodega bodega = new Bodega(1, "Bodega 1", "El valle", ciudad);
		Bodega bodega2 = new Bodega(2, "Bodega 2", "Quito", ciudad2);
		Bodega bodega3 = new Bodega(3, "Bodega 3", "Guayaquil", ciudad3);
		
		bodega.addProductos(pord);
		bodega.addProductos(pord2);
		bodega.addProductos(pord6);
		bodega.addProductos(pord9);
		bodega.addProductos(pord10);
		bodega.addProductos(pord11);
		bodega.addProductos(pord14);
		bodega.addProductos(pord20);
		bodega.addProductos(pord21);
		
		bodega2.addProductos(pord3);
		bodega2.addProductos(pord7);
		bodega2.addProductos(pord12);
		bodega2.addProductos(pord13);
		bodega2.addProductos(pord15);
		bodega2.addProductos(pord22);
		
		bodega3.addProductos(pord4);
		bodega3.addProductos(pord5);
		bodega3.addProductos(pord8);
		bodega3.addProductos(pord16);
		bodega3.addProductos(pord17);
		bodega3.addProductos(pord18);
		bodega3.addProductos(pord19);
		bodega3.addProductos(pord23);
		bodega3.addProductos(pord24);
		bodega3.addProductos(pord25);
		
		ejbBodega.create(bodega);
		ejbBodega.create(bodega2);
		ejbBodega.create(bodega3);
		
		
		
		Persona persona = new Persona(1, "Cordova", "Juan","0105822209","Don Bosco", "123456", "juan@gmail.com", "123456", 'E');
		Persona persona2 = new Persona(2, "Jose", "Pandiguana","0105256568","Control Sur", "12578", "jose2@gmail.com", "123456", 'A');
		Persona persona3 = new Persona(3, "Ana", "Idrovo","0104760921","Miraflores", "01256", "ana@gmail.com", "123456", 'C');
		
	
		ejbPersona.create(persona);
		ejbPersona.create(persona2);
		ejbPersona.create(persona3);
		
		PedidoCabecera pedCab = new PedidoCabecera(0, new Date(),(float) 400,(float) 500,(float) 0.12, "Enviado", persona3);
		PedidoDetalle pedDetalle = new PedidoDetalle(0, 2, 400, pedCab, pord2);
		
		ejbPedidoCabecera.create(pedCab);
		ejbPedidoDetalle.create(pedDetalle);
	}
	
	public void listarProductosBodega() {

        List<Producto> sta = ejbBodega.find(1).getProductos();

        for (Producto producto : sta) {
            System.out.println(producto.getNombre());
        }
    }
	
}
