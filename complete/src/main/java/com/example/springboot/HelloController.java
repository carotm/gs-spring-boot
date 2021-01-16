package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		// The ID of your GCP project
		String projectId = "emerald-metrics-274919";

		// The ID of your GCS bucket
		String bucketName = "emerald-metrics-274919";
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		Bucket bucket = storage.get(bucketName);
		Page<Blob> blobs = bucket.list();

		for (Blob blob : blobs.iterateAll()) {
			System.out.println(blob.getName());
		}
		return "Greetings from Spring Boot!";
	}

}
