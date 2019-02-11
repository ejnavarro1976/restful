package es.gfi.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jmx.snmp.Timestamp;

import es.gfi.model.gedeon.v1.application.GedeonV1BodyApplications;
import es.gfi.model.gedeon.v1.application.GedeonV1BodyApplicationsLogin;
import es.gfi.model.gedeon.v1.application.GedeonV1Group;
import es.gfi.model.gedeon.v1.application.GedeonV1Model;
import es.gfi.model.gedeon.v1.application.GedeonV1PropertyDefinition;
import es.gfi.model.gedeon.v1.application.GedeonV1Root;
import es.gfi.model.gedeon.v1.application.GedeonV1TypeDefinition;
import es.gfi.model.gedeon.v1.application.GedeonV1TypeMap;
import es.gfi.model.gedeon.v1.application.GedeonV1User;
import es.gfi.model.gedeon.v1.application.GedeonV1View;
import es.gfi.model.gedeon.v1.exception.GedeonV1ReponseException;

@RestController
public class RestGedeonV1Application<T> {

	private final Logger logger = LoggerFactory.getLogger(RestGedeonV1Application.class);

	private static String[] lAppList = { "ARP", "GINCO", "AGREGE", "OSCAR" };
	private static String[] lUserList = { "usuario", "emilio", "cristobal", "bartolo", "sidy", "david" };
	private static String[] lUserPassword = { "usuario", "Arp123456" };

	/*****************************************************************************
	 * INICIO PARA PROBAR EL REST DE GEDEON
	 *****************************************************************************/

	// *******************************
	// application-resource Application Resource
	// *******************************

	// GET /gedeon/v1/applications/{appName} Récupérer la définition de
	// l'application
	@GetMapping(path = "/gedeon/v1/applications/{appName}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getApplicationsbyName(@PathVariable String appName) {

		logger.debug("getApplicationsbyName Debut");
		logger.debug("appName = " + appName);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsbyName Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1BodyApplications response = new GedeonV1BodyApplications();

			response.setDescription("Descripción Test 1");

			GedeonV1Group groups = new GedeonV1Group();
			groups.setDisplayName("DisplayName Test 1");
			groups.setName("Display Test 1");

			response.setGroups(groups);

			response.setId("2019-02-07T12:07:05.987Z");

			GedeonV1Model gedeonV1Model = new GedeonV1Model();

			GedeonV1TypeDefinition gedeonV1TypeDefinition = new GedeonV1TypeDefinition();

			gedeonV1TypeDefinition.setAspect(Boolean.TRUE);
			gedeonV1TypeDefinition.setName("name Test 1");

			GedeonV1PropertyDefinition gedeonV1PropertyDefinition = new GedeonV1PropertyDefinition();
			gedeonV1PropertyDefinition.setDatatype("datatype Test 1");
			gedeonV1PropertyDefinition.setName("name Test 1");
			gedeonV1PropertyDefinition.setTitle("title Test 1");

			gedeonV1TypeDefinition.setProperties(gedeonV1PropertyDefinition);

			gedeonV1TypeDefinition.setTitle("title Test 1");

			gedeonV1Model.setDefinitions(gedeonV1TypeDefinition);
			gedeonV1Model.setFile("file Test 1");
			gedeonV1Model.setName("name Test 1");

			GedeonV1TypeMap gedeonV1TypeMap = new GedeonV1TypeMap();
			gedeonV1TypeMap.setDocument("document Test 1");
			gedeonV1TypeMap.setFolder("folder Test 1");
			gedeonV1Model.setTypeMap(gedeonV1TypeMap);

			gedeonV1Model.setTypeMap(gedeonV1TypeMap);

			response.setModel(gedeonV1Model);

			response.setName("name Tests 1");

			GedeonV1Root gedeonV1Root = new GedeonV1Root();
			gedeonV1Root.setDisplayPath("displayPath Test 1");
			gedeonV1Root.setPath("path Test 1");
			gedeonV1Root.setTemplate("template Test 1 ");

			response.setRoot(gedeonV1Root);

			GedeonV1User gedeonV1User = new GedeonV1User();
			gedeonV1User.setEmail("email Test 1");

			List<String> groupList = new ArrayList<>();
			groupList.add("test-man");
			groupList.add("test-woman");
			groupList.add("furia");
			groupList.add("capitan-america");

			gedeonV1User.setGroups(groupList);
			gedeonV1User.setUsername("username Test 1");
			response.setUsers(gedeonV1User);

			GedeonV1View gedeonV1View = new GedeonV1View();
			gedeonV1View.setDef("def Test 1");
			gedeonV1View.setPath("path Test 1");

			response.setViews(gedeonV1View);

			logger.debug("getApplicationsbyName Fin");

			return new ResponseEntity(response, HttpStatus.OK);

		}

	}

	// POST /gedeon/v1/applications/{appName}/login S'identifier pour
	// l'application donnée
	@PostMapping(path = "/gedeon/v1/applications/{appName}/login", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getApplicationsbyNameLoginHeader(@PathVariable String appName,
			@RequestHeader("utilisateur") String utilisateur, @RequestHeader("password") String password) {

		logger.debug("getApplicationsbyNameLoginHeader Debut");
		logger.debug("appName = " + appName);
		logger.debug("utilisateur = " + utilisateur);
		logger.debug("password = " + password);

		GedeonV1BodyApplicationsLogin response = new GedeonV1BodyApplicationsLogin();

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else if (!isUser(utilisateur, password)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else {
			response.setName("X-Auth-Token");
		}

		logger.debug("getApplicationsbyNameLoginHeader Fin");

		return new ResponseEntity(response, HttpStatus.OK);
	}

	// POST /gedeon/v1/applications/{appName}/login S'identifier pour
	// l'application donnée
	@PostMapping(path = "/gedeon/v1/applications/{appName}/login2", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getApplicationsbyNameLogin(@PathVariable String appName,
			@RequestParam("utilisateur") String utilisateur, @RequestParam("password") String password) {

		logger.debug("getApplicationsbyNameLogin Debut");
		logger.debug("appName = " + appName);
		logger.debug("utilisateur = " + utilisateur);
		logger.debug("password = " + password);

		GedeonV1BodyApplicationsLogin response = new GedeonV1BodyApplicationsLogin();

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else if (!isUser(utilisateur, password)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else {
			response.setName("X-Auth-Token");
		}

		logger.debug("getApplicationsbyNameLogin Fin");

		return new ResponseEntity(response, HttpStatus.OK);
	}

	private boolean isUser(String usuario, String password) {

		boolean lUser = true;
		for (int i = 0; i < lUserList.length; i++) {
			if (lUserList[i].equals(usuario)) {
				lUser = false;
				break;
			}
		}

		if (!lUser) {
			for (int i = 0; i < lUserPassword.length; i++) {
				if (lUserPassword[i].equals(password)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isApp(String appName) {

		for (int i = 0; i < lAppList.length; i++) {
			if (lAppList[i].equals(appName)) {
				return true;
			}
		}

		return false;
	}

	/*****************************************************************************
	 * FIN PARA PROBAR EL REST DE GEDEON
	 *****************************************************************************/

}
