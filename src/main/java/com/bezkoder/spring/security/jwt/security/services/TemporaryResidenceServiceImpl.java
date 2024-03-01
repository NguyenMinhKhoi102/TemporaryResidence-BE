package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.FileHandle.*;
import com.bezkoder.spring.security.jwt.jasper.ReportType;
import com.bezkoder.spring.security.jwt.jasper.ReportUtils;
import com.bezkoder.spring.security.jwt.mail.Mail;
import com.bezkoder.spring.security.jwt.mail.MailService;
import com.bezkoder.spring.security.jwt.models.AttachedProfile;
import com.bezkoder.spring.security.jwt.models.GeneralProfile;
import com.bezkoder.spring.security.jwt.models.MemberChangeTogether;
import com.bezkoder.spring.security.jwt.models.TemporaryResidenceProfile;
import com.bezkoder.spring.security.jwt.payload.request.*;
import com.bezkoder.spring.security.jwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TemporaryResidenceServiceImpl implements TemporaryResidenceService {

    @Autowired
    GeneralProfileRepository generalProfileRepository;

    @Autowired
    TemporaryResidenceProfileRepository temporaryResidenceProfileRepository;

    @Autowired
    MemberChangeTogetherRepository memberChangeTogetherRepository;

    @Autowired
    AttachedProfileRepository attachedProfileRepository;

    @Autowired
    MailService mailService;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TypeProfileRepository typeProfileRepository;

    @Autowired
    TypeNotificationRepository typeNotificationRepository;

    @Autowired
    ReceiveResultsRepository receiveResultsRepository;

    @Autowired
    FileService fileService;

    @Override
    public Boolean acceptedTemporaryResidence(Long id, AcceptedTemporaryResidenceRequest request) {
        try {
            GeneralProfile generalProfile = generalProfileRepository
                    .findByIdAndIsDeleteAndStatus(id, 0, 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            generalProfile.setStatus(1);
            generalProfile.setIsHistory(1);
            TemporaryResidenceProfile temp = temporaryResidenceProfileRepository
                    .findByCmndCccdAndIsDelete(generalProfile.getCmndCccd(), 0)
                    .orElse(new TemporaryResidenceProfile());
            temp.setWard(generalProfile.getWard());
            temp.setIsExpired(0);
            temp.setIsDelete(0);
            temp.setName(generalProfile.getName());
            temp.setBirthday(generalProfile.getBirthday());
            temp.setCmndCccd(generalProfile.getCmndCccd());
            temp.setGender(generalProfile.getGender());
            temp.setPhone(generalProfile.getPhone());
            temp.setEmail(generalProfile.getEmail());
            temp.setPermanentAddress(generalProfile.getPermanentAddress());
            temp.setCurrentAddress(generalProfile.getCurrentAddress());
            temp.setJob(generalProfile.getJob());
            temp.setWorkspace(generalProfile.getWorkspace());
            temp.setTemperaryAddress(generalProfile.getTemporaryAddress());
            temp.setTemporaryResidenceExpiration(generalProfile.getTemporaryResidenceExpiration());
            temp.setTemporaryDigitalAddress(generalProfile.getTemporaryDigitalAddress());
            temp.setTemporaryLatitude(generalProfile.getTemporaryLatitude());
            temp.setTemporaryLongitude(generalProfile.getTemporaryLongitude());
            temp.setHostName(generalProfile.getHostName());
            temp.setCmndCccdHost(generalProfile.getCmndCccdHost());
            temp.setRelationshipHost(generalProfile.getRelationshipHost());
            temp.setRelationshipDeclarent(generalProfile.getRelationshipDeclarent());
            temp.setUser(generalProfile.getUser());
            TemporaryResidenceProfile temporaryResidenceProfile = temporaryResidenceProfileRepository.save(temp);

            List<AttachedProfile> attachedProfiles = attachedProfileRepository
                    .findAllByGeneralProfileIdAndIsDelete(generalProfile.getId(), 0);
            for (AttachedProfile e : attachedProfiles) {
                e.setTemporaryResidenceProfile(temporaryResidenceProfile);
                attachedProfileRepository.save(e);
            }

            List<MemberChangeTogether> memberChangeTogethers = memberChangeTogetherRepository
                    .findAllByGeneralProfileIdAndIsDelete(generalProfile.getId(), 0);
            for (MemberChangeTogether e : memberChangeTogethers) {
                TemporaryResidenceProfile temp2 = temporaryResidenceProfileRepository
                        .findByCmndCccdAndIsDelete(e.getCmndCccd(), 0)
                        .orElse(new TemporaryResidenceProfile());
                temp2.setWard(generalProfile.getWard());
                temp2.setIsExpired(0);
                temp2.setIsDelete(0);
                temp2.setName(e.getName());
                temp2.setBirthday(e.getBirthday());
                temp2.setCmndCccd(e.getCmndCccd());
                temp2.setGender(e.getGender());
                temp2.setPhone(e.getPhone());
                temp2.setEmail(e.getEmail());
                temp2.setPermanentAddress(e.getPermanentAddress());
                temp2.setCurrentAddress(e.getCurrentAddress());
                temp2.setJob(e.getJob());
                temp2.setWorkspace(e.getWorkspace());
                temp2.setTemperaryAddress(generalProfile.getTemporaryAddress());
                temp2.setTemporaryResidenceExpiration(generalProfile.getTemporaryResidenceExpiration());
                temp2.setTemporaryDigitalAddress(generalProfile.getTemporaryDigitalAddress());
                temp2.setTemporaryLatitude(generalProfile.getTemporaryLatitude());
                temp2.setTemporaryLongitude(generalProfile.getTemporaryLongitude());
                temp2.setHostName(generalProfile.getHostName());
                temp2.setCmndCccdHost(generalProfile.getCmndCccdHost());
                temp2.setRelationshipHost(e.getRelationshipHost());
                temp2.setRelationshipDeclarent(e.getRelationshipDeclarent());
                temp2.setUser(generalProfile.getUser());
                temporaryResidenceProfileRepository.save(temp);
            }

            if (generalProfile.getReceiveResult().getId() == 1) {
                System.out.println("123");
            } else if (generalProfile.getReceiveResult().getId() == 2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String htmlContent = "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Document</title>\n" +
                        "    <style>\n" +
                        "        .container {\n" +
                        "            max-width: 700px;\n" +
                        "            border: 1px solid black;\n" +
                        "        }\n" +
                        "\n" +
                        "        .space {\n" +
                        "            padding-inline: 25px;\n" +
                        "        }\n" +
                        "\n" +
                        "        .title {\n" +
                        "            background-color: green;\n" +
                        "            color: white;\n" +
                        "            text-align: center;\n" +
                        "            padding: 20px 0;\n" +
                        "        }\n" +
                        "\n" +
                        "        .content {\n" +
                        "            padding: 10px 20px;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <div class=\"title\">THÔNG BÁO TIẾP NHẬN HỒ SƠ ĐĂNG KÝ TẠM TRÚ</div>\n" +
                        "        <div class=\"content\">\n" +
                        "            <p>Kính gửi ông/bà " + generalProfile.getName() + ",</p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Luật 68/2020/QH14 được ban hành ngày 13/11/2020 của Quốc hội quy\n" +
                        "                định\n" +
                        "                về việc thực hiện quyền tự do cư trú của công dân Việt Nam trên lãnh thổ nước Cộng hòa xã hội chủ nghĩa\n" +
                        "                Việt\n" +
                        "                Nam.\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Thông tư 55/2021/TT-BCA được ban hành ngày 15-05-2021 của Bộ Công\n" +
                        "                an\n" +
                        "                quy định chi tiết một số điều và biện pháp thi hành luật cư trú.\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Thông tư 56/2021/TT-BCA được ban hành ngày 15-05-2021 của Bộ Công\n" +
                        "                an\n" +
                        "                quy định về biểu mẫu trong đăng ký, quản lý cư trú.\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Thông tư 57/2021/TT-BCA được ban hành ngày 15-05-2021 của Bộ Công\n" +
                        "                an\n" +
                        "                quy định về quy trình đăng ký cư trú.\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Thông tư 85/2019/TT-BTC được ban hành ngày 29-11-2019 của Bộ Tài\n" +
                        "                chính\n" +
                        "                hướng dẫn về phí và lệ phí thuộc thẩm quyền quyết định của hội đồng nhân dân tỉnh, thành phố trực thuộc\n" +
                        "                trung ương.\n" +
                        "            </p>\n" +
                        "            <p>Cơ quan công an " + generalProfile.getWard().getFullName() + " quyết định tiếp nhận đơn đăng ký tạm trú của ông/bà " + generalProfile.getName() + " với\n" +
                        "                nội\n" +
                        "                dung yêu cầu là " + generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile() + ".\n" +
                        "            </p>\n" +
                        "            <p>Thời gian nhận hồ sơ vào lúc: " + generalProfile.getCreatedAt().getHours() + " giờ " + generalProfile.getCreatedAt().getMinutes() + " phút, ngày " + simpleDateFormat.format(generalProfile.getCreatedAt()) + ".</p>\n" +
                        "            <p>Thời gian trả trả kết quả giải quyết hồ sơ: " + request.getResultReturnTime().getHours() + " giờ " + request.getResultReturnTime().getMinutes() + " phút, ngày " + simpleDateFormat.format(request.getResultReturnTime()) + ".</p>\n" +
                        "            <p>Chi tiết về hồ sơ được đính kèm phía bên dưới hoặc truy cập vào trang chủ <a\n" +
                        "                    href=\"http://congtamtru.com:3001\">Cổng tạm\n" +
                        "                    trú</a>.</p>\n" +
                        "            <p>Cơ quan thực hiện,</p>\n" +
                        "            <p>Công an " + generalProfile.getWard().getFullName() + "</p>\n" +
                        "        </div>\n" +
                        "\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";


                //---------

                String path2 = "/templates/CT04_template.jasper";
                List<AttachedProfile> listData = attachedProfileRepository
                        .findAllByGeneralProfileIdAndIsDelete(id, 0);

                CT04 ct04 = new CT04(generalProfile.getName(), generalProfile.getCmndCccd(),
                        generalProfile.getPhone(), generalProfile.getEmail(),
                        generalProfile.getPermanentAddress(), generalProfile.getTemporaryAddress(),
                        generalProfile.getCurrentAddress(),
                        generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile(),
                        generalProfile.getWard().getDistrict().getFullName(),
                        generalProfile.getWard().getFullName(),
                        String.valueOf(request.getProfileNumber()), "123456",
                        String.valueOf(generalProfile.getCreatedAt().getHours()),
                        String.valueOf(generalProfile.getCreatedAt().getMinutes()),
                        simpleDateFormat.format(generalProfile.getCreatedAt()),
                        String.valueOf(request.getResultReturnTime().getHours()),
                        String.valueOf(request.getResultReturnTime().getMinutes()),
                        simpleDateFormat.format(request.getResultReturnTime()),
                        generalProfile.getReceiveResult().getName(), null, null, null);
                List<Map<String, Object>> dataSource = new ArrayList<>();
                if (listData.isEmpty() || listData == null) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("name", " ");
                    dataSource.add(data);
                } else {
                    for (AttachedProfile e : listData) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("nameAttachedFile", e.getName());
                        String temp1 = new String();

                        if (e.getIsCopy() == 1)
                            temp1 = "Bản sao";
                        else if (e.getIsCopy() == 0)
                            temp1 = "Bản chính";
                        else temp1 = "Bản chụp";
                        data.put("isCopy", temp1);
                        data.put("note", e.getNote());
                        dataSource.add(data);
                    }
                }
                byte[] fileBytes2 = ReportUtils.report(path2, ct04.initParameters(), dataSource, ReportType.DOCX).getFileByte();
                Mail mail = new Mail("nguyenkhoi25022018@gmail.com", "Cổng tạm trú thông báo tiếp nhận hồ sơ đăng ký tạm trú", htmlContent, fileBytes2, "CT04 Phiếu tiếp nhận hồ sơ và hẹn trả kết quả.doc");
                mailService.sendEmailWithAttachment(mail);
            } else {
                System.out.println(123);
            }

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean additionalTemporaryResidence(Long id, AdditionalTemporaryResidenceRequest request) {
        try {
            GeneralProfile generalProfile = generalProfileRepository
                    .findByIdAndIsDeleteAndStatus(id, 0, 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            generalProfile.setStatus(2);
            generalProfile.setIsHistory(1);
            if (generalProfile.getReceiveResult().getId() == 1) {
                generalProfileRepository.save(generalProfile);
            } else if (generalProfile.getReceiveResult().getId() == 2) {
                System.out.println("2");
                String htmlContent = "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Document</title>\n" +
                        "    <style>\n" +
                        "        .container {\n" +
                        "            max-width: 700px;\n" +
                        "            border: 1px solid black;\n" +
                        "        }\n" +
                        "\n" +
                        "        .space {\n" +
                        "            padding-inline: 25px;\n" +
                        "        }\n" +
                        "\n" +
                        "        .title {\n" +
                        "            background-color: orange;\n" +
                        "            color: white;\n" +
                        "            text-align: center;\n" +
                        "            padding: 20px 0;\n" +
                        "        }\n" +
                        "\n" +
                        "        .content {\n" +
                        "            padding: 10px 20px;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <div class=\"title\">THÔNG BÁO YÊU CẦU BỔ SUNG HỒ SƠ ĐĂNG KÝ TẠM TRÚ</div>\n" +
                        "        <div class=\"content\">\n" +
                        "            <p>Kính gửi ông/bà " + generalProfile.getName() + ",</p>\n" +
                        "            <p><span class=\"space\"></span>Qua nghiên cứu hồ sơ và căn cứ vào quy định của Luật Cư trú và các văn bản\n" +
                        "                hướng dẫn thi hành, đề nghị Ông/Bà hoàn thiện hồ sơ gồm những nội dung sau:\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Bổ sung thêm các giấy tờ, thủ tục sau: " + request.getAdditionalFile() + ".\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Kê khai lại các biểu mẫu sau: " + request.getDeclareAgain() + ".\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Hướng dẫn khác: " + request.getOtherInstructions() + ".\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Lý do: " + request.getReason() + ".\n" +
                        "            </p>\n" +
                        "            <p>Chi tiết về hồ sơ được đính kèm phía bên dưới hoặc truy cập vào trang chủ <a\n" +
                        "                    href=\"http://congtamtru.com:3001\">Cổng tạm\n" +
                        "                    trú</a>.</p>\n" +
                        "            <p>Cơ quan thực hiện,</p>\n" +
                        "            <p>" + generalProfile.getWard().getFullName() + "</p>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";
                String path = "/templates/CT05_template.jasper";
                CT05 ct05 = new CT05(generalProfile.getName(), generalProfile.getCmndCccd(),
                        generalProfile.getPhone(), generalProfile.getEmail(), generalProfile.getPermanentAddress(),
                        generalProfile.getTemporaryAddress(), generalProfile.getCurrentAddress(),
                        generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile(),
                        generalProfile.getWard().getDistrict().getFullName(), generalProfile.getWard().getFullName(),
                        request.getAdditionalFile(), request.getDeclareAgain(), request.getOtherInstructions(), request.getReason(),
                        request.getPhoneOrgan(), null, null, null);
                List<Map<String, Object>> dataSource = new ArrayList<>();
                Map<String, Object> data = new HashMap<>();
                data.put("name", " ");
                dataSource.add(data);

                byte[] fileBytes = ReportUtils.report(path, ct05.initParameters(), dataSource, ReportType.DOCX).getFileByte();
                Mail mail = new Mail("nguyenkhoi25022018@gmail.com", "Cổng tạm trú yêu cầu bổ sung hồ sơ đăng ký tạm trú", htmlContent, fileBytes, "CT05 Phiếu hướng dẫn bổ sung hoàn thiện hồ sơ.doc");
                mailService.sendEmailWithAttachment(mail);
                generalProfileRepository.save(generalProfile);
            } else {
                System.out.println("3");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean deniedTemporaryResidence(Long id, DeniedTemporaryResidenceRequest request) {
        try {
            GeneralProfile generalProfile = generalProfileRepository
                    .findByIdAndIsDeleteAndStatus(id, 0, 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            generalProfile.setStatus(3);
            generalProfile.setIsHistory(1);
            if (generalProfile.getReceiveResult().getId() == 1) {
                generalProfileRepository.save(generalProfile);
            } else if (generalProfile.getReceiveResult().getId() == 2) {
                String path = "/templates/CT06_template.jasper";
                CT06 ct06 = new CT06(generalProfile.getName(), generalProfile.getCmndCccd(),
                        generalProfile.getPhone(), generalProfile.getEmail(),
                        generalProfile.getPermanentAddress(),
                        generalProfile.getTemporaryAddress(),
                        generalProfile.getCurrentAddress(),
                        generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile(),
                        generalProfile.getWard().getDistrict().getFullName(),
                        generalProfile.getWard().getFullName(),
                        request.getReason(), null, null, null);
                List<Map<String, Object>> dataSource = new ArrayList<>();
                Map<String, Object> data = new HashMap<>();
                data.put("name", " ");
                dataSource.add(data);
                String htmlContent = "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Document</title>\n" +
                        "    <style>\n" +
                        "        .container {\n" +
                        "            max-width: 700px;\n" +
                        "            border: 1px solid black;\n" +
                        "        }\n" +
                        "\n" +
                        "        .space {\n" +
                        "            padding-inline: 25px;\n" +
                        "        }\n" +
                        "\n" +
                        "        .title {\n" +
                        "            background-color: rgba(189, 2, 2, 0.813);\n" +
                        "            color: white;\n" +
                        "            text-align: center;\n" +
                        "            padding: 20px 0;\n" +
                        "        }\n" +
                        "\n" +
                        "        .content {\n" +
                        "            padding: 10px 20px;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <div class=\"title\">THÔNG BÁO TỪ CHỐI TIẾP NHẬN, GIẢI QUYẾT HỒ SƠ ĐĂNG KÝ TẠM TRÚ</div>\n" +
                        "        <div class=\"content\">\n" +
                        "            <p>Kính gửi ông/bà " + generalProfile.getName() + ",</p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Luật 68/2020/QH14 được ban hành ngày 13/11/2020 của Quốc hội quy\n" +
                        "                định\n" +
                        "                về việc thực hiện quyền tự do cư trú của công dân Việt Nam trên lãnh thổ nước Cộng hòa xã hội chủ nghĩa\n" +
                        "                Việt\n" +
                        "                Nam.\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Thông tư 55/2021/TT-BCA được ban hành ngày 15-05-2021 của Bộ Công\n" +
                        "                an\n" +
                        "                quy định chi tiết một số điều và biện pháp thi hành luật cư trú.\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Thông tư 56/2021/TT-BCA được ban hành ngày 15-05-2021 của Bộ Công\n" +
                        "                an\n" +
                        "                quy định về biểu mẫu trong đăng ký, quản lý cư trú.\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Thông tư 57/2021/TT-BCA được ban hành ngày 15-05-2021 của Bộ Công\n" +
                        "                an\n" +
                        "                quy định về quy trình đăng ký cư trú.\n" +
                        "            </p>\n" +
                        "            <p><span class=\"space\"></span>Căn cứ vào Thông tư 85/2019/TT-BTC được ban hành ngày 29-11-2019 của Bộ Tài\n" +
                        "                chính\n" +
                        "                hướng dẫn về phí và lệ phí thuộc thẩm quyền quyết định của hội đồng nhân dân tỉnh, thành phố trực thuộc\n" +
                        "                trung ương.\n" +
                        "            </p>\n" +
                        "            <p>Cơ quan công an " + generalProfile.getWard().getFullName() + " quyết định từ chối tiếp nhận, giải quyết hồ sơ đăng ký tạm trú của ông/bà\n" +
                        "                " + generalProfile.getWard().getFullName() + " với\n" +
                        "                nội\n" +
                        "                dung yêu cầu là " + generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile() + ".\n" +
                        "            </p>\n" +
                        "            <p>Lý do từ chối: " + request.getReason() + ".</p>\n" +
                        "            <p>Chi tiết về hồ sơ được đính kèm phía bên dưới hoặc truy cập vào trang chủ <a\n" +
                        "                    href=\"http://congtamtru.com:3001\">Cổng tạm\n" +
                        "                    trú</a>.</p>\n" +
                        "            <p>Cơ quan thực hiện,</p>\n" +
                        "            <p>Công an " + generalProfile.getWard().getFullName() + "</p>\n" +
                        "        </div>\n" +
                        "\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";
//
                byte[] fileBytes = ReportUtils.report(path, ct06.initParameters(), dataSource, ReportType.DOCX).getFileByte();
                Mail mail = new Mail("nguyenkhoi25022018@gmail.com", "Cổng tạm trú từ chối tiếp nận, giải quyết hồ sơ đăng ký tạm trú", htmlContent, fileBytes, "CT06 Phiếu từ chối tiếp nhận, giải quyết hồ sơ.doc");
                mailService.sendEmailWithAttachment(mail);
                generalProfileRepository.save(generalProfile);
            } else {
                System.out.println(123);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    @Override
    public Boolean registerTemporaryResidence(Long userId, TemporaryResidenceRegistrationRequest rq, Boolean isCopy) {
        try {
            GeneralProfile generalProfile = GeneralProfile.builder()
                    .ward(rq.getWardId() == null ? null : wardRepository.findByCodeAndIsDelete(rq.getWardId(), 0).orElse(null))
                    .isCopy(isCopy ? 1 : 0)
                    .isHistory(0)
                    .status(0)
                    .isDelete(0)
                    .name(rq.getName())
                    .birthday(rq.getBirthday())
                    .cmndCccd(rq.getCmndCccd())
                    .gender(rq.getGender())
                    .phone(rq.getPhone())
                    .email(rq.getEmail())
                    .permanentAddress(rq.getPermanentAddress())
                    .currentAddress(rq.getCurrentAddress())
                    .job(rq.getJob())
                    .workspace(rq.getWorkspace())
                    .temporaryAddress(rq.getTemperaryAddress())
                    .temporaryResidenceExpiration(rq.getTemporaryResidenceExpiration())
                    .temporaryDigitalAddress(rq.getTemporaryDigitalAddress())
                    .temporaryLatitude(rq.getTemporaryLatitude())
                    .temporaryLongitude(rq.getTemporaryLongitude())
                    .hostName(rq.getHostName())
                    .cmndCccdHost(rq.getCmndCccdHost())
                    .relationshipHost(rq.getRelationshipHost())
                    .relationshipDeclarent(rq.getRelationshipDeclarent())
                    .noteDeclarent(rq.getNoteDeclarent())
                    .caseProfile(rq.getCaseProfile())
                    .typeProfile(rq.getTypeProfileId() == null ? null : typeProfileRepository.findByIdAndIsDelete(rq.getTypeProfileId(), 0).orElse(null))
                    .typeNotification(rq.getTypeNotificationId() == null ? null : typeNotificationRepository.findByIdAndIsDelete(rq.getTypeNotificationId(), 0).orElse(null))
                    .receiveResult(rq.getReceiveResultId() == null ? null : receiveResultsRepository.findByIdAndIsDelete(rq.getReceiveResultId(), 0).orElse(null))
                    .user(userRepository.findById(userId).orElse(null))
                    .build();
            generalProfile = generalProfileRepository.save(generalProfile);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean acceptedExtension(Long id){
        try{
            GeneralProfile generalProfile = generalProfileRepository
                    .findByIdAndIsDeleteAndStatus(id, 0, 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            TemporaryResidenceProfile temp2 = temporaryResidenceProfileRepository
                    .findByCmndCccdAndIsDelete(generalProfile.getCmndCccd(), 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            temp2.setTemporaryResidenceExpiration(generalProfile.getTemporaryResidenceExpiration());
            generalProfile.setStatus(1);
            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Document</title>\n" +
                    "    <style>\n" +
                    "        .container {\n" +
                    "            max-width: 700px;\n" +
                    "            border: 1px solid black;\n" +
                    "        }\n" +
                    "\n" +
                    "        .space {\n" +
                    "            padding-inline: 25px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .title {\n" +
                    "            background-color: green;\n" +
                    "            color: white;\n" +
                    "            text-align: center;\n" +
                    "            padding: 20px 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        .content {\n" +
                    "            padding: 10px 20px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"title\">THÔNG BÁO TIẾP NHẬN HỒ SƠ GIA HẠN TẠM TRÚ</div>\n" +
                    "        <div class=\"content\">\n" +
                    "            <p>Kính gửi ông/bà " + generalProfile.getName() + ",</p>\n"+
                    "            <p>Cơ quan công an " + generalProfile.getWard().getFullName() + " quyết định tiếp nhận hồ sơ gia hạn tạm trú của ông/bà\n" +
                    "                " + generalProfile.getWard().getFullName() + " với\n" +
                    "                nội\n" +
                    "                dung yêu cầu là " + generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile() + ".\n" +
                    "            </p>\n" +
                    "            <p>Chi tiết về hồ sơ được đính kèm phía bên dưới hoặc truy cập vào trang chủ <a\n" +
                    "                    href=\"http://congtamtru.com:3001\">Cổng tạm\n" +
                    "                    trú</a>.</p>\n" +
                    "            <p>Cơ quan thực hiện,</p>\n" +
                    "            <p>Công an " + generalProfile.getWard().getFullName() + "</p>\n" +
                    "        </div>\n" +
                    "\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            Mail mail = new Mail("nguyenkhoi25022018@gmail.com", "Cổng tạm trú tiếp nhận hồ sơ gia hạn tạm trú", htmlContent);
            mailService.sendEmail(mail);
            temporaryResidenceProfileRepository.save((temp2));
            generalProfileRepository.save(generalProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean deniedExtension(Long id, DeniedTemporaryResidenceRequest request){
        try{
            GeneralProfile generalProfile = generalProfileRepository
                    .findByIdAndIsDeleteAndStatus(id, 0, 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            TemporaryResidenceProfile temp2 = temporaryResidenceProfileRepository
                    .findByCmndCccdAndIsDelete(generalProfile.getCmndCccd(), 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            generalProfile.setStatus(2);
            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Document</title>\n" +
                    "    <style>\n" +
                    "        .container {\n" +
                    "            max-width: 700px;\n" +
                    "            border: 1px solid black;\n" +
                    "        }\n" +
                    "\n" +
                    "        .space {\n" +
                    "            padding-inline: 25px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .title {\n" +
                    "            background-color: rgba(189, 2, 2, 0.813);\n" +
                    "            color: white;\n" +
                    "            text-align: center;\n" +
                    "            padding: 20px 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        .content {\n" +
                    "            padding: 10px 20px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"title\">THÔNG BÁO TỪ CHỐI TIẾP NHẬN, GIẢI QUYẾT HỒ SƠ GIA HẠN TẠM TRÚ</div>\n" +
                    "        <div class=\"content\">\n" +
                    "            <p>Kính gửi ông/bà " + generalProfile.getName() + ",</p>\n"+
                    "            <p>Cơ quan công an " + generalProfile.getWard().getFullName() + " quyết định từ chối tiếp nhận, giải quyết hồ sơ gia hạn tạm trú của ông/bà\n" +
                    "                " + generalProfile.getWard().getFullName() + " với\n" +
                    "                nội\n" +
                    "                dung yêu cầu là " + generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile() + ".\n" +
                    "            </p>\n" +
                    "            <p>Lý do: "+request.getReason()+".</p>"
                    +
                    "            <p>Chi tiết về hồ sơ được đính kèm phía bên dưới hoặc truy cập vào trang chủ <a\n" +
                    "                    href=\"http://congtamtru.com:3001\">Cổng tạm\n" +
                    "                    trú</a>.</p>\n" +
                    "            <p>Cơ quan thực hiện,</p>\n" +
                    "            <p>Công an " + generalProfile.getWard().getFullName() + "</p>\n" +
                    "        </div>\n" +
                    "\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            Mail mail = new Mail("nguyenkhoi25022018@gmail.com", "Cổng tạm trú từ chối tiếp nhận, giải quyết hồ sơ gia hạn tạm trú", htmlContent);
            mailService.sendEmail(mail);
            generalProfileRepository.save(generalProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean acceptedDelete(Long id){
        try{
            GeneralProfile generalProfile = generalProfileRepository
                    .findByIdAndIsDeleteAndStatus(id, 0, 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            TemporaryResidenceProfile temp2 = temporaryResidenceProfileRepository
                    .findByCmndCccdAndIsDelete(generalProfile.getCmndCccd(), 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            temp2.setIsDelete(1);
            generalProfile.setStatus(1);
            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Document</title>\n" +
                    "    <style>\n" +
                    "        .container {\n" +
                    "            max-width: 700px;\n" +
                    "            border: 1px solid black;\n" +
                    "        }\n" +
                    "\n" +
                    "        .space {\n" +
                    "            padding-inline: 25px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .title {\n" +
                    "            background-color: green;\n" +
                    "            color: white;\n" +
                    "            text-align: center;\n" +
                    "            padding: 20px 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        .content {\n" +
                    "            padding: 10px 20px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"title\">THÔNG BÁO TIẾP NHẬN HỒ SƠ XOÁ ĐĂNG KÝ TẠM TRÚ</div>\n" +
                    "        <div class=\"content\">\n" +
                    "            <p>Kính gửi ông/bà " + generalProfile.getName() + ",</p>\n"+
                    "            <p>Cơ quan công an " + generalProfile.getWard().getFullName() + " quyết định tiếp nhận hồ sơ xoá đăng ký tạm trú của ông/bà\n" +
                    "                " + generalProfile.getWard().getFullName() + " với\n" +
                    "                nội\n" +
                    "                dung yêu cầu là " + generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile() + ".\n" +
                    "            </p>\n" +
                    "            <p>Chi tiết về hồ sơ được đính kèm phía bên dưới hoặc truy cập vào trang chủ <a\n" +
                    "                    href=\"http://congtamtru.com:3001\">Cổng tạm\n" +
                    "                    trú</a>.</p>\n" +
                    "            <p>Cơ quan thực hiện,</p>\n" +
                    "            <p>Công an " + generalProfile.getWard().getFullName() + "</p>\n" +
                    "        </div>\n" +
                    "\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            Mail mail = new Mail("nguyenkhoi25022018@gmail.com", "Cổng tạm trú tiếp nhận hồ sơ xoá đăng ký tạm trú", htmlContent);
            mailService.sendEmail(mail);
            temporaryResidenceProfileRepository.save((temp2));
            generalProfileRepository.save(generalProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }
    @Override
    public Boolean deniedDelete(Long id, DeniedTemporaryResidenceRequest request){
        try{
            GeneralProfile generalProfile = generalProfileRepository
                    .findByIdAndIsDeleteAndStatus(id, 0, 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            TemporaryResidenceProfile temp2 = temporaryResidenceProfileRepository
                    .findByCmndCccdAndIsDelete(generalProfile.getCmndCccd(), 0)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            generalProfile.setStatus(2);
            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Document</title>\n" +
                    "    <style>\n" +
                    "        .container {\n" +
                    "            max-width: 700px;\n" +
                    "            border: 1px solid black;\n" +
                    "        }\n" +
                    "\n" +
                    "        .space {\n" +
                    "            padding-inline: 25px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .title {\n" +
                    "            background-color: rgba(189, 2, 2, 0.813);\n" +
                    "            color: white;\n" +
                    "            text-align: center;\n" +
                    "            padding: 20px 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        .content {\n" +
                    "            padding: 10px 20px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"title\">THÔNG BÁO TỪ CHỐI TIẾP NHẬN, GIẢI QUYẾT HỒ SƠ XOÁ ĐĂNG KÝ TẠM TRÚ</div>\n" +
                    "        <div class=\"content\">\n" +
                    "            <p>Kính gửi ông/bà " + generalProfile.getName() + ",</p>\n"+
                    "            <p>Cơ quan công an " + generalProfile.getWard().getFullName() + " quyết định từ chối tiếp nhận, giải quyết hồ sơ xoá đăng ký tạm trú của ông/bà\n" +
                    "                " + generalProfile.getWard().getFullName() + " với\n" +
                    "                nội\n" +
                    "                dung yêu cầu là " + generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile() + ".\n" +
                    "            </p>\n" +
                    "            <p>Lý do: "+request.getReason()+".</p>"
                    +
                    "            <p>Chi tiết về hồ sơ được đính kèm phía bên dưới hoặc truy cập vào trang chủ <a\n" +
                    "                    href=\"http://congtamtru.com:3001\">Cổng tạm\n" +
                    "                    trú</a>.</p>\n" +
                    "            <p>Cơ quan thực hiện,</p>\n" +
                    "            <p>Công an " + generalProfile.getWard().getFullName() + "</p>\n" +
                    "        </div>\n" +
                    "\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            Mail mail = new Mail("nguyenkhoi25022018@gmail.com", "Cổng tạm trú từ chối tiếp nhận, giải quyết hồ sơ xoá đăng ký tạm trú", htmlContent);
            mailService.sendEmail(mail);
            generalProfileRepository.save(generalProfile);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
