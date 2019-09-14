package com.gl.alipay;

public class AlipayConfig {
    //这里用natapp内外网穿透
    public static final String natUrl = "http://www.613sq.cn";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2018092561495365";//在后台获取（必须配置）

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String  merchant_private_key  ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCO+aQtaGAKbdZcuOz9YygxutnvzlNEpCJA1jDF3lP5QoC482xnQp+UpJl6NjxBFyEP3bfVJx5Rlub/XZIQqiuuVvqybd0m8ZU0QdQQxIJmgnzFjltYeWabWxcWylaBTDTND1ZBMbnmm79Iy0FE2isXRMj6T1Juil3Ykmxz17BDLnvIDegb7sGUSWVld/OlWxHUnkRq+JURfhF/mBB9MhP1QFg2tGH4pF4Gu2URys6Y+kPoD48vSIN4OmlVC/aGog7E3tEsp5AXf3DVYmonGfXu1LalbNPOm+NkUzg9LXmzs0KnnzcMx5LPGxVxoBuDmtONXekNu8lqT/hd4/UEEAXJAgMBAAECggEAERhQRQ2hzh1r8BI8Y9+jQq7wt/eXhTGK1u3FTyNSLw+AjJyubXpN0lwL7czmT3IQhfEgtd/DhEXiwM3TVSLDrkjAGO2B79Ia4PCuBoM4hEE94fEvgtihKBowHI184mDWP16CFbF4ZNZifLWwzCa52EJlNWielpD8v5RiW0eCz8VkMVzAYy03apjFfdtdQN+VxCpizvAdTucsXwx0ETKjNaEOYLPchiWkuaSbpXcXNCFq139T7Atb2vyXYu2gLUWh4OnzFIiVH/7XhSAyBCjvOySG3lmQozZPY5w50608MsHdTezjOJAGt/R70m4W/C4vBD3IFCQaRfciZcKPrj4SMQKBgQDuhrA4yBpVwlx6PHHtsJ6zj6LPtHIxfC1UBSHlN6CHPECM+uicOkxmtz7CZTutrPwH7/nVBy9wOrtIfMYQ2DAATOb5zwj9DJQqCtEmj3mx31BbFC3M+TDYFVywexgW7pKn2Czt5uFhKYHkXtosX+4dEz1zmq3ynoSedXF4xTWQHwKBgQCZcv47auhUkLXmXrRgxSQr91qfqcDs1AaJyaBGyoLmfYgXfhuTwjFz3u3C4k80ax+XSKSUBNaQF7c+IfFgjj1KPIPMcC/VzPpzfAnooM7OtVtCQJpngruWjKfyANcOWgiwP6DFHW3+KPTvggyVz72Yu9UL7cFxlUFXqECmTEGNFwKBgEYo7fq1uTFSizsVPnmowvWUIVKxBAnUOc0F+XNfymMc8OvG/da+OyzXZ6lsd4JdaIC5MKZdh7GeROpMcUIRJQVhmWsPX9h287nuFpkeCLCuPAeeRt6MybY1il8KxgowwIiISKbti4u11tQipk/5I6P/vcqcS+Aaim3Fo8vfTiMHAoGAP7qEX4Ts7IIVKZHXdAEsHp5iIsFLgJAmDv+P8xaT+snTxX34UgtqombJdBCtOGXs/tWKlHbV7Y4L6mAKcUFhrv0m97b4BuFUerXfH5/5g9vKBN/zwF2JLuNHtLgomKtHxHsIeBTa45167CWkuGtIoLARsRyTJGsKci9MekRdTQ0CgYEA6bcW4gkjidQ7OwBlzvYzt5hFgwQxkASwiN8KZ5L9+7L27gC1fY2Fw9WDxXJ5SVwypnE3Zatq2IUCaxmWfkS627AkS+uT5cpPWnZr1pr1AqdMiRE5DnFCB34meLWSJmac6lf5EGt/c04TYnFVF+88i9BhhBZnLlhB/obgFe0RS3Q=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.html 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA+Az3usYrwBXOMaLqE/XWDTO56aLuWeoIU76n/ww3g6avH21ourYKuoX9p3egYrHKxkVqB0DwEVEbREVKg18jD9sUmAeTXGuXekTioH7Az5rhT22t8DuVToD5oeyRhlvuJ/b7+Tsy/7poB8lezaaEPYxkL0B7dflZ09cjs4t8iH8hxMUieV/+srE6uYeXNY9Wkm1Drp4QnQcTtkV+7X1sy4XX3weZr/rc5MP7v3Xq75n1MrGJXs1TNUZCOuxLzj8yhPUdwmHSExOFO8BzOJeb2W6Re1c8FXgTT+RR8gUPwPkRIrgtBysaiDPo3kTILkPIo4wmRhax70M551lzqOvG+wIDAQAB" ;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = natUrl + "/alipay/alipayNotifyNotice";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = natUrl + "/alipay/alipayReturnNotice";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "UTF-8";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";//注意：沙箱测试环境，正式环境为：https://openapi.alipay.com/gateway.do
}