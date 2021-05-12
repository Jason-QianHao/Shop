package com.qian.config;

public class AliPayConfig {

	// 商户appid
	public static String APPID = "2016102500755532";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtV6hfLM9K/x5tIvRt+56jftgZvnJdVu/sfpKFqrpByOp11Fb6xtCVwwWiPmsKqLUVg0yELLSv59XBcDrfM6+1LTDv57w4QAQC/y5Z8BnE/E0oVDP7YpmptDioluyTk50Y1mqH3CgQlUZFZ4QVeeYEds0EFXuvgOEnSP8/ai9TDcygd/zING81SaBwgKHjb8zzUtbb38hlqis7p8D7qD+CelDqUD7eJm3IMLVWDUZFpABx7q+8WGlnpkCvX3wesdVFCDWVoIKRQuGuFzJlz8kFG7bX4O3TBSFZG3oVPh/r8MOMcBvr/XTaPJGZuv9bt+Q39fSZOwP7y/HFJikpG5p3AgMBAAECggEBAILYjlH5gyg7V35OSm/S5bbIgts79RwcCdNc38FiVGJgBQuYk2eNN9o0ckY8E5TiMH7is5l6HZJC0AnKa8YcP9/lEvGk3WEggWa5tQVSxkD5qkxs8ygpD5EYZ0RrWH01ifo7sAKJ3vxVkcvGDeAgOc7Y1nzkso6SHGMJKRN3Ve28WKnvrcECGPdJyGOSfSZMUKvwncDSkjw6UB/x5xb6DCQZY8zf60qL7hPCX4Yny1TTmFS5QzInns2+o6xprKbXLq/z//IfKmig/XTZWDl8RGgKyCagAt8yNLaECpDcCGAf4gS8hpskGeJECTVUsWNRcJUKoqO7ZFXDy+uugE8WaHECgYEA2fUAyk0nQdcHMgi0Tc/1xni/gjea7ivV6U7hboJsT8DaAYuJuFPaLxpIDW7J09UuUg0hnE3CD63hK4ddkj1dN8fWtlZv0tT9hGBsxMn08/H8JmDwIoAly1elp8/MYffesM4VKnMo3DoLw+JMDiGhh9iQQU3z55Eg/vBcrZRMSjUCgYEAy5kib0gZwzmLkwasBfPR1mnX/f8wf9vEpflkDoNJxLDCNLKZZmRBn3C6t5yKx6UtIlcBzDf6YB1eMv/X+JhF1pfEkSFoh96Sr8qXyGRjYGaeW4oOQInK7+mXBIOx13mXQKQtktNdtCy9dXUfkRFWFaFBqhIQfsjewB7OU5JKh3sCgYBgRcw3j6TLfEN9YkLiK/2P0T4mMpGIqmwuRd1lhs542+Jv1Ebc2I0DSUSSSmFz5DI1o/84n1AcxN008PHv6pMGVIsOt1dldRcWJdFlix9bsYODIEZxttD65JgTa8IHdAk1Z5IJ0hw2Sp1vxMF7bHeBHbN6ZQhbPMuqUFlfgqXAEQKBgQCkY7z+BUVn8CrWR+jV1Yx0h3myyD2svxIEwV0cqr0BZ4cnbZ2WoxSpXCgm4OrdUG0k8QOulPRAbvYET7deLfUV0+0PEd6I9hmSfw4W65eh2rm6R7pCOf/1vrIFHyCVTsPlmaqChs9+DzHaiPs38ruDM3UFkRhohl/01+rYZ6JtyQKBgBfUpD1IVe9UtW8fjVm9uuVSLD8HG7OheYVMeiJSkGFHSU30XiMP7A/Z7LoVeUdqpYWZt357LyBXK7RUqCas/Y+GrfA3WwJ3CtTe/f+YDoipQWXdF81b8GBoOddQCAbx4Nw2CjFUm6vdVcmYggSWId4FzGRVrBhzDspxD5/v/AS8";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://3a40r96515.zicp.vip/Shop/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://3a40r96515.zicp.vip/Shop/return_url.jsp";
	// 请求网关地址
	public static String URL  = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgmChd4nd3pinVpswAM8J9obI30oa0neqSoCDO+6GU6M69xmmWIPBjQhyRxGBZ2kzQyxAZeOabu5FUdSZd7KETFBWACkUOi8f8tcrwN3pEbZ8FZOCW6I3hNx7gKBggbjO/dawQuZWR18hze53cVU8ZUYfuNFV1gLvG/55NTbGUuynObVyqsWJIGHG2ZZDxVaF3FV4uz4ikQ/MmRQVH97caKUZIwD+PmDOthVD0PWnjRd8fkjypgKbbGwDAmBHpyyHHDLE5mZo+A0AkMcVAhnCrPgOZHqbN5KGogYCe9cKa89pJdPBwgOJUAppTMbOJPz3w+7nSMQ/Rbkh6fSnGNpM6wIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
