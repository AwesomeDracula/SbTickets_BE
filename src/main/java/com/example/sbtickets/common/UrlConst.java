package com.example.sbtickets.common;


/**
 * @author CuongNv
 * Khai báo đường dẫn
 */
public class UrlConst {
    /**
     * @author CuongNv
     * Class kha  báo URL hệ thống Sbtickets
     */

    /**
     * Home url hệ thống Sbtickets
     */
    public static final String HOME = "/Sbtickets";

    /**
     * API login hệ thống Sbtickets
     */
    public static final String LOGIN_SBTICKETS = HOME + "/login";

    /**
     * API getAllDriver CuongNv
     */
    public static final String GET_DRIVER = HOME + "/getDriver";

    /**
     * API find name Driver CuongNv
     */
    public static final String FIND_DRIVER = HOME + "/findDriver";

    public static final String CREATE_DRIVER = HOME + "/createDriver";

    public static final String UPDATE_DRIVER = HOME + "/updateDriver/{id}";

    public static final String DELETE_DRIVER = HOME + "/deleteDriver/{id}";

    public static final String EXPORT_EXCEL_ALL_DRIVER = HOME + "/driver/excelAll";
}
