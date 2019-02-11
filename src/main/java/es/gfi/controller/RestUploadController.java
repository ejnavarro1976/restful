package es.gfi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.gfi.model.UploadModel;

@RestController
public class RestUploadController<T> {

	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "C:\\Users\\ejnavarro\\Documents\\temporal\\";

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

			saveUploadedFiles(Arrays.asList(uploadfiles));

		} catch (IOException e) {
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
			Path path = Paths.get(UPLOADED_FOLDER + file.getName() + ".otr");
			Files.write(path, bytes);

		}

	}

}
