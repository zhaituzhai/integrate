package com.zhaojm.study;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaojm
 * @date 2020/7/6 20:44
 */
public class DownloadTest {

    @Test
    public void testDownload() {
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("test", "https://fs291.gounlimited.to/tea5vrygd32azxfffmcyznjh4s5txjduh72eigwz5c4zryydqk2n6betbwua/v.mp4");
        downloadMovie("F:/video/", urlMap);
        System.out.println("hello");
    }

    /**
     * 开启多线程下载
     *
     * @param DOWNLOAD_DIR
     * @param urlMap
     */
    private static void downloadMovie(final String DOWNLOAD_DIR, Map<String, String> urlMap) {
        ExecutorService es = Executors.newFixedThreadPool(8);
        for (Map.Entry<String, String> entry : urlMap.entrySet()) {
            final String title = entry.getKey();// 视频名称
            final String url = entry.getValue();// 视频url

            es.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("正在下载:    " + title + ".......");
                        File destFile = new File(DOWNLOAD_DIR + title + ".mp4");

                        download(url, destFile);
                        System.out.println("=========> " + title + " 下载完毕!");

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 通过视频的URL下载该视频并存入本地
     *
     * @param url      视频的URL
     * @param destFile 视频存入的文件夹
     * @throws IOException
     */
    private static void download(String url, File destFile) throws IOException {
        URL videoUrl = new URL(url);

        InputStream is = videoUrl.openStream();
        FileOutputStream fos = new FileOutputStream(destFile);

        int len = 0;
        byte[] buffer = new byte[1024];
        while ((-1) != (len = is.read(buffer))) {
            fos.write(buffer, 0, len);
        }
        fos.flush();

        if (null != fos) {
            fos.close();
        }

        if (null != is) {
            is.close();
        }
    }

    /**
     * 链接url 返回字节流
     *
     * @param url
     * @return
     * @throws IOException
     * @throws ProtocolException
     * @throws UnsupportedEncodingException
     */
    private static BufferedReader connectURL(URL url)
            throws IOException, ProtocolException, UnsupportedEncodingException {
        // 这里的代理服务器端口号 需要自己配置
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7959));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
        // 若遇到反爬机制则使用该方法将程序伪装为浏览器进行访问
        conn.setRequestMethod("GET");
        conn.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        return br;
    }

}
