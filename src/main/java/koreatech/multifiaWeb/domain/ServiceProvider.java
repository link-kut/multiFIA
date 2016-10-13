package koreatech.multifiaWeb.domain;

import koreatech.multifiaWeb.domain.constant.ServiceType;
import lombok.Data;
import org.h2.table.Plan;

@Data
public class ServiceProvider {
    private int userId;
    private String serviceType;
    private String serviceQuality;
    private String serviceCapacity;
    private String plan;
}
