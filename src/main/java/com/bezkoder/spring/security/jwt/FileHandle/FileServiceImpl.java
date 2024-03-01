package com.bezkoder.spring.security.jwt.FileHandle;

import com.bezkoder.spring.security.jwt.jasper.ReportUtils;
import com.bezkoder.spring.security.jwt.jasper.ReportType;
import com.bezkoder.spring.security.jwt.models.AttachedProfile;
import com.bezkoder.spring.security.jwt.models.GeneralProfile;
import com.bezkoder.spring.security.jwt.models.MemberChangeTogether;
import com.bezkoder.spring.security.jwt.payload.request.AcceptedTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.AdditionalTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.DeniedTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.repository.AttachedProfileRepository;
import com.bezkoder.spring.security.jwt.repository.GeneralProfileRepository;
import com.bezkoder.spring.security.jwt.repository.MemberChangeTogetherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    GeneralProfileRepository generalProfileRepository;

    @Autowired
    AttachedProfileRepository attachedProfileRepository;

    @Autowired
    MemberChangeTogetherRepository memberChangeTogetherRepository;

    @Override
    public Boolean uploadAttachedFile(MultipartFile file, String path) {
        String absolutePath = uploadPath + "attached/" + path;
        try (OutputStream os = new FileOutputStream(absolutePath)) {
            os.write(file.getBytes());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public FileResponse uploadAttachedFile(MultipartFile file) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(now);
        String path = timestamp + "_" + file.getOriginalFilename();
        String absolutePath = uploadPath + "attached/" + path;
        try (OutputStream os = new FileOutputStream(absolutePath)) {
            os.write(file.getBytes());
            return new FileResponse(path, file.getSize());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Boolean uploadAvatar(MultipartFile file, String path) {
        String absolutePath = uploadPath + "avatar/" + path;
        try (OutputStream os = new FileOutputStream(absolutePath)) {
            os.write(file.getBytes());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ResponseEntity CT01Response(Long generalProfileId, ReportType reportType) {
        String path = "/templates/CT01_template.jasper";
        GeneralProfile generalProfile = generalProfileRepository
                .findByIdAndIsDelete(generalProfileId, 0)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        List<MemberChangeTogether> dataMem = memberChangeTogetherRepository
                .findAllByGeneralProfileIdAndIsDelete(generalProfileId, 0);
        System.out.println(dataMem);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        CT01 ct01 = new CT01("Công an " + generalProfile.getWard().getFullName(),
                generalProfile.getName(), simpleDateFormat.format(generalProfile.getBirthday()),
                generalProfile.getCmndCccd(), generalProfile.getGender() == 1 ? "Nam" : "Nữ",
                generalProfile.getPhone(), generalProfile.getEmail(),
                generalProfile.getPermanentAddress(), generalProfile.getTemporaryAddress(),
                generalProfile.getCurrentAddress(), generalProfile.getJob() + ", " + generalProfile.getWorkspace(),
                generalProfile.getHostName(), generalProfile.getCmndCccdHost(), generalProfile.getRelationshipHost(),
                generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile());
        List<Map<String, Object>> dataSource = new ArrayList<>();
        if (dataMem.isEmpty() || dataMem == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", " ");
            dataSource.add(data);
        } else {
            for (MemberChangeTogether e : dataMem) {
                Map<String, Object> data = new HashMap<>();
                data.put("name", e.getName());
                data.put("birthday", simpleDateFormat.format(e.getBirthday()));
                data.put("gender", e.getGender() == 1 ? "Nam" : "Nữ");
                data.put("identify", e.getCmndCccd());
                data.put("job", e.getJob() + ", " + e.getWorkspace());
                data.put("relationshipDeclarent", e.getRelationshipDeclarent());
                data.put("relationshipHost", e.getRelationshipHost());
                System.out.println(data);
                dataSource.add(data);
            }
        }

        return ReportUtils.downloadReport(path, ct01.initParameters(), dataSource, reportType);
    }

    @Override
    public ResponseEntity CT01ResponsePDF(Long generalProfileId, ReportType reportType) throws Exception {
        String path = "/templates/CT01_template.jasper";
        GeneralProfile generalProfile = generalProfileRepository
                .findByIdAndIsDelete(generalProfileId, 0)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        List<MemberChangeTogether> dataMem = memberChangeTogetherRepository
                .findAllByGeneralProfileIdAndIsDelete(generalProfileId, 0);
        System.out.println(dataMem);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        CT01 ct01 = new CT01("Công an " + generalProfile.getWard().getFullName(),
                generalProfile.getName(), simpleDateFormat.format(generalProfile.getBirthday()),
                generalProfile.getCmndCccd(), generalProfile.getGender() == 1 ? "Nam" : "Nữ",
                generalProfile.getPhone(), generalProfile.getEmail(),
                generalProfile.getPermanentAddress(), generalProfile.getTemporaryAddress(),
                generalProfile.getCurrentAddress(), generalProfile.getJob() + ", " + generalProfile.getWorkspace(),
                generalProfile.getHostName(), generalProfile.getCmndCccdHost(), generalProfile.getRelationshipHost(),
                generalProfile.getTypeProfile().getName() + " - " + generalProfile.getCaseProfile());
        List<Map<String, Object>> dataSource = new ArrayList<>();
        if (dataMem.isEmpty() || dataMem == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", " ");
            dataSource.add(data);
        } else {
            for (MemberChangeTogether e : dataMem) {
                Map<String, Object> data = new HashMap<>();
                data.put("name", e.getName());
                data.put("birthday", simpleDateFormat.format(e.getBirthday()));
                data.put("gender", e.getGender() == 1 ? "Nam" : "Nữ");
                data.put("identify", e.getCmndCccd());
                data.put("job", e.getJob() + ", " + e.getWorkspace());
                data.put("relationshipDeclarent", e.getRelationshipDeclarent());
                data.put("relationshipHost", e.getRelationshipHost());
                System.out.println(data);
                dataSource.add(data);
            }
        }

        return ReportUtils.downloadReport2(path, ct01.initParameters(), dataSource, reportType);
    }

    @Override
    public ResponseEntity CT04Response(Long generalProfileId, AcceptedTemporaryResidenceRequest request, ReportType reportType) {
        String path = "/templates/CT04_template.jasper";
        GeneralProfile generalProfile = generalProfileRepository
                .findByIdAndIsDelete(generalProfileId, 0)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        List<AttachedProfile> listData = attachedProfileRepository
                .findAllByGeneralProfileIdAndIsDelete(generalProfileId, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
                String temp = new String();
                if (e.getIsCopy() == 1)
                    temp = "Bản sao";
                else if (e.getIsCopy() == 0)
                    temp = "Bản chính";
                else temp = "Bản chụp";
                data.put("isCopy", temp);
                data.put("note", e.getNote());
                dataSource.add(data);
            }
        }
        return ReportUtils.downloadReport(path, ct04.initParameters(), dataSource, reportType);
    }

    @Override
    public ResponseEntity CT05Response(Long generalProfileId, AdditionalTemporaryResidenceRequest request, ReportType reportType) {
        String path = "/templates/CT05_template.jasper";
        GeneralProfile generalProfile = generalProfileRepository
                .findByIdAndIsDelete(generalProfileId, 0)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
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
        return ReportUtils.downloadReport(path, ct05.initParameters(), dataSource, reportType);
    }

    @Override
    public ResponseEntity CT06Response(Long generalProfileId, DeniedTemporaryResidenceRequest request, ReportType reportType) {
        String path = "/templates/CT06_template.jasper";
        GeneralProfile generalProfile = generalProfileRepository
                .findByIdAndIsDelete(generalProfileId, 0)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
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
        return ReportUtils.downloadReport(path, ct06.initParameters(), dataSource, reportType);
    }
}
