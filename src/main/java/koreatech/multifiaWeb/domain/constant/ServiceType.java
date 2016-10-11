package koreatech.multifiaWeb.domain.constant;

public enum ServiceType {
    vod("Video on Demand"),
    fileTransfer("File Transfer");

    private String name;
    ServiceType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
