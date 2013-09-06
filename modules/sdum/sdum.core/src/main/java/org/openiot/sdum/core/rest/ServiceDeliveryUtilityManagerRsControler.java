package org.openiot.sdum.core.rest;

/**
 * Copyright (c) 2011-2014, OpenIoT
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: OpenIoT mailto: info@openiot.eu
 */

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.openiot.commons.descriptiveids.model.DescreptiveIDs;
import org.openiot.commons.osdspec.model.OAMO;
import org.openiot.commons.osdspec.model.OSDSpec;
import org.openiot.commons.osdspec.model.OSMO;
import org.openiot.commons.sdum.serviceresultset.model.SdumServiceResultSet;
import org.openiot.sdum.core.api.impl.PollForReportImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
@Path("/services")
@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
public class ServiceDeliveryUtilityManagerRsControler {

	//Logger's initialization
	final static Logger logger = LoggerFactory.getLogger(ServiceDeliveryUtilityManagerRsControler.class);
	



	/**
	 * @return
	 */
	@GET()
	@Produces("text/plain")
	public String welcomeMessage() {

		String welcomeText;

		welcomeText = "Welcome to Service Delivery & Utility Manager Rest Interface\n" 
				+ "=============================================================\n\n"
				+ "This Interface provides the folowing Services:\n"
				+ "subscribeForReport(serviceID: String, dest: URI): String\n"
				+ "unsubscribe (serviceID: String): boolean\n"
				+ "pollForReport (serviceID: String): SdumServiceResultSet\n"
				+ "getSubscribers(serviceID: String): List<URI>\n"
				+ "getUtilityUsage(userID: String): UtilityUsage\n"
				+ "getServiceUsage(serviceID: String): ServiceUsage\n"
				+ "getServiceStatus (serviceID: String): ServiceStatus\n"
				+ "getService(serviceID: String): OSMO\n" + "getUser(userID: String): OpenIotUser\n"
				+ "getAvailableServicesIDs(userID: String): List<DescriptiveID>\n"
				+ "getAvailableServices (userID: String): OSDSpec";

		logger.debug(welcomeText);

		return welcomeText;
	}
	
	

	/**
	 * Invokes a previously defined Service having the specified applicationID.
	 * This call will produce only one Result Set.
	 * 
	 * @param applicationID
	 * @return
	 */
	@GET
	@Path("/pollforreport")
	public SdumServiceResultSet pollForReport(@QueryParam("applicationID") String applicationID) {

		PollForReportImpl pollForReportImpl = new PollForReportImpl(applicationID);

		return pollForReportImpl.getSdumServiceResultSet();

	}
	
	
	
	/**
	 * Used to retrieve the description (OAMO) of an available Application.
	 * Requires as input the Application ID
	 * 
	 * @param applicationID
	 * @return
	 */
	@GET
	@Path("/getApplication")
	public OAMO getApplication(@QueryParam("applicationID") String applicationID) {

		OAMO oamo = new OAMO();

		return oamo;
	}
	
	
	
	

	/**
	 * Used to retrieve the description (OSMO) of an available service. Requires
	 * as input the Service ID
	 * 
	 * @param serviceID
	 * @return
	 */
	@GET
	@Path("/getService")
	public OSMO getService(@QueryParam("serviceID") String serviceID) {

		OSMO osmo = new OSMO();

		return osmo;
	}
	
	
	
	
	/**
	 * Used to retrieve the available applications (a list of
	 * applicationID/ServiceName/ServiceDescription triplet) already registered
	 * by a specific user. Requires as input the User ID.
	 * 
	 * @param userID
	 * @return
	 */
	@GET
	@Path("/getAvailableAppIDs")
	public DescreptiveIDs getAvailableAppIDs(@QueryParam("userID") String userID) {

		DescreptiveIDs descreptiveIDs = new DescreptiveIDs();

		return descreptiveIDs;
	}
	
	
	

	/**
	 * Used to retrieve the available services (a list of
	 * serviceID/ServiceName/ServiceDescription triplet) already registered by a
	 * specific user. Requires as input the Service ID.
	 * 
	 * @param applicationID
	 * @return
	 */
	@GET
	@Path("/getAvailableServiceIDs")
	public DescreptiveIDs getAvailableServiceIDs(@QueryParam("applicationID") String applicationID) {

		DescreptiveIDs descreptiveIDs = new DescreptiveIDs();

		return descreptiveIDs;
	}
	
	

}
