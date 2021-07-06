package ec.edu.ups.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

import javax.ejb.EJB;

import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.FacturaDetalle;
import ec.edu.ups.entidades.PedidoCabecera;
import ec.edu.ups.entidades.PedidoDetalle;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.Producto;
import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.CiudadFacade;
import ec.edu.ups.ejb.FacturaCabeceraFacade;
import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.PedidoCabeceraFacade;
import ec.edu.ups.ejb.PedidoDetalleFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.ejb.ProvinciaFacade;

@Path("/pedidos/")
public class GestionPedidos {
	
	@EJB
    PersonaFacade personaFacade;
	
	@EJB
	CategoriaFacade categoriaFacade;
	
	@EJB
	FacturaCabeceraFacade cabeceraFacade;
	
	@EJB
	FacturaDetalleFacade detalleFacade;
	
	@EJB
	ProductoFacade productoFacade;
	
	@EJB
	BodegaFacade bodegaFacade;
	
	@EJB
	CiudadFacade ciudadFacadw;
	
	@EJB
	ProvinciaFacade provinciaFacade;
	
	@EJB
	PedidoCabeceraFacade pedidoCabeceraFacade;
	
	private PedidoCabecera pedidoCabecera;
	@EJB
	PedidoDetalleFacade pedidoDetalleFacade;
	@GET
    @Path("/productos/{bodega}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProductos(@PathParam("bodega") int bodega) {
        System.out.println(bodega);
        try {
        	Bodega bog= bodegaFacade.find(bodega);
        	for (int i=0; i<bog.getProductos().size(); i++) {
        		Producto pro =bog.getProductos().get(i);
        		if (pro.getCategoria().equals(bog)) {
        			
					
				}
				
			}
            List<Categoria> cat = categoriaFacade.categoriasOrdenadas();
            List<Producto> pro = new ArrayList<Producto>();

            Jsonb jsonb = JsonbBuilder.create();

            for(int i=0; i<cat.size(); i++) {
                for(int j=0; j<cat.get(i).getProductos().size(); j++) {
                    if(cat.get(i).getProductos().get(j).getBodegas().get(0).getNombre().equals(bodega)) {
                        Producto prod = cat.get(i).getProductos().get(j);
                        prod.setBodegaNombre(prod.getBodegas().get(0).getNombre());
                        prod.setCategoriaNombre(prod.getCategoria().getNombre());
                        pro.add(prod);
                    }
                }
            }

            if(pro.size()!=0) {
                return Response.ok(jsonb.toJson(pro))
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
            }else {
                return Response.status(404).entity("La bodega mensionada no cuenta con productos").build();
            }


        } catch (Exception e) {
            return Response.status(404).entity("Bodega no encontrada").build();
        }
	
	}
	
	

	
	
	@GET
    @Path("/listaPedidos/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response estadoPedidos(@PathParam("cedula") String cedula) {
        try {
            List<PedidoCabecera> pc;
            Persona cli = personaFacade.buscarCliente(cedula);

            Jsonb jsonb = JsonbBuilder.create();

            pc = cli.getPedidosCab();

            if(pc.size()!=0) {

                for(int i=0; i<pc.size(); i++) {
                    Map<String, Integer> productos = new HashMap<String, Integer>();
                    for(int j=0; j<pc.get(i).getPedidosDetale().size(); j++) {
                        String producto = pc.get(i).getPedidosDetale().get(j).getProducto().getNombre();
                        int cantidad = pc.get(i).getPedidosDetale().get(j).getCantidad();
                        productos.put(producto, cantidad);
                    }
                    pc.get(i).setProductos(productos);
                    pc.get(i).setCedula(cedula);
                }

                return Response.ok(jsonb.toJson(pc)).build();
            }else {
                return Response.status(404).entity("El cliente no ha realizado pedidos").build();
            }


        } catch (Exception e) {
            return Response.status(404).entity("Cliente no encontrado").build();
        }


    }
	
	
	
	
	@PUT
    @Path("/distribuidor")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pedir(String jsonPedido){
		
		
        try {
            Jsonb jsonb = JsonbBuilder.create();
            PedidoCabecera pc = jsonb.fromJson(jsonPedido, PedidoCabecera.class);
             Persona per= personaFacade.buscarCliente(pc.getCedula());
            if (per  != null) {
 	
            	pc.setPersona(per);
            
				     pedidoCabeceraFacade.create(pc);
				     for (int i = 0; i < pc.getPedidosDetale().size(); i++) {
				    	 PedidoDetalle pdet= pc.getPedidosDetale().get(i);
				    	 pdet.setPedidoCabecera(pc);
				    	 pedidoDetalleFacade.edit(pdet);
					}
				     return Response.status(200).entity("Se ha registrado el pedido").build();
			}else {
				 return Response.status(404).entity("No se pudo encontrar  el cliente").build();
			}
 

        } catch (Exception e) {
            return Response.status(404).entity("No se pudo encontrar un producto"+e.getMessage()).build();
        }
    }
	
	
	@PUT
    @Path("/Factura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Facpedir(String jsonPedido){
		
		
        try {
            Jsonb jsonb = JsonbBuilder.create();
            PedidoCabecera pc = jsonb.fromJson(jsonPedido, PedidoCabecera.class);
             Persona per= personaFacade.buscarCliente(pc.getCedula());
            if (per  != null) {
 	
            	pc.setPersona(per);
            
				     pedidoCabeceraFacade.create(pc);
				     FacturaCabecera fca= new FacturaCabecera();
				     fca.setFecha(pc.getFecha());
				     fca.setPersona(per);
				     fca.setSubtotal(pc.getSubtotal());
				     fca.setIva(pc.getIva());
				     fca.setTotal(pc.getTotal());
				     List<FacturaDetalle> deta=new ArrayList<FacturaDetalle>();
				     for (int i = 0; i < pc.getPedidosDetale().size(); i++) {
				    	 PedidoDetalle pdet= pc.getPedidosDetale().get(i);
				    	 pdet.setPedidoCabecera(pc);
				    	 pedidoDetalleFacade.edit(pdet);
				    	 
				    	 FacturaDetalle fdet= new FacturaDetalle();
				    	 fdet.setCantidad(pdet.getCantidad());
				    	 fdet.setProducto(pdet.getProducto());
				    	 fdet.setTotal(pdet.getTotal());
				    	 fdet.setFacturaCabecera(fca);
				    	 deta.add(fdet);
				    	 
					}
				     fca.setFacturasDetalle(deta);
				     cabeceraFacade.create(fca);
				     return Response.status(200).entity("Se ha registrado el pedido").build();
			}else {
				 return Response.status(404).entity("No se pudo encontrar  el cliente").build();
			}
 

        } catch (Exception e) {
            return Response.status(404).entity("No se pudo encontrar un producto"+e.getMessage()).build();
        }
    }
	
	
	@GET
	@Path("/estados/{cedula}")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postEstados(@PathParam("cedula") String cedula) throws IOException{
		 Persona p =personaFacade.buscarPorCedula(cedula);
		 try {
			
		 if (p!=null) {
			 List<String>lista= new ArrayList<String>();
			 for (int i = 0; i < p.getPedidosCab().size(); i++) {
				 lista.add(p.getPedidosCab().get(i).getEstado());
				
			}
			 Jsonb buil=JsonbBuilder.create();
			 return Response.ok().entity(buil.toJson(lista)).build();
			 
		}else {
			System.out.println("No se encuentra el cliente");
		return Response.ok().build();	
		
		}
		 
		
	} catch (Exception e) {
		return Response.ok().entity(e.getMessage()).build();
	}
	
	}
}
