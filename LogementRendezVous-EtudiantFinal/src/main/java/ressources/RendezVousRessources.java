package ressources;

import entities.Logement;
import entities.RendezVous;
import filtres.Secured;
import metiers.LogementBusiness;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


public class RendezVousRessources {

    public static RendezVousBusiness rendezVousMetier = new RendezVousBusiness();


    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/newrdv")
    public Response addrendezVous(RendezVous r) {
        if(rendezVousMetier.addRendezVous(r))
            return  Response.status(Response.Status.CREATED).build();
        return  Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }


    @Secured
    @GET
    @Path("/rdv/{ref}")
    public Response getRendezVous(@PathParam("ref") String refLogement) {
        List<RendezVous> liste=new ArrayList<RendezVous>();
        if(refLogement != null) {
            liste = rendezVousMetier.getListeRendezVousByLogementReference(Integer.parseInt(refLogement));

        } else {
            liste = rendezVousMetier.getListeRendezVous();
        }

        if(liste.size()==0)
            return  Response.status(Response.Status.NOT_FOUND).build();
        return  Response.status(Response.Status.OK).entity(liste).build();
    }

    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    public Response updateRdv(RendezVous updatedRendezVous, @PathParam("id") int id) {


        if (rendezVousMetier.updateRendezVous(id,updatedRendezVous)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Secured
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    public  Response deleteRendezVous (@PathParam("id")int id){
        if(rendezVousMetier.deleteRendezVous(id))
            return Response.status(Response.Status.OK).build();


        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @Secured
    @GET
    @Path("/list/{id}")
    @Produces(MediaType.TEXT_PLAIN)

    public  Response getRendezVousbyIdint  (@PathParam("id") int id){
        if(rendezVousMetier.getRendezVousById(id)!=null)
            return Response.status(Response.Status.OK).entity(rendezVousMetier.getRendezVousById(id)).build();


        return Response.status(Response.Status.NOT_FOUND).build();

    }

}
