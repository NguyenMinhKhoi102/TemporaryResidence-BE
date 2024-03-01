package com.bezkoder.spring.security.jwt.FileHandle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JRParameter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CT01 {
    private String sendTo;
    private String name;
    private String birthday;
    private String identify;
    private String gender;
    private String phone;
    private String email;
    private String permanentAddress;
    private String temporaryAddress;
    private String currentAddress;
    private String jobAndWorkSpace;
    private String hostName;
    private String identifyHost;
    private String relationshipHost;
    private String caseProfile;

    public Map<String, Object> initParameters(){
        Map<String, Object> parameters = new HashMap<>();
        Locale locale = new Locale("vi", "VN");
        parameters.put(JRParameter.REPORT_LOCALE, locale);
        parameters.put("sendTo", this.sendTo);
        parameters.put("name", this.name);
        parameters.put("birthday", this.birthday);
        parameters.put("identify", this.identify);
        parameters.put("gender", this.gender);
        parameters.put("phone", this.phone);
        parameters.put("email", this.email);
        parameters.put("pernamentAddress", this.permanentAddress);
        parameters.put("temporaryAddress", this.temporaryAddress);
        parameters.put("currentAddress", this.currentAddress);
        parameters.put("job", this.jobAndWorkSpace);
        parameters.put("hostName", this.hostName);
        parameters.put("identifyHost", this.identifyHost);
        parameters.put("relationshipHost", this.relationshipHost);
        parameters.put("case", this.caseProfile);
        return parameters;
    }
}
