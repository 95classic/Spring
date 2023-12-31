package edu.spring.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.spring.ex01.domain.ProductVO;

@Controller
public class SampleJSONController {
	private static final Logger logger =
				LoggerFactory.getLogger(SampleJSONController.class);
	
	@GetMapping("/json1")
	public String json1() {
		logger.info("json() 호출");
		return "sample1";
	} // end json1()
	
	@GetMapping("/json2")
	@ResponseBody
	public String json2() {
		logger.info("json2() 호출");
		return "Hello, Spring";
	} // end json2()
	
	@GetMapping("/json3")
	@ResponseBody
	public ProductVO json3() {
		logger.info("json3() 호출");
		return new ProductVO("json3",10000);
	}
	
} // end SampleJSONController
