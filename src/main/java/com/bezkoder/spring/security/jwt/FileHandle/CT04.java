package com.bezkoder.spring.security.jwt.FileHandle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JRParameter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CT04 {
    private String name;
    private String identify;
    private String phone;
    private String email;
    private String permanentAddress;
    private String temporaryAddress;
    private String currentAddress;
    private String caseProfile;
    private String organ1;
    private String organ2;
    private String number;
    private String code;
    private String sendHour;
    private String sendMinute;
    private String sendDate;
    private String receiveHour;
    private String receiveMinute;
    private String receiveDate;
    private String receiveResult;
    private String day;
    private String month;
    private String year;
    public Map<String, Object> initParameters(){
        Map<String, Object> parameters = new HashMap<>();
        Locale locale = new Locale("vi", "VN");
        parameters.put(JRParameter.REPORT_LOCALE, locale);
        parameters.put("name", this.name);
        parameters.put("identify", this.identify);
        parameters.put("phone", this.phone);
        parameters.put("email", this.email);
        parameters.put("pernamentAddress", this.permanentAddress);
        parameters.put("temporaryAddress", this.temporaryAddress);
        parameters.put("currentAddress", this.currentAddress);
        parameters.put("case", this.caseProfile);
        parameters.put("organ1", this.organ1);
        parameters.put("organ2", this.organ2);
        parameters.put("number", this.number);
        parameters.put("code", this.code);
        parameters.put("sendHour", this.sendHour);
        parameters.put("sendMinute", this.sendMinute);
        parameters.put("sendDate", this.sendDate);
        parameters.put("receiveHour", this.receiveHour);
        parameters.put("receiveMinute", this.receiveMinute);
        parameters.put("receiveDate", this.receiveDate);
        parameters.put("receiveResult", this.receiveResult);
        LocalDate date = LocalDate.now();
        parameters.put("day", String.valueOf(date.getDayOfMonth()));
        parameters.put("month", String.valueOf(date.getMonthValue()));
        parameters.put("year", String.valueOf(date.getYear()));
        return parameters;
    }
}
