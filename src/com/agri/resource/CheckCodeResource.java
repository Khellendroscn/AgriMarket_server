package com.agri.resource;

import net.khe.util.Generator;
import webutil.CheckCode;
import webutil.CheckCodeGenerator;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.awt.*;
import java.awt.image.RenderedImage;

/**
 * Created by hyc on 2017/4/17.
 */
@Singleton
@Path("/checkcode")
public class CheckCodeResource {
    private Generator<CheckCode> gen = new CheckCodeGenerator();
    private CheckCode code;
    @GET
    @Path("/img")
    @Produces({"image/jpeg"})
    public Image getImage() {
        code = gen.next();
        return code.getImage();
    }
    @GET
    @Path("/code")
    @Produces({"application/json"})
    public String getCode() {
        return code.getCheckCode();
    }
}
