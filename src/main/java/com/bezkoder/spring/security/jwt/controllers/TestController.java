package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.jasper.ReportUtils;
import com.bezkoder.spring.security.jwt.jasper.ReportType;
import net.sf.jasperreports.engine.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping("/all")
	public ResponseEntity JasperReportToPdf() {
		try{
			String path = "/templates/CT05_template.jasper";
			Map<String, Object> parameters = new HashMap<>();
			Locale locale = new Locale("vi", "VN");
			parameters.put(JRParameter.REPORT_LOCALE, locale);
			parameters.put("organ2", "Phường An Thới");
			List<Map<String, Object>> dataSource = new ArrayList<>();
			Map<String, Object> data = new HashMap<>();
			data.put("name", "Nguyễn Minh Khôi");
			dataSource.add(data);
			return ReportUtils.downloadReport(path, parameters, dataSource, ReportType.DOCX);
		}catch (Exception e){
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}

	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('PROVINCE') or hasRole('DISTRICT') or hasRole('WARD') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('DISTRICT')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
