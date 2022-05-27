package works.yermi.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import works.yermi.domain.AttachFileDTO;

@Controller
@Log4j
public class UploadController {
	private static final String UPLOAD_FOLDER = "c:/pension";
	
	@PostMapping("upload") @ResponseBody
    public List<AttachFileDTO> upload(MultipartFile[] files, String type) throws IllegalStateException, IOException {
		  log.info(type);
//        log.info(getFolder(type));
        List<AttachFileDTO> list = new ArrayList<>();
        File uploadPath = new File(UPLOAD_FOLDER, getFolder(type));
        if(!uploadPath.exists()) {
        	uploadPath.mkdirs();
        }
        int i = 0;
        for(MultipartFile multipartFile : files) {
            String origin = multipartFile.getOriginalFilename();
            String ext = "";
            if(origin.lastIndexOf(".") != -1) {
            	ext = origin.substring(origin.lastIndexOf("."));
            }
            
            String uuid = UUID.randomUUID().toString() + ext;
            
            File file = new File(uploadPath, uuid);
            multipartFile.transferTo(file);
            
            AttachFileDTO dto = new AttachFileDTO(uuid, origin, getFolder(type), i, isImage(file));
            
            if(isImage(file)) {
            	Thumbnails
				.of(file)
				.sourceRegion(Positions.CENTER, 200, 200)
				.size(200, 200)
				.toFile(new File(uploadPath, "s_" + uuid));
            }
            
            list.add(dto);
            i++;
        }
        return list;
    }
	
	
	@GetMapping("display") @ResponseBody
	public ResponseEntity<byte[]> getFile(AttachFileDTO dto) {
		log.info(dto);
		File file = new File(UPLOAD_FOLDER, dto.getPath() + "/" + dto.getUuid());
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (Exception e) {
//			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@PostMapping("deleteFile") @ResponseBody
	public String deleteFile(AttachFileDTO dto) {
		log.info(dto);
		
		File file = new File(UPLOAD_FOLDER, dto.getPath() + "/" + dto.getUuid());
		file.delete();
		
		if(dto.isImage()) {
			 file = new File(UPLOAD_FOLDER, dto.getPath() + "/s_" + dto.getUuid());
			 file.delete();
		}
		
		return "success";
	}
	
	private boolean isImage(File file) throws IOException {
		String mime = Files.probeContentType(file.toPath());
		if(mime == null || mime.equals("image/x-icon")) return false;
		
		return Files.probeContentType(file.toPath()).startsWith("image");
	}
	
	private String getFolder(String type) {
		return type.equals("pension") ? "main/" : type.equals("room") ? "room/" : "reply/";
	}
}
