package ch.rmy.android.http_shortcuts.http;

import java.util.HashMap;
import java.util.Map;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

public class AuthRequest extends StringRequest {

	private final String username;
	private final String password;
	private final String bodyContent;
	private final Map<String, String> parameters;
	private final Map<String, String> headers;

	public AuthRequest(int method, String url, String username, String password, String bodyContent, Listener<String> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);

		this.username = username;
		this.password = password;
		this.bodyContent = bodyContent;

		parameters = new HashMap<String, String>();
		headers = new HashMap<String, String>();
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		return bodyContent.getBytes();
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		headers.putAll(super.getHeaders());

		headers.put("Connection", "close");

		if (!username.isEmpty() || !password.isEmpty()) {
			String creds = String.format("%s:%s", username, password);
			String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
			headers.put("Authorization", auth);
		}
		return headers;
	}

	@Override
	public Map<String, String> getParams() {
		return parameters;
	}

	public void addParameter(String key, String value) {
		parameters.put(key, value);
	}

	public void addHeader(String key, String value) {
		headers.put(key, value);
	}

}
