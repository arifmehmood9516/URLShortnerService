package service.gr.com;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import businesslayer.gr.com.UrlService;
import businesslayer.gr.com.UrlServiceRemote;
import entity.gr.com.HitRecord;
import entity.gr.com.UrlEntity;


@Path("/url")
public class WebService {

	@GET
    @Path("/helloworld")
    public Response getHelloWorld() {
        String value = "Hi world";
        return Response.status(200).entity(value).build();
    }
	
	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
    public List<UrlEntity> getAllData() {
    	UrlServiceRemote br = (UrlServiceRemote ) ServiceManager.jndiLookup(UrlService.class);
    	List<UrlEntity> urlEntity = br.getUrl();
    	return urlEntity;
    }
	
	
	@POST
	@Path("/addurl")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public UrlEntity addShortUrl(UrlEntity urlEntity) {
    	UrlServiceRemote br = (UrlServiceRemote ) ServiceManager.jndiLookup(UrlService.class);
    	urlEntity = br.addShortUrl(urlEntity);
    	return urlEntity;
    }
    
	@POST
    @Path("/deleteurl")
    @Consumes(MediaType.APPLICATION_JSON)
	  public Response addUrl(UrlEntity urlEntity) {
    	UrlServiceRemote br = (UrlServiceRemote ) ServiceManager.jndiLookup(UrlService.class);
    	Boolean resp = br.deleteShortUrl(urlEntity);
    	return Response.status(200).entity(resp.toString()).build();
    }
	
	@GET
    @Path("/hiturl/{shortname}")
	@Consumes(MediaType.TEXT_HTML)
	 public Response urlHit(@HeaderParam("user-agent") String userAgent,@PathParam("shortname") String shorturl) 
	 {
    	UrlServiceRemote br = (UrlServiceRemote ) ServiceManager.jndiLookup(UrlService.class);
    	URI urlFull=URI.create(br.hitUrl(shorturl,userAgent));
    	return Response.temporaryRedirect(urlFull).build();
    }
	
	
	@POST
	@Path("/hitrecord")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public List<HitRecord> getRecords(UrlEntity urlEntity) {
    	UrlServiceRemote br = (UrlServiceRemote ) ServiceManager.jndiLookup(UrlService.class);
    	return br.getRecords(urlEntity.getId());
    }
	
	
}
