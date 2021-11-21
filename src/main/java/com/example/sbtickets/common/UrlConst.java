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
     * API login hệ thống Sbtickets
     */

    public static final String HOME = "/Sbtickets";
    public static final String LOGIN_SBTICKETS = HOME + "/login";

    public static class HOMEADIM{

        /**
         * Home url hệ thống Sbtickets (ROLE_ADMIN)
         */
        public static final String HOMEADIM = HOME + "/admin";
        /**
         * API getAllDriver CuongNv
         */
        public static final String GET_DRIVER = HOMEADIM + "/getDriver";

        public static final String GET_DRIVER_BY_ID = HOMEADIM + "/getDriver/{id}";

        /**
         * API find name Driver CuongNv
         */
        public static final String FIND_DRIVER = HOMEADIM + "/findDriver";

        public static final String CREATE_DRIVER = HOMEADIM + "/createDriver";

        public static final String UPDATE_DRIVER = HOMEADIM + "/updateDriver/{id}";

        public static final String DELETE_DRIVER = HOMEADIM + "/deleteDriver/{id}";

        public static final String DELETE_DRIVERS = HOMEADIM + "/deleteDrivers";

        public static final String EXPORT_EXCEL_ALL_DRIVER = HOMEADIM + "/driver/excelAll";

        /**
         * API getAllBus SonPK
         */
        public static final String GET_BUS = HOMEADIM + "/getBus";

        public static final String FIND_BUS = HOMEADIM + "/findBus/{id}";

        public static final String CREATE_BUS = HOMEADIM + "/createBus";

        public static final String UPDATE_BUS = HOMEADIM + "/updateBus/{id}";

        public static final String DELETE_BUS = HOMEADIM + "/deleteBus/{id}";

        /**
         * API find name Line Bus HaLv
         */
        public static final String GET_LINE_BUS = HOMEADIM + "/getLineBus";

        public static final String FIND_LINE_BUS = HOMEADIM + "/findLineBus";

        public static final String CREATE_LINE_BUS = HOMEADIM + "/createLineBus";

        public static final String UPDATE_LINE_BUS = HOMEADIM + "/updateLineBus/{id}";

        public static final String DELETE_LINE_BUS = HOMEADIM + "/deleteLineBus/{id}";

    }

    public static class HOME_USER{
        public static final String HOME_USER = HOME + "/user";

        public static final String GET_USER = HOME_USER + "/getUser";
    }
}
