package com.ssafy.enjoytrip.Attraction.Controller;

import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.Attraction.service.AttractionService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/attraction")
public class AttractionController {

    AttractionService attractionService;

    @Autowired
    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping("/")
    public String showMap() {
        return "map";
    }

    @GetMapping("/{sidoCode}/{gugunCode}/{contentTypeId}")
    @ResponseBody
    public ResponseEntity<List<AttractionDto>> getAttraction(@PathVariable int sidoCode, @PathVariable int gugunCode,
            @PathVariable int contentTypeId) throws SQLException {
        return ResponseEntity.ok(attractionService.getAttraction(sidoCode, gugunCode, contentTypeId));
    }

}
