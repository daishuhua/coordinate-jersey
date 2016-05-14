package com.gatz.dc.api.rest.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gatz.dc.api.rest.common.SpringBeanHelper;
import com.gatz.dc.api.rest.service.CoordinateService;

@Path("/coordinate")
public class CoordinateResource implements ICoordinateResource {

	@POST
	@Path("/baidu")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Map<String, String>> getBaiduCoordinate(List<String> addresses) throws Exception {
		CoordinateService coordinateService = (CoordinateService) SpringBeanHelper.getBean("coordinateService");
		return coordinateService.getBaiduCoordinate(addresses);
	}
}
