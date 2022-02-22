package com.bilgeadam.constants;

public class RestApiUrls {
    /**
     * Resp Api Servislerinizin versiyonlarını tutmak için kullanın.
     */
    public static final String VERSION = "/v1";

    /**
     * CONTOLLER kısımlarında ki map listesini ayrıca burada yazalım
     */
    public static final String POST = "/post";
    public static final String MEDIA = "/media";


    /**
     * GENEL olarak tanımlanan isteklerin burada tanımlayalım
     */
    public static final String SAVE= "/save";
    public static final String UPDATE= "/update";
    public static final String DELETE= "/delete";
    public static final String FINDALL= "/findall";
    public static final String GETALL= "/getall";
    public static final String FINDBYID= "/findbyid";
    public static final String FINDBYNAME= "/findbyname";
    public static final String FINDBYUSERID= "/findbyuserid";
    public static final String UPLOADFILE= "/uploadfile";


    /**
     * CONTROLLER için özel olarak tanımladığınız istek url lerini burada tanımlayalım ayrıştırabilirsiniz.
     */

    public static final String DOLOGIN= "/dologin";
    public static final String REGISTER= "/register";


}
