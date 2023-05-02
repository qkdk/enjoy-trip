package com.ssafy.enjoytrip.place.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.place.dto.PlaceDto;
import com.ssafy.enjoytrip.place.service.PlaceService;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.util.PageNavigation;

@Controller
@RequestMapping("/place")
public class PlaceController {

	private PlaceService placeService;

	@Autowired
	public PlaceController(PlaceService placeService) {
		super();
		this.placeService = placeService;
	}

	@Value("${file.path}")
	private String uploadPath;

	@GetMapping("")
	public String listHotplace(String pgno, String key, String word, Model model) throws Exception {
		List<PlaceDto> placeList = placeService.list(pgno, key, word);
		PageNavigation navigation = placeService.makePageNavigation(pgno, key, word);
		model.addAttribute("placeList", placeList);
		model.addAttribute("navigation", navigation);
		return "/place/list";
	}

	@GetMapping("/{placeNo}")
	public String viewNotice(@PathVariable int placeNo, Model model) throws Exception {
//        notice.setNoticeHit(notice.getNoticeHit()+1);
		System.out.println(placeNo);
		PlaceDto placeDto = placeService.getPlaceByPlaceNo(placeNo);
		if(placeDto == null) {
			placeDto = placeService.view(placeNo);
			model.addAttribute("place", placeDto);
			return "/place/view";
		}
		else {
			System.out.println(placeDto);
			model.addAttribute("place", placeDto);
			return "/place/view";
		}
	}

	@GetMapping("/write")
	public String write(HttpSession session) {
		if (session.getAttribute("userDto") == null)
			return "redirect:/place?pgno=1&key&word=";
		else
			return "place/write";
	}

	@PostMapping("/write")
	public String write(String placeImgSrc, String placeTitle, String placeContent, HttpSession session,
			@RequestParam("upfile") MultipartFile[] files) throws Exception, IOException {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		PlaceDto placeDto = new PlaceDto();
		placeDto.setPlaceTitle(placeTitle);
		placeDto.setPlaceContent(placeContent);
		placeDto.setPlaceImgSrc(placeImgSrc);
		placeDto.setUserId(userDto.getUserId());
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
		return "redirect:/place?pgno=1&key&word=";
	}
}
