package com.ssafy.enjoytrip.place.controller;

import com.ssafy.enjoytrip.place.dto.PlaceDto;
import com.ssafy.enjoytrip.place.dto.RecommendDto;
import com.ssafy.enjoytrip.place.dto.ReplyDto;
import com.ssafy.enjoytrip.place.service.PlaceService;
import com.ssafy.enjoytrip.util.PageNavigation;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


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


    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(int placeNo) {
        try {
            placeService.deletePlaceImg(placeNo);
            placeService.deletePlace(placeNo);
            return ResponseEntity.ok("삭제완료");
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> list(String pgno, String key, String word) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PlaceDto> placeList = null;
        try {
            placeList = placeService.list(pgno, key, word);
            PageNavigation navigation = placeService.makePageNavigation(pgno, key, word);
            for (int i = 0; i < placeList.size(); i++) {
                int placeNo = placeList.get(i).getPlaceNo();
                if (placeService.getPlaceByPlaceNo(placeNo) != null) {
                    placeList.get(i).setPlaceImgSrc(
                            "/upload/" + placeService.getPlaceByPlaceNo(placeNo)
                                    .getPlaceImgSrc());
                }
            }
            map.put("data", placeList);
            map.put("page", navigation);
            map.put("msg", "조회성공");
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/sort")
    public ResponseEntity<Map<String, Object>> listSort(String pgno, String key, String word) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PlaceDto> placeList = null;
        try {
            placeList = placeService.listSort(pgno, key, word);
            PageNavigation navigation = placeService.makePageNavigation(pgno, key, word);
            for (int i = 0; i < placeList.size(); i++) {
                int placeNo = placeList.get(i).getPlaceNo();
                if (placeService.getPlaceByPlaceNo(placeNo) != null) {
                    placeList.get(i).setPlaceImgSrc(
                            "/upload/" + placeService.getPlaceByPlaceNo(placeNo)
                                    .getPlaceImgSrc());
                }
            }
            map.put("data", placeList);
            map.put("page", navigation);
            map.put("msg", "조회성공");
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/recommend/{count}/{placeNo}")
    public ResponseEntity<String> modifyRecommend(@PathVariable int count, @PathVariable int placeNo) throws Exception {
        placeService.modifyRecommend(count, placeNo);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/{placeNo}")
    public ResponseEntity<Map<String, Object>> view(@PathVariable int placeNo) {
        System.out.println("asdasdasdasd");
        Map<String, Object> map = new HashMap<String, Object>();
        List<ReplyDto> replyList = null;
        try {
            System.out.println(replyList);
            System.out.println(placeNo);
            replyList = placeService.replyList(placeNo);
//			System.out.println(replyList.get(0).getUserId());
            System.out.println("종료");
            System.out.println("ASDASDA");
            map.put("reply", replyList);
            map.put("msg", "조회성공");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("msg", "조회실패");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/write")
    public ResponseEntity<String> write(String placeTitle, String placeContent, String userId
            , @RequestParam("upfile") MultipartFile[] files) {
        try {
            System.out.println("asdasd");
            System.out.println(files[0] + "asdasdsa");
            PlaceDto placeDto = new PlaceDto();
            placeDto.setPlaceTitle(placeTitle);
            placeDto.setPlaceContent(placeContent);
            placeDto.setUserId(userId);
            System.out.println(placeDto);
            placeService.writePlace(placeDto);
            System.out.println(placeDto);
            int placeNo = placeService.lastIndex();
            System.out.println(placeNo);
            if (!files[0].isEmpty()) {
                String saveFolder = uploadPath + File.separator;
                System.out.println("폴더에 넣어보자" + files[0]);
                File folder = new File(saveFolder);
				if (!folder.exists()) {
					folder.mkdirs();
				}
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
                System.out.println("폴더에 넣었다");
                placeService.writePlaceImg(placeNo, fileInfos.get(0).getPlaceImgSrc());
            }
            return ResponseEntity.ok("글쓰기 완료");
        } catch (Exception e) {
            System.out.println("왜 안되는거임");
            return null;
        }
    }


    @PatchMapping("/modify")
    public ResponseEntity<String> modify(int placeNo, String placeTitle, String placeContent,
            @RequestParam("upfile") MultipartFile[] files) {
        try {
            placeService.modifyPlace(placeNo, placeTitle, placeContent);
            if (!files[0].isEmpty()) {
                int cnt = placeService.checkImg(placeNo);
                if (cnt > 0) {
                    placeService.deletePlaceImg(placeNo);
                }
                String saveFolder = uploadPath + File.separator;
                File folder = new File(saveFolder);
				if (!folder.exists()) {
					folder.mkdirs();
				}
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

    @PostMapping("/reply")
    public ResponseEntity<String> reply(@RequestBody ReplyDto replyDto) {
        try {
            placeService.writeReply(replyDto);
            return new ResponseEntity<String>("작성성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("작성실패", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recommend/{no}")
    public ResponseEntity<List<RecommendDto>> recommend(@PathVariable int no) throws Exception {
        return new ResponseEntity<List<RecommendDto>>(placeService.recommend(no), HttpStatus.OK);
    }

    @PostMapping("/recommend/add")
    public ResponseEntity<String> recommendAdd(@RequestBody RecommendDto recommendDto) throws Exception {
        placeService.addRecommend(recommendDto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping("/recommend/del")
    public ResponseEntity<String> recommendDel(String user_id, int place_no) throws Exception {
        System.out.println("삭제합니다");
        RecommendDto recommendDto = new RecommendDto();
        recommendDto.setUser_id(user_id);
        recommendDto.setPlace_no(place_no);
        placeService.delRecommend(recommendDto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
