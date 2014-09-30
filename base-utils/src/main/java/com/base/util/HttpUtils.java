package com.base.util;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpUtils {
	private String cookie = "";
	private String charset = "utf8";

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getCookie() {
		return this.cookie;
	}

	public String get(String url) {
		StringBuilder sb = new StringBuilder();
		try {
			URL _url = new URL(url);

			if (url.toLowerCase().contains("https://")) {
				HttpsHandler httpsH = new HttpsHandler();
				httpsH.trustAllHttpsCertificates();
				HostnameVerifier hv = new HostnameVerifier() {
					public boolean verify(String urlHostName, SSLSession session) {
						return true;
					}
				};
				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				HttpsURLConnection sconn = (HttpsURLConnection) _url
						.openConnection();

				sconn.setReadTimeout(3000);
				sconn.setRequestProperty("Cookie", this.cookie);

				BufferedReader br = new BufferedReader(new InputStreamReader(
						sconn.getInputStream(), this.charset));
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
				br.close();
				String cookie = sconn.getHeaderField("Set-Cookie");
				if (cookie != null) {
					this.cookie = cookie;
				}
			} else {
				HttpURLConnection conn = (HttpURLConnection) _url
						.openConnection();
				conn.setRequestProperty("Cookie", this.cookie);
				conn.setRequestProperty("merchantid",
						"7b3852b4-25ff-48eb-8657-656af7bc0573");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), this.charset));
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
				br.close();
				String cookie = conn.getHeaderField("Set-Cookie");
				if (cookie != null) {
					this.cookie = cookie;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String post(String url, String data) {
		StringBuilder sb = new StringBuilder();
		try {
			URL _url = new URL(url);
			if (url.toLowerCase().contains("https://")) {
				HttpsHandler httpsH = new HttpsHandler();
				httpsH.trustAllHttpsCertificates();
				HostnameVerifier hv = new HostnameVerifier() {
					public boolean verify(String urlHostName, SSLSession session) {
						return true;
					}
				};
				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				HttpsURLConnection sconn = (HttpsURLConnection) _url
						.openConnection();
				sconn.setDoInput(true);
				sconn.setDoOutput(true);
				sconn.setRequestProperty("Cookie", this.cookie);

				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						sconn.getOutputStream(), this.charset));
				bw.write(data);
				bw.flush();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						sconn.getInputStream(), this.charset));
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
					sb.append("\n");
				}
				br.close();
				String cookie = sconn.getHeaderField("Set-Cookie");
				if (cookie != null) {
					this.cookie = cookie;
				}
			} else {
				HttpURLConnection conn = (HttpURLConnection) _url
						.openConnection();
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Cookie", this.cookie);
				conn.setRequestProperty("merchantid",
						"7b3852b4-25ff-48eb-8657-656af7bc0573");
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						conn.getOutputStream(), this.charset));
				bw.write(data);
				bw.flush();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), this.charset));
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
					sb.append("\n");
				}
				br.close();
				bw.close();
				String cookie = conn.getHeaderField("Set-Cookie");
				if (cookie != null) {
					this.cookie = cookie;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

    
    public static HttpClient wrapClient(HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = base.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));
            return new DefaultHttpClient(ccm, base.getParams());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public byte[] file(String url) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			URL _url = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
			conn.setRequestProperty("Cookie", this.cookie);
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			byte[] b = new byte[1024];
			int len = -1;
			while ((len = bis.read(b)) != -1) {
				bos.write(b, 0, len);
				bos.flush();
			}
			bos.close();
			bis.close();
			String cookie = conn.getHeaderField("Set-Cookie");
			if (cookie != null) {
				this.cookie = cookie;
			}
		} catch (Exception e) {
			return null;
		}
		return bos.toByteArray();
	}

	private class HttpsHandler {
		private HttpsHandler() {
		}

		public void trustAllHttpsCertificates() throws Exception {
			TrustManager[] tm_array = new TrustManager[1];
			TrustManager tm = new MyTrustManager();
			tm_array[0] = tm;
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, tm_array, null);
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}

		private class MyTrustManager implements TrustManager, X509TrustManager {
			private MyTrustManager() {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public boolean isServerTrusted(X509Certificate[] certs) {
				return true;
			}

			public boolean isClientTrusted(X509Certificate[] certs) {
				return true;
			}

			public void checkServerTrusted(X509Certificate[] certs,
					String authType) throws CertificateException {
			}

			public void checkClientTrusted(X509Certificate[] certs,
					String authType) throws CertificateException {
			}
		}
	}
}
