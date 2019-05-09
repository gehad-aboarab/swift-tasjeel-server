package BrokerServer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/broker-server")
public class BrokerServerWebServices {
	private static final Helper helper = new Helper("cloud-computing");
	
	// =============Broker=============
	@GET
	@Path("register-entity/{type}/{name}/{path}")
	@Produces(MediaType.TEXT_PLAIN)
	public String registerEntity(@PathParam("type") String type, 
			@PathParam("name") String name, 
			@PathParam("path") String path) {

		return helper.registerEntity(type, name, path);
	}
	
	// =============Insurance=============
	@GET
	@Path("insurance/{insurance}/get-packages")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllPackages(@PathParam("insurance") String insurance) {
		
		String path = helper.getPath("insurance");
		String servicePath = helper.getServicePath("insurance", "get-packages");
		return helper.callRest(path + insurance + "/" + servicePath);
	}
	
	@GET
	@Path("insurance/{insurance}/get-plan/{reference-no}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlan(@PathParam("insurance") String insurance,
			@PathParam("reference-no") String reference_no) {
		
		String path = helper.getPath("insurance");
		String servicePath = helper.getServicePath("insurance","get-plan");
		return helper.callRest(path + insurance + "/" + servicePath + reference_no);
	}
	
	@GET
	@Path("insurance/{insurance}/register-package/{name}/{contact}/{package-no}")
	@Produces(MediaType.TEXT_PLAIN)
	public String registerToPackage(@PathParam("insurance") String insurance,
			@PathParam("name") String name, 
			@PathParam("contact") String contact, 
			@PathParam("package-no") String package_no) {
		
		String path = helper.getPath("insurance");
		String servicePath = helper.getServicePath("insurance","register-package");
		return helper.callRest(path + insurance + "/" + servicePath + name + "/" + contact + "/" + package_no);
	}
	
	@GET
	@Path("insurance/{insurance}/renew-plan/{reference-no}/{credit-card}/{amount}")
	@Produces(MediaType.TEXT_PLAIN)
	public String renewPlan(@PathParam("insurance") String insurance,
			@PathParam("reference-no") String reference_no,
			@PathParam("credit-card") String credit_card, 
			@PathParam("amount") double amount) {
		
		String path = helper.getPath("insurance");
		String servicePath = helper.getServicePath("insurance","renew-plan");
		return helper.callRest(path + insurance + "/" + servicePath + reference_no + "/" + credit_card + "/" + amount);
	}
	
	// =============Testing Center=============
	@GET
	@Path("testing-center/{center}/get-timings")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTimings(@PathParam("center") String center) {
		
		String path = helper.getPath("testing-center");
		String servicePath = helper.getServicePath("testing-center","get-timings");
		return helper.callRest(path + center + "/" + servicePath);
	}
	
	@GET
	@Path("testing-center/{center}/booked-timing/{customer-no}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getBookedTiming(@PathParam("center") String center,
			@PathParam("customer-no") String customer_no) {

		String path = helper.getPath("testing-center");
		String servicePath = helper.getServicePath("testing-center","booked-timing");
		return helper.callRest(path + center + "/" + servicePath + customer_no);
	}
	
	@GET
	@Path("testing-center/{center}/book-timing/{day}/{month}/{year}/{time}")
	@Produces(MediaType.TEXT_PLAIN)
	public String bookTiming(@PathParam("center") String center,
			@PathParam("day") String day,
			@PathParam("month") String month,
			@PathParam("year") String year,
			@PathParam("time") String time) {
		
		String path = helper.getPath("testing-center");
		String servicePath = helper.getServicePath("testing-center","book-timing");
		return helper.callRest(path + center + "/" + servicePath + day + "/" + month + "/" + year + "/" + time);
	}
	
	// =============Dubai Police=============
	@GET
	@Path("dubai-police/get-fines/{license-no}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFines(@PathParam("license-no") String license_no) {
		
		String path = helper.getPath("dubai-police");
		String servicePath = helper.getServicePath("dubai-police","get-fines");
		return helper.callRest(path + servicePath + license_no);
	}
	
	@GET
	@Path("dubai-police/payment/{fine-no}/{license-no}/{credit-card}/{amount}")
	@Produces(MediaType.TEXT_PLAIN)
	public String payment(@PathParam("fine-no") String fine_no, 
			@PathParam("license-no") String license_no, 
			@PathParam("credit-card") String credit_card, 
			@PathParam("amount") double amount) {
		
		String path = helper.getPath("dubai-police");
		String servicePath = helper.getServicePath("dubai-police","payment");
		return helper.callRest(path + servicePath + fine_no + "/" + license_no + "/" + credit_card + "/" + amount);
	}
	
	// =============RTA=============
	@GET
	@Path("rta/renewal-fees/{license-no}/{registration-no}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRenewalFees(@PathParam("license-no") String license_no, 
			@PathParam("registration-no") String registration_no) {
		
		String path = helper.getPath("rta");
		String servicePath = helper.getServicePath("rta","renewal-fees");
		return helper.callRest(path + servicePath + license_no + "/" + registration_no);
	}
	
	@GET
	@Path("rta/renew-registration/{license-no}/{registration-no}/{credit-card}/{amount}")
	@Produces(MediaType.TEXT_PLAIN)
	public String renewRegistration(@PathParam("license-no") String license_no, 
			@PathParam("registration-no") String registration_no,
			@PathParam("credit-card") String credit_card, 
			@PathParam("amount") double amount) {
		
		String path = helper.getPath("rta");
		String servicePath = helper.getServicePath("rta","renew-registration");
		return helper.callRest(path + servicePath + license_no + "/" + registration_no + "/" + credit_card + "/" + amount);
	}

}
