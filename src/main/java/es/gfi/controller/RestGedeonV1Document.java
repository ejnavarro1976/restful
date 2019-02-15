package es.gfi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sun.jmx.snmp.Timestamp;

import es.gfi.model.gedeon.v1.document.GedeonV1BodyDocuments;
import es.gfi.model.gedeon.v1.document.GedeonV1BodyStreamingResponseBody;
import es.gfi.model.gedeon.v1.document.GedeonV1Document;
import es.gfi.model.gedeon.v1.exception.GedeonV1ReponseException;

@RestController
public class RestGedeonV1Document<T> {

	private final Logger logger = LoggerFactory.getLogger(RestGedeonV1Document.class);

	private static String[] lAppList = { "ARP", "GINCO", "AGREGE", "OSCAR" };
	private static String[] lUserList = { "usuario", "emilio", "cristobal", "bartolo", "sidy", "david" };
	private static String[] lUserPassword = { "usuario", "Arp123456" };
	private static String[] lDocuments = { "f1bb784e-3105-4b02-a98e-be9543e623b2",
			"f1bb784e-3105-4b02-a98e-be9543e623b3", "f1bb784e-3105-4b02-a98e-be9543e623b4",
			"f1bb784e-3105-4b02-a98e-be9543e623b5" };

	/*****************************************************************************
	 * INICIO PARA PROBAR EL REST DE GEDEON
	 *****************************************************************************/

	// *******************************
	// document-resource Document Resource
	// *******************************

	// GET /gedeon/v1/{appName}/documents Recherche sur les documents
	@GetMapping(path = "/gedeon/v1/{appName}/documents", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getApplicationsDocuments(@PathVariable String appName) {

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

			GedeonV1BodyDocuments response = new GedeonV1BodyDocuments();

			response.setCount(0);

			GedeonV1Document<Object, String, List<Object>> items = new GedeonV1Document<Object, String, List<Object>>();
			items.setCreatedBy("createdBy Test 1");
			items.setCreationDate(String.valueOf(new Timestamp()));
			items.setDescription("description Test 1");
			items.setFolderId("c04976b6-5d38-4574-bc3e-2c065da343d1");
			items.setId("f1bb784e-3105-4b02-a98e-be9543e623b2");
			items.setLastModifictionDate(String.valueOf(new Timestamp()));
			items.setLastModifiedBy("mdupont");

			HashMap<String, List<Object>> metadata = new HashMap<String, List<Object>>();
			List<Object> listMetadata1 = new ArrayList<Object>();
			listMetadata1.add("metadata 1 aditional Test 1");
			listMetadata1.add("metadata 2 aditional Test 1");

			List<Object> listMetadata2 = new ArrayList<Object>();

			metadata.put("additionalProp1", listMetadata1);
			metadata.put("additionalProp2", listMetadata2);
			items.setMetadata(metadata);

			items.setName("example.txt");
			items.setPrimaryParentId("f1bb784e-3105-4b02-a98e-be9543e623b2");
			items.setVersionLabel("versionLabel Test 1");
			response.setItems(items);
			response.setLimit(0);
			response.setOffset(0);
			response.setTotal(0);

			logger.debug("getApplicationsbyName Fin");

			return new ResponseEntity(response, HttpStatus.OK);
		}

	}

