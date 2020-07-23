package com.components.xmlservlet.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api")
public class RestServiceController {

	public RestServiceController() {
		super();
	}
	
	@PostMapping("/post/{value}")
	@ResponseBody
	public ResponsePojo post(@PathVariable String value) {
		ResponsePojo resp = new ResponsePojo("SUCCESS","Yipee");
		resp.addParam("from request", value);
		return resp;
	}
	
	@GetMapping("/get")
	@ResponseBody
	public ResponseEntity<String> get() {
		return new ResponseEntity<String>("Yipee", HttpStatus.OK);
	}
	

	private static class ResponsePojo {

		private static final long serialVerisonUID = 1L;
		private String status;
		private String message;

		private Map<String, String> params = new HashMap<>();

		public ResponsePojo(String status, String message) {
			this.status = status;
			this.message = message;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Map<String, String> getParams() {
			return params;
		}

		public void setParams(Map<String, String> params) {
			this.params = params;
		}

		public void addParam(String key, String value) {
			this.params.put(key, value);
		}

		public void removeParam(String key) {
			this.params.remove(key);
		}

		public void clearParams() {
			this.params.clear();
		}

	}

}
