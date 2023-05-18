package com.ssafy.enjoytrip;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.Attraction.repository.AttractionRepository;
import com.ssafy.enjoytrip.trail.dto.TrailInputDto;
import com.ssafy.enjoytrip.trail.repository.TrailRepository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TrailInit {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AttractionRepository attractionRepository;
    @Autowired
    private TrailRepository trailRepository;


    static final Map<String, String> sidoConvertMap = new HashMap<>();

    @Test
    void initData() throws IOException {
        initSidoCode();

        File file = new File("/Users/angyubo/Documents/GitHub/enjoy-trip/src/main/resources/trailData.json");
        Map<String, List<Map<String, String>>> map = objectMapper.readValue(file, Map.class);
        List<Map<String, String>> records = map.get("records");

        List<TrailInputDto> trailInputDtos = new ArrayList<>();
        for (Map<String, String> record : records) {
            TrailInputDto trailInputDto = new TrailInputDto();
            for (String key : record.keySet()) {
                if (key.equals("길명")) {
                    trailInputDto.setTitle(record.get(key));
                } else if (key.equals("길소개")) {
                    trailInputDto.setDescription(record.get(key));
                } else if (key.equals("총길이")) {
                    trailInputDto.setTotal_length(record.get(key));
                } else if (key.equals("총소요시간")) {
                    trailInputDto.setEstimated_time(record.get(key));
                } else if (key.equals("시작지점명")) {
                    trailInputDto.setStart_name(record.get(key));
                } else if (key.equals("시작지점도로명주소")) {
                    // 없을때
                    if (record.get(key).length() == 0 || record.get(key).startsWith("없음") || record.get(key)
                            .startsWith("도로명")) {
                        // 지번주소로 실행
                        String[] addr = record.get("시작지점소재지지번주소").split(" ");
                        startAddrInit(trailInputDto, addr);
                    } else {
                        String[] addr = record.get(key).split(" ");
                        startAddrInit(trailInputDto, addr);
                    }
                } else if (key.equals("종료지점명")) {
                    trailInputDto.setEnd_name(record.get(key));
                } else if (key.equals("종료지점소재지도로명주소")) {
                    // 없을때
                    if (record.get(key).length() == 0 || record.get(key).startsWith("없음") || record.get(key)
                            .startsWith("도로명")) {
                        // 지번주소로 실행
                        String[] addr = record.get("종료지점소재지지번주소").split(" ");
                        endAddrInit(trailInputDto, addr);
                    } else {
                        String[] addr = record.get(key).split(" ");
                        endAddrInit(trailInputDto, addr);
                    }
                } else if (key.equals("경로정보")) {
                    trailInputDto.setRoute(record.get(key));
                } else if (key.equals("관리기관전화번호")) {
                    trailInputDto.setTel(record.get(key));
                } else if (key.equals("관리기관명")) {
                    trailInputDto.setManage_name(record.get(key));
                } else if (key.equals("데이터기준일자")) {
                    trailInputDto.setAdd_date(record.get(key));
                } else if (key.equals("제공기관코드")) {
                    trailInputDto.setProvide_code(record.get(key));
                } else if (key.equals("제공기관명")) {
                    trailInputDto.setProvide_name(record.get(key));
                }
            }
            trailInputDtos.add(trailInputDto);
        }
        trailRepository.insertData(trailInputDtos);
    }

    private void startAddrInit(TrailInputDto trailInputDto, String[] addr) {
        int sidoCode = calculateSido(addr[0]);
        trailInputDto.setStart_sido_code(sidoCode);
        if (sidoCode == 8) {
            trailInputDto.setStart_gugun_code(1);
            String[] detail = Arrays.copyOfRange(addr, 1, addr.length);
            trailInputDto.setStart_detail_addr(String.join(" ", detail));
            return;
        }
        trailInputDto.setStart_gugun_code(calculateGugun(addr[1], sidoCode));
        String[] detail = Arrays.copyOfRange(addr, 2, addr.length);
        trailInputDto.setStart_detail_addr(String.join(" ", detail));
    }

    private void endAddrInit(TrailInputDto trailInputDto, String[] addr) {
        int sidoCode = calculateSido(addr[0]);
        trailInputDto.setEnd_sido_code(sidoCode);
        if (sidoCode == 8) {
            trailInputDto.setEnd_gugun_code(1);
            String[] detail = Arrays.copyOfRange(addr, 1, addr.length);
            trailInputDto.setEnd_detail_addr(String.join(" ", detail));
            return;
        }
        trailInputDto.setEnd_gugun_code(calculateGugun(addr[1], sidoCode));
        String[] detail = Arrays.copyOfRange(addr, 2, addr.length);
        trailInputDto.setEnd_detail_addr(String.join(" ", detail));
    }

    private static void initSidoCode() {
        sidoConvertMap.put("서울특별시", "서울");
        sidoConvertMap.put("인천광역시", "인천");
        sidoConvertMap.put("대전광역시", "대전");
        sidoConvertMap.put("대구광역시", "대구");
        sidoConvertMap.put("광주광역시", "광주");
        sidoConvertMap.put("부산광역시", "부산");
        sidoConvertMap.put("울산광역시", "울산");
        sidoConvertMap.put("제주특별자치도", "제주도");
        sidoConvertMap.put("경북", "경상북도");
        sidoConvertMap.put("경남", "경상남도");
        sidoConvertMap.put("전북", "전라북도");
        sidoConvertMap.put("전남", "전라남도");
        sidoConvertMap.put("충북", "충청북도");
        sidoConvertMap.put("충남", "충청남도");
    }


    private int calculateSido(String sido) {
        if (sidoConvertMap.containsKey(sido)) {
            sido = sidoConvertMap.get(sido);
        }

        return attractionRepository.getSidoCodeBySidoName(sido);
    }

    private int calculateGugun(String gugun, int sidoCode) {
        return attractionRepository.getGugunCodeBySidoCodeAndGugunName(gugun, sidoCode);
    }

}