	// GET /gedeon/v1/{appName}/documents/{documentId} Récupérer les métadonnées
	// d'un document à partir de son identifiant
	@GetMapping(path = "/gedeon/v1/{appName}/documents/{documentId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getApplicationsDocumentbyId(@PathVariable String appName,
			@PathVariable String documentId) {

		logger.debug("getApplicationsDocumentbyId Debut");
		logger.debug("appName = " + appName);
		logger.debug("documentId = " + documentId);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsDocumentbyId Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!isDocument(documentId)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsDocumentbyId Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1Document response = new GedeonV1Document();
			response.setCreationDate(String.valueOf(new Timestamp()));
			response.setDescription("description Test 1");
			response.setFolderId("c04976b6-5d38-4574-bc3e-2c065da343d1");
			response.setId(documentId);
			response.setLastModifictionDate(String.valueOf(new Timestamp()));
			response.setLastModifiedBy("mdupont");

			HashMap metadata = new HashMap<String, List<Object>>();
			List<Object> listMetadata1 = new ArrayList<Object>();
			listMetadata1.add("metadata 1 aditional Test 1");
			listMetadata1.add("metadata 2 aditional Test 1");

			List<Object> listMetadata2 = new ArrayList<Object>();

			metadata.put("additionalProp1", listMetadata1);
			metadata.put("additionalProp2", listMetadata2);
			response.setMetadata(metadata);

			response.setName("example.txt");
			response.setPrimaryParentId("f1bb784e-3105-4b02-a98e-be9543e623b2");
			response.setVersionLabel("versionLabel Test 1");

			logger.debug("getApplicationsDocumentbyId Fin");

			return new ResponseEntity(response, HttpStatus.OK);
		}
	}

	// DELETE /gedeon/v1/{appName}/documents/{documentId} Supprimer un document
	@DeleteMapping(path = "/gedeon/v1/{appName}/documents/{documentId}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> deleteApplicationsDocumentbyId(@PathVariable String appName,
			@PathVariable String documentId) {

		logger.debug("deleteApplicationsDocumentbyId Debut");
		logger.debug("appName = " + appName);
		logger.debug("documentId = " + documentId);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("deleteApplicationsDocumentbyId Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!isDocument(documentId)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsDocumentbyId Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {
			GedeonV1Document response = new GedeonV1Document();
			response.setCreationDate(String.valueOf(new Timestamp()));
			response.setDescription("description Test 1");
			response.setFolderId("c04976b6-5d38-4574-bc3e-2c065da343d1");
			response.setId(documentId);
			response.setLastModifictionDate(String.valueOf(new Timestamp()));
			response.setLastModifiedBy("mdupont");

			HashMap metadata = new HashMap<String, List<Object>>();
			List<Object> listMetadata1 = new ArrayList<Object>();
			listMetadata1.add("metadata 1 aditional Test 1");
			listMetadata1.add("metadata 2 aditional Test 1");

			List<Object> listMetadata2 = new ArrayList<Object>();

			metadata.put("additionalProp1", listMetadata1);
			metadata.put("additionalProp2", listMetadata2);
			response.setMetadata(metadata);

			response.setName("example.txt");
			response.setPrimaryParentId("f1bb784e-3105-4b02-a98e-be9543e623b2");
			response.setVersionLabel("versionLabel Test 1");

			logger.debug("deleteApplicationsDocumentbyId Fin");

			return new ResponseEntity(response, HttpStatus.OK);
		}
	}

	// PATCH /gedeon/v1/{appName}/documents/{documentId} Mettre à jour
	// (partiellement) un document
	@PatchMapping(path = "/gedeon/v1/{appName}/documents/{documentId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> patchApplicationsDocumentbyId(@PathVariable String appName, @PathVariable String documentId
		   , @RequestHeader("documento") String documento, @RequestHeader("versioning") String versioning
		   , @RequestPart("file") MultipartFile theFile
		   ){
		
		logger.debug("getApplicationsDocumentbyId Debut");
		logger.debug("appName = " + appName);
		logger.debug("documentId = " + documentId);
		logger.debug("documento = " + documento);
		logger.debug("versioning = " + versioning);
		logger.debug("theFile = " + theFile.getName());
		

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsDocumentbyId Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!isDocument(documentId)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsDocumentbyId Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1Document response = new GedeonV1Document();
			response.setCreationDate(String.valueOf(new Timestamp()));
			response.setDescription("description Test 1");
			response.setFolderId("c04976b6-5d38-4574-bc3e-2c065da343d1");
			response.setId(documentId);
			response.setLastModifictionDate(String.valueOf(new Timestamp()));
			response.setLastModifiedBy("mdupont");

			HashMap metadata = new HashMap<String, List<Object>>();
			List<Object> listMetadata1 = new ArrayList<Object>();
			listMetadata1.add("metadata 1 aditional Test 1");
			listMetadata1.add("metadata 2 aditional Test 1");

			List<Object> listMetadata2 = new ArrayList<Object>();

			metadata.put("additionalProp1", listMetadata1);
			metadata.put("additionalProp2", listMetadata2);
			response.setMetadata(metadata);

			response.setName("example.txt");
			response.setPrimaryParentId("f1bb784e-3105-4b02-a98e-be9543e623b2");
			response.setVersionLabel("versionLabel Test 1");

			logger.debug("getApplicationsDocumentbyId Fin");

			return new ResponseEntity(response, HttpStatus.OK);
		}
	}

	// GET /gedeon/v1/{appName}/documents/{documentId}/content Télécharger le
	// document (son contenu)
	@GetMapping(path = "/gedeon/v1/{appName}/documents/{documentId}/content", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getApplicationsDocumentbyIdContent(@PathVariable String appName,
			@PathVariable String documentId) {

		logger.debug("getApplicationsDocumentbyIdContent Debut");
		logger.debug("appName = " + appName);
		logger.debug("documentId = " + documentId);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsDocumentbyIdContent Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!isDocument(documentId)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsDocumentbyIdContent Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1BodyStreamingResponseBody response = new GedeonV1BodyStreamingResponseBody();
			response.setName("OK");

			logger.debug("getApplicationsDocumentbyIdContent Fin");

			return new ResponseEntity(response, HttpStatus.OK);
		}
	}

	private boolean isDocument(String documentId) {
		for (int i = 0; i < lDocuments.length; i++) {
			if (lDocuments[i].equals(documentId)) {
				return true;
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
