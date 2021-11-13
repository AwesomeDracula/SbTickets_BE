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

    /**
     * API getAllBus SonPK
     */
    public static final String GET_BUS = HOME + "/getBus";

    public static final String FIND_BUS = HOME + "/findBus";

    public static final String CREATE_BUS = HOME + "/createBus";

    public static final String UPDATE_BUS = HOME + "/updateBus/{id}";

    public static final String DELETE_BUS = HOME + "/deleteBus/{id}";
}
