# post-multipart-form-data-with-json-resttemplate
Using Spring Boot RestTemplate to post a form with file and json data to an endpoint.

Normally if you only set the Content-Type of the post request to MULTIPART_FORM_DATA is not enough. 
Instead you have to set the HttpHeaders content type of each element in the post data.
