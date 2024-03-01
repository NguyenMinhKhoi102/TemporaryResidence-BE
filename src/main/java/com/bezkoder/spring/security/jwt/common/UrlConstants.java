package com.bezkoder.spring.security.jwt.common;

public class UrlConstants {

    //ROOT
    public static final String ROOT = "/api";

    //CRUD
    public static final String INFO = "/info";
    public static final String LIST = "/list";
    public static final String ADD = "/add";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";

    //Khác
    public static final String TIM_THEO_TEN_DAY_DU = "/by-full-name";
    public static final String TIM_THEO_TEN_DAY_DU_FULL = "/by-full-name-full";
    public static final String THEO_HO_SO_TAM_TRU_ID = "/by-temporary-residence-profile-id";
    public static final String THEO_HO_SO_CHUNG_ID = "/by-general-profile-id";
    public static final String THEO_CON_HAN = "/by-is-expired";
    public static final String DANG_KY = "/register";
    public static final String UPLOAD = "/upload";
    public static final String THEO_TEN_3_4 = "/by-name-3-4";
    public static final String THEO_LOAI_VA_MA_XA = "/by-type-and-ward-code";
    public static final String THEO_MA_XA = "/by-ward-code";

    public static final String GIA_HAN = "/extension";
    public static final String XOA_DANG_KY= "/delete-register";

    //người dùng
    public static final String NGUOI_DUNG = ROOT + "/user";

    //GeoJSON của xã
    public static final String GEO_JSON_XA = ROOT + "/geo-json-wards";
    //Hồ sơ đính kèm
    public static final String HO_SO_DINH_KEM = ROOT + "/attached-file";

    //Thành viên cùng thay đổi
    public static final String THANH_VIEN_CUNG_THAY_DOI = ROOT + "/member-change-together";

    //Loại hồ sơ
    public static final String LOAI_HO_SO = ROOT + "/type-profile";

    //Loại thông báo
    public static final String LOAI_THONG_BAO = ROOT + "/type-notification";

    //Hình thức nhận kết quả
    public static final String HINH_THUC_NHAN_KET_QUA = ROOT + "/receive-result";

    //Hồ sơ chung
    public static final String HO_SO_CHUNG = ROOT + "/general-profile";
    public static final String DUYET_HO_SO = "/accepted";
    public static final String BO_SUNG_HO_SO = "/additional";
    public static final String TU_CHOI_HO_SO = "/denied";

    //Hồ sơ tạm trú
    public static final String HO_SO_TAM_TRU = ROOT + "/temporary-residence-profile";

    //Tỉnh
    public static final String Tinh = ROOT + "/province";

    //Huyện
    public static final String Huyen = ROOT + "/district";
    public static final String DANH_SACH_HUYEN_THEO_TINH = "/by-province-code";

    //Xã
    public static final String Xa = ROOT + "/ward";
    public static final String DANH_SACH_XA_THEO_HUYEN = "/by-district-code";

    //Xuất hồ sơ
    public static final String XUAT_THEO_MAU = ROOT + "/report";
    public static final String MAU_CT01 = "/ct01";
    public static final String SAMPLE_CT01 = "/sample-ct01";
    public static final String MAU_CT04 = "/ct04";
    public static final String MAU_CT05 = "/ct05";
    public static final String MAU_CT06 = "/ct06";

}
