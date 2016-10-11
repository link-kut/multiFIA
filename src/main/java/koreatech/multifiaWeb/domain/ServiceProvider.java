package koreatech.multifiaWeb.domain;

import koreatech.multifiaWeb.domain.constant.ServiceType;
import lombok.Data;

@Data
public class ServiceProvider {
    private int userId;
    private ServiceType serviceType;
}
