package efw.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import efw.framework;

/**
 * Rest Serviceにアクセスクラス。
 * @author lndljack
 */
public class RestManager {

    /**
     * エンコード形式
     */
    private static final String CHAR_SET = "UTF-8";

    /**
     * データタイプ
     */
    private static final String DATA_TYPE = "application/json;charset=UTF-8";

    /**
     * Restサービスにアクセス。
     * @param strUrl アクセスURL
     * @param strMethod アクセス方法(POST/PUT/DELETE/GET)
     * @param param パラメータ
     * @return Rest Serviceにアクセスの戻り値
     * @throws Exception
     */
    public static String visit(String strUrl, String strMethod, String param) throws Exception {
        HttpURLConnection httpConnection = null;
        BufferedReader responseBuffer = null;
        OutputStream outputStream = null;

        try {
            URL restServiceURL = new URL(strUrl);

            // Restサービスを接続する
            httpConnection = (HttpURLConnection) restServiceURL.openConnection();

            // アクセスタイプを設定する
            httpConnection.setRequestMethod(strMethod.toUpperCase());
            // データタイプを設定する
//            httpConnection.setRequestProperty("Accept", DATA_TYPE);
            // エンコード形式
            httpConnection.setRequestProperty("Content-Type", DATA_TYPE);

            if ("POST".equalsIgnoreCase(strMethod) || "PUT".equalsIgnoreCase(strMethod)) {
                httpConnection.setDoOutput(true);
                // パラメータを設定する
                outputStream = httpConnection.getOutputStream();
                outputStream.write(param.getBytes(CHAR_SET));
                outputStream.flush();
            }

            // Restサービスをアクセスする戻りコードを取得する
            int status = httpConnection.getResponseCode();
            // アクセスする戻りコードを設定する
            framework.setRestStatus(status);

            // レスポンス内容
            responseBuffer = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),CHAR_SET));
            String strLine;
            StringBuffer reserveVal = new StringBuffer();
            while ((strLine = responseBuffer.readLine()) != null) {
                if (strLine != null && !strLine.isEmpty()) {
                    reserveVal.append(strLine);
                }
            }
            return reserveVal.toString();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (responseBuffer != null) {
                responseBuffer.close();
            }
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
    }
    /**
     * RestのResponseコードを戻す
     * @return
     */
    public static Integer getStatus() {
    	return framework.getRestStatus();
    }
}
