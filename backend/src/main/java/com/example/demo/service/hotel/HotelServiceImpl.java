package com.example.demo.service.hotel;


import com.example.demo.entity.hotel.Hotel;
import com.example.demo.repository.hotel.HotelRepository;
import com.example.demo.utility.fileUpload.FileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class HotelServiceImpl extends FileUpload implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    // 사업자 매뉴얼 호텔 등록
    @Override
    public void register(Hotel hotel, List<MultipartFile> files) throws Exception {

        String path = "hotelImg";
        List<String> filePathList = new ArrayList<>();

        fileUpload(files,path,filePathList);

        for(int i = 0; i < filePathList.size(); i++) {
            switch (i){
                case 0:
                     hotel.setHotelImgPath1(filePathList.get(i));
                break;
                case 1:
                    hotel.setHotelImgPath2(filePathList.get(i));
                    break;
                case 2:
                    hotel.setHotelImgPath3(filePathList.get(i));
                    break;
                case 3:
                    hotel.setHotelImgPath4(filePathList.get(i));
                    break;
                case 4:
                    hotel.setHotelImgPath5(filePathList.get(i));
                    break;
                case 5:
                    hotel.setHotelImgPath6(filePathList.get(i));
                    break;
                case 6:
                    hotel.setHotelImgPath7(filePathList.get(i));
                    break;
                case 7:
                    hotel.setHotelImgPath8(filePathList.get(i));
                    break;
                case 8:
                    hotel.setHotelImgPath9(filePathList.get(i));
                    break;
            }

        }


        hotelRepository.save(hotel);
    }

    // 사업자 매뉴얼 호텔 리스트
    @Override
    public List<Hotel> bmHotelList() {
        return hotelRepository.findAll(Sort.by(Sort.Direction.DESC, "bmHotelNo"));
    public List<Hotel> list() {
        log.info("HotelServiceIMPL list");
        return hotelRepository.findAll(Sort.by(Sort.Direction.DESC, "hotelNo"));
    }*/


    public List<Hotel> random () {
        log.info("HotelServiceIMPL random");
        List<Hotel> randomResults = hotelRepository.randomPick(6);
        log.info("radomResults" + randomResults);
        return randomResults;
    }

    // 사업자 매뉴얼 호텔 읽기
    @Override
    public Hotel bmHotelRead(Integer hotelNo) {
        log.info("hotelNo" + hotelNo);
        Optional<Hotel> maybeReadBoard = hotelRepository.findById(Long.valueOf(hotelNo));

        if (maybeReadBoard.equals(Optional.empty())) {
            log.info("Can't read board!");
            return null;
        }

        return maybeReadBoard.get();
    }

    // 사업자 매뉴얼 호텔 수정
    @Override
    public void bmhotelModify(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    // 사업자 매뉴얼 호텔 삭제
    @Override
    public void bmhotelRemove(Integer hotelNo) {
        hotelRepository.deleteById(Long.valueOf(hotelNo));
    }


    // ---------------------------------------------------------------------------------------------------------------

      
    public List<Hotel> random () {
        log.info("HotelServiceIMPL random");
        List<Hotel> randomResults = hotelRepository.randomPick(6);

        return randomResults;
    }

    @Override
    public List<Hotel> searchList(String keyWord) {
        List<Hotel> findSearchList = hotelRepository.findByHotelInfoContaining(keyWord);

        log.info("findSearchList : " + findSearchList);

        return findSearchList;
    }
}