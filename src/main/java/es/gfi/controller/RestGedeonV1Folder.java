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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sun.jmx.snmp.Timestamp;

import es.gfi.model.gedeon.v1.document.GedeonV1Document;
import es.gfi.model.gedeon.v1.exception.GedeonV1ReponseException;
import es.gfi.model.gedeon.v1.folder.GedeonV1BodyFolders;
import es.gfi.model.gedeon.v1.folder.GedeonV1Folder;

@RestController
public class RestGedeonV1Folder<T> {

	private final Logger logger = LoggerFactory.getLogger(RestGedeonV1Folder.class);

	private static String[] lAppList = { "ARP", "GINCO", "AGREGE", "OSCAR" };
	private static String[] lUserList = { "usuario", "emilio", "cristobal", "bartolo", "sidy", "david" };
	private static String[] lUserPassword = { "usuario", "Arp123456" };
	private static String[] lFolders = { "f1bb784e-3105-4b02-a98e-be9543e623b2", "f1bb784e-3105-4b02-a98e-be9543e623b3",
			"f1bb784e-3105-4b02-a98e-be9543e623b4", "f1bb784e-3105-4b02-a98e-be9543e623b5" };

	/*****************************************************************************
	 * INICIO PARA PROBAR EL REST DE GEDEON
	 *****************************************************************************/

	// *******************************
	// folder-resource Folder Resource
	// *******************************

	// GET /gedeon/v1/{appName}/folders Récupérer la liste des dossiers de
	// l'application
	@GetMapping(path = "/gedeon/v1/{appName}/folders", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getApplicationsFolders(@PathVariable String appName) {

		logger.debug("getApplicationsFolders Debut");
		logger.debug("appName = " + appName);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsFolders Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1BodyFolders response = new GedeonV1BodyFolders();

			response.setCount(0);

			GedeonV1Folder<Object, String, List<Object>> items = new GedeonV1Folder<Object, String, List<Object>>();
			items.setCreatedBy("createdBy Test 1");
			items.setCreationDate(String.valueOf(new Timestamp()));
			items.setDescription("description Test 1");
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
			response.setItems(items);
			response.setLimit(0);
			response.setOffset(0);
			response.setTotal(0);

			logger.debug("getApplicationsFolders Fin");

			return new ResponseEntity(response, HttpStatus.OK);
		}
	}

	// POST /gedeon/v1/{appName}/folders Créer un dossier
	@PostMapping(path = "/gedeon/v1/{appName}/folders", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> postApplicationsFoldersPost(@PathVariable String appName,
			@RequestHeader("idFolder") String idFolder) {

		logger.debug("getApplicationsFoldersPost Debut");
		logger.debug("appName = " + appName);
		logger.debug("idFolder = " + idFolder);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsFoldersPos Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1Folder<Object, String, List<Object>> items = new GedeonV1Folder<Object, String, List<Object>>();
			items.setCreatedBy("createdBy Test 1");
			items.setCreationDate(String.valueOf(new Timestamp()));
			items.setDescription("description Test 1");
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

			logger.debug("getApplicationsFoldersPos Fin");

			return new ResponseEntity(items, HttpStatus.CREATED);
		}
	}

