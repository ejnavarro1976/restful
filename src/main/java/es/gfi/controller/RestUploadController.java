package es.gfi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.gfi.model.UploadModel;
import es.gfi.utils.Zipper;

@RestController
public class RestUploadController<T> {

	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "C:\\temporal2\\";

	/**
	 * uploadFile
	 * 
	 * @param uploadfile
	 * @return
	 */
	@PostMapping("/api/upload")
	// If not @RestController, uncomment this
	// @ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {

		logger.debug("Single file upload!");

		if (uploadfile.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {

		 saveUploadedFiles(Arrays.asList(uploadfile));
		
		
		 //Loading an existing PDF document
		 File file = new File(uploadfile.getName());
		 PDDocument document = PDDocument.load(file); 
		
		 //Instantiating Splitter class
		 Splitter splitter = new Splitter();
		
		 //splitting the pages of a PDF document
		 List<PDDocument> Pages = splitter.split(document);
		
		 //Creating an iterator 
		 Iterator<PDDocument> iterator = Pages.listIterator();
		
		 //Saving each page as an individual document
		 int k = 1;
		 while(iterator.hasNext()) {
		    PDDocument pd = iterator.next();
		    pd.save(UPLOADED_FOLDER + "sample"+ k++ +".pdf");
		 }
		 System.out.println("Multiple PDF’s created");
		 document.close();
					
		// cadena que contiene la ruta donde están los archivos a comprimir
		String directorioZip = UPLOADED_FOLDER;
		// ruta completa donde están los archivos a comprimir
				File carpetaComprimir = new File(directorioZip);
		 
				// valida si existe el directorio
		if (carpetaComprimir.exists()) {
			// lista los archivos que hay dentro del directorio
			File[] ficheros = carpetaComprimir.listFiles();
			System.out.println("Número de ficheros encontrados: " + ficheros.length);
		 
			// ciclo para recorrer todos los archivos a comprimir
			for (int i = 0; i < ficheros.length; i++) {
				System.out.println("Nombre del fichero: " + ficheros[i].getName());
				String extension="";
				for (int j = 0; j < ficheros[i].getName().length(); j++) {
					//obtiene la extensión del archivo
					if (ficheros[i].getName().charAt(j)=='.') {
						extension=ficheros[i].getName().substring(j, (int)ficheros[i].getName().length());
						//System.out.println(extension);
					}
				}
				try {
					// crea un buffer temporal para ir poniendo los archivos a comprimir
					ZipOutputStream zous = new ZipOutputStream(new FileOutputStream(directorioZip + ficheros[i].getName().replace(extension, ".zip")));
					
					//nombre con el que se va guardar el archivo dentro del zip
					ZipEntry entrada = new ZipEntry(ficheros[i].getName());
					zous.putNextEntry(entrada);
					
						//System.out.println("Nombre del Archivo: " + entrada.getName());
						System.out.println("Comprimiendo.....");
						//obtiene el archivo para irlo comprimiendo
						FileInputStream fis = new FileInputStream(directorioZip+entrada.getName());
						int leer;
						byte[] buffer = new byte[1024];
						while (0 < (leer = fis.read(buffer))) {
							zous.write(buffer, 0, leer);
						}
						fis.close();
						zous.closeEntry();
					zous.close();					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			System.out.println("Directorio de salida: " + directorioZip);
		} else {
			System.out.println("No se encontró el directorio..");
		}
				      
		      
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - " + uploadfile.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);

	}

	/**
	 * uploadFileMulti
	 * 
	 * @param extraField
	 * @param uploadfiles
	 * @return
	 */
	@PostMapping("/api/upload/multi")
	public ResponseEntity<?> uploadFileMulti(@RequestParam("extraField") String extraField,
			@RequestParam("files") MultipartFile[] uploadfiles) {

		logger.debug("Multiple file upload!");

		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {

			System.out.println("Emilio name file: - " + uploadedFileName);
			
			saveUploadedFiles(Arrays.asList(uploadfiles));
			
			 //Loading an existing PDF document
		      File file = new File(uploadedFileName);
		      PDDocument document = PDDocument.load(file); 

		      //Instantiating Splitter class
		      Splitter splitter = new Splitter();

		      //splitting the pages of a PDF document
		      List<PDDocument> Pages = splitter.split(document);

		      //Creating an iterator 
		      Iterator<PDDocument> iterator = Pages.listIterator();

		    //Saving each page as an individual document
				 int k = 1;
				 while(iterator.hasNext()) {
				    PDDocument pd = iterator.next();
				    pd.save(UPLOADED_FOLDER + "sample"+ k++ +".pdf");
				 }
				 System.out.println("Multiple PDF’s created");
				 document.close();
				
				 Zipper zip = new  Zipper();
				 zip.zipDir(UPLOADED_FOLDER,"C:/temporal3/test.zip"); 

		} catch (IOException e) {
			System.out.println("Error emilio: - " + HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - " + uploadedFileName, HttpStatus.OK);

	}

	/**
	 * multiUploadFileModel
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/api/upload/multi/model")
	public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {

		logger.debug("Multiple file upload! With UploadModel");

		try {

			saveUploadedFiles(Arrays.asList(model.getFiles()));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

	}

	/**
	 * saveUploadedFiles
	 * 
	 * @param files
	 * @throws IOException
	 */
	private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getName() + ".pdf");
			Files.write(path, bytes);

		}

	}
	
	
	

}
