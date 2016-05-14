package com.gatz.dc.api.rest.resource;

import java.util.List;
import java.util.Map;

public interface ICoordinateResource {

	List<Map<String, String>> getBaiduCoordinate(List<String> addresses)  throws Exception ;

}
