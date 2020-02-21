public class Response {
    private String KZTResponse;
    private String date_insert;

    public Response(String KZTResponse, String date_insert){
        this.KZTResponse = KZTResponse;
        this.date_insert = date_insert;
    }

    public String getKZTResponse() {
        return KZTResponse;
    }

    public void setKZTResponse(String KZTResponse) {
        this.KZTResponse = KZTResponse;
    }

    public String getDate_insert() {
        return date_insert;
    }

    public void setDate_insert(String date_insert) {
        this.date_insert = date_insert;
    }
}
