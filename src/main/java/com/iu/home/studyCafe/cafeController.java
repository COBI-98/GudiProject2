package com.iu.home.studyCafe;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/studyCafe/*")
public class cafeController {
	
	@Autowired
	private cafeService cafeService;
	
	@RequestMapping(value = "cafeList", method = RequestMethod.GET)
	public String list(Model model) throws Exception{
		System.out.println("cafeList");
		List<CafeDTO> ar = cafeService.getList();
		model.addAttribute("list", ar);
		
		return "studyCafe/cafeList";
	}
	
	@RequestMapping(value = "cafeDetail", method = RequestMethod.GET)
	public ModelAndView cafeDetail(CafeDTO cafeDTO) throws Exception{
		System.out.println("cafeDetail");
		ModelAndView mv = new ModelAndView();
		CafeDetailDTO cafeDetailDTO = new CafeDetailDTO();
		
		cafeDetailDTO = cafeService.getDetail(cafeDTO);
		
		mv.setViewName("studyCafe/cafeDetail");
		mv.addObject("dto", cafeDetailDTO);
		System.out.println(cafeDetailDTO.getLatitude());
		System.out.println(cafeDetailDTO.getLongitude());
		return mv;
	}
	
	@RequestMapping(value = "reservation", method = RequestMethod.GET)
	public String cafeRoomList(CafeDetailDTO cafeDetailDTO, Model model, CafeDTO cafeDTO) throws Exception{
		
		cafeDetailDTO.getDetailNum();
		System.out.println(cafeDetailDTO.getDetailNum());
		List<CafeRoomDTO> ar = cafeService.getRoomList(cafeDetailDTO);
		
		model.addAttribute("roomList", ar);
		System.out.println(ar);
		return "studyCafe/reservation";
	}
	
	@RequestMapping(value = "map")
	public String map() throws Exception{
		
		return "studyCafe/map";
	}
	

	@RequestMapping(value="cafeList", method=RequestMethod.POST)
	public ModelAndView addDate(ReservationDTO reservationDTO,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println("cafeList post 실행");
		System.out.println(reservationDTO.getSeTime());
		System.out.println(reservationDTO.getSeDate());
		int result = cafeService.addDate(reservationDTO);
		HttpSession session = request.getSession();
		session.setAttribute("reserNum", reservationDTO.getReserNum());
//		int result = cafeService.update(cafeRoomDTO);
		
		mv.setViewName("redirect:./reList");
		return mv;
	}
	
	@RequestMapping(value = "calender")
	public String calender() throws Exception{
		
		return "studyCafe/calender";
	}
	
	@RequestMapping(value = "reList")
	public String reList(Model model,HttpSession session) throws Exception{
		System.out.println("cafeReList");
		Long reserNum = (Long)session.getAttribute("reserNum");
		List<ReListDTO> ar = cafeService.getReList(reserNum);
		System.out.println(ar.get(0).getCdPay());
		model.addAttribute("list", ar);
		
		return "studyCafe/reList";
	}
	
//	@PostMapping("seTimeCheck")
//	@ResponseBody
//	public List<ReservationDTO> getSeTimeCheck(String value) throws Exception{
//		System.out.println("seTimeCheck : " + value);
////		ReservationDTO reservationDTO = new ReservationDTO();
////		reservationDTO.setSeTime(value);
////		System.out.println(reservationDTO.getSeTime());
//		String seDate = value;
////		List<ReservationDTO> ar = cafeService.getSeTimeCheck(seDate);
//		ModelAndView mv = new ModelAndView();
//		System.out.println(ar);
//		
//		
//		return ar;
//	}
	@RequestMapping(value = "updateDetail", method=RequestMethod.GET)
	public ModelAndView updateDetail(CafeDTO cafeDTO) throws Exception{
		System.out.println("updateDetail");
		ModelAndView mv = new ModelAndView();
		CafeDetailDTO cafeDetailDTO = new CafeDetailDTO();
		cafeDetailDTO = cafeService.getDetail(cafeDTO);
		
		mv.setViewName("studyCafe/updateDetail");
		mv.addObject("dto", cafeDetailDTO);

		return mv;
	}
	
	@RequestMapping(value = "updateDetail", method=RequestMethod.POST)
	public ModelAndView updateDetail(CafeDetailDTO cafeDetailDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = cafeService.updateDetail(cafeDetailDTO);
		System.out.println(cafeDetailDTO.getScName());
		mv.setViewName("studyCafe/cafeList");
		mv.addObject("dto", cafeDetailDTO);
		return mv;
	}
	
	@RequestMapping(value="addList")
	public String addList() throws Exception{
		return "studyCafe/addList";
	}
	@RequestMapping(value="updateList")
	public String updateList() throws Exception{
		return "studyCafe/updateList";
	}
	@RequestMapping(value="deleteList")
	public String deleteList() throws Exception{
		return "studyCafe/deleteList";
	}
	
}

