package com.iu.home.studyCafe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cafeService {
	
	@Autowired
	private cafeDAO cafeDAO;
	
	public List<CafeDTO> getList() throws Exception{
		return cafeDAO.getList();
	}
	
	public CafeDetailDTO getDetail(CafeDTO cafeDTO) throws Exception{
		return cafeDAO.getDetail(cafeDTO);
	}
	
	public List<CafeRoomDTO> getRoomList(CafeDetailDTO cafeDetailDTO) throws Exception{
		return cafeDAO.getRoomList(cafeDetailDTO);
	}
	
	public int addDate(ReservationDTO reservationDTO) throws Exception{
		return cafeDAO.addDate(reservationDTO);
	}
	
	public List<ReListDTO> getReList(Long reserNum) throws Exception{
		return cafeDAO.getReList(reserNum);
	}
	
	public List<ReservationDTO> getSeTimeCheck(String seDate) throws Exception{
		return cafeDAO.getSeTimeCheck(seDate);
	}
	
	public int updateSit(CafeRoomDTO cafeRoomDTO) throws Exception{
		return cafeDAO.updateSit(cafeRoomDTO);
	}
	
}
