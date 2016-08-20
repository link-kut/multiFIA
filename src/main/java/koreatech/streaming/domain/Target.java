package koreatech.streaming.domain;

/**
 * Created by Kyo on 2016. 8. 16..
 */
public class Target {

    private String id;
    private String method;
    private String targetAddress;
    private String targetPort;

    @Override
    public String toString() {
        return "Target{" +
                "id='" + id + '\'' +
                ", method='" + method + '\'' +
                ", targetAddress='" + targetAddress + '\'' +
                ", targetPort='" + targetPort + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(String targetPort) {
        this.targetPort = targetPort;
    }
}
