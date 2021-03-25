package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
    RequestSpecification requestSpecification;
    private String baseUrl = "https://api.spotify.com/v1";
    private String token = "BQC2IUJEMdXaWt23_uigbRmiI7vFBLTMKUJmuVqTNjYDxCA4KItHyYSjcwI6c6u0tTsTFfExQH-781pclIABmIUsB9UZKnRX4_ZpO2kJk5sfJ0qWAO3f3ELe6IVfsDLo0DHRLiWUHrAmlQddLqO2MobNRJPxcR0da_0MghwRdQeZKbVzZYpB63vOe_XdHMkGPhv_lfad-GYJH6N5s_iK301vZINLf4UiwOSAgVoQC7T4ITRM29XGdLPUytBC1A5_F5J_78rPwMVenjgeSm2S73tacsCBaYwuw8B4S81Q7TcI";
    public RequestSpec(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("Authorization","Bearer " +token)
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }
    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }


}
