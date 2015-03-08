
package Example.service;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.eclipse.emf.ecore.util.EcoreUtil;
import javax.ws.rs.core.Response;
import webmapi.service.configuration.Wrapper;
import webmapi.util.ResourceSaver;
import org.jboss.resteasy.annotations.providers.jaxb.json.Mapped;
import org.jboss.resteasy.annotations.providers.jaxb.json.XmlNsMap;

import Example.*;
import Example.impl.*;

public class SonResource{

    private Example.Son instance;
    private String id;
	private ResourceSaver resourceSaver;
    public SonResource(Example.Son instance,String id, ResourceSaver r){
    	this.instance = instance;
    	this.id = id;
    	resourceSaver = r;
    }

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Mapped(namespaceMap = {
	        @XmlNsMap(namespace = "http://emf-rest.com/proxy", jsonName = "proxy")
	})
	public Example.Son getSon(){
		return instance;
	}
		
	@PUT
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Mapped(namespaceMap = {
	        @XmlNsMap(namespace = "http://emf-rest.com/proxy", jsonName = "proxy")
	})
	public void updateSon(SonImpl object){
		resourceSaver.put(EcoreUtil.getIdentification(instance), object);
	}
	@DELETE
	@Mapped(namespaceMap = {
	        @XmlNsMap(namespace = "http://emf-rest.com/proxy", jsonName = "proxy")
	})
	public void deleteSon() {
		resourceSaver.remove(EcoreUtil.getIdentification(instance));
	}

  
   @Path("family")
   public Example.service.FamilyResource getResourceFamily()
   {
      return new Example.service.FamilyResource(instance.getFamily(),id,resourceSaver);
   }    
		
		 // REF family
		
		
	
}

