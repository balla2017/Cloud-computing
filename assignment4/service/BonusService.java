package com.csye6225.fall2018.courseservice667.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.amazonaws.http.HttpResponse;

public class BonusService 
{
	private static String WEB_URL = "http://Studentinformationadminsystem-env.yjmypmnyth.us-east-2.elasticbeanstalk.com/webapi";
	HttpClient c = HttpClientBuilder.create().build();
	public void callAPI(String apiPath, String request) throws IOException {

		HttpPost p = new HttpPost(WEB_URL + "/" + apiPath);

		StringEntity input = new StringEntity(request);
		p.addHeader("content-type","application/json");
		p.setEntity(input);

		HttpResponse response = (HttpResponse) c.execute(p);

		System.out.println(response);
	}
}
