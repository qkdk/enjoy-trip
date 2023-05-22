package com.ssafy.enjoytrip.Attraction.Controller;

import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import com.ssafy.enjoytrip.Attraction.service.AttractionService;
import com.ssafy.enjoytrip.util.ResponseTemplate;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/attraction")
public class AttractionController {

    AttractionService attractionService;

    @Autowired
    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping("/{sidoCode}/{gugunCode}/{contentTypeId}")
    public ResponseEntity<ResponseTemplate<List<AttractionDto>>> getAttraction(@PathVariable int sidoCode,
            @PathVariable int gugunCode,
            @PathVariable int contentTypeId, @RequestParam(defaultValue = "") String word) {
        return new ResponseEntity<>(
                ResponseTemplate.<List<AttractionDto>>builder()
                        .data(attractionService.getAttraction(sidoCode, gugunCode, contentTypeId, word))
                        .result(true)
                        .msg("불러오기 성공")
                        .build()
                , HttpStatus.OK
        );
    }

    @GetMapping("/getcode")
    public ResponseEntity<List<SidoCodeDto>> getSidoCodeAndName() {
        return ResponseEntity.ok(attractionService.getSidoCodeAndName());
    }

    @GetMapping("/getcode/{sidoCode}")
    public ResponseEntity<List<GugunCodeDto>> getGugunCodeAndName(@PathVariable int sidoCode) throws SQLException {
        return ResponseEntity.ok(attractionService.getGugunCodeAndName(sidoCode));
    }

    @GetMapping("{contentId}")
    public ResponseEntity<ResponseTemplate<String>> getAttractionDescription(@PathVariable String contentId) {
        return new ResponseEntity<>(
                ResponseTemplate.<String>builder()
                        .msg("불러오기 성공")
                        .result(true)
                        .data(attractionService.getAttractionDescription(contentId))
                        .build()
                , HttpStatus.OK
        );
    }

}
