package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    API crudObject = new API();


    @GET
    @Path("find")
    @Produces(MediaType.TEXT_PLAIN)
    public String find() {
        return (crudObject.Find());
    }


    @POST
    @Path("insert")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String insert(String input) throws JSONException {

        return (crudObject.Insert(input));
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(String input) throws Exception {
        return (crudObject.Update(input));
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") String id) {
        return (crudObject.Delete(id));
    }


    @GET
    @Path("sort/{field}/{order}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sort(@PathParam("field")String field,@PathParam("order")String order) throws JSONException {
        return (Features.sorted(field,order));
    }


    @GET
    @Path("search/{text}")
    @Produces(MediaType.TEXT_PLAIN)
    public String search(@PathParam("text")String text) {
        return (Features.search(text));
    }


}