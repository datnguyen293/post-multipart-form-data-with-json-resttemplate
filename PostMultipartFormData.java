public class PostMultipartFormData {

    // You are responsible to set the endpoint to upload
    private String resourceUploadEndpoint;

    // APIResponse is your predefined class somewhere
    public APIResponse postFileWithJson(byte[] file, ObjectNode jsonNode) {
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            // Create HttpEntity for JSON data
            final HttpHeaders jsonHeaders = new HttpHeaders();
            jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
            body.add("resourceInfo", new HttpEntity<>(jsonNode, jsonHeaders));

            // Create HttpEntity for multipart file
            final HttpHeaders fileHeaders = new HttpHeaders();
            fileHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

            ContentDisposition contentDisposition = ContentDisposition
                    .builder("form-data")
                    .name("file")
                    .filename("fileName.ext")
                    .build();
            fileHeaders.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
            body.add("file", new HttpEntity<>(request.getResourceFile(), fileHeaders));

            // Set upload header to multipart/form-data
            final HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);
            headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);

            // Build request entity
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Send request
            return restTemplate.postForObject(resourceUploadEndpoint, requestEntity, APIResponse.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