	// GET /gedeon/v1/{appName}/folders/{folderId} Récupérer les métadonnées
	// d'un dossier avec son identifian
	@GetMapping(path = "/gedeon/v1/{appName}/folders/{idFolder}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getApplicationsFoldersId(@PathVariable String appName,
			@PathVariable("idFolder") String idFolder) {

		logger.debug("getApplicationsFoldersGet Debut");
		logger.debug("appName = " + appName);
		logger.debug("idFolder = " + idFolder);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsFoldersGet Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!ifFolders(idFolder)) {

			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsFoldersGet Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);

		} else {

			GedeonV1Folder<Object, String, List<Object>> items = new GedeonV1Folder<Object, String, List<Object>>();
			items.setCreatedBy("createdBy Test 1");
			items.setCreationDate(String.valueOf(new Timestamp()));
			items.setDescription("description Test 1");
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

			logger.debug("getApplicationsFoldersGet Fin");

			return new ResponseEntity(items, HttpStatus.CREATED);
		}
	}

	// DELETE /gedeon/v1/{appName}/folders/{folderId} Supprimer un dossier
	@DeleteMapping(path = "/gedeon/v1/{appName}/folders/{idFolder}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> deleteApplicationsFolderbyId(@PathVariable String appName,
			@PathVariable("idFolder") String idFolder) {

		logger.debug("deleteApplicationsFolderbyId Debut");
		logger.debug("appName = " + appName);
		logger.debug("idFolder = " + idFolder);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsFoldersGet Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!ifFolders(idFolder)) {

			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsFoldersGet Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);

		} else {

			GedeonV1Folder<Object, String, List<Object>> items = new GedeonV1Folder<Object, String, List<Object>>();
			items.setCreatedBy("createdBy Test 1");
			items.setCreationDate(String.valueOf(new Timestamp()));
			items.setDescription("description Test 1");
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

			logger.debug("getApplicationsFoldersGet Fin");

			return new ResponseEntity(items, HttpStatus.OK);
		}
	}
	

	// PATCH /gedeon/v1/{appName}/folders/{folderId} Mettre à jour un dossier
	@PatchMapping(path = "/gedeon/v1/{appName}/folders/{folderId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getApplicationsFoldersById2(@PathVariable String appName, @PathVariable String folderId
		   , @RequestHeader("updatedFolder") String updatedFolder
	){
		
		logger.debug("getApplicationsFoldersById2 Debut");
		logger.debug("appName = " + appName);
		logger.debug("documentId = " + folderId);
		logger.debug("updatedFolder = " + updatedFolder);	
		

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsFoldersById2 Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!ifFolders(folderId)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getApplicationsFoldersById2 Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1Folder<Object, String, List<Object>> items = new GedeonV1Folder<Object, String, List<Object>>();
			items.setCreatedBy("createdBy Test 1");
			items.setCreationDate(String.valueOf(new Timestamp()));
			items.setDescription("description Test 1");
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

			logger.debug("getApplicationsFoldersById2 Fin");

			return new ResponseEntity(items, HttpStatus.OK);
		}
	}

	// POST /gedeon/v1/{appName}/folders/{folderId}/close Clôturer un dossier
	@PostMapping(path = "/gedeon/v1/{appName}/folders/{folderId}/close", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> closeApplicationsFoldersById(@PathVariable String appName, @PathVariable String folderId
	){
		
		logger.debug("closeApplicationsFoldersById Debut");
		logger.debug("appName = " + appName);
		logger.debug("documentId = " + folderId);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("closeApplicationsFoldersById Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!ifFolders(folderId)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("closeApplicationsFoldersById Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1Folder<Object, String, List<Object>> items = new GedeonV1Folder<Object, String, List<Object>>();
			items.setCreatedBy("createdBy Test 1");
			items.setCreationDate(String.valueOf(new Timestamp()));
			items.setDescription("description Test 1");
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

			logger.debug("closeApplicationsFoldersById Fin");

			return new ResponseEntity(items, HttpStatus.OK);
		}
	}
	
	// GET /gedeon/v1/{appName}/folders/{folderId}/documents Récupérer les
	// documents constituant d'un dossier
	@GetMapping(path = "/gedeon/v1/{appName}/folders/{folderId}/documents", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> getDocumentsFoldersById(@PathVariable String appName, @PathVariable String folderId
			, @RequestHeader("queryFilter") String queryFilter, @RequestHeader("sortBy") String sortBy 
	){
		
		logger.debug("getDocumentsFoldersById Debut");
		logger.debug("appName = " + appName);
		logger.debug("documentId = " + folderId);
		logger.debug("queryFilter = " + queryFilter);
		logger.debug("sortBy = " + sortBy);

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getDocumentsFoldersById Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!ifFolders(folderId)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("getDocumentsFoldersById Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1Folder<Object, String, List<Object>> items = new GedeonV1Folder<Object, String, List<Object>>();
			items.setCreatedBy("createdBy Test 1");
			items.setCreationDate(String.valueOf(new Timestamp()));
			items.setDescription("description Test 1");
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

			logger.debug("getDocumentsFoldersById Fin");

			return new ResponseEntity(items, HttpStatus.OK);
		}
	}

	// POST /gedeon/v1/{appName}/folders/{folderId}/documents Création d'un
	// document dans un dossier
	@PostMapping(path = "/gedeon/v1/{appName}/folders/{folderId}/documents", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> creationDocumentFolders(@PathVariable String appName, @PathVariable String folderId
			 , @RequestHeader("documento") String documento, @RequestHeader("versioning") String versioning
			 , @RequestPart("file") MultipartFile theFile 
	){
		
		logger.debug("closeApplicationsFoldersById Debut");
		logger.debug("appName = " + appName);		
		logger.debug("documentId = " + folderId);
		logger.debug("documento = " + documento);
		logger.debug("versioning = " + versioning);
		logger.debug("theFile = " + theFile.getName());
		

		if (!isApp(appName)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("closeApplicationsFoldersById Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.UNAUTHORIZED);
		} else if (!ifFolders(folderId)) {
			GedeonV1ReponseException gedeonV1ReponseException = new GedeonV1ReponseException();
			gedeonV1ReponseException.setDetails("details Test 1");
			gedeonV1ReponseException.setMessage("message Test 1");
			gedeonV1ReponseException.setTimestamp(String.valueOf(new Timestamp()));

			logger.debug("closeApplicationsFoldersById Fin");

			return new ResponseEntity(gedeonV1ReponseException, HttpStatus.NOT_FOUND);
		} else {

			GedeonV1Folder<Object, String, List<Object>> items = new GedeonV1Folder<Object, String, List<Object>>();
			items.setCreatedBy("createdBy Test 1");
			items.setCreationDate(String.valueOf(new Timestamp()));
			items.setDescription("description Test 1");
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

			logger.debug("closeApplicationsFoldersById Fin");

			return new ResponseEntity(items, HttpStatus.OK);
		}
	}
	

	private boolean ifFolders(String folderId) {
		for (int i = 0; i < lFolders.length; i++) {
			if (lFolders[i].equals(folderId)) {
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
