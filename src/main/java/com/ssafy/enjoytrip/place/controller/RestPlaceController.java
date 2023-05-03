package com.ssafy.enjoytrip.place.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.place.dto.PlaceDto;
import com.ssafy.enjoytrip.place.service.PlaceService;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.util.PageNavigation;

@RestController
@RequestMapping("/place/api")
public class RestPlaceController {
	
	private PlaceService placeService;

	@Autowired
	public RestPlaceController(PlaceService placeService) {
		super();
		this.placeService = placeService;
	}
	
	@Value("${file.path}")
	private String uploadPath;
	
	@GetMapping("")
	public ResponseEntity<Map<String, Object>> list(String pgno, String key, String word){
		Map<String, Object> map = new HashMap<String, Object>();
		List<PlaceDto> placeList = null;
		try {
			placeList = placeService.list(pgno, key, word);
			PageNavigation navigation = placeService.makePageNavigation(pgno, key, word);
			map.put("data", placeList);
			map.put("page", navigation);
			map.put("msg", "조회성공");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			return null;
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(int placeNo){
		try {
			placeService.deletePlaceImg(placeNo);
			placeService.deletePlace(placeNo);
			return ResponseEntity.ok("삭제완료");
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/{placeNo}")
	public ResponseEntity<PlaceDto> view(int placeNo){
		try {
			PlaceDto placeDto = placeService.getPlaceByPlaceNo(placeNo);
			if(placeDto == null) {
				placeDto = placeService.view(placeNo);
				placeDto.setPlaceImgSrc(null);
			}
			return ResponseEntity.ok(placeDto);
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/write")
	public ResponseEntity<String> write(String placeTitle, String placeContent
		,@RequestParam("upfile") MultipartFile[] files, HttpSession session){
		try {
			UserDto userDto = (UserDto) session.getAttribute("userDto");
			PlaceDto placeDto = new PlaceDto();
			placeDto.setPlaceTitle(placeTitle);
			placeDto.setPlaceContent(placeContent);
			placeDto.setUserId(userDto.getUserId());
			placeDto.setUserId("qqqqqq");
			placeService.writePlace(placeDto);
			int placeNo = placeService.lastIndex();
			if (!files[0].isEmpty()) {
				String saveFolder = uploadPath + File.separator;
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				List<PlaceDto> fileInfos = new ArrayList<PlaceDto>();
				for (MultipartFile mfile : files) {
					PlaceDto fileInfoDto = new PlaceDto();
					String originalFileName = mfile.getOriginalFilename();
					if (!originalFileName.isEmpty()) {
						String saveFileName = UUID.randomUUID().toString()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setPlaceImgSrc(saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					fileInfos.add(fileInfoDto);
				}
				placeService.writePlaceImg(placeNo, fileInfos.get(0).getPlaceImgSrc());
			}
			return ResponseEntity.ok("글쓰기 완료");
		} catch (Exception e) {
			return null;
		}
	}
	
	@PatchMapping("/modify")
	public ResponseEntity<String> modify(int placeNo, String placeTitle, String placeContent,
			@RequestParam("upfile") MultipartFile[] files){
		try {
			placeService.modifyPlace(placeNo, placeTitle, placeContent);
			if(!files[0].isEmpty()) {
				int cnt = placeService.checkImg(placeNo);
				if(cnt>0) {
					placeService.deletePlaceImg(placeNo);
				}
				String saveFolder = uploadPath + File.separator;
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				List<PlaceDto> fileInfos = new ArrayList<PlaceDto>();
				for (MultipartFile mfile : files) {
					PlaceDto fileInfoDto = new PlaceDto();
					String originalFileName = mfile.getOriginalFilename();
					if (!originalFileName.isEmpty()) {
						String saveFileName = UUID.randomUUID().toString()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setPlaceImgSrc(saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					fileInfos.add(fileInfoDto);
				}
				placeService.writePlaceImg(placeNo, fileInfos.get(0).getPlaceImgSrc());
			}
			return ResponseEntity.ok("수정완료");
		} catch (Exception e) {
			return null;
		}
	}

}