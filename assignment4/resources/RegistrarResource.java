package com.csye6225.fall2018.courseservice667.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice667.datamodel.Registrar;
import com.csye6225.fall2018.courseservice667.service.RegistrarService;

@Path("/registerOffering")
public class RegistrarResource 
{
	RegistrarService registrarService = new RegistrarService(); 
	
	//GET all registrars
	@GET
	@Path("/allregistrars")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Registrar> getRegistrars() {
		return registrarService.getAllRegistrars();
	}

	//GET registrar by department
	@GET
	@Path("/bydepartment/{department}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Registrar> getRegistrarByDepartment( @PathParam("department") String department ) {
		if (department == null) {
			return registrarService.getAllRegistrars();
		}
		return registrarService.getRegistrarByDepartment(department);
		
	}
	
	
	// ... webapi/registrars/ 
	@GET
	@Path("/{registrationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Registrar getRegistrar(@PathParam("registrationId") String registrationId) {
		return registrarService.getRegistrar(registrationId);
	}
	
	@DELETE
	@Path("/{registrationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Registrar deleteRegistrar(@PathParam("registrationId") String registrationId) {
		return registrarService.deleteRegistrar(registrationId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Registrar addRegistrar(Registrar registrar) {
			return registrarService.addRegistrar(registrar);
	}
	
	
	@PUT 
	@Path("/{registrationId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Registrar updateRegistrar(@PathParam("registrationId") String registrationId, Registrar registrar) {
		return registrarService.updateRegistrarInformation(registrationId, registrar);
	}
	
	public void addRegistrar( String registrationId, String offeringId, String offeringType, String department, int perUnitPrice) {
		registrarService.addRegistrar(registrationId, offeringId, offeringType, department, perUnitPrice);
	}
}
