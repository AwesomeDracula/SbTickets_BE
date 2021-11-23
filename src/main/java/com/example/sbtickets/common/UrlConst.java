package com.example.sbtickets.common;


/**
 * @author CuongNv
 * Khai báo đường dẫn
 */
public class UrlConst {
    /**
     * @author CuongNv
     * Class khai báo URL hệ thống Sbtickets
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

    /**
     * API find name Line Bus HaLv
     */
    public static final String GET_LINE_BUS = HOME + "/getLineBus";

    public static final String GET_LINE_BUS_BY_ID = HOME + "/getLineBusById";

    public static final String FIND_LINE_BUS = HOME + "/findLineBus";

    public static final String CREATE_LINE_BUS = HOME + "/createLineBus";

    public static final String UPDATE_LINE_BUS = HOME + "/updateLineBus/{id}";

    public static final String DELETE_LINE_BUS = HOME + "/deleteLineBus/{id}";

    public static final String DELETE_LINE_BUSES = HOME + "/deleteLineBuses";
}
