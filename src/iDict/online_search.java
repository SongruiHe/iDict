package iDict;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class online_search {
	private static final String UTF8 = "utf-8";

	// �����߿�����id
	private static final String appId = "********************";
	// ����ɹ����֤��token
	private static final String token = "*********************";
	// api��ַ
	private static final String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";
	// ���������������md5ֵ
	private static final Random random = new Random();

	// ��Unicodeת��Ϊ����
	public String convert(String utfString) {
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while ((i = utfString.indexOf("\\u", pos)) != -1) {
			sb.append(utfString.substring(pos, i));
			if (i + 5 < utfString.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
			}
		}
		return sb.toString();
	}

	// �ж�һ���ַ����Ƿ�������
	public static boolean isChinese(char c) {
		return c >= 0x4E00 && c <= 0x9FA5;// �����ֽ����ж�
	}
	// �ж�һ���ַ����Ƿ�������
	public static boolean isChinese(String str) {
		if (str == null)
			return false;
		for (char c : str.toCharArray()) {
			if (isChinese(c))
				return true;// ��һ�������ַ��ͷ���
		}
		return false;
	}

	// Ӣ����ѯ
	public String translate_ez(String q, String from, String to) throws Exception {
		// ����md5����
		int salt = random.nextInt(10000);

		// ��appId+Դ��+�����+token����md5ֵ
		StringBuilder md5String = new StringBuilder();
		md5String.append(appId).append(q).append(salt).append(token);
		String md5 = DigestUtils.md5Hex(md5String.toString());

		// ʹ��Post��ʽ����װ����
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("q", q));
		nvps.add(new BasicNameValuePair("from", from));
		nvps.add(new BasicNameValuePair("to", to));
		nvps.add(new BasicNameValuePair("appid", appId));
		nvps.add(new BasicNameValuePair("salt", String.valueOf(salt)));
		nvps.add(new BasicNameValuePair("sign", md5));
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		// ����httpclient���ӣ���ִ��
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpost);

		// ���ڷ���ʵ����н���
		HttpEntity entity = response.getEntity();
		InputStream returnStream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(returnStream, UTF8));
		StringBuilder result = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str).append("\n");
		}

		System.out.println(result);

		// ��ȡ���ط�����
		String regex = "dst\"(.*)\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(result);
		if (matcher.find())
			System.out.println(matcher.group());

		String text = convert(matcher.group());
		text = text.replaceAll("dst\":\"", "");
		if (isChinese(text) == false)
			return "û�в�ѯ��������ʣ�";
		return text;
	}

	// ��Ӣ��ѯ
	public String translate_ze(String q, String from, String to) throws Exception {
		// ����md5����
		int salt = random.nextInt(10000);

		// ��appId+Դ��+�����+token����md5ֵ
		StringBuilder md5String = new StringBuilder();
		md5String.append(appId).append(q).append(salt).append(token);
		String md5 = DigestUtils.md5Hex(md5String.toString());

		// ʹ��Post��ʽ����װ����
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("q", q));
		nvps.add(new BasicNameValuePair("from", from));
		nvps.add(new BasicNameValuePair("to", to));
		nvps.add(new BasicNameValuePair("appid", appId));
		nvps.add(new BasicNameValuePair("salt", String.valueOf(salt)));
		nvps.add(new BasicNameValuePair("sign", md5));
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		// ����httpclient���ӣ���ִ��
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpost);

		// ���ڷ���ʵ����н���
		HttpEntity entity = response.getEntity();
		InputStream returnStream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(returnStream, UTF8));
		StringBuilder result = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str).append("\n");
		}

		System.out.println(result);

		// ��ȡ���ط�����
		String regex = "dst\"(.*)\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(result);
		if (matcher.find())
			System.out.println(matcher.group());

		String text = matcher.group();
		text = text.replaceAll("dst\":\"", "");
		text = text.replaceAll("\"", "");
		System.out.println(text);
		if (text == null)
			return "û�в�ѯ��������ʣ�";
		return text;
	}
}
