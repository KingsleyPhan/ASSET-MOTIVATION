package com.pst.motivation.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UploadAttachmentCouponApi {
	
	@RequestMapping(value="UploadAttachmentFile", method = RequestMethod.GET)
	@ResponseBody
	public String UploadAttachmentFileCoupon() {
		return "Hello world";
	}
}
