package com.iu.home;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.licenseList.ScheduleDTO;
import com.iu.home.util.Pager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	 @Autowired
	 private HomeService homeService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	 
	@GetMapping("/")
	public ModelAndView home (Locale locale, Pager pager,HomeDTO homeDTO) throws Exception{
		logger.info("Welcome home! The client locale is {}.", locale);
		ModelAndView mv = new ModelAndView();
		pager.setPerPage(10L);
		
		List<HomeDTO> ar = homeService.getList(pager);
		
		List<HomeDTO> ds = new ArrayList<HomeDTO>();
	
		System.out.println(homeDTO.getLicenseNum());
		
		
		homeDTO.setLicenseNum(ar.get(0).getLicenseNum());
		System.out.println(homeDTO.getLicenseNum());
		ds = homeService.getDetailSchedule(homeDTO);

		mv.addObject("detailSchedule",ds);
		
		mv.addObject("list",ar);
		mv.addObject("pager",pager);
		
		mv.setViewName("index");
		
		//homeDTO.DATE  -> DATE
		//Date date = new Date();
		//DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//String formattedDate = dateFormat.format(date);
	
		return mv;
	}
	
//	@GetMapping("detail")
//	public ModelAndView getDetail(HomeDTO homeDTO) throws Exception{
//		ModelAndView mv = new ModelAndView();
//
//		int result = homeService.setHits(homeDTO);
//		HomeDTO name=homeService.getLicenseName(homeDTO);
//		List<HomeDTO> detailBook = homeService.getDetailBook(homeDTO);
//		List<HomeDTO> detailVideo = homeService.getDetailVideo(homeDTO);
//		List<HomeDTO> detailJob = homeService.getDetailJob(homeDTO);
//		List<HomeDTO> detailSchedule = homeService.getDetailSchedule(homeDTO);
//				System.out.println(detailSchedule);
//				
//		mv.addObject("name",name);
//		mv.addObject("detailBook",detailBook);
//		mv.addObject("detailVideo",detailVideo);
//		mv.addObject("detailJob",detailJob);
//		mv.addObject("detailSchedule",detailSchedule);
//		mv.setViewName("/info/detail");
//		return mv;	
//	}
	
}
