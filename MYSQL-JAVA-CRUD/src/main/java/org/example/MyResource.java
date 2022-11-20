package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    CRUD crudObject = new CRUD();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("read/{tableName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String selectTable(@PathParam("tableName") String tableName) {

        return (crudObject.select(tableName, "", ""));
    }

    @GET
    @Path("readSpecific/{tableName}/{column}/{condition}")
    @Produces(MediaType.TEXT_PLAIN)
    public String selectTable(@PathParam("tableName") String tableName, @PathParam("column") String Column,
                              @PathParam("condition") String condition) {
        return (crudObject.select(tableName, Column, condition));
    }

    @GET
    @Path("readSpecificID/{tableName}/{condition}")
    @Produces(MediaType.TEXT_PLAIN)
    public String selectTable(@PathParam("tableName") String tableName,
                              @PathParam("condition") String condition) {
        return (crudObject.select(tableName, "", condition));
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteData(@PathParam("id") String id) throws Exception {
        String tableName = " ApiInfoBase ";
        CheckErrorType errorType = new CheckErrorType();
        try {
            crudObject.delete(tableName, id);
        }catch (SQLException e) {
            return (errorType.CheckError(Integer.parseInt(e.getMessage())));
        } catch (ClassNotFoundException e) {
            return (errorType.CheckError(1));
        } catch (IOException e) {
            return (errorType.CheckError(2));
        }catch (RuntimeException e) {
            return (errorType.CheckError(3));
        }
        return ("Data Not Exist");
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(String input) throws Exception {
        CRUD opn = new CRUD();
        CheckErrorType errorType = new CheckErrorType();
        String output;
        try {
            output = opn.update(input);
        }catch (SQLException e) {
            return (errorType.CheckError(Integer.parseInt(e.getMessage())));
        } catch (ClassNotFoundException e) {
            return (errorType.CheckError(1));
        } catch (IOException e) {
            return (errorType.CheckError(2));
        }catch (RuntimeException e) {
            return (errorType.CheckError(3));
        }
        return output;
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String insert(String input) throws Exception {
        CheckErrorType errorType = new CheckErrorType();
        CRUD opn = new CRUD();
        String output;
        try {
            output = opn.insert("ApiInfoBase", new StringBuilder(input));
        } catch (SQLException e) {
            return (errorType.CheckError(Integer.parseInt(e.getMessage())));
        } catch (ClassNotFoundException e) {
            return (errorType.CheckError(1));
        } catch (IOException e) {
        return (errorType.CheckError(2));
        }
        return output;
    }
}